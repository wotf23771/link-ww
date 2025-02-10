package link.ww.third.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 安装的应用
 *
 * @author wangxiaolei
 * @since 2025/2/10 11:08
 */
@Data
@TableName(value = "ww_third_agent")
public class ThirdAgent {

  private String id;

  private String corpId;

  private Integer agentId;

  private String agentName;

  private Integer authMode;

  private Integer privilegeLevel;

  private Date createTime;

  private Integer isDeleted;

  private Date deleteTime;

}
