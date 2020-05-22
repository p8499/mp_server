package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.WorkCenterMask;
import com.kerryprops.mp.bean.WorkCenter;
import com.kerryprops.mp.mapper.db02.WorkCenterMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("workCenterService")
public class WorkCenterService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public WorkCenter get(String wcmcu, WorkCenterMask mask) {
    return workCenterMapper.get(wcmcu, mask);
  }

  @Transactional(value = "db02_transaction")
  public WorkCenter add(WorkCenter bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    workCenterMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public WorkCenter update(WorkCenter bean, WorkCenterMask mask) {
    Set<ConstraintViolation<WorkCenter>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<WorkCenter> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    workCenterMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String wcmcu) {
    return workCenterMapper.delete(wcmcu);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    workCenterMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return workCenterMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<WorkCenter> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, WorkCenterMask mask) {
    return workCenterMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String wcmcu) {
    return workCenterMapper.exists(wcmcu);
  }

  @Value(value = "#{workCenterMapper}")
  protected WorkCenterMapper workCenterMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
