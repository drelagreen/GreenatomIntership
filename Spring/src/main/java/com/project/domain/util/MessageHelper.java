package com.project.domain.util;

import com.project.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User user) {
        return user !=null ? user.getUsername() : "<none>";
    }
}
