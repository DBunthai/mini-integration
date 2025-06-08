package mini.integration.customerservice.lib;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

public class GooglePhoneNumber {

    PhoneNumberUtil util = PhoneNumberUtil.getInstance();

    @Test
    void TestPhoneNumber_Passed() {

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();
        Stream.of(
            "7034796223",
            "17034796223",
            "+17034796223",
            "703 479 6223"

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

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();
        Stream.of(
            "asdfsd",
            "sdfwewe"

        ).forEach(
            number -> {
                assertThatThrownBy(() -> {
                    util.parse(number, "US");
                });
            }
        );
    }

}
