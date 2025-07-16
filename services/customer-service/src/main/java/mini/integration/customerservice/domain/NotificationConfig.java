package mini.integration.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mini.integration.customerservice.domain.enumtype.NotificationChannel;
import mini.integration.customerservice.domain.enumtype.NotificationStatus;
import mini.integration.customerservice.lib.EntityBased;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FilterDef(name = "NotificationConfigStatus")
@Filter(name = "NotificationConfigStatus", condition = "status = 'ACTIVE' && notificationType.status = 'ACTIVE'")
public class NotificationConfig extends EntityBased {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private NotificationType notificationType;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChannel;

    private boolean requiredEnable;

    private boolean defaultEnable;

    private String description;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

}
