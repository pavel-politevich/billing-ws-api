package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictInformationSource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictInformationSourceRepository extends CrudRepository<DictInformationSource, String> {
    @Cacheable(value = "dictInformSources")
    Optional<DictInformationSource> findById(String Id);

    @Cacheable(value = "dictInfSourceAll")
    List<DictInformationSource> findAll();
}
