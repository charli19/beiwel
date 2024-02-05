package com.beiwel.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccess implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Authentication authentication) {
    if (authentication != null && authentication.getDetails() != null) {
      try {
        httpServletRequest.getSession().invalidate();
        authentication.setAuthenticated(false);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
  }

}