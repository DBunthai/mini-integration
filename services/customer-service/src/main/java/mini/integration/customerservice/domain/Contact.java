package mini.integration.customerservice.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini.integration.customerservice.domain.constant.PhoneRegion;
import mini.integration.customerservice.exception.InvalidArgumentException;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Contact {


    private String phoneNumber;

    @Email(message = "Invalid Email")
    private String email;


    public void setPhoneNumber(String phoneNumber) throws InvalidArgumentException {
        try {
            PhoneNumberUtil.getInstance().parse(phoneNumber, PhoneRegion.US);
        } catch (NumberParseException e) {
            throw new InvalidArgumentException("Invalid Phone Number");
        }
    }

}
