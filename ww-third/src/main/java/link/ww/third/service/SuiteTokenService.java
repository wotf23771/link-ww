package link.ww.third.service;

import link.common.utils.StringUtils;
import link.ww.third.ThirdProperties;
import link.ww.third.cache.SuiteTokenCache;
import link.ww.third.manager.GetSuiteTokenResponse;
import link.ww.third.manager.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 第三方应用凭证服务
 *
 * @author wangxiaolei
 * @since 2025/2/9 21:55
 */
@Service
public class SuiteTokenService {

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private ThirdProperties thirdProperties;

  @Autowired
  private SuiteTokenCache suiteTokenCache;

  @Autowired
  private SuiteTicketService suiteTicketService;

  public String getToken() {
    String suiteId = thirdProperties.getSuiteId();
    String token = suiteTokenCache.get(suiteId);
    if (StringUtils.isNotBlank(token)) {
      return token;
    }
    String secret = thirdProperties.getSecret();
    String ticket = suiteTicketService.getTicket();
    GetSuiteTokenResponse tokenResponse = serviceManager.getSuiteToken(suiteId, secret, ticket);
    if (tokenResponse != null) {
      token = tokenResponse.getSuiteAccessToken();
      if (StringUtils.isNotBlank(token)) {
        suiteTokenCache.put(suiteId, token);
        return token;
      }
    }
    return null;
  }

}
