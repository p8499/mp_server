package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.InspectionTypeMask;
import com.kerryprops.mp.bean.InspectionType;
import com.kerryprops.mp.mapper.db02.InspectionTypeMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("inspectionTypeService")
public class InspectionTypeService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public InspectionType get(String itid, InspectionTypeMask mask) {
    return inspectionTypeMapper.get(itid, mask);
  }

  @Transactional(value = "db02_transaction")
  public InspectionType add(InspectionType bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    inspectionTypeMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public InspectionType update(InspectionType bean, InspectionTypeMask mask) {
    Set<ConstraintViolation<InspectionType>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<InspectionType> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    inspectionTypeMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String itid) {
    return inspectionTypeMapper.delete(itid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    inspectionTypeMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return inspectionTypeMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<InspectionType> query(
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      InspectionTypeMask mask) {
    return inspectionTypeMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String itid) {
    return inspectionTypeMapper.exists(itid);
  }

  @Value(value = "#{inspectionTypeMapper}")
  protected InspectionTypeMapper inspectionTypeMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
