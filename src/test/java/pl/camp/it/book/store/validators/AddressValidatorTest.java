package pl.camp.it.book.store.validators;

import org.junit.Test;
import pl.camp.it.book.store.exceptions.ValidationException;

public class AddressValidatorTest {

    @Test(expected = ValidationException.class)
    public void streetNameLowerCaseTest() {
        String address = "ogrodowa 12";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void noBuildingNumberTest() {
        String address = "Ogrodowa";
        AddressValidator.validateAddress(address);
    }

    @Test
    public void threeDigitBuildingNumberTest() {
        String address = "Ogrodowa 123";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void tooLongBuildingNumberTest() {
        String address = "Ogrodowa 123456";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void twoLettersInBuildingNumberTest() {
        String address = "Ogrodowa 12AB";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void incorrectFlatNumberSeparatorTest() {
        String address = "Ogrodowa 12,6";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void tooLongFlatNumberTest() {
        String address = "Ogrodowa 12/12345";
        AddressValidator.validateAddress(address);
    }

    @Test
    public void correctAddressTest() {
        String address = "Ogrodowa 12A/5";
        AddressValidator.validateAddress(address);
    }

    @Test(expected = ValidationException.class)
    public void tooManyDigitsFirstPartPostalCodeTest() {
        String code = "123-123";
        AddressValidator.validatePostalCode(code);
    }

    @Test(expected = ValidationException.class)
    public void notEnoughDigitsFirstPartPostalCodeTest() {
        String code = "1-123";
        AddressValidator.validatePostalCode(code);
    }

    @Test(expected = ValidationException.class)
    public void incorrectSeparatorPostalCodeTest() {
        String code = "12+123";
        AddressValidator.validatePostalCode(code);
    }

    @Test(expected = ValidationException.class)
    public void tooManyDigitsSecondPartPostalCodeTest() {
        String code = "12-12334";
        AddressValidator.validatePostalCode(code);
    }

    @Test(expected = ValidationException.class)
    public void notEnoughDigitsSecondPartPostalCodeTest() {
        String code = "1-13";
        AddressValidator.validatePostalCode(code);
    }

    @Test
    public void correctPostalCodeTest() {
        String code = "12-123";
        AddressValidator.validatePostalCode(code);
    }

    @Test(expected = ValidationException.class)
    public void firstLowerCaseCityTest() {
        String city = "krakow";
        AddressValidator.validateCity(city);
    }

    @Test(expected = ValidationException.class)
    public void incorrectSpaceSeparatorTest() {
        String city = "Nowa*Huta";
        AddressValidator.validateCity(city);
    }

    @Test(expected = ValidationException.class)
    public void secondPartLowerCaseTest() {
        String city = "Nowa huta";
        AddressValidator.validateCity(city);
    }

    @Test(expected = ValidationException.class)
    public void secondPartLowerCase2Test() {
        String city = "Nowa-huta";
        AddressValidator.validateCity(city);
    }

    @Test
    public void correctCityTest() {
        String city1 = "Kraków";
        String city2 = "Kraków Coś";
        String city3 = "Kraków-Coś";
        String city4 = "Kraków-Coś Cos";
        AddressValidator.validateCity(city1);
        AddressValidator.validateCity(city2);
        AddressValidator.validateCity(city3);
        AddressValidator.validateCity(city4);
    }

    @Test(expected = ValidationException.class)
    public void firstZeroPhoneNumber() {
        String phone = "012345678";
        AddressValidator.validatePhone(phone);
    }

    @Test(expected = ValidationException.class)
    public void notEnoughDigitsPhoneTest() {
        String phone = "3123456";
        AddressValidator.validatePhone(phone);
    }

    @Test
    public void correctPhoneTest() {
        String phone = "123123123";
        AddressValidator.validatePhone(phone);
    }
}
