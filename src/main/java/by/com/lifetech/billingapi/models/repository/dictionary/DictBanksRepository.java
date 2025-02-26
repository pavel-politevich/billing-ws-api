package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictBanks;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictBanksRepository extends JpaRepository<DictBanks, String> {
    @Cacheable(value = "banksNames")
    List<DictBanks> findByNameContainsIgnoreCase(String name);

    @Cacheable(value = "dictBanks", key = "#code", condition = "#code != null")
    Optional<DictBanks> findByCode(@Param("code") String code);

    @Cacheable(value = "dictBanksAll")
    List<DictBanks> findAll();
}