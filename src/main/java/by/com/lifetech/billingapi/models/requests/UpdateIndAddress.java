package by.com.lifetech.billingapi.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndAddress {
    @Schema(description = "Contract No", example = "300169379")
    @NotBlank
    @JsonProperty("agreementCode")
    private String AgreementCode;
    @Schema(description = "Login of user", example = "Test.Test@life.com.by")
    @NotBlank
    private String agent;
    @Schema(description = "Post index", example = "231300")
    @JsonProperty("adrIndex")
    private String AdrIndex;
    @Schema(description = "email", example = "user123@gmail.com")
    @JsonProperty("adrEmail")
    private String AdrEmail;
    @Schema(description = "Region code", example = "GRODNO")
    @JsonProperty("adrRegionCode")
    private String AdrRegionCode;
    @Schema(description = "City code", example = "15148")
    @JsonProperty("adrCityCode")
    private String AdrCityCode;
    @Schema(description = "Street name", example = "Набережная")
    @JsonProperty("adrStreet")
    private String AdrStreet;
    @Schema(description = "Building number", example = "7")
    @JsonProperty("adrBuildingNo")
    private String AdrBuildingNo;
    @Schema(description = "entrance of the house", example = "1")
    @JsonProperty("adrHousing")
    private String AdrHousing;
    @Schema(description = "apartment number", example = "19")
    @JsonProperty("adrApartment")
    private String AdrApartment;
    @Schema(description = "District code", example = "GR_LIDA")
    @JsonProperty("adrDistrictCode")
    private String AdrDistrictCode;
    @Schema(description = "Address type code", example = "USUAL")
    @JsonProperty("adrTypeCode")
    private String AdrTypeCode;
    @Schema(description = "Y - if address is custom or NULL", example = " ")
    @JsonProperty("adrIsCustom")
    private String AdrIsCustom;
    @Schema(description = "Country code", example = "BY")
    @JsonProperty("adrCountryCode")
    private String AdrCountryCode;
    @Schema(description = "City type code", example = "CITY")
    @JsonProperty("adrCityTypeCode")
    private String AdrCityTypeCode;
    @Schema(description = "Street type code", example = "1")
    @JsonProperty("adrStreetTypeCode")
    private String AdrStreetTypeCode;
    @Schema(description = "When AdrIsCustom == Y, custom address string", example = "region=Лиссабонская;district=Спортинговский;city=Бенфика;")
    @JsonProperty("adrCustomLine")
    private String AdrCustomLine;
    @Schema(description = "Address comments", example = "")
    @JsonProperty("adrComments")
    private String AdrComments;
    @Schema(description = "Address comments", example = "231300, Беларусь, Гродненская обл., Лидский район, город Лида, ул. Набережная, д.10, кв.7")
    @JsonProperty("adrPrintLine")
    private String AdrPrintLine;
    @Schema(description = "Address phone", example = "375256257415")
    @JsonProperty("adrPhone")
    private String AdrPhone;
    @Schema(description = "Landline Phone", example = "375173566900")
    @JsonProperty("adrLandlinePhone")
    private String AdrLandlinePhone;
}
