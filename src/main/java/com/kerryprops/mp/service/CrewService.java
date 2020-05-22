package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.CrewMask;
import com.kerryprops.mp.bean.Crew;
import com.kerryprops.mp.mapper.db02.CrewMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("crewService")
public class CrewService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Crew get(Integer cwid, CrewMask mask) {
    return crewMapper.get(cwid, mask);
  }

  @Transactional(value = "db02_transaction")
  public Crew add(Crew bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    crewMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Crew update(Crew bean, CrewMask mask) {
    Set<ConstraintViolation<Crew>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Crew> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    crewMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer cwid) {
    return crewMapper.delete(cwid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    crewMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return crewMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Crew> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, CrewMask mask) {
    return crewMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer cwid) {
    return crewMapper.exists(cwid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minCwid(FilterExpr filter, Integer defaultValue) {
    return crewMapper.minCwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxCwid(FilterExpr filter, Integer defaultValue) {
    return crewMapper.maxCwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minCwan8(FilterExpr filter, Integer defaultValue) {
    return crewMapper.minCwan8(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxCwan8(FilterExpr filter, Integer defaultValue) {
    return crewMapper.maxCwan8(filter, defaultValue);
  }

  @Value(value = "#{crewMapper}")
  protected CrewMapper crewMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
