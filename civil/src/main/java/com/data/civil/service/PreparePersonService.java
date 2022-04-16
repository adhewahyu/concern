package com.data.civil.service;

import com.data.civil.model.entity.Person;
import com.data.civil.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class PreparePersonService {

    private final PersonRepository personRepository;

    public void execute(){
        personRepository.findByIdNumber("3322021515150001").ifPresentOrElse(person->{
            log.info("Data sample already Exists");
        },()->{
            Person person = new Person();
            person.setIdNumber("3322021515150001");
            person.setFamilyIdNumber("4433021515150001");
            person.setFullname("Adhe Wahyu Ardanto");
            person.setBirthPlace("Place of Birth");
            person.setBirthDate(new Date());
            person.setGender("M");
            person.setBloodType("B");
            person.setHomeAddress("Alamat lengkap");
            person.setHomeRt("001");
            person.setHomeRw("001");
            person.setHomeDistrict("District");
            person.setHomeSubdistrict("Subdistrict");
            person.setReligion("Religion");
            person.setMaritalStatus("Married");
            person.setOccupation("Occupation");
            person.setNationality("Indonesia");
            personRepository.save(person);
        });
    }

}
