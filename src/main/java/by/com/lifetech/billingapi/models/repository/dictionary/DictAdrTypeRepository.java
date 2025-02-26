package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictAdrType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictAdrTypeRepository extends JpaRepository<DictAdrType, String> {
    @Cacheable(value = "dictAdrTypes")
    Optional<DictAdrType> findById(String Id);

    @Cacheable(value = "dictAdrTypeAll")
    List<DictAdrType> findAll();
}