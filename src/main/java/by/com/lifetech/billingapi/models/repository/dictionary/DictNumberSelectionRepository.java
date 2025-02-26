package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictNumberSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictNumberSelectionRepository extends JpaRepository<DictNumberSelection, String> {
    @Query(value = """
        select t.ss_code, t.om_code, chloc.value as name
            from conf.Dict_Number_Selection t
            join tm_om.om_channel_registry ch on ch.product_code = t.om_code
            join tm_om.om_channel_localizations chloc on ch.code = chloc.registry_code
            where ch.rc_type_code = :channelCode and chloc.rc_lang_code = :lang
    """, nativeQuery = true)
    List<DictNumberSelection> findAllByLang(@Param("channelCode") String channelCode, @Param("lang") String lang);
}