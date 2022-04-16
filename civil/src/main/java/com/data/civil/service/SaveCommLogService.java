package com.data.civil.service;

import com.data.civil.model.entity.CommLog;
import com.data.civil.model.request.SaveCommLogRequest;
import com.data.civil.repository.CommLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveCommLogService {

    private final CommLogRepository commLogRepository;

    public void execute(SaveCommLogRequest input){
        CommLog commLog = new CommLog();
        BeanUtils.copyProperties(input, commLog);
        commLogRepository.save(commLog);
    }

}
