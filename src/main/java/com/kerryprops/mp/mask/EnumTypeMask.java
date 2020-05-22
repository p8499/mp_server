package com.kerryprops.mp.mask;

public class EnumTypeMask {
  protected long value = 0b00;

  public boolean getEtid() {
    return (value >> 1 & 1) == 1;
  }

  public EnumTypeMask setEtid(boolean etid) {
    if (etid) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getEtname() {
    return (value >> 0 & 1) == 1;
  }

  public EnumTypeMask setEtname(boolean etname) {
    if (etname) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public EnumTypeMask(boolean etid, boolean etname) {
    setEtid(etid);
    setEtname(etname);
  }

  public EnumTypeMask(long v) {
    value = v;
  }

  public EnumTypeMask() {}

  public EnumTypeMask all(boolean b) {
    setEtid(b);
    setEtname(b);
    return this;
  }

  public EnumTypeMask keys(boolean b) {
    setEtid(b);
    return this;
  }

  public EnumTypeMask attributes(boolean b) {
    setEtname(b);
    return this;
  }

  public EnumTypeMask physicals(boolean b) {
    setEtid(b);
    setEtname(b);
    return this;
  }

  public EnumTypeMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "etid":
        return getEtid();
      case "etname":
        return getEtname();
    }
    return false;
  }

  public EnumTypeMask set(String p, boolean b) {
    switch (p) {
      case "etid":
        setEtid(b);
        break;
      case "etname":
        setEtname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
