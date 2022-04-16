package com.data.civil.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class CommonUtility {

    public String date2String(Date inputDate, String pattern){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(inputDate);
        }catch (Exception e){
            log.error("Failed to Convert Date to String");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERR_MSG_INTERNAL_ERROR);
        }
    }

}
