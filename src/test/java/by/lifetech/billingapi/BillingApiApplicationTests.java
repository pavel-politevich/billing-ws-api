package by.lifetech.billingapi;

import by.com.lifetech.billingapi.BillingApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BillingApiApplication.class)
@EnabledIfSystemProperty(named = "test.integration", matches = "true")
class BillingApiApplicationTests {

	@Test
	void contextLoads() {
		// This test will fail if the application context cannot start
	}

}
