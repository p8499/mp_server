package com.kerryprops.mp.mask;

public class WorkCenterMask {
  protected long value = 0b00;

  public boolean getWcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public WorkCenterMask setWcmcu(boolean wcmcu) {
    if (wcmcu) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getWcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public WorkCenterMask setWcdl01(boolean wcdl01) {
    if (wcdl01) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public WorkCenterMask(boolean wcmcu, boolean wcdl01) {
    setWcmcu(wcmcu);
    setWcdl01(wcdl01);
  }

  public WorkCenterMask(long v) {
    value = v;
  }

  public WorkCenterMask() {}

  public WorkCenterMask all(boolean b) {
    setWcmcu(b);
    setWcdl01(b);
    return this;
  }

  public WorkCenterMask keys(boolean b) {
    setWcmcu(b);
    return this;
  }

  public WorkCenterMask attributes(boolean b) {
    setWcdl01(b);
    return this;
  }

  public WorkCenterMask physicals(boolean b) {
    setWcmcu(b);
    setWcdl01(b);
    return this;
  }

  public WorkCenterMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "wcmcu":
        return getWcmcu();
      case "wcdl01":
        return getWcdl01();
    }
    return false;
  }

  public WorkCenterMask set(String p, boolean b) {
    switch (p) {
      case "wcmcu":
        setWcmcu(b);
        break;
      case "wcdl01":
        setWcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
