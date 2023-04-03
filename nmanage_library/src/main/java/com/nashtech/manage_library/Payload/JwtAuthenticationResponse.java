package com.nashtech.manage_library.Payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

}
