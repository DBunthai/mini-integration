package mini.integration.customerservice.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import mini.integration.customerservice.domain.constant.PhoneRegion;
import mini.integration.customerservice.exception.InvalidArgumentException;

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
