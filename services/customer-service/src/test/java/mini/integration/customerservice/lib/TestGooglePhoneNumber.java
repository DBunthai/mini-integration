package mini.integration.customerservice.lib;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import mini.integration.customerservice.domain.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestGooglePhoneNumber {

    @Test
    void TestPhoneNumber_Passed() {

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();
        Stream.of(
            "7034796223",
            "17034796223",
            "+17034796223",
            "703 479 6223",
            "703-479-6223"

        ).forEach(
            number -> {
                Phonenumber.PhoneNumber parsedNumber = null;
                try {
                    parsedNumber = util.parse(number, "US");
                } catch (NumberParseException e) {
                    throw new RuntimeException(e);
                }
                boolean isGeographical = util.isNumberGeographical(parsedNumber);
                boolean isValid = util.isValidNumber(parsedNumber);
                System.out.println(parsedNumber + " => Geographical: " + isGeographical + ", Valid: " + isValid);
                Assert.isTrue(isGeographical && isValid, "expected True");
            }
        );
    }

    @Test
    void TestPhoneNumber_Failed() {

        Stream.of(
            "asdfsd",
            "sdfwewe",
            "(703) 513-6334asdf"

        ).forEach(
            number -> {

                assertThatThrownBy(() -> {
                    Contact contact = Contact.builder()
                        .phoneNumber(number)
                        .build();
                    System.out.println(contact.getPhoneNumber());
                });
            }
        );
    }

}
