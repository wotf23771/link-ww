package link.ww.internal;

import lombok.Data;
import lombok.ToString;

/**
 * 企业微信应用配置实体
 * 包含应用的ID、密钥等配置信息
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Data
public class Agent {

  private String agentId;

  @ToString.Exclude
  private String secret;

  @ToString.Exclude
  private String token;

  @ToString.Exclude
  private String encodingAesKey;

}
