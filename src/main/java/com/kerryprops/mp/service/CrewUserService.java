package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.CrewUserMask;
import com.kerryprops.mp.bean.CrewUser;
import com.kerryprops.mp.mapper.db02.CrewUserMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("crewUserService")
public class CrewUserService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public CrewUser get(Integer cuid, CrewUserMask mask) {
    return crewUserMapper.get(cuid, mask);
  }

  @Transactional(value = "db02_transaction")
  public CrewUser add(CrewUser bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    crewUserMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public CrewUser update(CrewUser bean, CrewUserMask mask) {
    Set<ConstraintViolation<CrewUser>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<CrewUser> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    crewUserMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer cuid) {
    return crewUserMapper.delete(cuid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    crewUserMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return crewUserMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<CrewUser> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, CrewUserMask mask) {
    return crewUserMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer cuid) {
    return crewUserMapper.exists(cuid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minCuid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.minCuid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxCuid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.maxCuid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minCucwid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.minCucwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxCucwid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.maxCucwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minCuusid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.minCuusid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxCuusid(FilterExpr filter, Integer defaultValue) {
    return crewUserMapper.maxCuusid(filter, defaultValue);
  }

  @Value(value = "#{crewUserMapper}")
  protected CrewUserMapper crewUserMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
