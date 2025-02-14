package link.ww.base.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * JsTicket
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:25
 */
@Slf4j
@Service
public class JsTicketManager extends BaseManager {

  public GetCorpTicketResponse getCorpTicket(String accessToken) {
    Assert.notNull(accessToken, "调用接口凭证不能为空");
    String uri = "/get_jsapi_ticket";
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", accessToken);
    return executeGet(uri, params, GetCorpTicketResponse.class);
  }

  public GetAgentTicketResponse getAgentTicket(String accessToken) {
    Assert.notNull(accessToken, "调用接口凭证不能为空");
    String uri = "/ticket/get";
    Map<String, Object> params = new HashMap<>();
    params.put("type", "agent_config");
    params.put("access_token", accessToken);
    return executeGet(uri, params, GetAgentTicketResponse.class);
  }

}
