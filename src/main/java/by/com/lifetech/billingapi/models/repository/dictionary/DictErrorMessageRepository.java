package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictErrorMessage;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictErrorMessageRepository extends JpaRepository<DictErrorMessage, String> {
    @Cacheable(value = "dictError")
    Optional<DictErrorMessage> findByCode(@Param("code") String code);
    
    @Cacheable(value = "dictErrorNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

    @Cacheable(value = "dictErrorAll")
    List<DictErrorMessage> findAll();
}
