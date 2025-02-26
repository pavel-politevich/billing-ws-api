package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictCommunities;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DictCommunitiesRepository extends JpaRepository<DictCommunities, String> {

    @Cacheable(value = "dictFtReason", key = "#code", condition = "#code != null")
    Optional<DictCommunities> findByCode(@Param("code") String code);
}