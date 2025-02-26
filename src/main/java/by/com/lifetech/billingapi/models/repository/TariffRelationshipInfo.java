package by.com.lifetech.billingapi.models.repository;

/**
 * Projection for {@link by.com.lifetech.billingapi.models.entity.TariffRelationship}
 */
public interface TariffRelationshipInfo {
    String getTargetProduct();

    String getCost();

    String getMid();
}