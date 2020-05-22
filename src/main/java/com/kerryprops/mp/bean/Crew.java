package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Crew {
  public static final String TABLE = "F3003";
  public static final String VIEW = "V3003";
  public static final String NAME = "CREW";

  //region cwid 班组序号
  public static final String FIELD_CWID = "CWID";
  protected Integer cwid = null;
  public static final int CONSTRAINT_CWID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_CWID_MIN = -99999999;
  public static final int CONSTRAINT_CWID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getCwid() {
    return cwid;
  }

  public Crew setCwid(Integer cwid) {
    this.cwid = cwid;
    return this;
  }
  //endregion

  //region cwname 班组名称
  public static final String FIELD_CWNAME = "CWNAME";
  protected String cwname = null;
  public static final int CONSTRAINT_CWNAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_CWNAME_LENGTH_STRING)
  public String getCwname() {
    return cwname;
  }

  public Crew setCwname(String cwname) {
    this.cwname = cwname;
    return this;
  }
  //endregion

  //region cwan8 JDE地址号
  public static final String FIELD_CWAN8 = "CWAN8";
  protected Integer cwan8 = null;
  public static final int CONSTRAINT_CWAN8_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_CWAN8_MIN = -99999999;
  public static final int CONSTRAINT_CWAN8_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_CWAN8_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_CWAN8_MAX)
  public Integer getCwan8() {
    return cwan8;
  }

  public Crew setCwan8(Integer cwan8) {
    this.cwan8 = cwan8;
    return this;
  }
  //endregion

  //region cwwcmcu 工作中心编号
  public static final String FIELD_CWWCMCU = "CWWCMCU";
  protected String cwwcmcu = null;
  public static final int CONSTRAINT_CWWCMCU_LENGTH_STRING = 12;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_CWWCMCU_LENGTH_STRING)
  public String getCwwcmcu() {
    return cwwcmcu;
  }

  public Crew setCwwcmcu(String cwwcmcu) {
    this.cwwcmcu = cwwcmcu;
    return this;
  }
  //endregion

  //region cwwcdl01 工作中心名称
  public static final String FIELD_CWWCDL01 = "CWWCDL01";
  protected String cwwcdl01 = null;
  public static final int CONSTRAINT_CWWCDL01_LENGTH_STRING = 30;

  public String getCwwcdl01() {
    return cwwcdl01;
  }

  public Crew setCwwcdl01(String cwwcdl01) {
    this.cwwcdl01 = cwwcdl01;
    return this;
  }
  //endregion

  public Crew(Integer cwid, String cwname, Integer cwan8, String cwwcmcu, String cwwcdl01) {
    if (cwid != null) this.cwid = cwid;
    if (cwname != null) this.cwname = cwname;
    if (cwan8 != null) this.cwan8 = cwan8;
    if (cwwcmcu != null) this.cwwcmcu = cwwcmcu;
    if (cwwcdl01 != null) this.cwwcdl01 = cwwcdl01;
  }

  public Crew() {
    this(null, null, null, null, null);
  }

  public Crew clone() {
    return new Crew(cwid, cwname, cwan8, cwwcmcu, cwwcdl01);
  }
}
