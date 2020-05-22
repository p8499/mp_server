package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnumType {
  public static final String TABLE = "F0004";
  public static final String VIEW = "V0004";
  public static final String NAME = "ENUMTYPE";

  //region etid 枚举类型编号
  public static final String FIELD_ETID = "ETID";
  protected String etid = null;
  public static final int CONSTRAINT_ETID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_ETID_LENGTH_STRING)
  public String getEtid() {
    return etid;
  }

  public EnumType setEtid(String etid) {
    this.etid = etid;
    return this;
  }
  //endregion

  //region etname 枚举类型名称
  public static final String FIELD_ETNAME = "ETNAME";
  protected String etname = null;
  public static final int CONSTRAINT_ETNAME_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_ETNAME_LENGTH_STRING)
  public String getEtname() {
    return etname;
  }

  public EnumType setEtname(String etname) {
    this.etname = etname;
    return this;
  }
  //endregion

  public EnumType(String etid, String etname) {
    if (etid != null) this.etid = etid;
    if (etname != null) this.etname = etname;
  }

  public EnumType() {
    this(null, null);
  }

  public EnumType clone() {
    return new EnumType(etid, etname);
  }
}
