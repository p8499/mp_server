package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkCenter {
  public static final String TABLE = "F3000";
  public static final String VIEW = "V3000";
  public static final String NAME = "WORKCENTER";

  //region wcmcu 工作中心编号
  public static final String FIELD_WCMCU = "WCMCU";
  protected String wcmcu = null;
  public static final int CONSTRAINT_WCMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_WCMCU_LENGTH_STRING)
  public String getWcmcu() {
    return wcmcu;
  }

  public WorkCenter setWcmcu(String wcmcu) {
    this.wcmcu = wcmcu;
    return this;
  }
  //endregion

  //region wcdl01 工作中心名称
  public static final String FIELD_WCDL01 = "WCDL01";
  protected String wcdl01 = null;
  public static final int CONSTRAINT_WCDL01_LENGTH_STRING = 30;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_WCDL01_LENGTH_STRING)
  public String getWcdl01() {
    return wcdl01;
  }

  public WorkCenter setWcdl01(String wcdl01) {
    this.wcdl01 = wcdl01;
    return this;
  }
  //endregion

  public WorkCenter(String wcmcu, String wcdl01) {
    if (wcmcu != null) this.wcmcu = wcmcu;
    if (wcdl01 != null) this.wcdl01 = wcdl01;
  }

  public WorkCenter() {
    this(null, null);
  }

  public WorkCenter clone() {
    return new WorkCenter(wcmcu, wcdl01);
  }
}
