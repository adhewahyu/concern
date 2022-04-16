package com.data.civil.service;

import com.data.civil.model.request.SaveCommLogRequest;
import com.data.civil.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PushCommLogService {

    private final JmsTemplate jmsTemplate;

    public void execute(SaveCommLogRequest input){
        doValidateRequest(input);
        jmsTemplate.convertAndSend(Constants.MQ_SEND_COMMLOG, input);
    }

    private void doValidateRequest(SaveCommLogRequest input){
        if(ObjectUtils.isEmpty(input.getCreateDate())){
            log.error(Constants.ERR_MSG_BAD_REQ_CREATE_DATE_REQUIRED);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERR_MSG_BAD_REQ_CREATE_DATE_REQUIRED);
        }
        if(StringUtils.isEmpty(input.getAccessFrom())){
            log.error(Constants.ERR_MSG_BAD_REQ_ACCESS_FROM_REQUIRED);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERR_MSG_BAD_REQ_ACCESS_FROM_REQUIRED);
        }
        if(StringUtils.isEmpty(input.getRequest())){
            log.error(Constants.ERR_MSG_BAD_REQ_REQUEST_REQUIRED);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERR_MSG_BAD_REQ_REQUEST_REQUIRED);
        }
    }

}
