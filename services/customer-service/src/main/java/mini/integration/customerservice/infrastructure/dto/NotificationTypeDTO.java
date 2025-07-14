package mini.integration.customerservice.infrastructure.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.NotificationStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class NotificationTypeDTO {

    private String name;

    private String description;

    private NotificationStatus status;
}
