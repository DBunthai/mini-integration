package mini.integration.customerservice.presentation.query;

import mini.integration.customerservice.application.query.CustomerProfileQuery;
import mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO;
import mini.integration.customerservice.infrastructure.querybus.QueryBus;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerQueryController {

    private final QueryBus queryBus;


    public CustomerQueryController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<CustomerProfileDTO> getProfile(@PathVariable("id") String id) throws GeneralException {
        var customerProfileQuery = CustomerProfileQuery.builder().id(id).build();
        return ResponseEntity.ok(queryBus.dispatch(customerProfileQuery));
    }

}
