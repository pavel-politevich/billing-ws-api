package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrCountry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrCountryRepository extends CrudRepository<DictAdrCountry, String> {
    @Cacheable(value = "dictAdrCountries")
    Optional<DictAdrCountry> findById(String Id);

    @Cacheable(value = "dictAdrCountryAll")
    List<DictAdrCountry> findAll();
}
