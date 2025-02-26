package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.dto.suspend.BillingDate;
import by.com.lifetech.billingapi.models.dto.suspend.SuspendLineSnapshot;
import by.com.lifetech.billingapi.models.entity.SuspendLineInfoEntity;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.repository.SuspendLineInfoRepository;
import by.com.lifetech.billingapi.models.requests.DefaultChainServiceRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.DateUtils;
import by.com.lifetech.billingapi.utils.compressor.Compressor;
import by.com.lifetech.billingapi.utils.compressor.DeflaterCompressionStrategy;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class SuspendService {
    Logger logger = LoggerFactory.getLogger(SuspendService.class);

    private final int OLD_SUSPEND_YEAR_OFFSET = 10;
    private final String DEFAULT_CHARSET = "UTF-8";
    private final String INCORRECT_RECORDS_COUNT_ERROR = "Incorrect records count of suspend snapshot";
    private final String GET_SNAPSHOT_ERROR = "Can not get snapshot";
    private final String SAVE_SNAPSHOT_ERROR = "Can not save snapshot";
    private final String ACTIVE_SUSPEND_ERROR = "Line already have active suspend snapshot";
    private final String UNSUSPEND_ERROR = "Error when deactivating suspension";
    private final ObjectMapper objectMapper;
    private final ChainService chainService;
    private final SuspendLineInfoRepository suspendLineInfoRepository;

    public ServiceResponseDto<Map<String, Object>> updateSuspendSnapshot(String msisdn, SuspendLineSnapshot snapshot) throws BusinessException, InternalException {
        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<Map<String, Object>>();

        List<SuspendLineInfoEntity> suspendLineInfoList = suspendLineInfoRepository.findByMsisdn(msisdn);
        if (suspendLineInfoList.size() != 1) {
            throw new BusinessException(BusinessErrorCode.INCORRECT_RECORDS_COUNT.name(), INCORRECT_RECORDS_COUNT_ERROR);
        }
        SuspendLineInfoEntity suspendLineInfo = suspendLineInfoList.get(0);

        try {
            byte[] compressedData = compressString(objectMapper.writeValueAsString(snapshot));
            suspendLineInfo.setSnapshot(compressedData);
            suspendLineInfoRepository.save(suspendLineInfo);
        } catch (IOException e) {
            logger.error("{}. Error: {}", GET_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(GET_SNAPSHOT_ERROR);
        } catch (Exception e) {
            logger.error("{}. Error: {}", SAVE_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(SAVE_SNAPSHOT_ERROR);
        }

        return serviceResponse.setDefaultSuccessResponse();
    }

    public ServiceResponseDto<SuspendLineSnapshot> getSuspendSnapshot(String msisdn) throws BusinessException, InternalException {
        ServiceResponseDto<SuspendLineSnapshot> serviceResponse = new ServiceResponseDto<>();

        List<SuspendLineInfoEntity> suspendLineInfoList = suspendLineInfoRepository.findByMsisdn(msisdn);
        if (suspendLineInfoList.size() != 1) {
            throw new BusinessException(BusinessErrorCode.INCORRECT_RECORDS_COUNT.name(), INCORRECT_RECORDS_COUNT_ERROR);
        }
        SuspendLineInfoEntity suspendLineInfo = suspendLineInfoList.get(0);

        Compressor compressor = new Compressor(new DeflaterCompressionStrategy());
        SuspendLineSnapshot suspendLineSnapshot;
        String jsonString;
        try {
            jsonString = compressor.decompressBytesToString(suspendLineInfo.getSnapshot(), DEFAULT_CHARSET);
            suspendLineSnapshot = objectMapper.readValue(jsonString, SuspendLineSnapshot.class);
        } catch (Exception e) {
            logger.error("{}. Error: {}", GET_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(GET_SNAPSHOT_ERROR);
        }

        serviceResponse.setResultMap(suspendLineSnapshot);
        return serviceResponse.setDefaultSuccessResponse();
    }

    public ServiceResponseDto<Map<String, Object>> suspendWithFreezeLineLogic(DefaultChainServiceRequest request) throws BusinessException, InternalException {
        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<Map<String, Object>>();

        List<SuspendLineInfoEntity> suspendLineInfoList = suspendLineInfoRepository.findByMsisdn(request.getMsisdn());
        if (suspendLineInfoList.size() != 0) {
            throw new BusinessException(BusinessErrorCode.INCORRECT_RECORDS_COUNT.name(), ACTIVE_SUSPEND_ERROR);
        }

        String jsonString;
        try {
            Map<String, Object> map = ChainResultToServiceResponseConverter.objectToMap(request);
            ChainResult chainResult = chainService.executeChain(ChainType.OM, "testSuspLogic", map);
            jsonString = ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "suspendLineSnapshot").toString();

            byte[] compressedData = compressString(jsonString);

            SuspendLineInfoEntity suspendLineInfo = new SuspendLineInfoEntity();
            suspendLineInfo.setMsisdn(request.getMsisdn())
                    .setSnapshot(compressedData)
                    .setEntryDate(LocalDateTime.now());
            suspendLineInfoRepository.save(suspendLineInfo);
        } catch (IllegalAccessException | NoSuchElementException | IOException e) {
            logger.error("{}. Error: {}", GET_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(GET_SNAPSHOT_ERROR);
        } catch (Exception e) {
            logger.error("{}. Error: {}", SAVE_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(SAVE_SNAPSHOT_ERROR);
        }

        return serviceResponse.setDefaultSuccessResponse();
    }

    public ServiceResponseDto<Map<String, Object>> unsuspendWithFreezeLineLogic(DefaultChainServiceRequest request) throws BusinessException, InternalException {
        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<Map<String, Object>>();

        List<SuspendLineInfoEntity> suspendLineInfoList = suspendLineInfoRepository.findByMsisdn(request.getMsisdn());
        if (suspendLineInfoList.size() != 1) {
            throw new BusinessException(BusinessErrorCode.INCORRECT_RECORDS_COUNT.name(), INCORRECT_RECORDS_COUNT_ERROR);
        }
        SuspendLineInfoEntity suspendLineInfo = suspendLineInfoList.get(0);

        Compressor compressor = new Compressor(new DeflaterCompressionStrategy());
        String jsonString;
        try {
            jsonString = compressor.decompressBytesToString(suspendLineInfo.getSnapshot(), DEFAULT_CHARSET);

            SuspendLineSnapshot suspendLineSnapshot = objectMapper.readValue(jsonString, SuspendLineSnapshot.class);
            Duration duration = Duration.between(suspendLineInfo.getEntryDate(), LocalDateTime.now());
            shiftTimeBySuspendDuration(suspendLineSnapshot, duration);
            jsonString = objectMapper.writeValueAsString(suspendLineSnapshot);
        } catch (Exception e) {
            logger.error("{}. Error: {}", GET_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(GET_SNAPSHOT_ERROR);
        }

        try {
            Map<String, Object> map = ChainResultToServiceResponseConverter.objectToMap(request);
            map.put("suspendSnapshot", jsonString);
            chainService.executeChain(ChainType.OM, "testUnsuspLogic", map);
            suspendLineInfoRepository.delete(suspendLineInfo);
        } catch(BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("{}. Error: {}", UNSUSPEND_ERROR, e.getMessage());
            throw new InternalException(UNSUSPEND_ERROR);
        }

        return serviceResponse.setDefaultSuccessResponse();
    }

    public ServiceResponseDto<Map<String, Object>> suspendOldToNewLogic(String msisdn) throws BusinessException, InternalException {
        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<Map<String, Object>>();

        List<SuspendLineInfoEntity> suspendLineInfoList = suspendLineInfoRepository.findByMsisdn(msisdn);
        if (suspendLineInfoList.size() != 0) {
            throw new BusinessException(BusinessErrorCode.INCORRECT_RECORDS_COUNT.name(), ACTIVE_SUSPEND_ERROR);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        SuspendLineSnapshot suspendLineSnapshot;
        String jsonString;
        String suspendStartDate;
        try {
            ChainResult chainResult = chainService.executeChain(ChainType.OM, "testSuspOldToNew", map);

            Map<String,Object> resultMap = ChainResultToServiceResponseConverter.convertResultListToMap(chainResult);
            jsonString = resultMap.get("suspendLineSnapshot").toString();
            suspendStartDate = resultMap.get("suspendStartDate").toString();
            suspendLineSnapshot = objectMapper.readValue(jsonString, SuspendLineSnapshot.class);

            shiftYears(suspendLineSnapshot, -OLD_SUSPEND_YEAR_OFFSET);
            jsonString = objectMapper.writeValueAsString(suspendLineSnapshot);
        } catch(BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("{}. Error: {}", GET_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(GET_SNAPSHOT_ERROR);
        }

        Compressor compressor = new Compressor(new DeflaterCompressionStrategy());
        try {
            byte[] compressedData = compressor.compressString(jsonString, DEFAULT_CHARSET);

            SuspendLineInfoEntity suspendLineInfo = new SuspendLineInfoEntity();
            suspendLineInfo.setMsisdn(msisdn)
                    .setSnapshot(compressedData)
                    .setEntryDate(BillingDate.billingStringToLocalDateTimeWithoutMillis(suspendStartDate));
            suspendLineInfoRepository.save(suspendLineInfo);
        } catch (Exception e) {
            logger.error("{}. Error: {}", SAVE_SNAPSHOT_ERROR, e.getMessage());
            throw new InternalException(SAVE_SNAPSHOT_ERROR);
        }

        return serviceResponse.setDefaultSuccessResponse();
    }

    private byte[] compressString(String inputString) throws IOException {
        Compressor compressor = new Compressor(new DeflaterCompressionStrategy());
        return compressor.compressString(inputString, DEFAULT_CHARSET);
    }

    private void shiftTimeBySuspendDuration(SuspendLineSnapshot suspendLineSnapshot, Duration duration) {
        if (duration == null || duration.isZero() || duration.isNegative()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        shiftTime(suspendLineSnapshot, duration, now);
    }

    private void shiftTime(SuspendLineSnapshot suspendLineSnapshot, Duration duration, LocalDateTime specBalanceStartDate) {
        if (duration == null || duration.isZero()) {
            return;
        }
        suspendLineSnapshot.getBalanceList().forEach(balance -> balance.getPocketList().forEach(pocket -> changeBillingDateByDuration(pocket.getDate(), duration, specBalanceStartDate)));
        suspendLineSnapshot.getAccountLCList().forEach(lc -> changeBillingDateByDuration(lc.getDate(), duration));
        suspendLineSnapshot.getProductList().forEach(product -> product.getComponentList().forEach(component
                        -> {
                    component.setNextTriggerDate(changeBillingDateStringByDuration(component.getNextTriggerDate(), duration));
                    component.getScheduleList().forEach(lc -> changeBillingDateByDuration(lc.getDate(), duration));
                }
        ));
    }

    private void changeBillingDateByDuration(BillingDate date, Duration duration) {
        changeBillingDateByDuration(date, duration, null);
    }

    private void changeBillingDateByDuration(BillingDate date, Duration duration, LocalDateTime specStartDate) {
        String startDate = date.getStartDate();
        String endDate = date.getEndDate();
        if (startDate != null) {
            startDate = specStartDate != null ? BillingDate.dateToBillingStringWithoutMillis(specStartDate) : changeBillingDateStringByDuration(startDate, duration);
            date.setStartDate(startDate);
        }
        if (endDate != null) {
            date.setEndDate(changeBillingDateStringByDuration(endDate, duration));
        }
    }

    private String changeBillingDateStringByDuration(String date, Duration duration) {
        if (date == null || duration == null || duration.isZero()) {
            return date;
        }
        LocalDateTime localDateTime = DateUtils.offsetByDuration(BillingDate.billingStringToLocalDateTimeWithoutMillis(date), duration);
        return BillingDate.dateToBillingStringWithoutMillis(localDateTime);
    }

    private void shiftYears(SuspendLineSnapshot suspendLineSnapshot, long years) {
        if (years == 0) {
            return;
        }
        suspendLineSnapshot.getBalanceList().forEach(balance -> balance.getPocketList().forEach(pocket -> offsetYearBillingDate(pocket.getDate(), years)));
        suspendLineSnapshot.getAccountLCList().forEach(lc -> offsetYearBillingDate(lc.getDate(), years));
        suspendLineSnapshot.getProductList().forEach(product -> product.getComponentList().forEach(component
                        -> {
                    component.setNextTriggerDate(offsetYearBillingDateString(component.getNextTriggerDate(), years));
                    component.getScheduleList().forEach(lc -> offsetYearBillingDate(lc.getDate(), years));
                }
        ));
    }

    private void offsetYearBillingDate(BillingDate date, long years) {
        String startDate = date.getStartDate();
        String endDate = date.getEndDate();
        if (startDate != null) {
            date.setStartDate(offsetYearBillingDateString(startDate, years));
        }
        if (endDate != null) {
            date.setEndDate(offsetYearBillingDateString(endDate, years));
        }
    }

    private String offsetYearBillingDateString(String date, long years) {
        if (date == null || years == 0) {
            return date;
        }
        return BillingDate.dateToBillingStringWithoutMillis(DateUtils.offsetYears(BillingDate.billingStringToLocalDateTimeWithoutMillis(date), years));
    }
}
