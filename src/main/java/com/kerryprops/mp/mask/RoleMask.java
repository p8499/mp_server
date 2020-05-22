package com.kerryprops.mp.mask;

public class RoleMask {
  protected long value = 0b00;

  public boolean getRoid() {
    return (value >> 1 & 1) == 1;
  }

  public RoleMask setRoid(boolean roid) {
    if (roid) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getRoname() {
    return (value >> 0 & 1) == 1;
  }

  public RoleMask setRoname(boolean roname) {
    if (roname) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public RoleMask(boolean roid, boolean roname) {
    setRoid(roid);
    setRoname(roname);
  }

  public RoleMask(long v) {
    value = v;
  }

  public RoleMask() {}

  public RoleMask all(boolean b) {
    setRoid(b);
    setRoname(b);
    return this;
  }

  public RoleMask keys(boolean b) {
    setRoid(b);
    return this;
  }

  public RoleMask attributes(boolean b) {
    setRoname(b);
    return this;
  }

  public RoleMask physicals(boolean b) {
    setRoid(b);
    setRoname(b);
    return this;
  }

  public RoleMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "roid":
        return getRoid();
      case "roname":
        return getRoname();
    }
    return false;
  }

  public RoleMask set(String p, boolean b) {
    switch (p) {
      case "roid":
        setRoid(b);
        break;
      case "roname":
        setRoname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
