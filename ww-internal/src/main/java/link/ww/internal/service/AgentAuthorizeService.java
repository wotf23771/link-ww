package link.ww.internal.service;

import link.common.exception.BaseException;
import link.ww.base.AuthorizeScope;
import link.ww.base.BaseProperties;
import link.ww.base.service.AuthorizeService;
import link.ww.internal.Agent;
import link.ww.internal.AgentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

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

  @Autowired
  private AgentService agentService;

  @Override
  public String getAuthorizeUrl(String redirectUrl, String state) {
    return getAuthorizeUrl(redirectUrl, state, AuthorizeScope.BASE);
  }

  /**
   * 获取企业微信OAuth2授权链接
   *
   * @param state 重定向后会带上state参数
   * @param scope 授权作用域
   * @return 授权链接
   */
  public String getAuthorizeUrl(String redirectUrl, String state, AuthorizeScope scope) {
    String url = agentProperties.getAuth().getAuthorizeUrl();
    DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(url);
    URI uri = uriBuilderFactory.builder()
        .queryParam("appid", baseProperties.getCorpId())
        .queryParam("redirect_uri", redirectUrl)
        .queryParam("response_type", "code")
        .queryParam("scope", scope.getScope())
        .queryParam("state", state)
        .build();
    return uri + "#wechat_redirect";
  }

  /**
   * 获取企业微信Web授权链接
   *
   * @param state 重定向后会带上state参数
   * @return 授权链接
   */
  @Override
  public String getWebAuthorizeUrl(String redirectUrl, String state) {
    Agent agent = agentService.getAgentByName(baseProperties.getDefaultAgent());
    if (agent == null) {
      throw new BaseException("找不到agent");
    }
    String url = agentProperties.getAuth().getWebAuthorizeUrl();
    DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(url);
    URI uri = uriBuilderFactory.builder()
        .queryParam("appid", baseProperties.getCorpId())
        .queryParam("redirect_uri", redirectUrl)
        .queryParam("agentid", agent.getAgentId())
        .queryParam("login_type", "CorpApp")
        .queryParam("state", state)
        .build();
    return uri.toString();
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", AgentAuthorizeService.class.getName());
  }

}
