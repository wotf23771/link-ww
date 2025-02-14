package link.ww.base.service;

import org.springframework.stereotype.Service;

/**
 * JsTicket 服务
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:38
 */
@Service
public interface JsTicketService {

  String getCorpJsTicket();

  String getAgentJsTicket();

}
