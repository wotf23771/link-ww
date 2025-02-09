package link.ww.third.service;

import link.common.utils.StringUtils;
import link.ww.base.BaseProperties;
import link.ww.third.ThirdProperties;
import link.ww.third.cache.ProviderTokenCache;
import link.ww.third.manager.GetProviderTokenResponse;
import link.ww.third.manager.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderTokenService {

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private ThirdProperties thirdProperties;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private ProviderTokenCache providerTokenCache;

  public String getToken() {
    String providerSecret = thirdProperties.getCommon().getProviderSecret();
    String corpId = baseProperties.getCorpId();
    String token = providerTokenCache.get(corpId);
    if (StringUtils.isNotBlank(token)) {
      return token;
    }
    GetProviderTokenResponse tokenResponse = serviceManager.getProviderToken(corpId, providerSecret);
    if (tokenResponse != null) {
      token = tokenResponse.getProviderAccessToken();
      if (StringUtils.isNotBlank(token)) {
        providerTokenCache.put(corpId, token);
        return token;
      }
    }
    return null;
  }

}
