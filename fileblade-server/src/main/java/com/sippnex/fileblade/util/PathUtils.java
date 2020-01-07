package com.sippnex.fileblade.util;

import javax.servlet.http.HttpServletRequest;

public class PathUtils {

    public static String getPathFromRequest(HttpServletRequest request) {
        String[] pathElements = request.getRequestURI().split(request.getContextPath() + "/");
        StringBuilder pathString = new StringBuilder("/");
        for (int i = 3; i < pathElements.length; i++) {
            pathString.append(pathElements[i]);
            if(i<pathElements.length - 1) pathString.append("/");
        }
        return pathString.toString();
    }

}
