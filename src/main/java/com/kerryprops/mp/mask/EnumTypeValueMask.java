package com.kerryprops.mp.mask;

public class EnumTypeValueMask {
  protected long value = 0b00000;

  public boolean getEvid() {
    return (value >> 4 & 1) == 1;
  }

  public EnumTypeValueMask setEvid(boolean evid) {
    if (evid) value |= 0b10000;
    else value &= 0b01111;
    return this;
  }

  public boolean getEvetid() {
    return (value >> 3 & 1) == 1;
  }

  public EnumTypeValueMask setEvetid(boolean evetid) {
    if (evetid) value |= 0b01000;
    else value &= 0b10111;
    return this;
  }

  public boolean getEvval() {
    return (value >> 2 & 1) == 1;
  }

  public EnumTypeValueMask setEvval(boolean evval) {
    if (evval) value |= 0b00100;
    else value &= 0b11011;
    return this;
  }

  public boolean getEvdescription() {
    return (value >> 1 & 1) == 1;
  }

  public EnumTypeValueMask setEvdescription(boolean evdescription) {
    if (evdescription) value |= 0b00010;
    else value &= 0b11101;
    return this;
  }

  public boolean getEvetname() {
    return (value >> 0 & 1) == 1;
  }

  public EnumTypeValueMask setEvetname(boolean evetname) {
    if (evetname) value |= 0b00001;
    else value &= 0b11110;
    return this;
  }

  public EnumTypeValueMask(
      boolean evid, boolean evetid, boolean evval, boolean evdescription, boolean evetname) {
    setEvid(evid);
    setEvetid(evetid);
    setEvval(evval);
    setEvdescription(evdescription);
    setEvetname(evetname);
  }

  public EnumTypeValueMask(long v) {
    value = v;
  }

  public EnumTypeValueMask() {}

  public EnumTypeValueMask all(boolean b) {
    setEvid(b);
    setEvetid(b);
    setEvval(b);
    setEvdescription(b);
    setEvetname(b);
    return this;
  }

  public EnumTypeValueMask keys(boolean b) {
    setEvid(b);
    return this;
  }

  public EnumTypeValueMask attributes(boolean b) {
    setEvetid(b);
    setEvval(b);
    setEvdescription(b);
    return this;
  }

  public EnumTypeValueMask physicals(boolean b) {
    setEvid(b);
    setEvetid(b);
    setEvval(b);
    setEvdescription(b);
    return this;
  }

  public EnumTypeValueMask virtuals(boolean b) {
    setEvetname(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "evid":
        return getEvid();
      case "evetid":
        return getEvetid();
      case "evval":
        return getEvval();
      case "evdescription":
        return getEvdescription();
      case "evetname":
        return getEvetname();
    }
    return false;
  }

  public EnumTypeValueMask set(String p, boolean b) {
    switch (p) {
      case "evid":
        setEvid(b);
        break;
      case "evetid":
        setEvetid(b);
        break;
      case "evval":
        setEvval(b);
        break;
      case "evdescription":
        setEvdescription(b);
        break;
      case "evetname":
        setEvetname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
