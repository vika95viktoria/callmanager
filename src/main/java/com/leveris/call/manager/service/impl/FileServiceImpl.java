package com.leveris.call.manager.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.leveris.call.manager.exception.ServiceException;
import com.leveris.call.manager.model.CallTO;
import com.leveris.call.manager.service.FileService;
import com.leveris.call.manager.service.FormatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final String DIRECTORY = "D://callmanager/callinfo/";

    private FormatService formatService;

    @Autowired
    public FileServiceImpl(FormatService formatService) {
        this.formatService = formatService;
    }

    public void writeCallToFile(String fileName, CallTO callTO) {

        try(FileWriter fileWriter = new FileWriter(DIRECTORY + fileName + ".txt", true)) {
            fileWriter.write(String.format("%s %10s \r\n",
                    formatService.formatPhone(callTO.getTelephone()),
                    new SimpleDateFormat("HH:mm:ss").format(new Date())));
        } catch (IOException e) {
            log.error("Error during write to file", e);
            throw new ServiceException("Error during write to file" + e.getMessage());
        }
    }
}
