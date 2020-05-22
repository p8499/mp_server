package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Branch {
  public static final String TABLE = "F3001";
  public static final String VIEW = "V3001";
  public static final String NAME = "BRANCH";

  //region bpmcu 楼盘编号
  public static final String FIELD_BPMCU = "BPMCU";
  protected String bpmcu = null;
  public static final int CONSTRAINT_BPMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_BPMCU_LENGTH_STRING)
  public String getBpmcu() {
    return bpmcu;
  }

  public Branch setBpmcu(String bpmcu) {
    this.bpmcu = bpmcu;
    return this;
  }
  //endregion

  //region bpdl01 楼盘名称
  public static final String FIELD_BPDL01 = "BPDL01";
  protected String bpdl01 = null;
  public static final int CONSTRAINT_BPDL01_LENGTH_STRING = 30;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_BPDL01_LENGTH_STRING)
  public String getBpdl01() {
    return bpdl01;
  }

  public Branch setBpdl01(String bpdl01) {
    this.bpdl01 = bpdl01;
    return this;
  }
  //endregion

  //region bpwcmcu 工作中心编号
  public static final String FIELD_BPWCMCU = "BPWCMCU";
  protected String bpwcmcu = null;
  public static final int CONSTRAINT_BPWCMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_BPWCMCU_LENGTH_STRING)
  public String getBpwcmcu() {
    return bpwcmcu;
  }

  public Branch setBpwcmcu(String bpwcmcu) {
    this.bpwcmcu = bpwcmcu;
    return this;
  }
  //endregion

  //region bpwcdl01 工作中心名称
  public static final String FIELD_BPWCDL01 = "BPWCDL01";
  protected String bpwcdl01 = null;
  public static final int CONSTRAINT_BPWCDL01_LENGTH_STRING = 30;

  public String getBpwcdl01() {
    return bpwcdl01;
  }

  public Branch setBpwcdl01(String bpwcdl01) {
    this.bpwcdl01 = bpwcdl01;
    return this;
  }
  //endregion

  public Branch(String bpmcu, String bpdl01, String bpwcmcu, String bpwcdl01) {
    if (bpmcu != null) this.bpmcu = bpmcu;
    if (bpdl01 != null) this.bpdl01 = bpdl01;
    if (bpwcmcu != null) this.bpwcmcu = bpwcmcu;
    if (bpwcdl01 != null) this.bpwcdl01 = bpwcdl01;
  }

  public Branch() {
    this(null, null, null, null);
  }

  public Branch clone() {
    return new Branch(bpmcu, bpdl01, bpwcmcu, bpwcdl01);
  }
}
