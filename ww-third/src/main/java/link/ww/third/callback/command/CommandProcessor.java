package link.ww.third.callback.command;

import org.w3c.dom.Element;

/**
 * 企业微信回调指令处理器接口
 * 用于处理企业微信的各类回调通知，如授权变更、ticket刷新等
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
public interface CommandProcessor {

  /**
   * 判断是否支持处理该类型的回调指令
   *
   * @param infoType 回调指令类型，如suite_ticket、create_auth等
   * @return true表示支持处理该类型指令
   */
  boolean support(String infoType);

  /**
   * 处理回调指令
   *
   * @param root XML消息的根节点元素
   */
  void process(Element root);

}
