package link.ww.contacts.service;

/**
 * 通讯录部门 command
 *
 * @author wangxiaolei
 * @since 2025/2/11 18:07
 */
public interface DeptCmdService {

  /**
   * 删除部门
   *
   * @param id 部门id。（注：不能删除根部门；不能删除含有子部门、成员的部门）
   * @return
   */
  boolean deleteDept(Integer id);

}
