package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole {
  public static final String TABLE = "F00921";
  public static final String VIEW = "V00921";
  public static final String NAME = "USERROLE";

  //region urid 序号
  public static final String FIELD_URID = "URID";
  protected Integer urid = null;
  public static final int CONSTRAINT_URID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_URID_MIN = -99999999;
  public static final int CONSTRAINT_URID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getUrid() {
    return urid;
  }

  public UserRole setUrid(Integer urid) {
    this.urid = urid;
    return this;
  }
  //endregion

  //region urusid 用户序号
  public static final String FIELD_URUSID = "URUSID";
  protected Integer urusid = null;
  public static final int CONSTRAINT_URUSID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_URUSID_MIN = -99999999;
  public static final int CONSTRAINT_URUSID_MAX = 99999999;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_URUSID_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_URUSID_MAX)
  public Integer getUrusid() {
    return urusid;
  }

  public UserRole setUrusid(Integer urusid) {
    this.urusid = urusid;
    return this;
  }
  //endregion

  //region urroid 角色编号
  public static final String FIELD_URROID = "URROID";
  protected String urroid = null;
  public static final int CONSTRAINT_URROID_LENGTH_STRING = 8;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_URROID_LENGTH_STRING)
  public String getUrroid() {
    return urroid;
  }

  public UserRole setUrroid(String urroid) {
    this.urroid = urroid;
    return this;
  }
  //endregion

  //region urusname 用户姓名
  public static final String FIELD_URUSNAME = "URUSNAME";
  protected String urusname = null;
  public static final int CONSTRAINT_URUSNAME_LENGTH_STRING = 16;

  public String getUrusname() {
    return urusname;
  }

  public UserRole setUrusname(String urusname) {
    this.urusname = urusname;
    return this;
  }
  //endregion

  //region urroname 角色名称
  public static final String FIELD_URRONAME = "URRONAME";
  protected String urroname = null;
  public static final int CONSTRAINT_URRONAME_LENGTH_STRING = 16;

  public String getUrroname() {
    return urroname;
  }

  public UserRole setUrroname(String urroname) {
    this.urroname = urroname;
    return this;
  }
  //endregion

  public UserRole(Integer urid, Integer urusid, String urroid, String urusname, String urroname) {
    if (urid != null) this.urid = urid;
    if (urusid != null) this.urusid = urusid;
    if (urroid != null) this.urroid = urroid;
    if (urusname != null) this.urusname = urusname;
    if (urroname != null) this.urroname = urroname;
  }

  public UserRole() {
    this(null, null, null, null, null);
  }

  public UserRole clone() {
    return new UserRole(urid, urusid, urroid, urusname, urroname);
  }
}
