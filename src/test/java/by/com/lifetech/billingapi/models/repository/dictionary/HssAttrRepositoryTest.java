package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.HssAttribute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfSystemProperty(named = "test.db", matches = "true")
@DataJpaTest
class HssAttrRepositoryTest {
    @Autowired
    private HssAttrRepository hssAttrRepository;

    @Test
    @DisplayName("Get existed HssAttribute by code And searchGroupCode")
    void findByCodeAndSearchGroupCode_whenCodeByConditionExist_thenReturnAttribute() {
        HssAttribute expectedHssAttribute = new HssAttribute();
        expectedHssAttribute.setCode("ODBIC");
        expectedHssAttribute.setSearchGroupCode("ODB Data");
        expectedHssAttribute.setGroupCode("operator_block");
        expectedHssAttribute.setSort(2);
        expectedHssAttribute.setNameRu("Запрет входящих вызовов или смс");
        expectedHssAttribute.setNameEn("ODBIC");

        HssAttribute actualHssAttribute = hssAttrRepository.findByCodeAndSearchGroupCode("ODBIC", "ODB Data").orElseThrow();

        assertEquals(expectedHssAttribute, actualHssAttribute);
    }

    @Test
    @DisplayName("Get existed HssAttribute by Code when searchGroupCode = NONE")
    void findByCodeAndSearchGroupCode_whenCodeExistAndGroupEqualsNone_thenReturnAttribute() {
        HssAttribute expectedHssAttribute = new HssAttribute();
        expectedHssAttribute.setCode("IMSI");
        expectedHssAttribute.setSearchGroupCode("NONE");
        expectedHssAttribute.setGroupCode("basic_info");
        expectedHssAttribute.setSort(1);
        expectedHssAttribute.setNameRu("International mobile subscriber Identity");
        expectedHssAttribute.setNameEn("IMSI");

        HssAttribute actualHssAttribute = hssAttrRepository.findByCodeAndSearchGroupCode("IMSI", "").orElseThrow();

        assertEquals(expectedHssAttribute, actualHssAttribute);
    }

    @Test
    @DisplayName("Get empty result for existed hss code and groupCode = Unknown")
    void findByCodeAndSearchGroupCode_whenCodeExistAndGroupCodeEqualsUnknown_thenReturnEmpty() {
        boolean hssAttributeIsEmpty = hssAttrRepository.findByCodeAndSearchGroupCode("SgsnInHplmn", "").isEmpty();

        assertTrue(hssAttributeIsEmpty);
    }

    @Test
    @DisplayName("Get true result for existed code")
    void existsByCode_whenCodeExist_thenReturnTrue() {
        boolean actualIsExistCode = hssAttrRepository.existsByCode("ODBIC");

        assertTrue(actualIsExistCode);
    }

    @Test
    @DisplayName("Get hss attributes by ru or en name (case insensitive)")
    void findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase_whenNameExist_thenReturnHssAttribute() {
        HssAttribute expectedHssAttribute = new HssAttribute();
        expectedHssAttribute.setCode("ODBIC");
        expectedHssAttribute.setNameRu("Запрет входящих вызовов или смс");
        expectedHssAttribute.setNameEn("ODBIC");

        List<HssAttribute> hssAttributeList = hssAttrRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(null, "ODbic");

        assertEquals(expectedHssAttribute.getCode(), hssAttributeList.get(0).getCode());
    }

    @Test
    @DisplayName("Check hss attributes dictionary not empty")
    void findAll_whenCall_thenReturnAttributes() {
        List<HssAttribute> hssAttributeList = hssAttrRepository.findAll();

        assertFalse(hssAttributeList.isEmpty());
    }
}