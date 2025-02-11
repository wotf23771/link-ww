package link.ww.third;

import link.ww.base.AuthorizeScope;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("thirdProperties")
@ConfigurationProperties(prefix = ThirdConstant.CONFIG_PREFIX)
public class ThirdProperties implements InitializingBean {

  private Common common;

  private String suiteId;

  private String secret;

  private String token;

  private String encodingAesKey;

  private String callbackPrefix;

  /**
   * OAuth2授权配置
   */
  private OAuth2 oauth2;

  @Data
  public static class Common {

    private String providerSecret;

    private String token;

    private String encodingAesKey;

  }

  @Data
  public static class OAuth2 {

    /**
     * 应用授权作用域
     * BASE: 静默授权，可获取成员基础信息
     * PRIVATE_INFO: 手动授权，可获取成员详细信息
     */
    private AuthorizeScope scope = AuthorizeScope.BASE;

    /**
     * 企业微信第三方应用OAuth2授权地址
     */
    private String authorizeUrl = "https://open.work.weixin.qq.com/wwopen/sso/3rd_qrConnect";

    /**
     * 企业微信第三方应用扫码授权地址
     */
    private String qrConnectUrl = "https://open.work.weixin.qq.com/wwopen/sso/3rd_qrConnect";

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (common != null) {
      log.debug("common.providerSecret:{}", common.getProviderSecret());
      log.debug("common.token:{}", common.getToken());
      log.debug("common.encodingAesKey:{}", common.getEncodingAesKey());
    }
    log.debug("suiteId:{}", suiteId);
    log.debug("secret:{}", secret);
    log.debug("token:{}", token);
    log.debug("encodingAesKey:{}", encodingAesKey);
    log.debug("callbackPrefix:{}", callbackPrefix);
    if (oauth2 != null) {
      log.debug("oauth2.scope:{}", oauth2.getScope());
      log.debug("oauth2.authorizeUrl:{}", oauth2.getAuthorizeUrl());
      log.debug("oauth2.qrConnectUrl:{}", oauth2.getQrConnectUrl());
    }
  }

}
