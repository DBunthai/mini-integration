package mini.integration.customerservice.application.query.handler.impl;

import mini.integration.customerservice.application.query.CustomerSettingNotificationQuery;
import mini.integration.customerservice.application.query.handler.CustomerSettingNotificationQueryHandler;
import mini.integration.customerservice.domain.CustomerNotificationConfig;
import mini.integration.customerservice.domain.CustomerSetting;
import mini.integration.customerservice.domain.NotificationType;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingNotificationDTO;
import mini.integration.customerservice.infrastructure.repository.read.CustomerSettingReadRepository;
import mini.integration.customerservice.infrastructure.repository.read.NotificationTypeReadRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerSettingNotificationQueryHandlerImpl implements CustomerSettingNotificationQueryHandler {

    private final CustomerSettingReadRepository customerSettingReadRepository;
    private final NotificationTypeReadRepository notificationTypeReadRepository;

    public CustomerSettingNotificationQueryHandlerImpl(CustomerSettingReadRepository customerSettingReadRepository,
                    NotificationTypeReadRepository notificationTypeReadRepository) {
        this.customerSettingReadRepository = customerSettingReadRepository;
        this.notificationTypeReadRepository = notificationTypeReadRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerSettingNotificationDTO> handle(CustomerSettingNotificationQuery query) throws GeneralException {

        CustomerSetting customerSetting = customerSettingReadRepository.findCustomerSettingByCustomer_Id(query.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Customer setting is not found"));

        Map<UUID, Boolean> customerNotificationConfig = customerSetting.getCustomerNotificationConfigs().stream()
                        .collect(Collectors.toMap(map -> map.getNotificationConfig().getId(), CustomerNotificationConfig::isEnabled));


        List<NotificationType> notificationTypes = notificationTypeReadRepository.findAll();

        List<CustomerSettingNotificationDTO> customerSettingNotificationDTOS = notificationTypes.stream().map(m -> {

            List<CustomerSettingNotificationDTO.CustomerNotificationConfigDTO> notificationConfigDTOS =
                            m.getNotificationConfigs().stream().map(m2 -> {

                                Boolean customerConfig = customerNotificationConfig.getOrDefault(m2.getId(), null);
                                boolean isOverriding = Objects.nonNull(customerConfig) ? customerConfig : m2.isDefaultEnable();

                                return CustomerSettingNotificationDTO.CustomerNotificationConfigDTO.builder().id(m2.getId())
                                                .notificationChannel(m2.getNotificationChannel()).enabled(isOverriding)
                                                .requiredEnable(m2.isRequiredEnable()).build();
                            }).toList();


            return CustomerSettingNotificationDTO.builder().id(m.getId()).name(m.getName()).enable(true).notificationConfigs(notificationConfigDTOS)
                            .description(m.getDescription()).build();

        }).toList();


        return new PageImpl<>(customerSettingNotificationDTOS);
    }
}
