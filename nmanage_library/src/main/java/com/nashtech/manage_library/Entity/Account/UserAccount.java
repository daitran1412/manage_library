package com.nashtech.manage_library.Entity.Account;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nashtech.manage_library.Entity.Reader.User;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = CustomSerializer.class)
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // private Long id;

    @Column(name = "username")
    private String username; 

    @Column(name = "password")
    private String password;   

    @Column(name = "role", columnDefinition = "varchar(255) default 'USER'")
    private String role = "USER";

    @Column(name = "status", columnDefinition = "varchar(255) default 'active'")
    private String status = "active";

    // foreign key to user table
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token", columnDefinition = "varchar(255) default 'null'")
    private String token;

    public UserAccount orElseThrow(Object object) {
        return null;
    }

}
