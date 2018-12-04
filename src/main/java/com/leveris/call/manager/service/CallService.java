package com.leveris.call.manager.service;

import com.leveris.call.manager.model.dto.CallTO;

public interface CallService {
    /**
     * Save call with valid telephone number to .txt file named as LASTNAME_FIRSTNAME.txt
     * according to the information in callTO.
     * @param callTO
     * @return
     */
    CallTO saveCall(CallTO callTO);
}
