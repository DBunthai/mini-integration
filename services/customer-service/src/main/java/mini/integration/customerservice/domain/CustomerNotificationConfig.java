package mini.integration.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @JsonBackReference
    private CustomerSetting customerSetting;

    @Setter
    private boolean isEnabled;

    @ManyToOne
    @JsonBackReference
    private NotificationConfig notificationConfig;
}
