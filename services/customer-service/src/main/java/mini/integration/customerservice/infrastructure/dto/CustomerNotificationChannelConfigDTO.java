package mini.integration.customerservice.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerNotificationChannelConfigDTO {


    private UUID customerSettingId;
    private UUID notificationConfigId;


    @JsonProperty("isEnabled")
    private boolean enabled;


}
