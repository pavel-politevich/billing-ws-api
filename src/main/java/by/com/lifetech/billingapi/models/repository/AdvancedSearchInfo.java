package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;

/**
 * Projection for {@link by.com.lifetech.billingapi.models.entity.AdvancedSearch}
 */
public interface AdvancedSearchInfo {
    Long getAccountId();

    String getMsisdn();

    String getContractId();

    LocalDateTime getActivationDate();

    String getStateCode();

    String getContractTypeCode();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getCompanyName();

    String getTariffCode();
}