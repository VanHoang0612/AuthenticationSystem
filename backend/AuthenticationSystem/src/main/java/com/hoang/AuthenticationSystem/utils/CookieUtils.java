package com.hoang.AuthenticationSystem.utils;

import com.hoang.AuthenticationSystem.Exception.AppException;
import com.hoang.AuthenticationSystem.enums.ErrorCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtils {
    public static String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName()
                        .equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        throw new AppException(ErrorCode.NOT_FOUND_IN_COOKIES);

    }
}
