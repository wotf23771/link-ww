package link.ww.agent;

import link.ww.base.AuthorizeScope;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 企业微信应用配置属性
 * 用于配置企业微信应用的回调地址和应用信息
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Data
@Component("agentProperties")
@ConfigurationProperties(prefix = AgentConstant.CONFIG_PREFIX)
public class AgentProperties implements InitializingBean {

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
  private OAuth2 oauth2;

  @Data
  public static class OAuth2 {

    /**
     * 应用授权作用域
     * BASE: 静默授权，可获取成员基础信息
     * PRIVATE_INFO: 手动授权，可获取成员详细信息
     */
    private AuthorizeScope scope = AuthorizeScope.BASE;

    /**
     * 企业微信OAuth2授权地址
     */
    private String authorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /**
     * 企业微信扫码授权地址
     */
    private String qrConnectUrl = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect";

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("callbackPrefix:{}", callbackPrefix);
    log.debug("agents:{}", agents);
    if (oauth2 != null) {
      log.debug("oauth2.scope:{}", oauth2.getScope());
      log.debug("oauth2.authorizeUrl:{}", oauth2.getAuthorizeUrl());
      log.debug("oauth2.qrConnectUrl:{}", oauth2.getQrConnectUrl());
    }
  }

}
