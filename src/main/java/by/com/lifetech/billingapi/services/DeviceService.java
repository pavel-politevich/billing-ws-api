package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.models.dto.OmPoInstanceValuesInfo;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.AssetType;
import by.com.lifetech.billingapi.models.repository.AssetTypeRepository;
import by.com.lifetech.billingapi.models.repository.OmPoInstanceValuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final OmPoInstanceValuesRepository poInstanceValuesRepository;
    private final AssetTypeRepository assetTypeRepository;


    public ServiceResponseDto<List<OmPoInstanceValuesInfo>> getPoInstanceAttributes(String tariff, String productCode, String deviceCode){
        ServiceResponseDto<List<OmPoInstanceValuesInfo>> response = new ServiceResponseDto<>();
        response.setDefaultSuccessResponse().setResultMap(
                poInstanceValuesRepository.findByDeviceCodeAndProductCodeAndTariffCode(deviceCode, productCode, tariff)
                        .stream()
                        .filter(po -> po.getValue() != null)
                        .filter(po -> !po.getValue().equals("undefined"))
                        .toList()
        );
        return response;
    }

    public ServiceResponseDto<List<AssetType>> getOtpDevices(){
        ServiceResponseDto<List<AssetType>> response = new ServiceResponseDto<>();
        response.setDefaultSuccessResponse().setResultMap(
                assetTypeRepository.findOpenedOtpDevices()
        );
        return response;
    }
}
