package mini.integration.customerservice.presentation.command;

import jakarta.validation.Valid;
import mini.integration.customerservice.application.command.CustomerProfileEditCommand;
import mini.integration.customerservice.application.command.CustomerRegisterCommand;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<?> register(@RequestBody @Valid CustomerRegisterCommand command) throws GeneralException {
        commandBus.dispatch(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<?> edit(@RequestBody @Valid CustomerProfileEditCommand command) throws GeneralException {
        commandBus.dispatch(command);
        return ResponseEntity.ok().build();
    }

}
