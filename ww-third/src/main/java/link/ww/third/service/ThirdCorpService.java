package link.ww.third.service;

import link.common.result.ResultCode;
import link.common.service.BaseService;
import link.common.spring.SpringApplicationContext;
import link.common.utils.CollectionUtils;
import link.common.utils.JsonUtils;
import link.common.utils.StringUtils;
import link.common.utils.UidGenerator;
import link.ww.third.domain.entity.ThirdAgent;
import link.ww.third.domain.entity.ThirdCorp;
import link.ww.third.manager.GetAuthInfoResponse;
import link.ww.third.manager.ServiceManager;
import link.ww.third.mapper.ThirdCorpMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ThirdCorpService extends BaseService {

  @Autowired
  private ThirdCorpMapper thirdCorpMapper;

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private SuiteTokenService suiteTokenService;

  public ThirdCorp get(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return null;
    }
    return thirdCorpMapper.selectById(corpId);
  }

  @Transactional
  public void save(ThirdCorp entity) {
    if (entity == null) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    entity.setCreateTime(new Date());
    int n = thirdCorpMapper.insert(entity);
    if (n != 1) {
      log.error("save entity error, entity:{}", entity);
      throwBizException("保存失败");
    }
  }

  public void update(ThirdCorp entity) {
    if (entity == null) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    int n = thirdCorpMapper.updateById(entity);
    if (n != 1) {
      log.error("update entity error, entity:{}", entity);
      throwBizException("更新失败");
    }
  }

  /**
   * 更新企业授权信息，包括：更新企业信息、更新已安装的应用信息、更新成员信息
   *
   * @param corpId
   */
  @Transactional
  public void updateAuthInfo(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    ThirdCorp thirdCorp = get(corpId);
    if (thirdCorp == null) {
      throwBizException(ResultCode.BIZ_DATA_NOT_EXISTS, "企业不存在");
    }
    String suiteAccessToken = suiteTokenService.getToken();
    if (StringUtils.isBlank(suiteAccessToken)) {
      throwBizException(ResultCode.BIZ_DATA_NOT_EXISTS, "suiteAccessToken不存在");
    }
    GetAuthInfoResponse authInfoResponse = serviceManager.getAuthInfo(suiteAccessToken, corpId, thirdCorp.getPermanentCode());
    if (authInfoResponse == null) {
      throwBizException(ResultCode.BIZ_DATA_NOT_EXISTS, "authInfo为空");
    }
    thirdCorp.setAuthInfo(JsonUtils.toJson(authInfoResponse));
    update(thirdCorp);

    // 更新应用
    ThirdAgentService thirdAgentService = SpringApplicationContext.getBean(ThirdAgentService.class);
    AgentMemberService agentMemberService = SpringApplicationContext.getBean(AgentMemberService.class);

    GetAuthInfoResponse.AuthInfo authInfo = authInfoResponse.getAuthInfo();
    if (authInfo == null) {
      return;
    }
    List<GetAuthInfoResponse.AuthAgent> agents = authInfo.getAgent();
    if (CollectionUtils.isEmpty(agents)) {
      return;
    }
    for (GetAuthInfoResponse.AuthAgent agent : agents) {
      Integer agentId = agent.getAgentId();
      ThirdAgent entity = new ThirdAgent();
      entity.setId(UidGenerator.nextIdStr());
      entity.setCorpId(corpId);
      entity.setAgentId(agentId);
      entity.setAgentName(agent.getName());
      entity.setAuthMode(agent.getAuthMode());
      GetAuthInfoResponse.AuthPrivilege privilege = agent.getPrivilege();
      if (privilege != null) {
        entity.setPrivilegeLevel(privilege.getLevel());
      }

      // 调用第三方应用服务保存或更新应用
      thirdAgentService.saveOrUpdate(corpId, agentId, entity);

      // 更新成员信息
      // 第一步：删除旧成员
      agentMemberService.delete(corpId, agentId);
      // 第二步：保存新成员
      if (privilege != null) {
        // 部门
        List<Integer> allowParty = privilege.getAllowParty();
        if (CollectionUtils.isNotEmpty(allowParty)) {
          for (Integer deptId : allowParty) {
            agentMemberService.saveMember(corpId, agentId, "dept", String.valueOf(deptId));
          }
        }
        // 用户
        List<String> allowUser = privilege.getAllowUser();
        if (CollectionUtils.isNotEmpty(allowUser)) {
          for (String userId : allowUser) {
            agentMemberService.saveMember(corpId, agentId, "user", userId);
          }
        }
        // 标签
        List<Integer> allowTag = privilege.getAllowTag();
        if (CollectionUtils.isNotEmpty(allowTag)) {
          for (Integer tagId : allowTag) {
            agentMemberService.saveMember(corpId, agentId, "tag", String.valueOf(tagId));
          }
        }
      }
    }
  }

}
