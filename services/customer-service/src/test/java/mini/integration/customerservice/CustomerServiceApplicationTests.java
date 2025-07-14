package mini.integration.customerservice;

import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.application.query.handler.CustomerSettingNotificationQueryHandler;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceApplicationTests {

	@Autowired
	private CustomerSettingNotificationQueryHandler customerSettingNotificationQueryHandler;

	@Test
	void contextLoads() throws GeneralException {

		var a = customerSettingNotificationQueryHandler.handle(CustomerSettingNotificationQuery.builder()
				.customerId("45cfb43c-1675-4ed8-bf7f-aae3060c6b58")
			.build());
		System.out.println(a.getContent());
	}

}
