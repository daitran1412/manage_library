package com.nashtech.manage_library.dto.Reader;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public abstract class PersonDto {

    protected Long id;
    protected String username;
    protected int gender;
    protected LocalDate dateOfbirth;
    protected String address;

    public PersonDto() {
    }

    public PersonDto(Long id, String username, int gender, String dateOfbirth, String address) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.dateOfbirth = LocalDate.parse(dateOfbirth);
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = LocalDate.parse(dateOfbirth);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(int gender){
        this.gender = gender;
    }

}
