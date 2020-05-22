package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnumTypeValue {
  public static final String TABLE = "F0005";
  public static final String VIEW = "V0005";
  public static final String NAME = "ENUMTYPEVALUE";

  //region evid 枚举类型值序号
  public static final String FIELD_EVID = "EVID";
  protected Integer evid = null;
  public static final int CONSTRAINT_EVID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_EVID_MIN = -99999999;
  public static final int CONSTRAINT_EVID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getEvid() {
    return evid;
  }

  public EnumTypeValue setEvid(Integer evid) {
    this.evid = evid;
    return this;
  }
  //endregion

  //region evetid 枚举类型编号
  public static final String FIELD_EVETID = "EVETID";
  protected String evetid = null;
  public static final int CONSTRAINT_EVETID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_EVETID_LENGTH_STRING)
  public String getEvetid() {
    return evetid;
  }

  public EnumTypeValue setEvetid(String evetid) {
    this.evetid = evetid;
    return this;
  }
  //endregion

  //region evval 数值
  public static final String FIELD_EVVAL = "EVVAL";
  protected Integer evval = null;
  public static final int CONSTRAINT_EVVAL_LENGTH_INTEGER = 1;
  public static final int CONSTRAINT_EVVAL_MIN = -9;
  public static final int CONSTRAINT_EVVAL_MAX = 9;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_EVVAL_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_EVVAL_MAX)
  public Integer getEvval() {
    return evval;
  }

  public EnumTypeValue setEvval(Integer evval) {
    this.evval = evval;
    return this;
  }
  //endregion

  //region evdescription 数值涵义
  public static final String FIELD_EVDESCRIPTION = "EVDESCRIPTION";
  protected String evdescription = null;
  public static final int CONSTRAINT_EVDESCRIPTION_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_EVDESCRIPTION_LENGTH_STRING)
  public String getEvdescription() {
    return evdescription;
  }

  public EnumTypeValue setEvdescription(String evdescription) {
    this.evdescription = evdescription;
    return this;
  }
  //endregion

  //region evetname 枚举类型名称
  public static final String FIELD_EVETNAME = "EVETNAME";
  protected String evetname = null;
  public static final int CONSTRAINT_EVETNAME_LENGTH_STRING = 8;

  public String getEvetname() {
    return evetname;
  }

  public EnumTypeValue setEvetname(String evetname) {
    this.evetname = evetname;
    return this;
  }
  //endregion

  public EnumTypeValue(
      Integer evid, String evetid, Integer evval, String evdescription, String evetname) {
    if (evid != null) this.evid = evid;
    if (evetid != null) this.evetid = evetid;
    if (evval != null) this.evval = evval;
    if (evdescription != null) this.evdescription = evdescription;
    if (evetname != null) this.evetname = evetname;
  }

  public EnumTypeValue() {
    this(null, null, null, null, null);
  }

  public EnumTypeValue clone() {
    return new EnumTypeValue(evid, evetid, evval, evdescription, evetname);
  }
}
