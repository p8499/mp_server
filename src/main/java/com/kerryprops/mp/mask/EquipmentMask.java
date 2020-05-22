package com.kerryprops.mp.mask;

public class EquipmentMask {
  protected long value = 0b00000000;

  public boolean getEqid() {
    return (value >> 7 & 1) == 1;
  }

  public EquipmentMask setEqid(boolean eqid) {
    if (eqid) value |= 0b10000000;
    else value &= 0b01111111;
    return this;
  }

  public boolean getEqserial() {
    return (value >> 6 & 1) == 1;
  }

  public EquipmentMask setEqserial(boolean eqserial) {
    if (eqserial) value |= 0b01000000;
    else value &= 0b10111111;
    return this;
  }

  public boolean getEqnumb() {
    return (value >> 5 & 1) == 1;
  }

  public EquipmentMask setEqnumb(boolean eqnumb) {
    if (eqnumb) value |= 0b00100000;
    else value &= 0b11011111;
    return this;
  }

  public boolean getEqname() {
    return (value >> 4 & 1) == 1;
  }

  public EquipmentMask setEqname(boolean eqname) {
    if (eqname) value |= 0b00010000;
    else value &= 0b11101111;
    return this;
  }

  public boolean getEqcwid() {
    return (value >> 3 & 1) == 1;
  }

  public EquipmentMask setEqcwid(boolean eqcwid) {
    if (eqcwid) value |= 0b00001000;
    else value &= 0b11110111;
    return this;
  }

  public boolean getEqcwname() {
    return (value >> 2 & 1) == 1;
  }

  public EquipmentMask setEqcwname(boolean eqcwname) {
    if (eqcwname) value |= 0b00000100;
    else value &= 0b11111011;
    return this;
  }

  public boolean getEqwcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public EquipmentMask setEqwcmcu(boolean eqwcmcu) {
    if (eqwcmcu) value |= 0b00000010;
    else value &= 0b11111101;
    return this;
  }

  public boolean getEqwcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public EquipmentMask setEqwcdl01(boolean eqwcdl01) {
    if (eqwcdl01) value |= 0b00000001;
    else value &= 0b11111110;
    return this;
  }

  public EquipmentMask(
      boolean eqid,
      boolean eqserial,
      boolean eqnumb,
      boolean eqname,
      boolean eqcwid,
      boolean eqcwname,
      boolean eqwcmcu,
      boolean eqwcdl01) {
    setEqid(eqid);
    setEqserial(eqserial);
    setEqnumb(eqnumb);
    setEqname(eqname);
    setEqcwid(eqcwid);
    setEqcwname(eqcwname);
    setEqwcmcu(eqwcmcu);
    setEqwcdl01(eqwcdl01);
  }

  public EquipmentMask(long v) {
    value = v;
  }

  public EquipmentMask() {}

  public EquipmentMask all(boolean b) {
    setEqid(b);
    setEqserial(b);
    setEqnumb(b);
    setEqname(b);
    setEqcwid(b);
    setEqcwname(b);
    setEqwcmcu(b);
    setEqwcdl01(b);
    return this;
  }

  public EquipmentMask keys(boolean b) {
    setEqid(b);
    return this;
  }

  public EquipmentMask attributes(boolean b) {
    setEqserial(b);
    setEqnumb(b);
    setEqname(b);
    setEqcwid(b);
    return this;
  }

  public EquipmentMask physicals(boolean b) {
    setEqid(b);
    setEqserial(b);
    setEqnumb(b);
    setEqname(b);
    setEqcwid(b);
    return this;
  }

  public EquipmentMask virtuals(boolean b) {
    setEqcwname(b);
    setEqwcmcu(b);
    setEqwcdl01(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "eqid":
        return getEqid();
      case "eqserial":
        return getEqserial();
      case "eqnumb":
        return getEqnumb();
      case "eqname":
        return getEqname();
      case "eqcwid":
        return getEqcwid();
      case "eqcwname":
        return getEqcwname();
      case "eqwcmcu":
        return getEqwcmcu();
      case "eqwcdl01":
        return getEqwcdl01();
    }
    return false;
  }

  public EquipmentMask set(String p, boolean b) {
    switch (p) {
      case "eqid":
        setEqid(b);
        break;
      case "eqserial":
        setEqserial(b);
        break;
      case "eqnumb":
        setEqnumb(b);
        break;
      case "eqname":
        setEqname(b);
        break;
      case "eqcwid":
        setEqcwid(b);
        break;
      case "eqcwname":
        setEqcwname(b);
        break;
      case "eqwcmcu":
        setEqwcmcu(b);
        break;
      case "eqwcdl01":
        setEqwcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
