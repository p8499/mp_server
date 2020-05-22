package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.PrivilegeMask;
import com.kerryprops.mp.bean.Privilege;
import com.kerryprops.mp.mapper.db02.PrivilegeMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("privilegeService")
public class PrivilegeService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Privilege get(String prid, PrivilegeMask mask) {
    return privilegeMapper.get(prid, mask);
  }

  @Transactional(value = "db02_transaction")
  public Privilege add(Privilege bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    privilegeMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Privilege update(Privilege bean, PrivilegeMask mask) {
    Set<ConstraintViolation<Privilege>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Privilege> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    privilegeMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String prid) {
    return privilegeMapper.delete(prid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    privilegeMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return privilegeMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Privilege> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, PrivilegeMask mask) {
    return privilegeMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String prid) {
    return privilegeMapper.exists(prid);
  }

  @Value(value = "#{privilegeMapper}")
  protected PrivilegeMapper privilegeMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
