package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.MaintenanceTypeMask;
import com.kerryprops.mp.bean.MaintenanceType;
import com.kerryprops.mp.mapper.db02.MaintenanceTypeMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("maintenanceTypeService")
public class MaintenanceTypeService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public MaintenanceType get(String mtid, MaintenanceTypeMask mask) {
    return maintenanceTypeMapper.get(mtid, mask);
  }

  @Transactional(value = "db02_transaction")
  public MaintenanceType add(MaintenanceType bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    maintenanceTypeMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public MaintenanceType update(MaintenanceType bean, MaintenanceTypeMask mask) {
    Set<ConstraintViolation<MaintenanceType>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<MaintenanceType> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    maintenanceTypeMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String mtid) {
    return maintenanceTypeMapper.delete(mtid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    maintenanceTypeMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return maintenanceTypeMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<MaintenanceType> query(
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      MaintenanceTypeMask mask) {
    return maintenanceTypeMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String mtid) {
    return maintenanceTypeMapper.exists(mtid);
  }

  @Value(value = "#{maintenanceTypeMapper}")
  protected MaintenanceTypeMapper maintenanceTypeMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
