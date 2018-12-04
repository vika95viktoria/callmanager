package com.leveris.call.manager.service;

import com.leveris.call.manager.model.dto.CallTO;

public interface FileService {
    /**
     * Write call information to file with fileName.
     * If such file has already existed, append new information about the call.
     * If no such file is present in the folder - create new
     * @param fileName
     * @param callTO
     */
    void writeCallToFile(String fileName, CallTO callTO);
}
