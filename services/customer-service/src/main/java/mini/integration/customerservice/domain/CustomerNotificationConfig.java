package mini.integration.customerservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mini.integration.customerservice.lib.EntityBased;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FilterDef(name = "CustomerNotificationConfigStatus")
@Filter(name = "CustomerNotificationConfigStatus", condition = "notificationConfig.status = 'ACTIVE'")
public class CustomerNotificationConfig extends EntityBased {

    @ManyToOne
    CustomerSetting customerSetting;

    boolean isEnabled;

    @ManyToOne
    NotificationConfig notificationConfig;
}
