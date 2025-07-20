package mini.integration.customerservice.application.command.handler.impl;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.CustomerNotificationChannelConfigCommand;
import mini.integration.customerservice.application.command.handler.CustomerNotificationChannelConfigCommandHandler;
import mini.integration.customerservice.domain.CustomerNotificationConfig;
import mini.integration.customerservice.infrastructure.dto.CustomerNotificationChannelConfigDTO;
import mini.integration.customerservice.infrastructure.repository.write.CustomerNotificationConfigRepository;
import mini.integration.customerservice.infrastructure.repository.write.CustomerSettingRepository;
import mini.integration.customerservice.infrastructure.repository.write.NotificationConfigRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.util.ObjectMapperLib;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@Transactional
public class CustomerNotificationChannelChannelConfigCommandHandlerImpl implements CustomerNotificationChannelConfigCommandHandler {

    private final CustomerNotificationConfigRepository customerNotificationConfigRepository;
    private final CustomerSettingRepository customerSettingRepository;
    private final NotificationConfigRepository notificationConfigRepository;

    public CustomerNotificationChannelChannelConfigCommandHandlerImpl(CustomerNotificationConfigRepository customerNotificationConfigRepository, CustomerSettingRepository customerSettingRepository, NotificationConfigRepository notificationConfigRepository) {
        this.customerNotificationConfigRepository = customerNotificationConfigRepository;
        this.customerSettingRepository = customerSettingRepository;
        this.notificationConfigRepository = notificationConfigRepository;
    }


    @Override
    public CustomerNotificationChannelConfigDTO handle(CustomerNotificationChannelConfigCommand command) throws GeneralException {


        var notificationConfig = notificationConfigRepository.findById(command.getCustomerNotificationConfigId())
            .orElseThrow(() -> new RuntimeException("Notification Config is not found"));

        CustomerNotificationConfig customerNotificationConfig = customerNotificationConfigRepository
            .findCustomerNotificationConfig(
                command.getCustomerId(),
                notificationConfig.getId(),
                notificationConfig.getNotificationChannel()
            )
            .orElseGet(() ->
                CustomerNotificationConfig
                    .builder()
                    .customerSetting(
                        customerSettingRepository
                            .findCustomerSettingByCustomer_Id(command.getCustomerId())
                            .orElseThrow(() -> new RuntimeException("Customer Setting is not found"))
                    )
                    .notificationConfig(notificationConfig)
                    .build());


        customerNotificationConfig.setEnabled(command.isEnabled());
        var savedCustomerNotificationConfig = customerNotificationConfigRepository.save(customerNotificationConfig);
//        log.info("""
//
//                Save Customer Notification Config
//                    Customer: {},
//                    NotificationType: {}
//                    Channel: {},
//                    isEnabled To: {}
//                """,
//            savedCustomerNotificationConfig.getCustomerSetting().getCustomer().getId(),
//            savedCustomerNotificationConfig.getNotificationConfig().getNotificationType().getName(),
//            savedCustomerNotificationConfig.getNotificationConfig().getNotificationChannel(),
//            savedCustomerNotificationConfig.isEnabled());

        return ObjectMapperLib.mapObj(savedCustomerNotificationConfig, CustomerNotificationChannelConfigDTO.class, ObjectMapperLib.ObjectMapperRule.UNRESTRICTED);
    }
}
