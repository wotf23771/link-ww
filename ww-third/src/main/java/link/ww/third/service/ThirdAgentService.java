package link.ww.third.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import link.common.constant.GlobalConstant;
import link.common.result.ResultCode;
import link.common.service.BaseService;
import link.common.utils.CollectionUtils;
import link.common.utils.StringUtils;
import link.common.utils.UidGenerator;
import link.redisson.lock.annotation.RedisLock;
import link.redisson.lock.annotation.RedisLockParam;
import link.ww.third.domain.entity.ThirdAgent;
import link.ww.third.mapper.ThirdAgentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 第三方应用服务
 *
 * @author wangxiaolei
 * @since 2025/2/10 16:15
 */
@Slf4j
@Service
public class ThirdAgentService extends BaseService {

  @Autowired
  private ThirdAgentMapper thirdAgentMapper;

  @RedisLock
  @Transactional
  public ThirdAgent saveOrUpdate(@RedisLockParam String corpId, @RedisLockParam Integer agentId, ThirdAgent entity) {
    if (StringUtils.isBlank(corpId) || agentId == null || entity == null) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    if (entity.getId() == null) {
      entity.setId(UidGenerator.nextIdStr());
    }
    ThirdAgent _entity = get(corpId, agentId);
    if (_entity == null) {
      // 新增
      entity.setCreateTime(new Date());
      entity.setIsDeleted(GlobalConstant.DB_NOT_DELETED);
      int n = thirdAgentMapper.insert(entity);
      if (n != 1) {
        log.error("save entity error, entity:{}", entity);
        throwBizException("保存失败");
      }
      return entity;
    } else {
      // 修改
      _entity.setAgentName(entity.getAgentName());
      _entity.setAuthMode(entity.getAuthMode());
      _entity.setPrivilegeLevel(entity.getPrivilegeLevel());
      _entity.setIsDeleted(GlobalConstant.DB_NOT_DELETED);
      int n = thirdAgentMapper.updateById(_entity);
      if (n != 1) {
        log.error("update entity error, entity:{}", entity);
        throwBizException("更新失败");
      }
      return _entity;
    }
  }

  public ThirdAgent get(String corpId, Integer agentId) {
    if (StringUtils.isBlank(corpId) || agentId == null) {
      return null;
    }
    LambdaQueryWrapper<ThirdAgent> queryWrapper = Wrappers.lambdaQuery(ThirdAgent.class);
    queryWrapper.eq(ThirdAgent::getCorpId, corpId);
    queryWrapper.eq(ThirdAgent::getAgentId, agentId);
    List<ThirdAgent> agents = thirdAgentMapper.selectList(queryWrapper);
    if (CollectionUtils.isNotEmpty(agents)) {
      return agents.get(0);
    }
    return null;
  }

  @Transactional
  public void delete(String corpId, Integer agentId) {
    ThirdAgent entity = get(corpId, agentId);
    if (entity == null) {
      return;
    }
    entity.setIsDeleted(GlobalConstant.DB_NOT_DELETED);
    entity.setDeleteTime(new Date());
    int n = thirdAgentMapper.updateById(entity);
    if (n != 1) {
      log.error("delete entity error, entity:{}", entity);
      throwBizException("删除失败");
    }
  }

}
