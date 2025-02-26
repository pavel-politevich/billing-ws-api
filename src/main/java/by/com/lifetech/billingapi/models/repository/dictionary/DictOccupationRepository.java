package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictOccupation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictOccupationRepository extends CrudRepository<DictOccupation, String> {
    @Cacheable(value = "dictOccupations")
    Optional<DictOccupation> findById(String Id);

    @Cacheable(value = "dictOccupationAll")
    @Query("select d from DictOccupation d where d.act = 1")
    List<DictOccupation> findAll();
}
