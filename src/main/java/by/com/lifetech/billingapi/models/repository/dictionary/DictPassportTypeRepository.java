package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictPassportType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictPassportTypeRepository extends JpaRepository<DictPassportType, String> {
    @Cacheable(value = "dictPassportTypeAll")
    List<DictPassportType> findAll();
}