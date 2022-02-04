package pl.camp.it.book.store.validators;

import org.junit.Test;
import pl.camp.it.book.store.exceptions.ValidationException;

public class ValidatorTest {

    @Test(expected = ValidationException.class)
    public void tooShortLoginTest() {
        String login = "df";
        Validator.validateLogin(login);
    }

    @Test
    public void correctLoginTest() {
        String login = "admin";
        Validator.validateLogin(login);
    }

    @Test(expected = ValidationException.class)
    public void tooShortPassTest() {
        String pass = "df";
        Validator.validatePassword(pass);
    }

    @Test
    public void correctPassTest() {
        String pass = "admin";
        Validator.validatePassword(pass);
    }

    @Test(expected = ValidationException.class)
    public void lowerCaseNameTest() {
        String name = "mateusz";
        Validator.validateName(name);
    }

    @Test(expected = ValidationException.class)
    public void tooShortNameTest() {
        String name = "A";
        Validator.validateName(name);
    }

    @Test
    public void correctNameTest() {
        String name = "Mateusz";
        Validator.validateName(name);
    }

    @Test(expected = ValidationException.class)
    public void lowerCaseSurnameTest() {
        String surname = "mateusz";
        Validator.validateSurname(surname);
    }

    @Test(expected = ValidationException.class)
    public void tooShortSurnameTest() {
        String surname = "A";
        Validator.validateSurname(surname);
    }

    @Test
    public void correctSurnameTest() {
        String surname = "Mateusz";
        Validator.validateSurname(surname);
    }

    @Test(expected = ValidationException.class)
    public void noLettersBeforeAtTest() {
        String mail = "@cos.pl";
        Validator.validateMail(mail);
    }

    @Test(expected = ValidationException.class)
    public void otherAtSeparatorTest() {
        String mail = "cos+cos.pl";
        Validator.validateMail(mail);
    }

    @Test(expected = ValidationException.class)
    public void noLettersAfterAtTest() {
        String mail = "cos@.pl";
        Validator.validateMail(mail);
    }

    @Test(expected = ValidationException.class)
    public void otherDotSeparatorTest() {
        String mail = "cos@cos*pl";
        Validator.validateMail(mail);
    }

    @Test(expected = ValidationException.class)
    public void tooManyLettersAfterDotTest() {
        String mail = "cos@cos.plasd";
        Validator.validateMail(mail);
    }

    @Test
    public void correctMailTest() {
        String mail = "cos@gmail.com";
        Validator.validateMail(mail);
    }

    @Test(expected = ValidationException.class)
    public void notEqualPasswordsTest() {
        String pass1 = "admin";
        String pass2 = "admin2";
        Validator.validatePasswordsEquality(pass1, pass2);
    }

    @Test
    public void equalPasswordsTest() {
        String pass1 = "admin";
        String pass2 = "admin";
        Validator.validatePasswordsEquality(pass1, pass2);
    }
}
