package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.RoomMask;
import com.kerryprops.mp.bean.Room;
import com.kerryprops.mp.mapper.db02.RoomMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("roomService")
public class RoomService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Room get(Integer rmid, RoomMask mask) {
    return roomMapper.get(rmid, mask);
  }

  @Transactional(value = "db02_transaction")
  public Room add(Room bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    roomMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Room update(Room bean, RoomMask mask) {
    Set<ConstraintViolation<Room>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Room> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    roomMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(Integer rmid) {
    return roomMapper.delete(rmid);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    roomMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return roomMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Room> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, RoomMask mask) {
    return roomMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(Integer rmid) {
    return roomMapper.exists(rmid);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minRmid(FilterExpr filter, Integer defaultValue) {
    return roomMapper.minRmid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxRmid(FilterExpr filter, Integer defaultValue) {
    return roomMapper.maxRmid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minRman8(FilterExpr filter, Integer defaultValue) {
    return roomMapper.minRman8(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxRman8(FilterExpr filter, Integer defaultValue) {
    return roomMapper.maxRman8(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer minRmcwid(FilterExpr filter, Integer defaultValue) {
    return roomMapper.minRmcwid(filter, defaultValue);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public Integer maxRmcwid(FilterExpr filter, Integer defaultValue) {
    return roomMapper.maxRmcwid(filter, defaultValue);
  }

  @Value(value = "#{roomMapper}")
  protected RoomMapper roomMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
