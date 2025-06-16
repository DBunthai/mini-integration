package mini.integration.customerservice.presentation.command;

import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.exception.BusinessRuleException;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CommandBus commandBus;


    public CustomerController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @PostMapping("/register")
    public void register(@RequestBody CustomerRegisterCommand command) throws BusinessRuleException {
        commandBus.dispatch(command);
    }

}
