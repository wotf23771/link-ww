package link.ww.third.service;

import link.ww.base.AuthorizeScope;
import link.ww.base.service.AuthorizeService;
import link.ww.third.ThirdProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ThirdAuthorizeService implements AuthorizeService, InitializingBean {

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
            "&usertype=member" +
            "&scope=%s",
        thirdProperties.getSuiteId(),
        redirectUri,
        state,
        scope.getScope());
  }

  @Override
  public String getWebAuthorizeUrl(String state) {
    // 调用重载方法，传入默认的scope值
    return getWebAuthorizeUrl(state, thirdProperties.getOauth2().getScope());
  }

  public String getWebAuthorizeUrl(String state, AuthorizeScope scope) {
    // 扫码授权需要scope参数
    // 前端传入完整的回调地址
    String redirectUri = state;

    return String.format(thirdProperties.getOauth2().getQrConnectUrl() +
            "?suite_id=%s" +
            "&redirect_uri=%s" +
            "&state=%s" +
            "&scope=%s",
        thirdProperties.getSuiteId(),
        redirectUri,
        state,
        scope.getScope());
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", ThirdAuthorizeService.class.getName());
  }

}
