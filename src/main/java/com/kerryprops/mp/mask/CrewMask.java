package com.kerryprops.mp.mask;

public class CrewMask {
  protected long value = 0b00000;

  public boolean getCwid() {
    return (value >> 4 & 1) == 1;
  }

  public CrewMask setCwid(boolean cwid) {
    if (cwid) value |= 0b10000;
    else value &= 0b01111;
    return this;
  }

  public boolean getCwname() {
    return (value >> 3 & 1) == 1;
  }

  public CrewMask setCwname(boolean cwname) {
    if (cwname) value |= 0b01000;
    else value &= 0b10111;
    return this;
  }

  public boolean getCwan8() {
    return (value >> 2 & 1) == 1;
  }

  public CrewMask setCwan8(boolean cwan8) {
    if (cwan8) value |= 0b00100;
    else value &= 0b11011;
    return this;
  }

  public boolean getCwwcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public CrewMask setCwwcmcu(boolean cwwcmcu) {
    if (cwwcmcu) value |= 0b00010;
    else value &= 0b11101;
    return this;
  }

  public boolean getCwwcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public CrewMask setCwwcdl01(boolean cwwcdl01) {
    if (cwwcdl01) value |= 0b00001;
    else value &= 0b11110;
    return this;
  }

  public CrewMask(boolean cwid, boolean cwname, boolean cwan8, boolean cwwcmcu, boolean cwwcdl01) {
    setCwid(cwid);
    setCwname(cwname);
    setCwan8(cwan8);
    setCwwcmcu(cwwcmcu);
    setCwwcdl01(cwwcdl01);
  }

  public CrewMask(long v) {
    value = v;
  }

  public CrewMask() {}

  public CrewMask all(boolean b) {
    setCwid(b);
    setCwname(b);
    setCwan8(b);
    setCwwcmcu(b);
    setCwwcdl01(b);
    return this;
  }

  public CrewMask keys(boolean b) {
    setCwid(b);
    return this;
  }

  public CrewMask attributes(boolean b) {
    setCwname(b);
    setCwan8(b);
    setCwwcmcu(b);
    return this;
  }

  public CrewMask physicals(boolean b) {
    setCwid(b);
    setCwname(b);
    setCwan8(b);
    setCwwcmcu(b);
    return this;
  }

  public CrewMask virtuals(boolean b) {
    setCwwcdl01(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "cwid":
        return getCwid();
      case "cwname":
        return getCwname();
      case "cwan8":
        return getCwan8();
      case "cwwcmcu":
        return getCwwcmcu();
      case "cwwcdl01":
        return getCwwcdl01();
    }
    return false;
  }

  public CrewMask set(String p, boolean b) {
    switch (p) {
      case "cwid":
        setCwid(b);
        break;
      case "cwname":
        setCwname(b);
        break;
      case "cwan8":
        setCwan8(b);
        break;
      case "cwwcmcu":
        setCwwcmcu(b);
        break;
      case "cwwcdl01":
        setCwwcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
