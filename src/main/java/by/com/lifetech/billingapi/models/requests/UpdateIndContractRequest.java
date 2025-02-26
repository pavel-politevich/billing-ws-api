package by.com.lifetech.billingapi.models.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndContractRequest {

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
	@Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Place of registration", example = "")
    private String placeOfRegistration;
    @Schema(description = "Agent code", example = "62962618")
    private String representativeOfOperator;
    @Schema(description = "Date of Birth", example = "2000-01-23T03:00:00.000+03:00")
    private Date aliveDuring;
    @Schema(description = "Passport issue date", example = "2014-05-23T03:00:00.000+03:00")
    private Date passportIssue;
    @Schema(description = "Contract date from", example = "2024-05-23T03:00:00.000+03:00")
    private Date agreementPeriod;
    @Schema(description = "Login of user", example = "8464381_1")
    private String agent;
    @Schema(description = "Public figure", example = "YES")
    private String public_official;
    @Schema(description = "Activity code", example = "1")
    private String occupation;
    @Schema(description = "Community", example = "")
    private String community;
    @Schema(description = "Contract No", example = "300134744")
    private String ContractNo;
}
