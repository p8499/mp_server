package com.kerryprops.mp.mask;

public class RoomMask {
  protected long value = 0b00000000;

  public boolean getRmid() {
    return (value >> 7 & 1) == 1;
  }

  public RoomMask setRmid(boolean rmid) {
    if (rmid) value |= 0b10000000;
    else value &= 0b01111111;
    return this;
  }

  public boolean getRmserial() {
    return (value >> 6 & 1) == 1;
  }

  public RoomMask setRmserial(boolean rmserial) {
    if (rmserial) value |= 0b01000000;
    else value &= 0b10111111;
    return this;
  }

  public boolean getRman8() {
    return (value >> 5 & 1) == 1;
  }

  public RoomMask setRman8(boolean rman8) {
    if (rman8) value |= 0b00100000;
    else value &= 0b11011111;
    return this;
  }

  public boolean getRmname() {
    return (value >> 4 & 1) == 1;
  }

  public RoomMask setRmname(boolean rmname) {
    if (rmname) value |= 0b00010000;
    else value &= 0b11101111;
    return this;
  }

  public boolean getRmcwid() {
    return (value >> 3 & 1) == 1;
  }

  public RoomMask setRmcwid(boolean rmcwid) {
    if (rmcwid) value |= 0b00001000;
    else value &= 0b11110111;
    return this;
  }

  public boolean getRmcwname() {
    return (value >> 2 & 1) == 1;
  }

  public RoomMask setRmcwname(boolean rmcwname) {
    if (rmcwname) value |= 0b00000100;
    else value &= 0b11111011;
    return this;
  }

  public boolean getRmwcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public RoomMask setRmwcmcu(boolean rmwcmcu) {
    if (rmwcmcu) value |= 0b00000010;
    else value &= 0b11111101;
    return this;
  }

  public boolean getRmwcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public RoomMask setRmwcdl01(boolean rmwcdl01) {
    if (rmwcdl01) value |= 0b00000001;
    else value &= 0b11111110;
    return this;
  }

  public RoomMask(
      boolean rmid,
      boolean rmserial,
      boolean rman8,
      boolean rmname,
      boolean rmcwid,
      boolean rmcwname,
      boolean rmwcmcu,
      boolean rmwcdl01) {
    setRmid(rmid);
    setRmserial(rmserial);
    setRman8(rman8);
    setRmname(rmname);
    setRmcwid(rmcwid);
    setRmcwname(rmcwname);
    setRmwcmcu(rmwcmcu);
    setRmwcdl01(rmwcdl01);
  }

  public RoomMask(long v) {
    value = v;
  }

  public RoomMask() {}

  public RoomMask all(boolean b) {
    setRmid(b);
    setRmserial(b);
    setRman8(b);
    setRmname(b);
    setRmcwid(b);
    setRmcwname(b);
    setRmwcmcu(b);
    setRmwcdl01(b);
    return this;
  }

  public RoomMask keys(boolean b) {
    setRmid(b);
    return this;
  }

  public RoomMask attributes(boolean b) {
    setRmserial(b);
    setRman8(b);
    setRmname(b);
    setRmcwid(b);
    return this;
  }

  public RoomMask physicals(boolean b) {
    setRmid(b);
    setRmserial(b);
    setRman8(b);
    setRmname(b);
    setRmcwid(b);
    return this;
  }

  public RoomMask virtuals(boolean b) {
    setRmcwname(b);
    setRmwcmcu(b);
    setRmwcdl01(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "rmid":
        return getRmid();
      case "rmserial":
        return getRmserial();
      case "rman8":
        return getRman8();
      case "rmname":
        return getRmname();
      case "rmcwid":
        return getRmcwid();
      case "rmcwname":
        return getRmcwname();
      case "rmwcmcu":
        return getRmwcmcu();
      case "rmwcdl01":
        return getRmwcdl01();
    }
    return false;
  }

  public RoomMask set(String p, boolean b) {
    switch (p) {
      case "rmid":
        setRmid(b);
        break;
      case "rmserial":
        setRmserial(b);
        break;
      case "rman8":
        setRman8(b);
        break;
      case "rmname":
        setRmname(b);
        break;
      case "rmcwid":
        setRmcwid(b);
        break;
      case "rmcwname":
        setRmcwname(b);
        break;
      case "rmwcmcu":
        setRmwcmcu(b);
        break;
      case "rmwcdl01":
        setRmwcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
