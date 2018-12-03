package com.leveris.call.manager.service.impl;

import com.leveris.call.manager.service.FormatService;
import org.springframework.stereotype.Service;

@Service
public class FormatServiceImpl implements FormatService {

    private static final String DEFAULT_INTERNATIONAL_AREA = "420";
    private static final int TELEPHONE_LENGTH_WITH_INTERNATIONAL_CODE = 12;
    private static final int INTERNATIONAL_CODE_LENGTH = 3;
    private static final String PRECEDING_ZEROES = "00";

    @Override
    public String formatPhone(String phone) {
        phone = phone.replaceAll("[-()+\\s]","");
        String compulsoryPart = phone.substring(phone.length() - 9);
        String international = DEFAULT_INTERNATIONAL_AREA;
        if(phone.length() >= TELEPHONE_LENGTH_WITH_INTERNATIONAL_CODE) {
            int internationalCodeStart = phone.length() - TELEPHONE_LENGTH_WITH_INTERNATIONAL_CODE;
            international = phone.substring(internationalCodeStart,
                    INTERNATIONAL_CODE_LENGTH + internationalCodeStart);
        }
        StringBuilder uniformPhone = new StringBuilder(PRECEDING_ZEROES);
        uniformPhone.append(international);
        uniformPhone.append(compulsoryPart);
        return uniformPhone.toString();
    }
}
