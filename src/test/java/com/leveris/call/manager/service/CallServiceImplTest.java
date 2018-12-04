package com.leveris.call.manager.service;

import com.leveris.call.manager.exception.ValidationException;
import com.leveris.call.manager.model.dto.CallTO;
import com.leveris.call.manager.service.impl.CallServiceImpl;
import com.leveris.call.manager.validation.CallValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallServiceImplTest {

    private static final String PHONE = "PHONE";
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String UNDERSCORE = "_";

    @Mock
    private CallValidator callValidator;
    @Mock
    private FileService fileService;
    @InjectMocks
    private CallServiceImpl callService;

    @Mock
    private CallTO callTO;

    @Before
    public void setUp() {
        when(callTO.getTelephone()).thenReturn(PHONE);
        when(callTO.getFirstName()).thenReturn(FIRSTNAME);
        when(callTO.getLastName()).thenReturn(LASTNAME);
    }

    @Test
    public void shouldCallValidatorAndFileWriter() {
        callService.saveCall(callTO);
        verify(callValidator).validatePhone(PHONE);
        verify(fileService).writeCallToFile(LASTNAME + UNDERSCORE + FIRSTNAME, callTO);
    }

    @Test(expected = ValidationException.class)
    public void shouldCallOnlyValidatorr() {
        Mockito.doThrow(ValidationException.class).when(callValidator).validatePhone(PHONE);
        callService.saveCall(callTO);
        verify(callValidator).validatePhone(PHONE);
        verify(fileService, never()).writeCallToFile(any(String.class), any(CallTO.class));
    }
}
