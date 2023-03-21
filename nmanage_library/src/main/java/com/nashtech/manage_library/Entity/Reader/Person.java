package com.nashtech.manage_library.Entity.Reader;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "date of birth", nullable = false)
    protected Date dateOfBirth;

    @Column(name = "address", nullable = true)
    protected String address;
    
}
