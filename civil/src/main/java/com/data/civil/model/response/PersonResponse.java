package com.data.civil.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private String idNumber;
    private String familyIdNumber;
    private String fullname;
    private String birthPlace;
    private String birthDate;
    private String gender;
    private String bloodType;
    private String homeAddress;
    private String homeRt;
    private String homeRw;
    private String homeDistrict;
    private String homeSubdistrict;
    private String religion;
    private String maritalStatus;
    private String occupation;
    private String nationality;

}
