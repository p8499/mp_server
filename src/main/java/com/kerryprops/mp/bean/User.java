package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
  public static final String TABLE = "F0092";
  public static final String VIEW = "V0092";
  public static final String NAME = "USER";

  //region usid 用户序号
  public static final String FIELD_USID = "USID";
  protected Integer usid = null;
  public static final int CONSTRAINT_USID_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_USID_MIN = -99999999;
  public static final int CONSTRAINT_USID_MAX = 99999999;

  @javax.validation.constraints.Null(groups = {Insert.class})
  @javax.validation.constraints.NotNull(groups = {Update.class})
  public Integer getUsid() {
    return usid;
  }

  public User setUsid(Integer usid) {
    this.usid = usid;
    return this;
  }
  //endregion

  //region usan8 JDE地址号
  public static final String FIELD_USAN8 = "USAN8";
  protected Integer usan8 = null;
  public static final int CONSTRAINT_USAN8_LENGTH_INTEGER = 8;
  public static final int CONSTRAINT_USAN8_MIN = -99999999;
  public static final int CONSTRAINT_USAN8_MAX = 99999999;

  @javax.validation.constraints.Min(value = CONSTRAINT_USAN8_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_USAN8_MAX)
  public Integer getUsan8() {
    return usan8;
  }

  public User setUsan8(Integer usan8) {
    this.usan8 = usan8;
    return this;
  }
  //endregion

  //region uscell 手机号
  public static final String FIELD_USCELL = "USCELL";
  protected String uscell = null;
  public static final int CONSTRAINT_USCELL_LENGTH_STRING = 11;

  @javax.validation.constraints.Size(max = CONSTRAINT_USCELL_LENGTH_STRING)
  public String getUscell() {
    return uscell;
  }

  public User setUscell(String uscell) {
    this.uscell = uscell;
    return this;
  }
  //endregion

  //region usmail 电子邮箱
  public static final String FIELD_USMAIL = "USMAIL";
  protected String usmail = null;
  public static final int CONSTRAINT_USMAIL_LENGTH_STRING = 32;

  @javax.validation.constraints.Size(max = CONSTRAINT_USMAIL_LENGTH_STRING)
  public String getUsmail() {
    return usmail;
  }

  public User setUsmail(String usmail) {
    this.usmail = usmail;
    return this;
  }
  //endregion

  //region usname 姓名
  public static final String FIELD_USNAME = "USNAME";
  protected String usname = null;
  public static final int CONSTRAINT_USNAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_USNAME_LENGTH_STRING)
  public String getUsname() {
    return usname;
  }

  public User setUsname(String usname) {
    this.usname = usname;
    return this;
  }
  //endregion

  //region uspswd 密码
  public static final String FIELD_USPSWD = "USPSWD";
  protected String uspswd = null;
  public static final int CONSTRAINT_USPSWD_LENGTH_STRING = 32;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_USPSWD_LENGTH_STRING)
  public String getUspswd() {
    return uspswd;
  }

  public User setUspswd(String uspswd) {
    this.uspswd = uspswd;
    return this;
  }
  //endregion

  //region usstatus 状态
  public static final String FIELD_USSTATUS = "USSTATUS";
  public static final Integer USSTATUS_ACTIVE = 0;
  public static final Integer USSTATUS_LOCKED = 1;
  public static final Integer DEFAULT_USSTATUS = 0;
  protected Integer usstatus = DEFAULT_USSTATUS;
  public static final int CONSTRAINT_USSTATUS_LENGTH_INTEGER = 1;
  public static final int CONSTRAINT_USSTATUS_MIN = -9;
  public static final int CONSTRAINT_USSTATUS_MAX = 9;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Min(value = CONSTRAINT_USSTATUS_MIN)
  @javax.validation.constraints.Max(value = CONSTRAINT_USSTATUS_MAX)
  public Integer getUsstatus() {
    return usstatus;
  }

  public User setUsstatus(Integer usstatus) {
    this.usstatus = usstatus;
    return this;
  }
  //endregion

  public User(
      Integer usid,
      Integer usan8,
      String uscell,
      String usmail,
      String usname,
      String uspswd,
      Integer usstatus) {
    if (usid != null) this.usid = usid;
    if (usan8 != null) this.usan8 = usan8;
    if (uscell != null) this.uscell = uscell;
    if (usmail != null) this.usmail = usmail;
    if (usname != null) this.usname = usname;
    if (uspswd != null) this.uspswd = uspswd;
    if (usstatus != null) this.usstatus = usstatus;
  }

  public User() {
    this(null, null, null, null, null, null, null);
  }

  public User clone() {
    return new User(usid, usan8, uscell, usmail, usname, uspswd, usstatus);
  }
}
