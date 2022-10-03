package com.example.demo.users.authenDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponeJwt {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponeJwt(String accessToken) {
        this.accessToken = accessToken;
    }
}
