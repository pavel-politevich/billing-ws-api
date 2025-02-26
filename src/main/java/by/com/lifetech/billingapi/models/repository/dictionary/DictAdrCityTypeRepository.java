package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrCityType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrCityTypeRepository extends CrudRepository<DictAdrCityType, String> {
    @Cacheable(value = "dictAdrCityTypes")
    Optional<DictAdrCityType> findById(String Id);

    @Cacheable(value = "dictAdrCityTypeAll")
    List<DictAdrCityType> findAll();
}
