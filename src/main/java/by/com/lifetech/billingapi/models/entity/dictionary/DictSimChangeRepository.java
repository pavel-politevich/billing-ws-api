package by.com.lifetech.billingapi.models.entity.dictionary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DictSimChangeRepository extends JpaRepository<DictSimChange, String> {
    @Cacheable(value = "dictSimChangeAll")
    List<DictSimChange> findAll();

    @Cacheable(value = "dictSimChangeByType")
    List<DictSimChange> findByTypeIn(Collection<String> types);
}