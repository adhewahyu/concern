package com.data.civil.controller;

import com.data.civil.model.request.InquiryByIdNumberRequest;
import com.data.civil.model.response.PersonResponse;
import com.data.civil.model.response.RestResponse;
import com.data.civil.service.InquiryByIdNumberService;
import com.data.civil.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = Constants.API_TAGS_PERSON)
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final InquiryByIdNumberService inquiryByIdNumberService;

    @ApiOperation(value = "Inquiry By ID Number", notes = "API to find person data by ID Number")
    @PostMapping("/v1/inquiry-by-id-number")
    public ResponseEntity<RestResponse> inquiryByIdNumber(@RequestBody InquiryByIdNumberRequest inquiryByIdNumberRequest){
        PersonResponse personResponse = inquiryByIdNumberService.execute(inquiryByIdNumberRequest);
        return new ResponseEntity<>(new RestResponse(personResponse, Constants.MSG_DATA_FOUND , true), HttpStatus.OK);
    }

}
