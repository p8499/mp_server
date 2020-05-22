package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.EnumTypeValueMask;
import com.kerryprops.mp.bean.EnumTypeValue;
import com.kerryprops.mp.mapper.db02.EnumTypeValueMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("enumTypeValueService")
public class EnumTypeValueService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public EnumTypeValue get(Integer evid, EnumTypeValueMask mask) {
    return enumTypeValueMapper.get(evid, mask);
  }

  @Transactional(value = "db02_transaction")
  public EnumTypeValue add(EnumTypeValue bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    enumTypeValueMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public EnumTypeValue update(EnumTypeValue bean, EnumTypeValueMask mask) {
    Set<ConstraintViolation<EnumTypeValue>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<EnumTypeValue> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    enumTypeValueMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer evid) {
    return enumTypeValueMapper.delete(evid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    enumTypeValueMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return enumTypeValueMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<EnumTypeValue> query(
      FilterExpr filter,
      OrderByListExpr orderByList,
      long start,
      long count,
      EnumTypeValueMask mask) {
    return enumTypeValueMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer evid) {
    return enumTypeValueMapper.exists(evid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minEvid(FilterExpr filter, Integer defaultValue) {
    return enumTypeValueMapper.minEvid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxEvid(FilterExpr filter, Integer defaultValue) {
    return enumTypeValueMapper.maxEvid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minEvval(FilterExpr filter, Integer defaultValue) {
    return enumTypeValueMapper.minEvval(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxEvval(FilterExpr filter, Integer defaultValue) {
    return enumTypeValueMapper.maxEvval(filter, defaultValue);
  }

  @Value(value = "#{enumTypeValueMapper}")
  protected EnumTypeValueMapper enumTypeValueMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
