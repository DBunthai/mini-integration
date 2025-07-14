package mini.integration.customerservice.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.NotificationChannel;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CustomerSettingNotificationDTO {

    private UUID id;

    private String name;

    @JsonProperty("isEnabled")
    private boolean enable;

    private String description;

    private List<CustomerNotificationConfigDTO> notificationConfigs;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class CustomerNotificationConfigDTO {

        private UUID id;

        private NotificationChannel notificationChannel;
        private boolean requiredEnable;

        @JsonProperty("isEnabled")
        private boolean enabled;

    }

}
