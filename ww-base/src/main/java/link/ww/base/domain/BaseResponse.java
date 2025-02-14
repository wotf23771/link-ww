package link.ww.base.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 企业微信接口通用返回值
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Data
public class BaseResponse {

  /**
   * 返回码
   */
  @JsonProperty("errcode")
  private Integer errCode = 0;

  /**
   * 对返回码的文本描述内容
   */
  @JsonProperty("errmsg")
  private String errMsg;

  public boolean isSuccess() {
    return this.errCode == 0;
  }

}
