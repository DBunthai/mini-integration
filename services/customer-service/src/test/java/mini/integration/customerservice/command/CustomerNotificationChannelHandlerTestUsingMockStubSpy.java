package mini.integration.customerservice.command;

import mini.integration.customerservice.application.command.CustomerNotificationChannelConfigCommand;
import mini.integration.customerservice.application.command.handler.impl.CustomerNotificationChannelChannelConfigCommandHandlerImpl;
import mini.integration.customerservice.domain.CustomerNotificationConfig;
import mini.integration.customerservice.domain.CustomerSetting;
import mini.integration.customerservice.domain.NotificationConfig;
import mini.integration.customerservice.domain.NotificationType;
import mini.integration.customerservice.domain.enumtype.NotificationChannel;
import mini.integration.customerservice.infrastructure.dto.CustomerNotificationChannelConfigDTO;
import mini.integration.customerservice.infrastructure.repository.write.CustomerNotificationConfigRepository;
import mini.integration.customerservice.infrastructure.repository.write.NotificationConfigRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerNotificationChannelHandlerTest {


    @Mock
    CustomerNotificationConfigRepository customerNotificationConfigRepository;


    @Mock
    NotificationConfigRepository notificationConfigRepo;

    @InjectMocks
    @Spy // 👈 this makes the handler a spy
    CustomerNotificationChannelChannelConfigCommandHandlerImpl handler;

    @Test
    void testHandle_spy() throws GeneralException {
        // Arrange mocks as usual
        UUID customerId = UUID.randomUUID();
        UUID configId = UUID.randomUUID();


        NotificationType type = NotificationType.builder().name("Welcome").build();
        NotificationChannel channel = NotificationChannel.EMAIL;

        NotificationConfig notificationConfig = NotificationConfig.builder()
            .id(configId)
            .notificationChannel(channel)
            .notificationType(type)
            .defaultEnable(false)
            .build();

//
        var notificationConfigSpy = notificationConfig;
        when(notificationConfigRepo.findById(configId)).thenReturn(Mockito.spy(Optional.of(notificationConfigSpy)));

        when(customerNotificationConfigRepository.findCustomerNotificationConfig(any(), any(), any()))
            .thenReturn(Mockito.spy(Optional.of(
                CustomerNotificationConfig.builder()
                    .id(UUID.randomUUID())
                    .customerSetting(CustomerSetting.builder().build())
                    .notificationConfig(notificationConfig)
                    .isEnabled(false)
                    .build()
            )));


        var command = CustomerNotificationChannelConfigCommand.builder()
            .customerId(customerId.toString())
            .customerNotificationConfigId(configId.toString())
            .enabled(true)
            .build();

        when(customerNotificationConfigRepository.save(any()))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var result = handler.handle(command);

        System.out.println(result.isEnabled());
    }
}




