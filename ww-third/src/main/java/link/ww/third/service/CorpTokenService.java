package link.ww.third.service;

import link.common.context.UserContextHolder;
import link.common.utils.StringUtils;
import link.ww.base.service.AccessTokenService;
import link.ww.third.cache.CorpTokenCache;
import link.ww.third.domain.entity.ThirdCorp;
import link.ww.third.manager.GetCorpTokenResponse;
import link.ww.third.manager.ServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CorpTokenService implements AccessTokenService, InitializingBean {

  @Autowired
  private CorpTokenCache corpTokenCache;

  @Autowired
  private SuiteTokenService suiteTokenService;

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private ThirdCorpService thirdCorpService;

  @Override
  public String getAccessToken() {
    String corpId = UserContextHolder.getUserAttrAsString("corpId");
    if (StringUtils.isBlank(corpId)) {
      return null;
    }
    String token = corpTokenCache.get(corpId);
    if (StringUtils.isNotBlank(token)) {
      return token;
    }
    String suiteAccessToken = suiteTokenService.getToken();
    if (StringUtils.isBlank(suiteAccessToken)) {
      return null;
    }

    ThirdCorp thirdCorp = thirdCorpService.get(corpId);
    if (thirdCorp == null) {
      return null;
    }
    String permanentCode = thirdCorp.getPermanentCode();
    if (StringUtils.isBlank(permanentCode)) {
      return null;
    }
    GetCorpTokenResponse response = serviceManager.getCorpToken(suiteAccessToken, corpId, permanentCode);
    if (response != null) {
      token = response.getAccessToken();
      corpTokenCache.put(corpId, token);
      return token;
    }
    return "";
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AccessTokenService is [{}]", CorpTokenService.class.getName());
  }

}
