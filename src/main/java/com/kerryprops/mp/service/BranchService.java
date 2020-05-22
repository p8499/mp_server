package com.kerryprops.mp.service;

import com.kerryprops.mp.FilterExpr;
import com.kerryprops.mp.OrderByListExpr;
import com.kerryprops.mp.mask.BranchMask;
import com.kerryprops.mp.bean.Branch;
import com.kerryprops.mp.mapper.db02.BranchMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service("branchService")
public class BranchService {
  @Transactional(value = "db02_transaction", readOnly = true)
  public Branch get(String bpmcu, BranchMask mask) {
    return branchMapper.get(bpmcu, mask);
  }

  @Transactional(value = "db02_transaction")
  public Branch add(Branch bean) {
    if (!validatorFactory.getValidator().validate(bean, Insert.class).isEmpty()) {
      return null;
    }
    branchMapper.add(bean);
    return bean;
  }

  @Transactional(value = "db02_transaction")
  public Branch update(Branch bean, BranchMask mask) {
    Set<ConstraintViolation<Branch>> violationSet =
        validatorFactory.getValidator().validate(bean, Update.class);
    for (ConstraintViolation<Branch> violation : violationSet)
      if (mask.get(violation.getPropertyPath().toString())) return null;
    branchMapper.update(bean, mask);
    return bean;
  }

  public boolean delete(String bpmcu) {
    return branchMapper.delete(bpmcu);
  }

  @Transactional(value = "db02_transaction")
  public void delete(FilterExpr filter) {
    branchMapper.deleteWhere(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public long count(FilterExpr filter) {
    return branchMapper.count(filter);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public List<Branch> query(
      FilterExpr filter, OrderByListExpr orderByList, long start, long count, BranchMask mask) {
    return branchMapper.query(filter, orderByList, start, count, mask);
  }

  @Transactional(value = "db02_transaction", readOnly = true)
  public boolean exists(String bpmcu) {
    return branchMapper.exists(bpmcu);
  }

  @Value(value = "#{branchMapper}")
  protected BranchMapper branchMapper;

  @Value(value = "#{validatorFactory}")
  protected LocalValidatorFactoryBean validatorFactory;
}
