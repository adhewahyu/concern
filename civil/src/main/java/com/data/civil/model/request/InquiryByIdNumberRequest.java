package com.data.civil.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryByIdNumberRequest {

    @ApiModelProperty(name = "access from ", example = "BUMN")
    private String accessFrom;

    @ApiModelProperty(name = "Your ID Number (NIK)", example = "3322021515150001")
    private String idNumber;

}
