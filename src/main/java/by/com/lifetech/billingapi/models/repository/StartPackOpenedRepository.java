package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.dto.StartPackOpenedInfo;
import by.com.lifetech.billingapi.models.entity.StartPackOpened;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StartPackOpenedRepository extends JpaRepository<StartPackOpened, String> {
    @Cacheable(value = "openedTariffCodes")
    @Query("""
            select distinct new by.com.lifetech.billingapi.models.dto.StartPackOpenedInfo(s.methodOfSale, s.tariffCode, s.customerType, s.age, s.nonresident)
            from StartPackOpened s
            """)
    List<StartPackOpenedInfo> findDistinctBy();

    @Cacheable(value = "openedStartPacks")
    @Query("""
            select s from StartPackOpened s
            where s.tariffCode = :tariffCode and s.methodOfSale like %:methodOfSale% and s.customerType like %:customerType%
            and s.age <= :age
            """)
    List<StartPackOpened> findStartPacks(String methodOfSale, String customerType, String tariffCode, int age);
}