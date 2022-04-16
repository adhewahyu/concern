package com.data.civil.service;

import com.alibaba.fastjson.JSON;
import com.data.civil.model.request.InquiryByIdNumberRequest;
import com.data.civil.model.request.SaveCommLogRequest;
import com.data.civil.model.response.PersonResponse;
import com.data.civil.repository.PersonRepository;
import com.data.civil.util.CommonUtility;
import com.data.civil.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquiryByIdNumberService {

    private final PersonRepository personRepository;
    private final CommonUtility commonUtility;
    private final PushCommLogService pushCommLogService;

    public PersonResponse execute(InquiryByIdNumberRequest input){
        doValidateRequest(input);
        AtomicReference<PersonResponse> personResponseAtomicReference = new AtomicReference<>();
        personRepository.findByIdNumber(input.getIdNumber()).ifPresentOrElse(person-> {
            personResponseAtomicReference.set(PersonResponse.builder()
                    .idNumber(person.getIdNumber())
                    .familyIdNumber(person.getFamilyIdNumber())
                    .fullname(person.getFullname())
                    .birthPlace(person.getBirthPlace())
                    .birthDate(commonUtility.date2String(person.getBirthDate(), "dd-MM-yyyy"))
                    .gender(person.getGender())
                    .bloodType(person.getBloodType())
                    .homeAddress(person.getHomeAddress())
                    .homeRt(person.getHomeRt())
                    .homeRw(person.getHomeRw())
                    .homeDistrict(person.getHomeDistrict())
                    .homeSubdistrict(person.getHomeSubdistrict())
                    .religion(person.getReligion())
                    .maritalStatus(person.getMaritalStatus())
                    .occupation(person.getOccupation())
                    .nationality(person.getNationality())
                    .build());
            pushCommLogService.execute(SaveCommLogRequest.builder()
                    .createDate(new Date())
                    .accessFrom(input.getAccessFrom())
                    .request(JSON.toJSONString(input))
                    .response(JSON.toJSONString(personResponseAtomicReference.get()))
                    .build());
        },()->{
            log.error(Constants.ERR_MSG_NOT_FOUND_PERSON);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.ERR_MSG_NOT_FOUND_PERSON);
        });
        return personResponseAtomicReference.get();
    }

    private void doValidateRequest(InquiryByIdNumberRequest input){
        if(StringUtils.isEmpty(input.getIdNumber())){
            log.error(Constants.ERR_MSG_BAD_REQ_ID_NUMBER_REQUIRED);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERR_MSG_BAD_REQ_ID_NUMBER_REQUIRED);
        }
        if(StringUtils.isEmpty(input.getAccessFrom())){
            log.error(Constants.ERR_MSG_BAD_REQ_ACCESS_FROM_REQUIRED);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERR_MSG_BAD_REQ_ACCESS_FROM_REQUIRED);
        }
    }

}
