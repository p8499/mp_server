package com.kerryprops.mp.mask;

public class InspectionTypeMask {
  protected long value = 0b00;

  public boolean getItid() {
    return (value >> 1 & 1) == 1;
  }

  public InspectionTypeMask setItid(boolean itid) {
    if (itid) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getItname() {
    return (value >> 0 & 1) == 1;
  }

  public InspectionTypeMask setItname(boolean itname) {
    if (itname) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public InspectionTypeMask(boolean itid, boolean itname) {
    setItid(itid);
    setItname(itname);
  }

  public InspectionTypeMask(long v) {
    value = v;
  }

  public InspectionTypeMask() {}

  public InspectionTypeMask all(boolean b) {
    setItid(b);
    setItname(b);
    return this;
  }

  public InspectionTypeMask keys(boolean b) {
    setItid(b);
    return this;
  }

  public InspectionTypeMask attributes(boolean b) {
    setItname(b);
    return this;
  }

  public InspectionTypeMask physicals(boolean b) {
    setItid(b);
    setItname(b);
    return this;
  }

  public InspectionTypeMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "itid":
        return getItid();
      case "itname":
        return getItname();
    }
    return false;
  }

  public InspectionTypeMask set(String p, boolean b) {
    switch (p) {
      case "itid":
        setItid(b);
        break;
      case "itname":
        setItname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
