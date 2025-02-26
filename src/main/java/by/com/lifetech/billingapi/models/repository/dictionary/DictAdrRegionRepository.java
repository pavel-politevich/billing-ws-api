package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrRegion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrRegionRepository extends CrudRepository<DictAdrRegion, String> {
    @Cacheable(value = "dictAdrRegions")
    Optional<DictAdrRegion> findById(String Id);

    @Cacheable(value = "dictAdrRegionAll")
    List<DictAdrRegion> findAll();
}
