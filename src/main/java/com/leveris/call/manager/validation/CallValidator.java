package com.leveris.call.manager.validation;

import java.util.regex.Pattern;

import com.leveris.call.manager.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CallValidator {
    private static final String VALID_SYMBOLS_REGEX = "[+]?[0-9()-]+";
    private static final String VALID_PHONE_REGEX = "[0-9]{9,14}";

    public static final String INVALID_SYMBOLS_ERROR = "Invalid symbols present";
    public static final String UNEQUAL_BRACKETS_ERROR = "Invalid symbols present";
    public static final String INVALID_LENGTH_ERROR = "Invalid number of digital characters: ";
    public static final String LENGTH_BORDERS_ERROR = ". Should be between 9 and 14";
    
    public void validatePhone(String phone) {
        phone = phone.replaceAll("\\s", "");
        Pattern validSymbolsPattern = Pattern.compile(VALID_SYMBOLS_REGEX);
        if(!validSymbolsPattern.matcher(phone).matches()) {
            throw new ValidationException(INVALID_SYMBOLS_ERROR);
        }
        int openBracketCount = StringUtils.countMatches(phone, "(");
        int closeBracketCount = StringUtils.countMatches(phone, ")");
        if(openBracketCount != closeBracketCount) {
            throw new ValidationException(UNEQUAL_BRACKETS_ERROR);
        }
        phone = phone.replaceAll("[-()+]","");
        Pattern validPhonePattern = Pattern.compile(VALID_PHONE_REGEX);
        if(!validPhonePattern.matcher(phone).matches()) {
            throw new ValidationException(INVALID_LENGTH_ERROR + phone.length() + LENGTH_BORDERS_ERROR);
        }
    }
}
