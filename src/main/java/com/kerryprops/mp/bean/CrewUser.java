package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrewUser {
  public static final String TABLE = "F30031";
  public static final String VIEW = "V30031";
  public static final String NAME = "CREWUSER";

  //region cuid 序号
  public static final String FIELD_CUID = "CUID";
  protected Integer cuid = null;
  public static final int CONSTRAINT_CUID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_CUID_MIN = -99999999;
  public static final int CONSTRAINT_CUID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getCuid() {
    return cuid;
  }

  public CrewUser setCuid(Integer cuid) {
    this.cuid = cuid;
    return this;
  }
  //endregion

  //region cucwid 班组序号
  public static final String FIELD_CUCWID = "CUCWID";
  protected Integer cucwid = null;
  public static final int CONSTRAINT_CUCWID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_CUCWID_MIN = -99999999;
  public static final int CONSTRAINT_CUCWID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_CUCWID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_CUCWID_MAX)
  public Integer getCucwid() {
    return cucwid;
  }

  public CrewUser setCucwid(Integer cucwid) {
    this.cucwid = cucwid;
    return this;
  }
  //endregion

  //region cucwname 班组名称
  public static final String FIELD_CUCWNAME = "CUCWNAME";
  protected String cucwname = null;
  public static final int CONSTRAINT_CUCWNAME_LENGTH_STRING = 30;

  public String getCucwname() {
    return cucwname;
  }

  public CrewUser setCucwname(String cucwname) {
    this.cucwname = cucwname;
    return this;
  }
  //endregion

  //region cuwcmcu 工作中心编号
  public static final String FIELD_CUWCMCU = "CUWCMCU";
  protected String cuwcmcu = null;
  public static final int CONSTRAINT_CUWCMCU_LENGTH_STRING = 12;

  public String getCuwcmcu() {
    return cuwcmcu;
  }

  public CrewUser setCuwcmcu(String cuwcmcu) {
    this.cuwcmcu = cuwcmcu;
    return this;
  }
  //endregion

  //region cuwcdl01 工作中心名称
  public static final String FIELD_CUWCDL01 = "CUWCDL01";
  protected String cuwcdl01 = null;
  public static final int CONSTRAINT_CUWCDL01_LENGTH_STRING = 30;

  public String getCuwcdl01() {
    return cuwcdl01;
  }

  public CrewUser setCuwcdl01(String cuwcdl01) {
    this.cuwcdl01 = cuwcdl01;
    return this;
  }
  //endregion

  //region cuusid 用户序号
  public static final String FIELD_CUUSID = "CUUSID";
  protected Integer cuusid = null;
  public static final int CONSTRAINT_CUUSID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_CUUSID_MIN = -99999999;
  public static final int CONSTRAINT_CUUSID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_CUUSID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_CUUSID_MAX)
  public Integer getCuusid() {
    return cuusid;
  }

  public CrewUser setCuusid(Integer cuusid) {
    this.cuusid = cuusid;
    return this;
  }
  //endregion

  //region cuusname 用户姓名
  public static final String FIELD_CUUSNAME = "CUUSNAME";
  protected String cuusname = null;
  public static final int CONSTRAINT_CUUSNAME_LENGTH_STRING = 16;

  public String getCuusname() {
    return cuusname;
  }

  public CrewUser setCuusname(String cuusname) {
    this.cuusname = cuusname;
    return this;
  }
  //endregion

  public CrewUser(
      Integer cuid,
      Integer cucwid,
      String cucwname,
      String cuwcmcu,
      String cuwcdl01,
      Integer cuusid,
      String cuusname) {
    if (cuid != null) this.cuid = cuid;
    if (cucwid != null) this.cucwid = cucwid;
    if (cucwname != null) this.cucwname = cucwname;
    if (cuwcmcu != null) this.cuwcmcu = cuwcmcu;
    if (cuwcdl01 != null) this.cuwcdl01 = cuwcdl01;
    if (cuusid != null) this.cuusid = cuusid;
    if (cuusname != null) this.cuusname = cuusname;
  }

  public CrewUser() {
    this(null, null, null, null, null, null, null);
  }

  public CrewUser clone() {
    return new CrewUser(cuid, cucwid, cucwname, cuwcmcu, cuwcdl01, cuusid, cuusname);
  }
}
