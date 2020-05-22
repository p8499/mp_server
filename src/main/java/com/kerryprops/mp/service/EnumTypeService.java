package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.EnumTypeMask;
import com.kerryprops.mp.bean.EnumType;
import com.kerryprops.mp.mapper.db02.EnumTypeMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("enumTypeService")
public class EnumTypeService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public EnumType get(String etid, EnumTypeMask mask) {
    return enumTypeMapper.get(etid, mask);
  }

  @Transactional(value = "db02_transaction")
  public EnumType add(EnumType bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    enumTypeMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public EnumType update(EnumType bean, EnumTypeMask mask) {
    Set<ConstraintViolation<EnumType>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<EnumType> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    enumTypeMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String etid) {
    return enumTypeMapper.delete(etid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    enumTypeMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return enumTypeMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<EnumType> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, EnumTypeMask mask) {
    return enumTypeMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String etid) {
    return enumTypeMapper.exists(etid);
  }

  @Value(value = "#{enumTypeMapper}")
  protected EnumTypeMapper enumTypeMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
