package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InspectionType {
  public static final String TABLE = "F1700";
  public static final String VIEW = "V1700";
  public static final String NAME = "INSPECTIONTYPE";

  //region itid 巡视类型编号
  public static final String FIELD_ITID = "ITID";
  protected String itid = null;
  public static final int CONSTRAINT_ITID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_ITID_LENGTH_STRING)
  public String getItid() {
    return itid;
  }

  public InspectionType setItid(String itid) {
    this.itid = itid;
    return this;
  }
  //endregion

  //region itname 巡视类型名称
  public static final String FIELD_ITNAME = "ITNAME";
  protected String itname = null;
  public static final int CONSTRAINT_ITNAME_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_ITNAME_LENGTH_STRING)
  public String getItname() {
    return itname;
  }

  public InspectionType setItname(String itname) {
    this.itname = itname;
    return this;
  }
  //endregion

  public InspectionType(String itid, String itname) {
    if (itid != null) this.itid = itid;
    if (itname != null) this.itname = itname;
  }

  public InspectionType() {
    this(null, null);
  }

  public InspectionType clone() {
    return new InspectionType(itid, itname);
  }
}
