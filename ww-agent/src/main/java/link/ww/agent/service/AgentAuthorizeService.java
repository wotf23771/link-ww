package link.ww.agent.service;

import link.ww.agent.AgentProperties;
import link.ww.base.AuthorizeScope;
import link.ww.base.BaseProperties;
import link.ww.base.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业微信自建应用OAuth2认证服务实现
 * 处理企业微信自建应用的网页授权
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Service
public class AgentAuthorizeService implements AuthorizeService, InitializingBean {

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private AgentProperties agentProperties;

  @Override
  public String getAuthorizeUrl(String state) {
    return getAuthorizeUrl(state, AuthorizeScope.BASE);
  }

  /**
   * 获取企业微信OAuth2授权链接
   *
   * @param state 重定向后会带上state参数
   * @param scope 授权作用域
   * @return 授权链接
   */
  public String getAuthorizeUrl(String state, AuthorizeScope scope) {
    // 前端传入完整的回调地址
    String redirectUri = state;

    return String.format(agentProperties.getOauth2().getAuthorizeUrl() +
            "?appid=%s" +
            "&redirect_uri=%s" +
            "&response_type=code" +
            "&scope=%s" +
            "&state=%s" +
            "#wechat_redirect",
        baseProperties.getCorpId(),
        redirectUri,
        scope.getScope(),
        state);
  }

  @Override
  public String getWebAuthorizeUrl(String state) {
    return getWebAuthorizeUrl(state, AuthorizeScope.PRIVATE_INFO);
  }

  /**
   * 获取企业微信OAuth2开放式授权链接
   *
   * @param state 重定向后会带上state参数
   * @param scope 授权作用域
   * @return 授权链接
   */
  public String getWebAuthorizeUrl(String state, AuthorizeScope scope) {
    // 前端传入完整的回调地址
    String redirectUri = state;
    String appId = baseProperties.getDefaultAgent();

    return String.format(agentProperties.getOauth2().getQrConnectUrl() +
            "?appid=%s" +
            "&agentid=%s" +
            "&redirect_uri=%s" +
            "&response_type=code" +
            "&scope=%s" +
            "&state=%s",
        baseProperties.getCorpId(),
        appId,
        redirectUri,
        scope.getScope(),
        state);
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", AgentAuthorizeService.class.getName());
  }

}
