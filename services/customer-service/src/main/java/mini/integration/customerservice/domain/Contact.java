package mini.integration.customerservice.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini.integration.customerservice.domain.constant.PhoneRegion;
import mini.integration.customerservice.lib.util.PhoneLib;

@Getter
@Builder(builderClassName = "ContactBuilder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Contact {

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    public static class ContactBuilder {
        public Contact build() {
            return new Contact(validatePhoneNumber(phoneNumber), email);
        }
    }

    private static String validatePhoneNumber(String phoneNumber) {
        var error = new IllegalArgumentException("Invalid phone number");
        try {
            var phoneUtil = PhoneLib.phoneNumberUtil();
            var sanityPhoneNumber = phoneUtil.parse(phoneNumber, PhoneRegion.US);
            if (!phoneUtil.isValidNumber(sanityPhoneNumber)) {
                throw error;
            }
            return phoneUtil.format(sanityPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            throw error;
        }
    }

}
