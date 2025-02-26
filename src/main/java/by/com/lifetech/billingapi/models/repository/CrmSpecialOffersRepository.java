package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.CrmSpecialOffers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrmSpecialOffersRepository extends JpaRepository<CrmSpecialOffers, String> {
    @Cacheable(value = "crmSpecialOffersAll")
    List<CrmSpecialOffers> findAll();
}