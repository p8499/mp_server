package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.BuildingMask;
import com.kerryprops.mp.bean.Building;
import com.kerryprops.mp.mapper.db02.BuildingMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("buildingService")
public class BuildingService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Building get(String mcmcu, BuildingMask mask) {
    return buildingMapper.get(mcmcu, mask);
  }

  @Transactional(value = "db02_transaction")
  public Building add(Building bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    buildingMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Building update(Building bean, BuildingMask mask) {
    Set<ConstraintViolation<Building>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Building> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    buildingMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String mcmcu) {
    return buildingMapper.delete(mcmcu);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    buildingMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return buildingMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Building> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, BuildingMask mask) {
    return buildingMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String mcmcu) {
    return buildingMapper.exists(mcmcu);
  }

  @Value(value = "#{buildingMapper}")
  protected BuildingMapper buildingMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
