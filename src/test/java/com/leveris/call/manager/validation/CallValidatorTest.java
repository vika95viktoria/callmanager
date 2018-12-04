package com.leveris.call.manager.validation;

import com.leveris.call.manager.exception.ValidationException;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.leveris.call.manager.validation.CallValidator.INVALID_SYMBOLS_ERROR;
import static com.leveris.call.manager.validation.CallValidator.LENGTH_BORDERS_ERROR;
import static com.leveris.call.manager.validation.CallValidator.UNEQUAL_BRACKETS_ERROR;
import static org.hamcrest.CoreMatchers.is;

@RunWith(JUnit4.class)
public class CallValidatorTest {
    private CallValidator callValidator = new CallValidator();

    private static final String VERY_LONG_TELEPHONE = "+123456789435435345435435435435345345345435";
    private static final String TELEPHONE_WITH_INVALID_SYMBOLS = "+123-456=789";
    private static final String TELEPHONE_WITH_UNEQUAL_NUMBER_OF_BRACKETS = "12(3456(78)9";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldValidateWithoutErrors() {
        callValidator.validatePhone("+(420) 111 222 333");
        callValidator.validatePhone("+(420)-111222333");
        callValidator.validatePhone("+420111222333");
        callValidator.validatePhone("00420111222333");
        callValidator.validatePhone("(111) 222 (333)");
        callValidator.validatePhone("123456789");
    }

    @Test
    public void shouldReturnValidationExceptionCauseOfInvalidSymbols() {
        thrown.expect(ValidationException.class);
        thrown.expectMessage(is(INVALID_SYMBOLS_ERROR));
        callValidator.validatePhone(TELEPHONE_WITH_INVALID_SYMBOLS);
    }

    @Test
    public void shouldReturnValidationExceptionCauseOfInvalidLength() {
        int expectedLength = VERY_LONG_TELEPHONE.replaceAll("[+()-]", "").length();
        thrown.expect(ValidationException.class);
        thrown.expectMessage(CoreMatchers.is(CallValidator.INVALID_LENGTH_ERROR + expectedLength + LENGTH_BORDERS_ERROR));
        callValidator.validatePhone(VERY_LONG_TELEPHONE);
    }

    @Test
    public void shouldReturnValidationExceptionCauseOfUnequalNumberOfBrackets() {
        thrown.expect(ValidationException.class);
        thrown.expectMessage(is(UNEQUAL_BRACKETS_ERROR));
        callValidator.validatePhone(TELEPHONE_WITH_UNEQUAL_NUMBER_OF_BRACKETS);
    }
}
