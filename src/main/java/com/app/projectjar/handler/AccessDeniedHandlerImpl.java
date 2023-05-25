package com.app.projectjar.handler;

import com.app.projectjar.provider.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private static final String REDIRECT_URL = "/error/error403";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("접근 권한 없음");
//        UserDetail userDetail = (UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        response.sendError(403);
    }
}

























