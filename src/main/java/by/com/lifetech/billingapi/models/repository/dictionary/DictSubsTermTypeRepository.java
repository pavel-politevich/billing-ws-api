package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictSubsTerminationType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictSubsTermTypeRepository extends JpaRepository<DictSubsTerminationType, String> {
    @Cacheable(value = "subsTermTypes")
    List<DictSubsTerminationType> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

    @Cacheable(value = "subsTermTypesAll")
    List<DictSubsTerminationType> findAll();
}