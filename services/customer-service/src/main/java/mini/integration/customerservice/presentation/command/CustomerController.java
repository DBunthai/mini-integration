package mini.integration.customerservice.presentation.command;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @PostMapping("/register")
    public void register(@RequestBody CustomerRegisterCommand command) {

    }

}
