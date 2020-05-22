package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.RoleMask;
import com.kerryprops.mp.bean.Role;
import com.kerryprops.mp.mapper.db02.RoleMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("roleService")
public class RoleService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Role get(String roid, RoleMask mask) {
    return roleMapper.get(roid, mask);
  }

  @Transactional(value = "db02_transaction")
  public Role add(Role bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    roleMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Role update(Role bean, RoleMask mask) {
    Set<ConstraintViolation<Role>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Role> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    roleMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String roid) {
    return roleMapper.delete(roid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    roleMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return roleMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Role> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, RoleMask mask) {
    return roleMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String roid) {
    return roleMapper.exists(roid);
  }

  @Value(value = "#{roleMapper}")
  protected RoleMapper roleMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
