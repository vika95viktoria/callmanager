package com.leveris.call.manager.validation;

import java.util.regex.Pattern;

import com.leveris.call.manager.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CallValidator {
    private static final String VALID_SYMBOLS_REGEX = "[+]?[0-9()-]+";
    private static final String VALID_PHONE_REGEX = "[0-9]{9,14}";
    
    public void validatePhone(String phone) {
        phone = phone.replaceAll("\\s", "");
        Pattern validSymbolsPattern = Pattern.compile(VALID_SYMBOLS_REGEX);
        if(!validSymbolsPattern.matcher(phone).matches()) {
            throw new ValidationException("Invalid symbols present");
        }
        int openBracketCount = StringUtils.countMatches(phone, "(");
        int closeBracketCount = StringUtils.countMatches(phone, ")");
        if(openBracketCount != closeBracketCount) {
            throw new ValidationException("Not equal number of open and close brackets");
        }
        phone = phone.replaceAll("[-()+]","");
        Pattern validPhonePattern = Pattern.compile(VALID_PHONE_REGEX);
        if(!validPhonePattern.matcher(phone).matches()) {
            throw new ValidationException("Invalid number of digital characters: " + phone.length() +
                    ". Should be between 9 and 14");
        }
    }
}
