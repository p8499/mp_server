package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceType {
  public static final String TABLE = "F1701";
  public static final String VIEW = "V1701";
  public static final String NAME = "MAINTENANCETYPE";

  //region mtid 维保类型编号
  public static final String FIELD_MTID = "MTID";
  protected String mtid = null;
  public static final int CONSTRAINT_MTID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_MTID_LENGTH_STRING)
  public String getMtid() {
    return mtid;
  }

  public MaintenanceType setMtid(String mtid) {
    this.mtid = mtid;
    return this;
  }
  //endregion

  //region mtname 维保类型名称
  public static final String FIELD_MTNAME = "MTNAME";
  protected String mtname = null;
  public static final int CONSTRAINT_MTNAME_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_MTNAME_LENGTH_STRING)
  public String getMtname() {
    return mtname;
  }

  public MaintenanceType setMtname(String mtname) {
    this.mtname = mtname;
    return this;
  }
  //endregion

  public MaintenanceType(String mtid, String mtname) {
    if (mtid != null) this.mtid = mtid;
    if (mtname != null) this.mtname = mtname;
  }

  public MaintenanceType() {
    this(null, null);
  }

  public MaintenanceType clone() {
    return new MaintenanceType(mtid, mtname);
  }
}
