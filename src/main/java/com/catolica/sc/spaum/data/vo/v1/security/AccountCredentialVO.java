package com.catolica.sc.spaum.data.vo.v1.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class AccountCredentialVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
