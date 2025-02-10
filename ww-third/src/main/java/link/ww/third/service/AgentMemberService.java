package link.ww.third.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import link.common.constant.GlobalConstant;
import link.common.result.ResultCode;
import link.common.service.BaseService;
import link.common.utils.CollectionUtils;
import link.common.utils.UidGenerator;
import link.redisson.lock.annotation.RedisLock;
import link.redisson.lock.annotation.RedisLockParam;
import link.ww.third.domain.entity.ThirdAgentMember;
import link.ww.third.mapper.ThirdAgentMemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 应用成员服务
 *
 * @author wangxiaolei
 * @since 2025/2/10 15:46
 */
@Slf4j
@Service
public class AgentMemberService extends BaseService {

  @Autowired
  private ThirdAgentMemberMapper thirdAgentMemberMapper;

  /**
   * 删除应用成员
   *
   * @param corpId
   * @param agentId
   */
  public void delete(String corpId, Integer agentId) {
    if (StringUtils.isBlank(corpId) || agentId == null) {
      return;
    }
    LambdaUpdateWrapper<ThirdAgentMember> updateWrapper = Wrappers.lambdaUpdate(ThirdAgentMember.class);
    updateWrapper.eq(ThirdAgentMember::getCorpId, corpId);
    updateWrapper.eq(ThirdAgentMember::getAgentId, agentId);
    thirdAgentMemberMapper.delete(updateWrapper);
  }

  /**
   * 保存成员
   *
   * @param corpId     成员所属企业id
   * @param agentId    应用agentId
   * @param memberType 成员类型，user:用户,dept:部门,tag:标签
   * @param memberId   成员id
   */
  @RedisLock
  public void saveMember(@RedisLockParam String corpId,
                         @RedisLockParam Integer agentId,
                         @RedisLockParam String memberType,
                         @RedisLockParam String memberId) {
    if (StringUtils.isBlank(corpId) || agentId == null || StringUtils.isBlank(memberType) || StringUtils.isBlank(memberId)) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    ThirdAgentMember entity = getMember(corpId, agentId, memberType, memberId);
    if (entity == null) {
      entity = new ThirdAgentMember();
      entity.setId(UidGenerator.nextIdStr());
      entity.setCorpId(corpId);
      entity.setAgentId(agentId);
      entity.setMemberType(memberType);
      entity.setMemberId(memberId);
      entity.setCreateTime(new Date());
      entity.setIsDeleted(GlobalConstant.DB_NOT_DELETED);
      int n = thirdAgentMemberMapper.insert(entity);
      if (n != 1) {
        log.error("save entity error, entity:{}", entity);
        throwBizException("保存失败");
      }
    } else {
      if (!Objects.equals(entity.getIsDeleted(), GlobalConstant.DB_NOT_DELETED)) {
        entity.setIsDeleted(GlobalConstant.DB_NOT_DELETED);
        thirdAgentMemberMapper.updateById(entity);
      }
    }
  }

  public ThirdAgentMember getMember(String corpId, Integer agentId, String memberType, String memberId) {
    if (StringUtils.isBlank(corpId) || agentId == null || StringUtils.isBlank(memberType) || StringUtils.isBlank(memberId)) {
      return null;
    }
    LambdaQueryWrapper<ThirdAgentMember> queryWrapper = Wrappers.lambdaQuery(ThirdAgentMember.class);
    queryWrapper.eq(ThirdAgentMember::getCorpId, corpId);
    queryWrapper.eq(ThirdAgentMember::getAgentId, agentId);
    queryWrapper.eq(ThirdAgentMember::getMemberType, memberType);
    queryWrapper.eq(ThirdAgentMember::getMemberId, memberId);
    List<ThirdAgentMember> members = thirdAgentMemberMapper.selectList(queryWrapper);
    if (CollectionUtils.isEmpty(members)) {
      return null;
    } else {
      return members.get(0);
    }
  }

}
