package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.RolePrivilegeMask;
import com.kerryprops.mp.bean.RolePrivilege;
import com.kerryprops.mp.mapper.db02.RolePrivilegeMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("rolePrivilegeService")
public class RolePrivilegeService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public RolePrivilege get(Integer rpid, RolePrivilegeMask mask) {
    return rolePrivilegeMapper.get(rpid, mask);
  }

  @Transactional(value = "db02_transaction")
  public RolePrivilege add(RolePrivilege bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    rolePrivilegeMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public RolePrivilege update(RolePrivilege bean, RolePrivilegeMask mask) {
    Set<ConstraintViolation<RolePrivilege>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<RolePrivilege> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    rolePrivilegeMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer rpid) {
    return rolePrivilegeMapper.delete(rpid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    rolePrivilegeMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return rolePrivilegeMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<RolePrivilege> query(
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      RolePrivilegeMask mask) {
    return rolePrivilegeMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer rpid) {
    return rolePrivilegeMapper.exists(rpid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minRpid(FilterExpr filter, Integer defaultValue) {
    return rolePrivilegeMapper.minRpid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxRpid(FilterExpr filter, Integer defaultValue) {
    return rolePrivilegeMapper.maxRpid(filter, defaultValue);
  }

  @Value(value = "#{rolePrivilegeMapper}")
  protected RolePrivilegeMapper rolePrivilegeMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
