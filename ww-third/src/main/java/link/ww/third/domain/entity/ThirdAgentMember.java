package link.ww.third.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 应用成员
 *
 * @author wangxiaolei
 * @since 2025/2/10 15:53
 */
@Data
@TableName(value = "ww_third_agent_member")
public class ThirdAgentMember {

  private String id;

  private String corpId;

  private Integer agentId;

  /**
   * 成员类型，user:用户,dept:部门,tag:标签
   */
  private String memberType;

  private String memberId;

  private Date createTime;

  private Integer isDeleted;

  private Date deleteTime;

}
