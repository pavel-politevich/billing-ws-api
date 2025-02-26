package by.com.lifetech.billingapi.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationSimRequest {
    @Schema(description = "First name", example = "Иван")
    private String givenNames;
    @Schema(description = "Patronymic ", example = "Иванов")
    private String middleNames;
    @Schema(description = "Second name", example = "Иванович")
    private String familyNames;
    @Schema(description = "Issued by", example = "ПЕРВОМАМСКОЕ РУВД Г.МИНСКА")
    private String passportAuthority;
    @Schema(description = "Passport series", example = "MP")
    private String passportSeries;
    @Schema(description = "Passport number", example = "784939")
    private String passportNumber;
    @Schema(description = "Document type", example = "PASSPORT_RB")
    private String passportType;
    @Schema(description = "Passport identification code", example = "3090598A023PB5")
    private String identificationCode;
    @Schema(description = "Nationality", example = "BY")
    private String nationality;
    @Schema(description = "Document validity period", example = "2029-05-23T03:00:00.000+03:00")
    private String passportValidTo;
    @Schema(description = "email", example = "test@test.com")
    private String email;
    @Schema(description = "Postcode", example = "220099")
    private String index;
    @Schema(description = "District", example = "GR_ZELVEN")
    private String district;
    @Schema(description = "Region", example = "GRODNO")
    private String region;
    @Schema(description = "Settlement", example = "12113")
    private String settlement;
    @Schema(description = "Street", example = "Тестовая")
    private String street;
    @Schema(description = "House", example = "1")
    private String house;
    @Schema(description = "Housing", example = "")
    private String housing;
    @Schema(description = "Apartment", example = "1")
    private String apartment;
    @Schema(description = "Additional phone", example = "")
    private String additionalPhone;
    @Schema(description = "Additional phone operator", example = "")
    private String additionalPhoneOperator;
    @NotBlank
    @Schema(description = "SIM card number", example = "893750310158619094")
    private String iccid;
    private String iccid2;
    private String iccid3;
    @Schema(description = "imei", example = "")
    private String imei;
    @Schema(description = "Terminal Id", example = "")
    private String deviceType;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Place of registration", example = "")
    private String placeOfRegistration;
    @Schema(description = "Agent code", example = "62962618")
    private String representativeOfOperator;
    @Schema(description = "Number of the Dealer Letter of Attorney", example = "")
    private String procuratoryNumber;
    @Schema(description = "Issue date of the Dealer Letter of Attorney", example = "")
    private String procuratoryIssued;
    @Schema(description = "Mobile number", example = "")
    private String msisdn;
    private String msisdn2;
    private String msisdn3;
    @Schema(description = "Tariff code", example = "INF")
    private String tariffCode;
    @Schema(description = "Bring your friend MSISDN", example = "")
    private String bringYourFriendMsisdn;
    @Schema(description = "Send SMS and get modem MSISDN", example = "")
    private String sendSmsAndGetModemMsisdn;
    @Schema(description = "Date of Birth", example = "2000-01-23T03:00:00.000+03:00")
    private Date aliveDuring;
    @Schema(description = "Passport issue date", example = "2014-05-23T03:00:00.000+03:00")
    private Date passportIssue;
    @Schema(description = "Contract date", example = "2024-05-23T03:00:00.000+03:00")
    private Date agreementPeriod;
    @Schema(description = "Login of user", example = "8464381_1")
    private String agent;
    @Schema(description = "Type of address", example = "USUAL")
    private String adrTypeCode;
    @Schema(description = "Is typical address", example = "")
    private String adrIsCustom;
    @Schema(description = "Country code", example = "BY")
    private String adrCountryCode;
    @Schema(description = "Locality type", example = "AGRO_TOWN")
    private String adrCityTypeCode;
    @Schema(description = "Street type", example = "33")
    private String adrStreetTypeCode;
    @Schema(description = "Region, district and locality name for subscribers with non typical address", example = "")
    private String adrCustomLine;
    @Schema(description = "Is typical address", example = "")
    private String adrComments;
    @Schema(description = "Full address", example = "220099, Беларусь, Гродненская обл., Гродненская район, агрогородок Князево - Тестовая, д.1, корп.null, кв.1")
    private String adrPrintLine;
    @Schema(description = "Contact phone number", example = "")
    private String adrPhone;
    @Schema(description = "Contact type", example = "IND")
    private String contract_type;
    private String service_template_code;
    @Schema(description = "Category", example = "Infinite COMM")
    private String category;
    @Schema(description = "Agreement", example = "/opt/iguana-uploads/agreements/IND_893750310158619094.pdf")
    private String url_document;
    private String bringYourFriendMsisdnMNP;
    @Schema(description = "Community", example = "")
    private String community;
    @Schema(description = "Sales ID", example = "")
    private String sid;
    @Schema(description = "Source from which subscriber got information about offer", example = "")
    private String informationSource;
    @Schema(description = "Photo of subscriber", example = "")
    private String attachment;
    private String adrLandlinePhone;
    @Schema(description = "Category of the number selected by the user", example = "")
    private String isGoldMsisdn;
    @Schema(description = "Public figure", example = "YES")
    private String public_official;
    @Schema(description = "Activity code", example = "1")
    private String occupation;
    @Schema(description = "Path to s3 folder", example = "893750310158619094/1/")
    private String s3_root;
    @Schema(description = "Job title", example = "")
    private String position;
    @Schema(description = "User Id from HUB", example = "1")
    private String user_id;
    @Schema(example = "HUB")
    private String channel;
}
