package link.ww.third.service;

import link.ww.base.AuthorizeScope;
import link.ww.base.BaseProperties;
import link.ww.base.service.AuthorizeService;
import link.ww.third.ThirdProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 企业微信第三方应用OAuth2认证服务实现
 * 处理企业微信第三方应用的网页授权
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "link.ww.base.agent-type", havingValue = "third")
public class ThirdAuthorizeService implements AuthorizeService, InitializingBean {

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private ThirdProperties thirdProperties;

  @Override
  public String getAuthorizeUrl(String state) {
    return getAuthorizeUrl(state, thirdProperties.getOauth2().getScope());
  }

  /**
   * 获取企业微信第三方应用OAuth2授权链接
   *
   * @param state 重定向后会带上state参数
   * @param scope 授权作用域
   * @return 授权链接
   */
  public String getAuthorizeUrl(String state, AuthorizeScope scope) {
    // 前端传入完整的回调地址
    String redirectUri = state;
    
    return String.format(thirdProperties.getOauth2().getAuthorizeUrl() +
            "?appid=%s" +
            "&redirect_uri=%s" +
            "&state=%s" +
            "&usertype=member",
        thirdProperties.getSuiteId(),
        redirectUri,
        state);
  }

  @Override
  public String getWebAuthorizeUrl(String state) {
    return getWebAuthorizeUrl(state, thirdProperties.getOauth2().getScope());
  }

  /**
   * 获取企业微信第三方应用扫码授权链接
   *
   * @param state 重定向后会带上state参数
   * @param scope 授权作用域
   * @return 授权链接
   */
  public String getWebAuthorizeUrl(String state, AuthorizeScope scope) {
    // 前端传入完整的回调地址
    String redirectUri = state;
    
    return String.format(thirdProperties.getOauth2().getQrConnectUrl() +
            "?suite_id=%s" +
            "&redirect_uri=%s" +
            "&state=%s",
        thirdProperties.getSuiteId(),
        redirectUri,
        state);
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", ThirdAuthorizeService.class.getName());
  }
}
