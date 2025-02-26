package by.com.lifetech.billingapi.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.TariffRelationship;

public interface TariffRelationshipRepository extends CrudRepository<TariffRelationship, String> {

	List<TariffRelationship> findAllBySourceProductAndType(@Param("sourceProduct") String sourceProduct,
			@Param("type") String type);

	List<TariffRelationshipInfo> findDistinctByTypeAndCostAndMidNotNull(String type, String cost);
}
