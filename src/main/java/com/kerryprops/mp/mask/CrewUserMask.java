package com.kerryprops.mp.mask;

public class CrewUserMask {
  protected long value = 0b0000000;

  public boolean getCuid() {
    return (value >> 6 & 1) == 1;
  }

  public CrewUserMask setCuid(boolean cuid) {
    if (cuid) value |= 0b1000000;
    else value &= 0b0111111;
    return this;
  }

  public boolean getCucwid() {
    return (value >> 5 & 1) == 1;
  }

  public CrewUserMask setCucwid(boolean cucwid) {
    if (cucwid) value |= 0b0100000;
    else value &= 0b1011111;
    return this;
  }

  public boolean getCucwname() {
    return (value >> 4 & 1) == 1;
  }

  public CrewUserMask setCucwname(boolean cucwname) {
    if (cucwname) value |= 0b0010000;
    else value &= 0b1101111;
    return this;
  }

  public boolean getCuwcmcu() {
    return (value >> 3 & 1) == 1;
  }

  public CrewUserMask setCuwcmcu(boolean cuwcmcu) {
    if (cuwcmcu) value |= 0b0001000;
    else value &= 0b1110111;
    return this;
  }

  public boolean getCuwcdl01() {
    return (value >> 2 & 1) == 1;
  }

  public CrewUserMask setCuwcdl01(boolean cuwcdl01) {
    if (cuwcdl01) value |= 0b0000100;
    else value &= 0b1111011;
    return this;
  }

  public boolean getCuusid() {
    return (value >> 1 & 1) == 1;
  }

  public CrewUserMask setCuusid(boolean cuusid) {
    if (cuusid) value |= 0b0000010;
    else value &= 0b1111101;
    return this;
  }

  public boolean getCuusname() {
    return (value >> 0 & 1) == 1;
  }

  public CrewUserMask setCuusname(boolean cuusname) {
    if (cuusname) value |= 0b0000001;
    else value &= 0b1111110;
    return this;
  }

  public CrewUserMask(
      boolean cuid,
      boolean cucwid,
      boolean cucwname,
      boolean cuwcmcu,
      boolean cuwcdl01,
      boolean cuusid,
      boolean cuusname) {
    setCuid(cuid);
    setCucwid(cucwid);
    setCucwname(cucwname);
    setCuwcmcu(cuwcmcu);
    setCuwcdl01(cuwcdl01);
    setCuusid(cuusid);
    setCuusname(cuusname);
  }

  public CrewUserMask(long v) {
    value = v;
  }

  public CrewUserMask() {}

  public CrewUserMask all(boolean b) {
    setCuid(b);
    setCucwid(b);
    setCucwname(b);
    setCuwcmcu(b);
    setCuwcdl01(b);
    setCuusid(b);
    setCuusname(b);
    return this;
  }

  public CrewUserMask keys(boolean b) {
    setCuid(b);
    return this;
  }

  public CrewUserMask attributes(boolean b) {
    setCucwid(b);
    setCuusid(b);
    return this;
  }

  public CrewUserMask physicals(boolean b) {
    setCuid(b);
    setCucwid(b);
    setCuusid(b);
    return this;
  }

  public CrewUserMask virtuals(boolean b) {
    setCucwname(b);
    setCuwcmcu(b);
    setCuwcdl01(b);
    setCuusname(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "cuid":
        return getCuid();
      case "cucwid":
        return getCucwid();
      case "cucwname":
        return getCucwname();
      case "cuwcmcu":
        return getCuwcmcu();
      case "cuwcdl01":
        return getCuwcdl01();
      case "cuusid":
        return getCuusid();
      case "cuusname":
        return getCuusname();
    }
    return false;
  }

  public CrewUserMask set(String p, boolean b) {
    switch (p) {
      case "cuid":
        setCuid(b);
        break;
      case "cucwid":
        setCucwid(b);
        break;
      case "cucwname":
        setCucwname(b);
        break;
      case "cuwcmcu":
        setCuwcmcu(b);
        break;
      case "cuwcdl01":
        setCuwcdl01(b);
        break;
      case "cuusid":
        setCuusid(b);
        break;
      case "cuusname":
        setCuusname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
