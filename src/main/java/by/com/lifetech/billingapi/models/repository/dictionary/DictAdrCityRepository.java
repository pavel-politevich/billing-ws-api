package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrCity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrCityRepository extends CrudRepository<DictAdrCity, String> {
    @Cacheable(value = "dictAdrCities")
    Optional<DictAdrCity> findById(String Id);

    @Cacheable(value = "dictAdrCityAll")
    List<DictAdrCity> findAll();
}
