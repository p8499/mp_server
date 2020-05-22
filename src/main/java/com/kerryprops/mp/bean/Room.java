package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {
  public static final String TABLE = "F1200";
  public static final String VIEW = "V1200";
  public static final String NAME = "ROOM";

  //region rmid 机房序号
  public static final String FIELD_RMID = "RMID";
  protected Integer rmid = null;
  public static final int CONSTRAINT_RMID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_RMID_MIN = -99999999;
  public static final int CONSTRAINT_RMID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getRmid() {
    return rmid;
  }

  public Room setRmid(Integer rmid) {
    this.rmid = rmid;
    return this;
  }
  //endregion

  //region rmserial 机房短号
  public static final String FIELD_RMSERIAL = "RMSERIAL";
  protected String rmserial = null;
  public static final int CONSTRAINT_RMSERIAL_LENGTH_STRING = 16;

  @javax.validation.constraints.Size(max = CONSTRAINT_RMSERIAL_LENGTH_STRING)
  public String getRmserial() {
    return rmserial;
  }

  public Room setRmserial(String rmserial) {
    this.rmserial = rmserial;
    return this;
  }
  //endregion

  //region rman8 JDE地址号
  public static final String FIELD_RMAN8 = "RMAN8";
  protected Integer rman8 = null;
  public static final int CONSTRAINT_RMAN8_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_RMAN8_MIN = -99999999;
  public static final int CONSTRAINT_RMAN8_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_RMAN8_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_RMAN8_MAX)
  public Integer getRman8() {
    return rman8;
  }

  public Room setRman8(Integer rman8) {
    this.rman8 = rman8;
    return this;
  }
  //endregion

  //region rmname 机房名称
  public static final String FIELD_RMNAME = "RMNAME";
  protected String rmname = null;
  public static final int CONSTRAINT_RMNAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RMNAME_LENGTH_STRING)
  public String getRmname() {
    return rmname;
  }

  public Room setRmname(String rmname) {
    this.rmname = rmname;
    return this;
  }
  //endregion

  //region rmcwid 班组序号
  public static final String FIELD_RMCWID = "RMCWID";
  protected Integer rmcwid = null;
  public static final int CONSTRAINT_RMCWID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_RMCWID_MIN = -99999999;
  public static final int CONSTRAINT_RMCWID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_RMCWID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_RMCWID_MAX)
  public Integer getRmcwid() {
    return rmcwid;
  }

  public Room setRmcwid(Integer rmcwid) {
    this.rmcwid = rmcwid;
    return this;
  }
  //endregion

  //region rmcwname 班组名称
  public static final String FIELD_RMCWNAME = "RMCWNAME";
  protected String rmcwname = null;
  public static final int CONSTRAINT_RMCWNAME_LENGTH_STRING = 16;

  public String getRmcwname() {
    return rmcwname;
  }

  public Room setRmcwname(String rmcwname) {
    this.rmcwname = rmcwname;
    return this;
  }
  //endregion

  //region rmwcmcu 工作中心编号
  public static final String FIELD_RMWCMCU = "RMWCMCU";
  protected String rmwcmcu = null;
  public static final int CONSTRAINT_RMWCMCU_LENGTH_STRING = 12;

  public String getRmwcmcu() {
    return rmwcmcu;
  }

  public Room setRmwcmcu(String rmwcmcu) {
    this.rmwcmcu = rmwcmcu;
    return this;
  }
  //endregion

  //region rmwcdl01 工作中心名称
  public static final String FIELD_RMWCDL01 = "RMWCDL01";
  protected String rmwcdl01 = null;
  public static final int CONSTRAINT_RMWCDL01_LENGTH_STRING = 30;

  public String getRmwcdl01() {
    return rmwcdl01;
  }

  public Room setRmwcdl01(String rmwcdl01) {
    this.rmwcdl01 = rmwcdl01;
    return this;
  }
  //endregion

  public Room(
      Integer rmid,
      String rmserial,
      Integer rman8,
      String rmname,
      Integer rmcwid,
      String rmcwname,
      String rmwcmcu,
      String rmwcdl01) {
    if (rmid != null) this.rmid = rmid;
    if (rmserial != null) this.rmserial = rmserial;
    if (rman8 != null) this.rman8 = rman8;
    if (rmname != null) this.rmname = rmname;
    if (rmcwid != null) this.rmcwid = rmcwid;
    if (rmcwname != null) this.rmcwname = rmcwname;
    if (rmwcmcu != null) this.rmwcmcu = rmwcmcu;
    if (rmwcdl01 != null) this.rmwcdl01 = rmwcdl01;
  }

  public Room() {
    this(null, null, null, null, null, null, null, null);
  }

  public Room clone() {
    return new Room(rmid, rmserial, rman8, rmname, rmcwid, rmcwname, rmwcmcu, rmwcdl01);
  }
}
