package com.nashtech.manage_library.dto.Publish;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    
}
