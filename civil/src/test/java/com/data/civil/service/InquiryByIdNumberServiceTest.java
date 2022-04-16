package com.data.civil.service;

import com.data.civil.model.entity.Person;
import com.data.civil.model.request.InquiryByIdNumberRequest;
import com.data.civil.model.response.PersonResponse;
import com.data.civil.repository.PersonRepository;
import com.data.civil.util.CommonUtility;
import com.data.civil.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InquiryByIdNumberServiceTest {

    @InjectMocks
    private InquiryByIdNumberService inquiryByIdNumberService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CommonUtility commonUtility;

    @Mock
    private PushCommLogService pushCommLogService;

    private String accessFrom = "accessFrom";
    private String idNumber = "idNumber";

    @Test
    void doTest_failedIdNumberRequired(){
        try{
            inquiryByIdNumberService.execute(InquiryByIdNumberRequest.builder()
                    .accessFrom(accessFrom)
                    .idNumber(null)
                    .build());
        }catch (ResponseStatusException e){
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            Assertions.assertEquals(Constants.ERR_MSG_BAD_REQ_ID_NUMBER_REQUIRED, e.getReason());
        }
    }

    @Test
    void doTest_failedAccessFromRequired(){
        try{
            inquiryByIdNumberService.execute(InquiryByIdNumberRequest.builder()
                    .accessFrom(null)
                    .idNumber(idNumber)
                    .build());
        }catch (ResponseStatusException e){
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            Assertions.assertEquals("Access From is required", e.getReason());
        }
    }

    @Test
    void doTest_failedDataNotFound(){
        Mockito.when(personRepository.findByIdNumber(idNumber)).thenReturn(Optional.empty());
        try{
            inquiryByIdNumberService.execute(InquiryByIdNumberRequest.builder()
                    .accessFrom(accessFrom)
                    .idNumber(idNumber)
                    .build());
        }catch (ResponseStatusException e){
            Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            Assertions.assertEquals(Constants.ERR_MSG_NOT_FOUND_PERSON, e.getReason());
        }
    }

    @Test
    void doTest_success(){
        Calendar cal = Calendar.getInstance();
        cal.set(1111, 11, 11);
        Person person = new Person();
        person.setIdNumber(idNumber);
        person.setFamilyIdNumber("123456");
        person.setFullname("yourname");
        person.setBirthDate(cal.getTime());
        PersonResponse expectedPersonResponse = PersonResponse.builder()
                .idNumber(idNumber)
                .familyIdNumber("123456")
                .fullname("yourname")
                .birthDate("11-11-1111")
                .build();
        Mockito.when(personRepository.findByIdNumber(idNumber)).thenReturn(Optional.of(person));
        Mockito.when(commonUtility.date2String(Mockito.any(), Mockito.anyString())).thenReturn("11-11-1111");
        Mockito.doNothing().when(pushCommLogService).execute(Mockito.any());
        PersonResponse personResponse = inquiryByIdNumberService.execute(InquiryByIdNumberRequest.builder()
                .idNumber(idNumber)
                .accessFrom(accessFrom)
                .build());
        Assertions.assertEquals(expectedPersonResponse, personResponse);
    }

}
