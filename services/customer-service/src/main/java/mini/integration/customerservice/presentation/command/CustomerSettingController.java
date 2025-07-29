package mini.integration.customerservice.presentation.command;

import jakarta.validation.Valid;
import mini.integration.customerservice.application.command.CustomerNotificationChannelConfigCommand;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/{customerId}/setting")
public class CustomerSettingController {

    private final CommandBus commandBus;

    public CustomerSettingController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PatchMapping("/notification/{notificationConfigId}/channel-config")
    public ResponseEntity<?> patchCustomerNotificationConfig(@PathVariable("customerId") String customerId,
                    @PathVariable("notificationConfigId") String notificationConfigId,
                    @RequestBody @Valid CustomerNotificationChannelConfigCommand command) throws GeneralException {

        commandBus.dispatch(CustomerNotificationChannelConfigCommand.builder().customerId(customerId)
                        .customerNotificationConfigId(notificationConfigId).enabled(command.isEnabled()).build());

        return ResponseEntity.ok().build();
    }

}
