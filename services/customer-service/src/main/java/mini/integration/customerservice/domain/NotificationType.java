package mini.integration.customerservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mini.integration.customerservice.domain.enumtype.NotificationStatus;
import mini.integration.customerservice.lib.EntityBased;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FilterDef(name = "NotificationTypeStatus")
@Filter(name = "NotificationTypeStatus", condition = "status = 'ACTIVE'")
public class NotificationType extends EntityBased {

    @Column(nullable = false)
    String name;

    String description;

    @Enumerated(EnumType.STRING)
    NotificationStatus status;


}
