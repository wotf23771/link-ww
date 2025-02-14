package link.ww.base.web;

import lombok.Builder;
import lombok.Data;

/**
 * 微信JS-SDK使用权限签名
 */
@Data
@Builder
public class JsSignature {

  /**
   * 生成签名的时间戳
   */
  private Long timestamp;

  /**
   * 生成签名的随机串
   */
  private String nonceStr;

  /**
   * 签名
   */
  private String signature;

}
