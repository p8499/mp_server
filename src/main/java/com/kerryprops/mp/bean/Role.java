package com.kerryprops.mp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {
  public static final String TABLE = "F0094";
  public static final String VIEW = "V0094";
  public static final String NAME = "ROLE";

  //region roid 角色编号
  public static final String FIELD_ROID = "ROID";
  protected String roid = null;
  public static final int CONSTRAINT_ROID_LENGTH_STRING = 4;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_ROID_LENGTH_STRING)
  public String getRoid() {
    return roid;
  }

  public Role setRoid(String roid) {
    this.roid = roid;
    return this;
  }
  //endregion

  //region roname 角色名称
  public static final String FIELD_RONAME = "RONAME";
  protected String roname = null;
  public static final int CONSTRAINT_RONAME_LENGTH_STRING = 16;

  @javax.validation.constraints.NotNull(groups = {Insert.class, Update.class})
  @javax.validation.constraints.Size(max = CONSTRAINT_RONAME_LENGTH_STRING)
  public String getRoname() {
    return roname;
  }

  public Role setRoname(String roname) {
    this.roname = roname;
    return this;
  }
  //endregion

  public Role(String roid, String roname) {
    if (roid != null) this.roid = roid;
    if (roname != null) this.roname = roname;
  }

  public Role() {
    this(null, null);
  }

  public Role clone() {
    return new Role(roid, roname);
  }
}
