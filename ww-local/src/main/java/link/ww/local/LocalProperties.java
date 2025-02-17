package link.ww.local;

import link.ww.base.AuthorizeScope;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 企业微信应用配置属性
 *
 * @author wangxiaolei
 * @since 2025/2/14 17:28
 */
@Slf4j
@Data
@Component("wwLocalProperties")
@ConfigurationProperties(prefix = LocalConstant.CONFIG_PREFIX)
public class LocalProperties implements InitializingBean {

  /**
   * 回调接口前缀
   * 用于接收企业微信的回调请求
   */
  private String callbackPrefix;

  /**
   * 应用配置映射
   * key: 应用标识
   * value: 应用配置信息
   */
  private Map<String, Agent> agents;

  /**
   * OAuth2授权配置
   */
  private Auth auth;

  @Data
  public static class Auth {

    /**
     * 应用授权作用域
     * BASE: 静默授权，可获取成员基础信息
     * PRIVATE_INFO: 手动授权，可获取成员详细信息
     */
    private AuthorizeScope scope = AuthorizeScope.BASE;

    /**
     * 企业微信OAuth2授权地址<br/>
     * https://wecom.work/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&agentid=AGENTID&state=STATE#wechat_redirect
     */
    private String authorizeUrl;

    private String requestAuthorizeUri;

    /**
     * web登录方式<br/>
     * https://管理端域名/wework_admin/new_web_login/login_panel?appid=APPID&redirect_uri=REDIRECT_URI&state=STATE&agentid=xxx
     */
    private String webAuthorizeUrl;

    private String requestWebAuthorizeUri;

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("callbackPrefix:{}", callbackPrefix);
    log.debug("agents:{}", agents);
    if (auth != null) {
      log.debug("auth.scope:{}", auth.getScope());
      log.debug("auth.authorizeUrl:{}", auth.getAuthorizeUrl());
      log.debug("auth.webAuthorizeUrl:{}", auth.getWebAuthorizeUrl());
    }
  }

}
