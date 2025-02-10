package link.ww.third.service;

import link.common.result.ResultCode;
import link.common.service.BaseService;
import link.common.utils.StringUtils;
import link.ww.third.domain.entity.ThirdCorp;
import link.ww.third.mapper.ThirdCorpMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class ThirdCorpService extends BaseService {

  @Autowired
  private ThirdCorpMapper thirdCorpMapper;

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
      throwBizException("保存ThirdCorp失败");
    }
  }

  public void update(ThirdCorp entity) {
    if (entity == null) {
      throwBizException(ResultCode.PARAM_ERROR);
    }
    int n = thirdCorpMapper.updateById(entity);
    if (n != 1) {
      log.error("update entity error, entity:{}", entity);
      throwBizException("更新ThirdCorp失败");
    }
  }

}
