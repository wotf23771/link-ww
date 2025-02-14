package link.ww.third.service;

import link.common.context.UserContextHolder;
import link.common.utils.StringUtils;
import link.ww.base.manager.GetAgentTicketResponse;
import link.ww.base.manager.GetCorpTicketResponse;
import link.ww.base.manager.JsTicketManager;
import link.ww.base.service.AccessTokenService;
import link.ww.base.service.JsTicketService;
import link.ww.third.cache.AgentJsTicketCache;
import link.ww.third.cache.CorpJsTicketCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * JsTicket 服务，第三方应用实现类
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:43
 */
@Service
public class ThirdJsTicketService implements JsTicketService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private JsTicketManager jsTicketManager;

  @Autowired
  private AgentJsTicketCache agentJsTicketCache;

  @Autowired
  private CorpJsTicketCache corpJsTicketCache;

  @Override
  public String getCorpJsTicket() {
    String corpId = UserContextHolder.getUserAttrAsString("corpId");
    String ticket = corpJsTicketCache.get(corpId);
    if (StringUtils.isNotBlank(ticket)) {
      return ticket;
    }
    String accessToken = accessTokenService.getAccessToken();
    GetCorpTicketResponse response = jsTicketManager.getCorpTicket(accessToken);
    if (response != null) {
      ticket = response.getTicket();
      if (StringUtils.isNotBlank(ticket)) {
        corpJsTicketCache.put(corpId, ticket);
        return ticket;
      }
    }
    return null;
  }

  @Override
  public String getAgentJsTicket() {
    String corpId = UserContextHolder.getUserAttrAsString("corpId");
    String agentId = UserContextHolder.getUserAttrAsString("agentId");
    String ticket = agentJsTicketCache.get(corpId, agentId);
    if (StringUtils.isNotBlank(ticket)) {
      return ticket;
    }
    String accessToken = accessTokenService.getAccessToken();
    GetAgentTicketResponse response = jsTicketManager.getAgentTicket(accessToken);
    if (response != null) {
      ticket = response.getTicket();
      if (StringUtils.isNotBlank(ticket)) {
        agentJsTicketCache.put(corpId, agentId, ticket);
        return ticket;
      }
    }
    return null;
  }

}
