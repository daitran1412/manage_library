package com.nashtech.manage_library.dto.Reader;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonDto {

    protected Long id;
    protected String username;
    protected Date dateOfbirth;
    protected String address;

}
