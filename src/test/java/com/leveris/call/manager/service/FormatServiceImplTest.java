package com.leveris.call.manager.service;

import com.leveris.call.manager.service.impl.FormatServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FormatServiceImplTest {

    private FormatServiceImpl formatService = new FormatServiceImpl();

    private static final String VALID_PHONE_WITHOUT_INTERNATIONAL = "369785124";
    private static final String VALID_PHONE_WITH_INTERNATIONAL = "375174569812";
    private static final String PRECEDING_ZEROES = "00";
    private static final String UNIFORM_INTERNATIONAL_AREA = "420";

    @Test
    public void shouldAddUniformInternationalArea() {
        String formattedPhone = formatService.formatPhone(VALID_PHONE_WITHOUT_INTERNATIONAL);
        String expectedPhone = PRECEDING_ZEROES + UNIFORM_INTERNATIONAL_AREA + VALID_PHONE_WITHOUT_INTERNATIONAL;
        assertEquals(expectedPhone, formattedPhone);
    }

    @Test
    public void shouldSaveWithOwnInternationalArea() {
        String formattedPhone = formatService.formatPhone(VALID_PHONE_WITH_INTERNATIONAL);
        String expectedPhone = PRECEDING_ZEROES + VALID_PHONE_WITH_INTERNATIONAL;
        assertEquals(expectedPhone, formattedPhone);
    }
}
