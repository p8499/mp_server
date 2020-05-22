package com.kerryprops.mp.mask;

public class RolePrivilegeMask {
  protected long value = 0b00000;

  public boolean getRpid() {
    return (value >> 4 & 1) == 1;
  }

  public RolePrivilegeMask setRpid(boolean rpid) {
    if (rpid) value |= 0b10000;
    else value &= 0b01111;
    return this;
  }

  public boolean getRproid() {
    return (value >> 3 & 1) == 1;
  }

  public RolePrivilegeMask setRproid(boolean rproid) {
    if (rproid) value |= 0b01000;
    else value &= 0b10111;
    return this;
  }

  public boolean getRpprid() {
    return (value >> 2 & 1) == 1;
  }

  public RolePrivilegeMask setRpprid(boolean rpprid) {
    if (rpprid) value |= 0b00100;
    else value &= 0b11011;
    return this;
  }

  public boolean getRproname() {
    return (value >> 1 & 1) == 1;
  }

  public RolePrivilegeMask setRproname(boolean rproname) {
    if (rproname) value |= 0b00010;
    else value &= 0b11101;
    return this;
  }

  public boolean getRpprname() {
    return (value >> 0 & 1) == 1;
  }

  public RolePrivilegeMask setRpprname(boolean rpprname) {
    if (rpprname) value |= 0b00001;
    else value &= 0b11110;
    return this;
  }

  public RolePrivilegeMask(
      boolean rpid, boolean rproid, boolean rpprid, boolean rproname, boolean rpprname) {
    setRpid(rpid);
    setRproid(rproid);
    setRpprid(rpprid);
    setRproname(rproname);
    setRpprname(rpprname);
  }

  public RolePrivilegeMask(long v) {
    value = v;
  }

  public RolePrivilegeMask() {}

  public RolePrivilegeMask all(boolean b) {
    setRpid(b);
    setRproid(b);
    setRpprid(b);
    setRproname(b);
    setRpprname(b);
    return this;
  }

  public RolePrivilegeMask keys(boolean b) {
    setRpid(b);
    return this;
  }

  public RolePrivilegeMask attributes(boolean b) {
    setRproid(b);
    setRpprid(b);
    return this;
  }

  public RolePrivilegeMask physicals(boolean b) {
    setRpid(b);
    setRproid(b);
    setRpprid(b);
    return this;
  }

  public RolePrivilegeMask virtuals(boolean b) {
    setRproname(b);
    setRpprname(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "rpid":
        return getRpid();
      case "rproid":
        return getRproid();
      case "rpprid":
        return getRpprid();
      case "rproname":
        return getRproname();
      case "rpprname":
        return getRpprname();
    }
    return false;
  }

  public RolePrivilegeMask set(String p, boolean b) {
    switch (p) {
      case "rpid":
        setRpid(b);
        break;
      case "rproid":
        setRproid(b);
        break;
      case "rpprid":
        setRpprid(b);
        break;
      case "rproname":
        setRproname(b);
        break;
      case "rpprname":
        setRpprname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
