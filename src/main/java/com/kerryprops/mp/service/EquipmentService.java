package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.EquipmentMask;
import com.kerryprops.mp.bean.Equipment;
import com.kerryprops.mp.mapper.db02.EquipmentMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("equipmentService")
public class EquipmentService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Equipment get(Integer eqid, EquipmentMask mask) {
    return equipmentMapper.get(eqid, mask);
  }

  @Transactional(value = "db02_transaction")
  public Equipment add(Equipment bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    equipmentMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Equipment update(Equipment bean, EquipmentMask mask) {
    Set<ConstraintViolation<Equipment>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Equipment> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    equipmentMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer eqid) {
    return equipmentMapper.delete(eqid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    equipmentMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return equipmentMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Equipment> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, EquipmentMask mask) {
    return equipmentMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer eqid) {
    return equipmentMapper.exists(eqid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minEqid(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.minEqid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxEqid(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.maxEqid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minEqnumb(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.minEqnumb(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxEqnumb(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.maxEqnumb(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minEqcwid(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.minEqcwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxEqcwid(FilterExpr filter, Integer defaultValue) {
    return equipmentMapper.maxEqcwid(filter, defaultValue);
  }

  @Value(value = "#{equipmentMapper}")
  protected EquipmentMapper equipmentMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
