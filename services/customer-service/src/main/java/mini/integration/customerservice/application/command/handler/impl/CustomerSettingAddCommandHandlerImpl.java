package mini.integration.customerservice.application.command.handler.impl;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.CustomerSettingAddCommand;
import mini.integration.customerservice.application.command.handler.CustomerSettingAddCommandHandler;
import mini.integration.customerservice.domain.Customer;
import mini.integration.customerservice.domain.CustomerSetting;
import mini.integration.customerservice.domain.enumtype.RegularPostedBalance;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingAddDTO;
import mini.integration.customerservice.infrastructure.repository.write.CustomerSettingRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.util.ObjectMapperLib;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@Transactional
public class CustomerSettingAddCommandHandlerImpl implements CustomerSettingAddCommandHandler {

    private final CustomerSettingRepository customerSettingRepository;

    public CustomerSettingAddCommandHandlerImpl(CustomerSettingRepository customerSettingRepository) {
        this.customerSettingRepository = customerSettingRepository;
    }

    @Override
    public CustomerSettingAddDTO handle(CustomerSettingAddCommand command) throws GeneralException {

        CustomerSetting customerSetting = CustomerSetting.builder()
            .customer(Customer.builder().id(command.getCustomerId()).build())
            .regularPostedBalance(RegularPostedBalance.MONTHLY)
            .build();

        CustomerSetting savedCustomerSetting = customerSettingRepository.save(customerSetting);
        log.info("Saved customer setting; CustomerSettingId: {}; CustomerId: {}; ", customerSetting.getId(), customerSetting.getCustomer().getId());

        return ObjectMapperLib.mapObj(savedCustomerSetting, CustomerSettingAddDTO.class, ObjectMapperLib.ObjectMapperRule.UNRESTRICTED);
    }
}
