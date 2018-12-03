package com.leveris.call.manager.service;

import com.leveris.call.manager.model.CallTO;

public interface FileService {
    void writeCallToFile(String fileName, CallTO callTO);
}
