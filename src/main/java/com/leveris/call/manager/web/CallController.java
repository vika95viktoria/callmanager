package com.leveris.call.manager.web;

import javax.validation.Valid;

import com.leveris.call.manager.model.CallTO;
import com.leveris.call.manager.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/calls")
public class CallController {

    private CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping
    public void addCall(@RequestBody @Valid CallTO callTO) {
        callService.saveCall(callTO);
    }
}
