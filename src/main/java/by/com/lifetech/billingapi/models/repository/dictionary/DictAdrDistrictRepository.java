package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrDistrict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrDistrictRepository extends CrudRepository<DictAdrDistrict, String> {
    @Cacheable(value = "dictAdrDistricts")
    Optional<DictAdrDistrict> findById(String Id);

    @Cacheable(value = "dictAdrDistrictAll")
    List<DictAdrDistrict> findAll();

}
