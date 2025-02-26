package by.com.lifetech.billingapi.services;

import java.util.*;

import by.com.lifetech.billingapi.models.dto.ChargingEventGroupDto;
import by.com.lifetech.billingapi.models.entity.dictionary.DictChargingEventGroup;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.ChargeDetailsRequest;
import by.com.lifetech.billingapi.models.requests.ChargesRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.ChargeDetails;
import by.com.lifetech.billingapi.models.entity.ChargeSummary;
import by.com.lifetech.billingapi.models.entity.Charges;
import by.com.lifetech.billingapi.models.enums.ChargeSearchType;
import by.com.lifetech.billingapi.models.repository.ChargeDetailsRepository;
import by.com.lifetech.billingapi.models.repository.ChargeSummaryRepository;
import by.com.lifetech.billingapi.models.repository.ChargesRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.ChargingEventGroupRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.DictCrmBalanceRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.DictMeasureUnitRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriberChargesService {

    private final ChargesRepository chargesRepository;
    private final ChargeSummaryRepository chargesSummaryRepository;
    private final ChargeDetailsRepository chargeDetailsRepository;
    private final ChargingEventGroupRepository chargingEventGroupRepository;
    private final DictCrmBalanceRepository dictCrmBalanceRepository;
    private final DictMeasureUnitRepository measureUnitRepository;

    public ServiceResponseDto<Page<Charges>> getCharges(ChargesRequest req, Pageable pageable) {

        ServiceResponseDto<Page<Charges>> serviceResponse = new ServiceResponseDto<>();
        String msisdn = "";

        Page<Charges> resultList = chargesRepository.getCharges(req.getStartDate(), req.getEndDate(), msisdn, req.getAccountId(), req.getSearchType().name(),
                (req.getIncludeAllLines() ? "Y" : "N"), (req.getOnlyPaidEvents() ? "Y" : "N"), req.getEventGroupCode()
                , PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort()));
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(resultList);
        return serviceResponse;
    }

    public ServiceResponseDto<List<ChargeSummary>> getChargeSummary(ChargesRequest req) {

        ServiceResponseDto<List<ChargeSummary>> serviceResponse = new ServiceResponseDto<>();
        String msisdn = "";

        List<ChargeSummary> resultList = chargesSummaryRepository.getChargeSummary(req.getStartDate(), req.getEndDate(), msisdn,
                req.getAccountId(), req.getSearchType().name(), (req.getIncludeAllLines() ? "Y" : "N"), (req.getOnlyPaidEvents() ? "Y" : "N"), req.getEventGroupCode());
        resultList.forEach(ch -> chargingEventGroupRepository.findByCode(ch.getEventGroupCode()).ifPresent(d -> ch.setEventGroupName(d.getNameByLang(req.getLang()))));
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(resultList);
        return serviceResponse;
    }

    public ServiceResponseDto<List<ChargeDetails>> getChargeDetails(ChargeDetailsRequest req) {

        ServiceResponseDto<List<ChargeDetails>> serviceResponse = new ServiceResponseDto<>();
        String msisdn = "";

        List<Charges> chargeList = chargesRepository.getCharges(req.getStartTime(), req.getStartTime(), msisdn, req.getAccountId(), ChargeSearchType.LINE.name(),
                "N", "N", null, Pageable.unpaged()).getContent().stream()
                .filter(charges -> charges.getTid().equals(req.getTid())).toList();
        List<ChargeDetails> resultList = chargeDetailsRepository.getChargeDetails(req.getStartTime(), msisdn, req.getAccountId(), req.getTid(), "");

        chargeList.forEach(ch -> {
            if (ch.getLineMain() > 0) {
                ChargeDetails cd = new ChargeDetails();
                cd.setBalanceCode("Line_Main");
                cd.setBalanceAmount(ch.getLineMain());
                cd.setBalanceBefore(ch.getLineMainBefore());
                cd.setBalanceAfter(ch.getLineMainAfter());
                cd.setMeasureUnitCode("BYR");
                cd.setServicePath(ch.getServicePath());
                resultList.add(cd);
            }
            if (ch.getLineBonus() > 0) {
                ChargeDetails cd = new ChargeDetails();
                cd.setBalanceCode("Line_Bonus");
                cd.setBalanceAmount(ch.getLineBonus());
                cd.setBalanceBefore(ch.getLineBonusBefore());
                cd.setBalanceAfter(ch.getLineBonusAfter());
                cd.setMeasureUnitCode("BYR");
                cd.setServicePath(ch.getServicePath());
                resultList.add(cd);
            }
            if (ch.getLineDebt() > 0) {
                ChargeDetails cd = new ChargeDetails();
                cd.setBalanceCode("Line_Debt");
                cd.setBalanceAmount(ch.getLineDebt());
                cd.setBalanceBefore(ch.getLineDebtBefore());
                cd.setBalanceAfter(ch.getLineDebtAfter());
                cd.setMeasureUnitCode("BYR");
                cd.setServicePath(ch.getServicePath());
                resultList.add(cd);
            }
            if (ch.getCommonMain() > 0) {
                ChargeDetails cd = new ChargeDetails();
                cd.setBalanceCode("Common_Main");
                cd.setBalanceAmount(ch.getCommonMain());
                cd.setBalanceBefore(ch.getCommonMainBefore());
                cd.setBalanceAfter(ch.getCommonMainAfter());
                cd.setMeasureUnitCode("BYR");
                cd.setServicePath(ch.getServicePath());
                resultList.add(cd);
            }
        });

        resultList.forEach(ch -> {
            dictCrmBalanceRepository.findByCode(ch.getBalanceCode()).ifPresent(d -> ch.setBalanceName(d.getNameByLang(req.getLang())));
            measureUnitRepository.findByCode(ch.getMeasureUnitCode()).ifPresent(d -> ch.setMeasureUnitName(d.getNameByLang(req.getLang())));
        });
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(resultList);
        return serviceResponse;
    }

    @Cacheable(value = "chargeEventDictionary")
    public List<ChargingEventGroupDto> getDictEventGroup(Lang lang) {
        List<DictChargingEventGroup> list = chargingEventGroupRepository.findAll().stream().filter(d -> d.getParentCode() == null).toList();
        List<ChargingEventGroupDto> resultList = new ArrayList<>();
        list.forEach( c -> {
            ChargingEventGroupDto gr = new ChargingEventGroupDto(c, lang);
            gr.getChildren().addAll(getChildrenList(gr, lang));
            gr.getChildren().forEach(
                    ch -> {
                        ch.getChildren().addAll(getChildrenList(ch, lang));
                        ch.getChildren().forEach(ch2 ->
                                ch2.getChildren().addAll(getChildrenList(ch2, lang)));
                    }
            );
            resultList.add(gr);
        });
        return resultList;
    }

    private List<ChargingEventGroupDto> getChildrenList(ChargingEventGroupDto object, Lang lang) {
        List<ChargingEventGroupDto> resultList = new ArrayList<>();
        chargingEventGroupRepository.findByParentCode(object.getId()).forEach(chld ->
                resultList.add(new ChargingEventGroupDto(chld, lang))
        );
        return resultList;
    }
}
