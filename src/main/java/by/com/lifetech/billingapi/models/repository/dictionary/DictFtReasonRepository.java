package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictFtReason;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictFtReasonRepository extends JpaRepository<DictFtReason, String> {
    @Cacheable(value = "dictFtReasonNames")
    List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

    @Cacheable(value = "dictFtReason", key = "#code", condition = "#code != null")
    Optional<DictFtReason> findByCode(@Param("code") String code);
}