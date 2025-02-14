package link.ww.third.callback.data;

import link.ww.base.event.DataCallbackEvent;

/**
 * 数据回调处理器接口
 * 用于处理企业微信的数据回调请求
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
public interface DataProcessor {

  /**
   * 判断是否支持处理该类型的事件
   *
   * @param msgType 消息类型
   * @param event 事件类型
   * @return true-支持，false-不支持
   */
  boolean support(String msgType, String event);

  /**
   * 处理数据回调事件
   *
   * @param event 数据回调事件
   */
  void process(DataCallbackEvent event);

}
