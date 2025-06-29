package mini.integration.customerservice.infrastructure.listener.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.event.BalancePostedEvent;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import mini.integration.customerservice.lib.util.ObjectMapperLib;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BalancePostedListener {

    private final CustomerCommandMapper mapper;
    private final CommandBus commandBus;

    public BalancePostedListener(CustomerCommandMapper mapper, CommandBus commandBus) {
        this.mapper = mapper;
        this.commandBus = commandBus;
    }

    @KafkaListener(topics = CustomerEvent.DAILY_BALANCE_POSTED_EVENT, groupId = "customer-group")
    void dailyBalancePostedListening(BalancePostedEvent obj) throws JsonProcessingException, GeneralException {
        System.out.println("AA: " + obj);
//        BalancePostedEvent event = ObjectMapperLib.objectMapper().readValue(obj, BalancePostedEvent.class);
        System.out.println("event: " + obj);
        PostedBalanceCommand command = mapper.balancePostedEventToPostedBalance(obj);
        PostedBalanceDTO postedBalanceDTO = commandBus.dispatch(command);
        System.out.println(postedBalanceDTO);

    }

}
