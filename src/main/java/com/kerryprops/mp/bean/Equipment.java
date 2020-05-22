package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Equipment {
  public static final String TABLE = "F1201";
  public static final String VIEW = "V1201";
  public static final String NAME = "EQUIPMENT";

  //region eqid 设备序号
  public static final String FIELD_EQID = "EQID";
  protected Integer eqid = null;
  public static final int CONSTRAINT_EQID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_EQID_MIN = -99999999;
  public static final int CONSTRAINT_EQID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getEqid() {
    return eqid;
  }

  public Equipment setEqid(Integer eqid) {
    this.eqid = eqid;
    return this;
  }
  //endregion

  //region eqserial 设备短号
  public static final String FIELD_EQSERIAL = "EQSERIAL";
  protected String eqserial = null;
  public static final int CONSTRAINT_EQSERIAL_LENGTH_STRING = 16;

  @javax.validation.constraints.Size(max = CONSTRAINT_EQSERIAL_LENGTH_STRING)
  public String getEqserial() {
    return eqserial;
  }

  public Equipment setEqserial(String eqserial) {
    this.eqserial = eqserial;
    return this;
  }
  //endregion

  //region eqnumb JDE资产号
  public static final String FIELD_EQNUMB = "EQNUMB";
  protected Integer eqnumb = null;
  public static final int CONSTRAINT_EQNUMB_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_EQNUMB_MIN = -99999999;
  public static final int CONSTRAINT_EQNUMB_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_EQNUMB_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_EQNUMB_MAX)
  public Integer getEqnumb() {
    return eqnumb;
  }

  public Equipment setEqnumb(Integer eqnumb) {
    this.eqnumb = eqnumb;
    return this;
  }
  //endregion

  //region eqname 设备名称
  public static final String FIELD_EQNAME = "EQNAME";
  protected String eqname = null;
  public static final int CONSTRAINT_EQNAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_EQNAME_LENGTH_STRING)
  public String getEqname() {
    return eqname;
  }

  public Equipment setEqname(String eqname) {
    this.eqname = eqname;
    return this;
  }
  //endregion

  //region eqcwid 班组序号
  public static final String FIELD_EQCWID = "EQCWID";
  protected Integer eqcwid = null;
  public static final int CONSTRAINT_EQCWID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_EQCWID_MIN = -99999999;
  public static final int CONSTRAINT_EQCWID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_EQCWID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_EQCWID_MAX)
  public Integer getEqcwid() {
    return eqcwid;
  }

  public Equipment setEqcwid(Integer eqcwid) {
    this.eqcwid = eqcwid;
    return this;
  }
  //endregion

  //region eqcwname 班组名称
  public static final String FIELD_EQCWNAME = "EQCWNAME";
  protected String eqcwname = null;
  public static final int CONSTRAINT_EQCWNAME_LENGTH_STRING = 16;

  public String getEqcwname() {
    return eqcwname;
  }

  public Equipment setEqcwname(String eqcwname) {
    this.eqcwname = eqcwname;
    return this;
  }
  //endregion

  //region eqwcmcu 工作中心编号
  public static final String FIELD_EQWCMCU = "EQWCMCU";
  protected String eqwcmcu = null;
  public static final int CONSTRAINT_EQWCMCU_LENGTH_STRING = 12;

  public String getEqwcmcu() {
    return eqwcmcu;
  }

  public Equipment setEqwcmcu(String eqwcmcu) {
    this.eqwcmcu = eqwcmcu;
    return this;
  }
  //endregion

  //region eqwcdl01 工作中心名称
  public static final String FIELD_EQWCDL01 = "EQWCDL01";
  protected String eqwcdl01 = null;
  public static final int CONSTRAINT_EQWCDL01_LENGTH_STRING = 30;

  public String getEqwcdl01() {
    return eqwcdl01;
  }

  public Equipment setEqwcdl01(String eqwcdl01) {
    this.eqwcdl01 = eqwcdl01;
    return this;
  }
  //endregion

  public Equipment(
      Integer eqid,
      String eqserial,
      Integer eqnumb,
      String eqname,
      Integer eqcwid,
      String eqcwname,
      String eqwcmcu,
      String eqwcdl01) {
    if (eqid != null) this.eqid = eqid;
    if (eqserial != null) this.eqserial = eqserial;
    if (eqnumb != null) this.eqnumb = eqnumb;
    if (eqname != null) this.eqname = eqname;
    if (eqcwid != null) this.eqcwid = eqcwid;
    if (eqcwname != null) this.eqcwname = eqcwname;
    if (eqwcmcu != null) this.eqwcmcu = eqwcmcu;
    if (eqwcdl01 != null) this.eqwcdl01 = eqwcdl01;
  }

  public Equipment() {
    this(null, null, null, null, null, null, null, null);
  }

  public Equipment clone() {
    return new Equipment(eqid, eqserial, eqnumb, eqname, eqcwid, eqcwname, eqwcmcu, eqwcdl01);
  }
}
