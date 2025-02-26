package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrStreetType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrStreetTypeRepository extends CrudRepository<DictAdrStreetType, String> {
    @Cacheable(value = "dictAdrStreetTypes")
    Optional<DictAdrStreetType> findById(String Id);

    @Cacheable(value = "dictAdrStreetTypeAll")
    List<DictAdrStreetType> findAll();
}
