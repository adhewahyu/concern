package com.data.civil.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "persons", indexes = {
        @Index(name = "PERSON_IDX_0", columnList = "id_number")
})
public class Person extends BaseEntity{

    @Id
    @SequenceGenerator(name = "seq_person", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_person")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "id_number", length = 20)
    private String idNumber;

    @Column(name = "family_id_number", length = 20)
    private String familyIdNumber;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "blood_type", length = 2)
    private String bloodType;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "home_rt", length = 3)
    private String homeRt;

    @Column(name = "home_rw", length = 3)
    private String homeRw;

    @Column(name = "home_district")
    private String homeDistrict;

    @Column(name = "home_subdistrict")
    private String homeSubdistrict;

    @Column(name = "religion", length = 20)
    private String religion;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "nationality")
    private String nationality;

}
