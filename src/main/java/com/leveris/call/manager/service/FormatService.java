package com.leveris.call.manager.service;

public interface FormatService {
    /**
     * Format phone to the uniform presentation: 00YYY XXX XXX XXX,
     * where the X block is the local part and the Y block is the international part.
     * If no international part is specified on input, it will be automatically saved
     * with the international area code 420.
     * @param phone
     * @return
     */
    String formatPhone(String phone);
}
