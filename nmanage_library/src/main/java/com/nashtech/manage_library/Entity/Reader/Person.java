package com.nashtech.manage_library.Entity.Reader;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "date of birth")
    protected LocalDate dateOfBirth;

    @Column(name = "address")
    protected String address;

    public Person() {
    }

    public Person(String username, String dateOfBirth, String address) {
        this.username = username;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return dateOfBirth.toString();
    }
    
}
