package com.kerryprops.mp.mask;

public class PrivilegeMask {
  protected long value = 0b00;

  public boolean getPrid() {
    return (value >> 1 & 1) == 1;
  }

  public PrivilegeMask setPrid(boolean prid) {
    if (prid) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getPrname() {
    return (value >> 0 & 1) == 1;
  }

  public PrivilegeMask setPrname(boolean prname) {
    if (prname) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public PrivilegeMask(boolean prid, boolean prname) {
    setPrid(prid);
    setPrname(prname);
  }

  public PrivilegeMask(long v) {
    value = v;
  }

  public PrivilegeMask() {}

  public PrivilegeMask all(boolean b) {
    setPrid(b);
    setPrname(b);
    return this;
  }

  public PrivilegeMask keys(boolean b) {
    setPrid(b);
    return this;
  }

  public PrivilegeMask attributes(boolean b) {
    setPrname(b);
    return this;
  }

  public PrivilegeMask physicals(boolean b) {
    setPrid(b);
    setPrname(b);
    return this;
  }

  public PrivilegeMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "prid":
        return getPrid();
      case "prname":
        return getPrname();
    }
    return false;
  }

  public PrivilegeMask set(String p, boolean b) {
    switch (p) {
      case "prid":
        setPrid(b);
        break;
      case "prname":
        setPrname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
