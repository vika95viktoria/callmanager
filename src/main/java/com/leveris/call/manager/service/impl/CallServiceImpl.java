package com.leveris.call.manager.service.impl;

import java.util.Optional;

import com.leveris.call.manager.model.dto.CallTO;
import com.leveris.call.manager.service.CallService;
import com.leveris.call.manager.service.FileService;
import com.leveris.call.manager.validation.CallValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallServiceImpl implements CallService {

    private CallValidator callValidator;
    private FileService fileService;

    @Autowired
    public CallServiceImpl(CallValidator callValidator, FileService fileService) {
        this.callValidator = callValidator;
        this.fileService = fileService;
    }

    @Override
    public CallTO saveCall(CallTO callTO) {
        callValidator.validatePhone(callTO.getTelephone());
        fileService.writeCallToFile(callTO.getLastName().toUpperCase() + "_" +
                    Optional.ofNullable(callTO.getFirstName()).map(String::toUpperCase).orElse(""), callTO);
        return callTO;
    }
}
