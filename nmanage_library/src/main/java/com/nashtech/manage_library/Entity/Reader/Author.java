package com.nashtech.manage_library.Entity.Reader;

import java.util.Set;

import com.nashtech.manage_library.Entity.ListBook.Book;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "author")
public class Author extends Person {
    
    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "verification", columnDefinition = "boolean default false")
    private boolean verification;

    @ManyToMany(mappedBy = "author")
    private Set<Book> book;

    public Author(String username, String dateOfBirth, String address, String phone) {
        super(username, dateOfBirth, address);
        this.phone = phone;
        this.verification = false;
    }

    public void setUsername(String username) {
        super.setUsername(username);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVerification() {
        if (this.username != null && this.dateOfBirth.toString() != null && this.address != null) {
            this.verification = true;
        }
    }

}