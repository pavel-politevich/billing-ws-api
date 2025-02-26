package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.SubscribersPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubscribersPhotoRepository extends JpaRepository<SubscribersPhoto, String> {
    @Query(value = """
            select * from TM_CIM.SUBSCRIBERS_PHOTO sp
               join (select t.entry_date, t.msisdn, t.contract, t.photo_id as photo_id2 from TM_CIM.SUBSCRIBERS_PHOTO_INFORMATION t) spi
               on sp.PHOTO_ID = spi.PHOTO_ID2
               where spi.MSISDN = :msisdn and spi.CONTRACT = :contractId
               and spi.ENTRY_DATE = (
               select max(entry_date) from TM_CIM.SUBSCRIBERS_PHOTO_INFORMATION where msisdn=spi.MSISDN)
            """, nativeQuery = true)
    Optional<SubscribersPhoto> findByPhotoId(@Param("msisdn") String msisdn,
                                             @Param("contractId") String contractId);
}