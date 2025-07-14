package mini.integration.customerservice.application.query.handler.impl;

import mini.integration.customerservice.application.query.CustomerSettingQuery;
import mini.integration.customerservice.application.query.handler.CustomerSettingQueryHandler;
import mini.integration.customerservice.domain.CustomerSetting;
import mini.integration.customerservice.exception.ResourceNotFoundException;
import mini.integration.customerservice.infrastructure.dto.CustomerSettingDTO;
import mini.integration.customerservice.infrastructure.repository.read.CustomerSettingReadRepository;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.util.ObjectMapperLib;
import org.springframework.stereotype.Component;

@Component
public class CustomerSettingQueryHandlerImpl implements CustomerSettingQueryHandler {


    private final CustomerSettingReadRepository customerSettingReadRepository;

    public CustomerSettingQueryHandlerImpl(CustomerSettingReadRepository customerSettingReadRepository) {
        this.customerSettingReadRepository = customerSettingReadRepository;
    }


    @Override
    public CustomerSettingDTO handle(CustomerSettingQuery query) throws GeneralException {



        CustomerSetting customerSetting = customerSettingReadRepository
            .findCustomerSettingByCustomer_Id(query.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer setting is not found"));


        return ObjectMapperLib.mapObj(customerSetting, CustomerSettingDTO.class);
    }
}
