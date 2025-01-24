package link.ww.agent;

import lombok.Data;

@Data
public class Agent {

  private String agentId;

  private String secret;

  private String token;

  private String encodingAESKey;

}
