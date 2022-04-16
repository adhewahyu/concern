package com.data.civil.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommLogRequest {

    private Date createDate;
    private String accessFrom;
    private String request;
    private String response;

}
