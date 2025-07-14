package mini.integration.customerservice.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.NotificationChannel;
import mini.integration.customerservice.domain.enumtype.NotificationStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class NotificationConfigDTO {


    private NotificationTypeDTO notificationType;

    private NotificationChannel notificationChannel;

    private boolean requiredEnable;

    private boolean defaultEnable;

    private String description;

    private NotificationStatus status;

}
