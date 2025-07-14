package mini.integration.customerservice.presentation.query;

import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.application.query.CustomerSettingQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingDTO;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingNotificationDTO;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import mini.integration.customerservice.lib.PageResponse;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/{customerId}/setting")
public class CustomerSettingQueryController {


    private final QueryBus queryBus;

    public CustomerSettingQueryController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/notification")
    public ResponseEntity<PageResponse> getCustomerSettingNotification(@PathVariable("customerId") String customerId) throws GeneralException {

        CustomerSettingNotificationQuery customerSettingNotificationQuery =
            CustomerSettingNotificationQuery.builder()
                .customerId(customerId)
                .build();

        var customerSettingNotifications = (Page<CustomerSettingNotificationDTO>) queryBus.dispatch(customerSettingNotificationQuery);

        return ResponseEntity.ok(PageResponse.of(customerSettingNotifications, CustomerSettingNotificationDTO.class));
    }

    @GetMapping
    public ResponseEntity<?> getCustomerSetting(@PathVariable("customerId") String customerId) throws GeneralException {

        CustomerSettingQuery customerSettingQuery =
            CustomerSettingQuery.builder()
                .customerId(customerId)
                .build();

        var customerSettings = queryBus.dispatch(customerSettingQuery);

        return ResponseEntity.ok(customerSettings);
    }


}
