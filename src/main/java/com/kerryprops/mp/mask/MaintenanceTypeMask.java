package com.kerryprops.mp.mask;

public class MaintenanceTypeMask {
  protected long value = 0b00;

  public boolean getMtid() {
    return (value >> 1 & 1) == 1;
  }

  public MaintenanceTypeMask setMtid(boolean mtid) {
    if (mtid) value |= 0b10;
    else value &= 0b01;
    return this;
  }

  public boolean getMtname() {
    return (value >> 0 & 1) == 1;
  }

  public MaintenanceTypeMask setMtname(boolean mtname) {
    if (mtname) value |= 0b01;
    else value &= 0b10;
    return this;
  }

  public MaintenanceTypeMask(boolean mtid, boolean mtname) {
    setMtid(mtid);
    setMtname(mtname);
  }

  public MaintenanceTypeMask(long v) {
    value = v;
  }

  public MaintenanceTypeMask() {}

  public MaintenanceTypeMask all(boolean b) {
    setMtid(b);
    setMtname(b);
    return this;
  }

  public MaintenanceTypeMask keys(boolean b) {
    setMtid(b);
    return this;
  }

  public MaintenanceTypeMask attributes(boolean b) {
    setMtname(b);
    return this;
  }

  public MaintenanceTypeMask physicals(boolean b) {
    setMtid(b);
    setMtname(b);
    return this;
  }

  public MaintenanceTypeMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "mtid":
        return getMtid();
      case "mtname":
        return getMtname();
    }
    return false;
  }

  public MaintenanceTypeMask set(String p, boolean b) {
    switch (p) {
      case "mtid":
        setMtid(b);
        break;
      case "mtname":
        setMtname(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
