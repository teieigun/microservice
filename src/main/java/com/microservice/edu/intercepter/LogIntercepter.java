package com.microservice.edu.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microservice.edu.util.LogUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogIntercepter implements HandlerInterceptor {

  /** 処理前 */
  @Override
  public boolean preHandle(
      final HttpServletRequest request, final HttpServletResponse response, final Object handler)
      throws Exception {

    // ログ情報設定
    LogUtil.setLogInfo(request, request.getRequestedSessionId());

    return true;
  }
}
