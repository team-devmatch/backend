package com.team03.project1.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtil {
    public static String getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH IN CONTROLLER: " + auth);
        return auth.getName(); // 보통 email 또는 username
    }
}
