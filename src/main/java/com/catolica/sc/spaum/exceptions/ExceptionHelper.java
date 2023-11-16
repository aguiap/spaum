package com.catolica.sc.spaum.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ExceptionHelper {
    public static ApiException usernameOrPasswordInvalid() {
        return new ApiException(MessageCodeConstants.ERROR_USERNAME_OR_PASSWORD_INVALID, HttpStatus.UNAUTHORIZED);
    }

    public static ApiException duplicateCourse() {
        return new ApiException(MessageCodeConstants.ERROR_DUPLICATE_COURSE, HttpStatus.FORBIDDEN);
    }

}
