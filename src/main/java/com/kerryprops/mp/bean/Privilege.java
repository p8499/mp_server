package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Privilege {
  public static final String TABLE = "F0095";
  public static final String VIEW = "V0095";
  public static final String NAME = "PRIVILEGE";

  //region prid 权限编号
  public static final String FIELD_PRID = "PRID";
  protected String prid = null;
  public static final int CONSTRAINT_PRID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_PRID_LENGTH_STRING)
  public String getPrid() {
    return prid;
  }

  public Privilege setPrid(String prid) {
    this.prid = prid;
    return this;
  }
  //endregion

  //region prname 权限名称
  public static final String FIELD_PRNAME = "PRNAME";
  protected String prname = null;
  public static final int CONSTRAINT_PRNAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_PRNAME_LENGTH_STRING)
  public String getPrname() {
    return prname;
  }

  public Privilege setPrname(String prname) {
    this.prname = prname;
    return this;
  }
  //endregion

  public Privilege(String prid, String prname) {
    if (prid != null) this.prid = prid;
    if (prname != null) this.prname = prname;
  }

  public Privilege() {
    this(null, null);
  }

  public Privilege clone() {
    return new Privilege(prid, prname);
  }
}
