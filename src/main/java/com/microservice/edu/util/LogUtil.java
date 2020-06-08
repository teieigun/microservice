package com.microservice.edu.util;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


public class LogUtil {
  protected static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

  /** ログキー：セッションID */
  private static final String LOG_KEY_SESSION_ID = "sessionId";

  /** ログキー：URL */
  private static final String LOG_KEY_URL = "url";

  /** 画面以外のパス */
  private static final String[] NOT_SCREEN_PATH = {"/img/common/", "/webpack-bundled/"};

  public static void info(String msg) {
    logger.info(msg);
  }

  public static void warn(String msg) {
    logger.warn(msg);
  }

  public static void error(String msg) {
    logger.error("【ERROR】" + msg);
  }

  public static void fatal(String msg) {
    logger.error("【FATAL】" + msg);
  }

  public static void debug(String msg) {
    logger.debug(msg);
  }

  public static void trace(String msg) {
    logger.trace(msg);
  }

  public static void info(String msg, Throwable t) {
    logger.info(msg, t);
  }

  public static void warn(String msg, Throwable t) {
    logger.warn(msg, t);
  }

  public static void error(String msg, Throwable t) {
    logger.error("【ERROR】" + msg, t);
  }

  public static void fatal(String msg, Throwable t) {
    logger.error("【FATAL】" + msg, t);
  }

  public static void debug(String msg, Throwable t) {
    logger.debug(msg, t);
  }

  public static void trace(String msg, Throwable t) {
    logger.trace(msg, t);
  }

  /** リクエスト情報を元に設定 */
  public static void setLogInfo(final HttpServletRequest request, String sessionID) {

    /** セッションID */
    try {
      MDC.put(LOG_KEY_SESSION_ID, sessionID);

    } catch (NullPointerException e) {
    }

    try {

      /** 画面以外なら空 */
      String uri = request.getRequestURI();

      // uriがあるかどうか
      if (uri != null) {

        // 画面以外のパスなら終了
        for (String path : NOT_SCREEN_PATH) {

          if (uri.length() >= path.length() && uri.substring(0, path.length()).equals(path)) {

            return;
          }
        }
      }

      /** URL */
      // リクエスト方法
      String method = request.getMethod();

      // 結果文字列
      String resultStr = "キー：";

      // GET
      if ("GET".equals(method)) {

        // URLとパラメーターを取得
        resultStr += request.getRequestURL();

        String queryString = request.getQueryString();

        if (queryString != null) {

          resultStr += "?" + queryString;
        }

        // POST
      } else if ("POST".equals(method)) {

        // 遷移元のURLを取得
        resultStr += request.getHeader("REFERER");

        // それ以外
      } else {
        return;
      }

      // 設定
      MDC.put(LOG_KEY_URL, resultStr);

    } catch (Exception e) {
    }
  }
}
