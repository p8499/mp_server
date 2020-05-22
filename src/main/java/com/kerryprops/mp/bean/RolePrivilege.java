package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolePrivilege {
  public static final String TABLE = "F00941";
  public static final String VIEW = "V00941";
  public static final String NAME = "ROLEPRIVILEGE";

  //region rpid 序号
  public static final String FIELD_RPID = "RPID";
  protected Integer rpid = null;
  public static final int CONSTRAINT_RPID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_RPID_MIN = -99999999;
  public static final int CONSTRAINT_RPID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getRpid() {
    return rpid;
  }

  public RolePrivilege setRpid(Integer rpid) {
    this.rpid = rpid;
    return this;
  }
  //endregion

  //region rproid 角色编号
  public static final String FIELD_RPROID = "RPROID";
  protected String rproid = null;
  public static final int CONSTRAINT_RPROID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RPROID_LENGTH_STRING)
  public String getRproid() {
    return rproid;
  }

  public RolePrivilege setRproid(String rproid) {
    this.rproid = rproid;
    return this;
  }
  //endregion

  //region rpprid 权限编号
  public static final String FIELD_RPPRID = "RPPRID";
  protected String rpprid = null;
  public static final int CONSTRAINT_RPPRID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RPPRID_LENGTH_STRING)
  public String getRpprid() {
    return rpprid;
  }

  public RolePrivilege setRpprid(String rpprid) {
    this.rpprid = rpprid;
    return this;
  }
  //endregion

  //region rproname 角色名称
  public static final String FIELD_RPRONAME = "RPRONAME";
  protected String rproname = null;
  public static final int CONSTRAINT_RPRONAME_LENGTH_STRING = 16;

  public String getRproname() {
    return rproname;
  }

  public RolePrivilege setRproname(String rproname) {
    this.rproname = rproname;
    return this;
  }
  //endregion

  //region rpprname 权限名称
  public static final String FIELD_RPPRNAME = "RPPRNAME";
  protected String rpprname = null;
  public static final int CONSTRAINT_RPPRNAME_LENGTH_STRING = 16;

  public String getRpprname() {
    return rpprname;
  }

  public RolePrivilege setRpprname(String rpprname) {
    this.rpprname = rpprname;
    return this;
  }
  //endregion

  public RolePrivilege(
      Integer rpid, String rproid, String rpprid, String rproname, String rpprname) {
    if (rpid != null) this.rpid = rpid;
    if (rproid != null) this.rproid = rproid;
    if (rpprid != null) this.rpprid = rpprid;
    if (rproname != null) this.rproname = rproname;
    if (rpprname != null) this.rpprname = rpprname;
  }

  public RolePrivilege() {
    this(null, null, null, null, null);
  }

  public RolePrivilege clone() {
    return new RolePrivilege(rpid, rproid, rpprid, rproname, rpprname);
  }
}
