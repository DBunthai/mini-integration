package mini.integration.customerservice.infrastructure.listener.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import mini.integration.customerservice.application.command.PostedBalanceCommand;
import mini.integration.customerservice.application.command.mapper.CustomerCommandMapper;
import mini.integration.customerservice.domain.event.BalancePostedEvent;
import mini.integration.customerservice.infrastructure.commandbus.CommandBus;
import mini.integration.customerservice.infrastructure.dto.PostedBalanceDTO;
import mini.integration.customerservice.lib.exception.GeneralException;
import mini.integration.customerservice.lib.topic.CustomerEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BalancePostedListener {

    private final CustomerCommandMapper mapper;
    private final CommandBus commandBus;

    public BalancePostedListener(CustomerCommandMapper mapper, CommandBus commandBus) {
        this.mapper = mapper;
        this.commandBus = commandBus;
    }

    @KafkaListener(topics = CustomerEvent.DAILY_BALANCE_POSTED_EVENT, groupId = "customer-group")
    public void dailyBalancePostedListening(BalancePostedEvent event) throws GeneralException {
        log.info("Listened event: {}; payload: {}", CustomerEvent.DAILY_BALANCE_POSTED_EVENT, event);
        PostedBalanceCommand command = mapper.balancePostedEventToPostedBalance(event);
        PostedBalanceDTO postedBalanceDTO = commandBus.dispatch(command);


    }

}
