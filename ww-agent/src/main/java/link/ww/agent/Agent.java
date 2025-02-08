package link.ww.agent;

import lombok.Data;
import lombok.ToString;

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
