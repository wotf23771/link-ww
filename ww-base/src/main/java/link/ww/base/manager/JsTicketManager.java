package link.ww.base.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * JsTicket
 * 处理企业微信的 jsapi_ticket 获取
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:25
 */
@Slf4j
@Service
public class JsTicketManager extends BaseManager {

  /**
   * 获取企业的 jsapi_ticket
   *
   * @param accessToken 调用接口凭证，必须有效
   * @return GetCorpTicketResponse 包含企业的 jsapi_ticket 和有效期
   */
  public GetCorpTicketResponse getCorpTicket(String accessToken) {
    Assert.notNull(accessToken, "调用接口凭证不能为空"); // 确保 accessToken 不为空
    String uri = "/get_jsapi_ticket"; // API 接口路径
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", accessToken); // 将 accessToken 添加到请求参数中
    return executeGet(uri, params, GetCorpTicketResponse.class); // 执行 GET 请求并返回响应
  }

  /**
   * 获取应用的 jsapi_ticket
   *
   * @param accessToken 调用接口凭证，必须有效
   * @return GetAgentTicketResponse 包含应用的 jsapi_ticket 和有效期
   */
  public GetAgentTicketResponse getAgentTicket(String accessToken) {
    Assert.notNull(accessToken, "调用接口凭证不能为空"); // 确保 accessToken 不为空
    String uri = "/ticket/get"; // API 接口路径
    Map<String, Object> params = new HashMap<>();
    params.put("type", "agent_config"); // 指定请求类型为 agent_config
    params.put("access_token", accessToken); // 将 accessToken 添加到请求参数中
    return executeGet(uri, params, GetAgentTicketResponse.class); // 执行 GET 请求并返回响应
  }

}
