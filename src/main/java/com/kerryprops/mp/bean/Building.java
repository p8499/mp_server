package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Building {
  public static final String TABLE = "F3002";
  public static final String VIEW = "V3002";
  public static final String NAME = "BUILDING";

  //region mcmcu 建筑物编号
  public static final String FIELD_MCMCU = "MCMCU";
  protected String mcmcu = null;
  public static final int CONSTRAINT_MCMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_MCMCU_LENGTH_STRING)
  public String getMcmcu() {
    return mcmcu;
  }

  public Building setMcmcu(String mcmcu) {
    this.mcmcu = mcmcu;
    return this;
  }
  //endregion

  //region mcdl01 建筑物名称
  public static final String FIELD_MCDL01 = "MCDL01";
  protected String mcdl01 = null;
  public static final int CONSTRAINT_MCDL01_LENGTH_STRING = 30;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_MCDL01_LENGTH_STRING)
  public String getMcdl01() {
    return mcdl01;
  }

  public Building setMcdl01(String mcdl01) {
    this.mcdl01 = mcdl01;
    return this;
  }
  //endregion

  //region mcbpmcu 楼盘编号
  public static final String FIELD_MCBPMCU = "MCBPMCU";
  protected String mcbpmcu = null;
  public static final int CONSTRAINT_MCBPMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_MCBPMCU_LENGTH_STRING)
  public String getMcbpmcu() {
    return mcbpmcu;
  }

  public Building setMcbpmcu(String mcbpmcu) {
    this.mcbpmcu = mcbpmcu;
    return this;
  }
  //endregion

  //region mcbpdl01 楼盘名称
  public static final String FIELD_MCBPDL01 = "MCBPDL01";
  protected String mcbpdl01 = null;
  public static final int CONSTRAINT_MCBPDL01_LENGTH_STRING = 30;

  public String getMcbpdl01() {
    return mcbpdl01;
  }

  public Building setMcbpdl01(String mcbpdl01) {
    this.mcbpdl01 = mcbpdl01;
    return this;
  }
  //endregion

  //region mcwcmcu 工作中心编号
  public static final String FIELD_MCWCMCU = "MCWCMCU";
  protected String mcwcmcu = null;
  public static final int CONSTRAINT_MCWCMCU_LENGTH_STRING = 12;

  public String getMcwcmcu() {
    return mcwcmcu;
  }

  public Building setMcwcmcu(String mcwcmcu) {
    this.mcwcmcu = mcwcmcu;
    return this;
  }
  //endregion

  //region mcwcdl01 工作中心名称
  public static final String FIELD_MCWCDL01 = "MCWCDL01";
  protected String mcwcdl01 = null;
  public static final int CONSTRAINT_MCWCDL01_LENGTH_STRING = 30;

  public String getMcwcdl01() {
    return mcwcdl01;
  }

  public Building setMcwcdl01(String mcwcdl01) {
    this.mcwcdl01 = mcwcdl01;
    return this;
  }
  //endregion

  public Building(
      String mcmcu,
      String mcdl01,
      String mcbpmcu,
      String mcbpdl01,
      String mcwcmcu,
      String mcwcdl01) {
    if (mcmcu != null) this.mcmcu = mcmcu;
    if (mcdl01 != null) this.mcdl01 = mcdl01;
    if (mcbpmcu != null) this.mcbpmcu = mcbpmcu;
    if (mcbpdl01 != null) this.mcbpdl01 = mcbpdl01;
    if (mcwcmcu != null) this.mcwcmcu = mcwcmcu;
    if (mcwcdl01 != null) this.mcwcdl01 = mcwcdl01;
  }

  public Building() {
    this(null, null, null, null, null, null);
  }

  public Building clone() {
    return new Building(mcmcu, mcdl01, mcbpmcu, mcbpdl01, mcwcmcu, mcwcdl01);
  }
}
