package mini.integration.customerservice.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mini.integration.customerservice.domain.enumtype.RegularPostedBalance;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerSettingDTO {

    private RegularPostedBalance regularPostedBalance;

}
