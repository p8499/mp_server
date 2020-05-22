package com.kerryprops.mp.mask;

public class UserRoleMask {
  protected long value = 0b00000;

  public boolean getUrid() {
    return (value >> 4 & 1) == 1;
  }

  public UserRoleMask setUrid(boolean urid) {
    if (urid) value |= 0b10000;
    else value &= 0b01111;
    return this;
  }

  public boolean getUrusid() {
    return (value >> 3 & 1) == 1;
  }

  public UserRoleMask setUrusid(boolean urusid) {
    if (urusid) value |= 0b01000;
    else value &= 0b10111;
    return this;
  }

  public boolean getUrroid() {
    return (value >> 2 & 1) == 1;
  }

  public UserRoleMask setUrroid(boolean urroid) {
    if (urroid) value |= 0b00100;
    else value &= 0b11011;
    return this;
  }

  public boolean getUrusname() {
    return (value >> 1 & 1) == 1;
  }

  public UserRoleMask setUrusname(boolean urusname) {
    if (urusname) value |= 0b00010;
    else value &= 0b11101;
    return this;
  }

  public boolean getUrroname() {
    return (value >> 0 & 1) == 1;
  }

  public UserRoleMask setUrroname(boolean urroname) {
    if (urroname) value |= 0b00001;
    else value &= 0b11110;
    return this;
  }

  public UserRoleMask(
      boolean urid, boolean urusid, boolean urroid, boolean urusname, boolean urroname) {
    setUrid(urid);
    setUrusid(urusid);
    setUrroid(urroid);
    setUrusname(urusname);
    setUrroname(urroname);
  }

  public UserRoleMask(long v) {
    value = v;
  }

  public UserRoleMask() {}

  public UserRoleMask all(boolean b) {
    setUrid(b);
    setUrusid(b);
    setUrroid(b);
    setUrusname(b);
    setUrroname(b);
    return this;
  }

  public UserRoleMask keys(boolean b) {
    setUrid(b);
    return this;
  }

  public UserRoleMask attributes(boolean b) {
    setUrusid(b);
    setUrroid(b);
    return this;
  }

  public UserRoleMask physicals(boolean b) {
    setUrid(b);
    setUrusid(b);
    setUrroid(b);
    return this;
  }

  public UserRoleMask virtuals(boolean b) {
    setUrusname(b);
    setUrroname(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "urid":
        return getUrid();
      case "urusid":
        return getUrusid();
      case "urroid":
        return getUrroid();
      case "urusname":
        return getUrusname();
      case "urroname":
        return getUrroname();
    }
    return false;
  }

  public UserRoleMask set(String p, boolean b) {
    switch (p) {
      case "urid":
        setUrid(b);
        break;
      case "urusid":
        setUrusid(b);
        break;
      case "urroid":
        setUrroid(b);
        break;
      case "urusname":
        setUrusname(b);
        break;
      case "urroname":
        setUrroname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
