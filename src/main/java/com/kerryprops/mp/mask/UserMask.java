package com.kerryprops.mp.mask;

public class UserMask {
  protected long value = 0b0000000;

  public boolean getUsid() {
    return (value >> 6 & 1) == 1;
  }

  public UserMask setUsid(boolean usid) {
    if (usid) value |= 0b1000000;
    else value &= 0b0111111;
    return this;
  }

  public boolean getUsan8() {
    return (value >> 5 & 1) == 1;
  }

  public UserMask setUsan8(boolean usan8) {
    if (usan8) value |= 0b0100000;
    else value &= 0b1011111;
    return this;
  }

  public boolean getUscell() {
    return (value >> 4 & 1) == 1;
  }

  public UserMask setUscell(boolean uscell) {
    if (uscell) value |= 0b0010000;
    else value &= 0b1101111;
    return this;
  }

  public boolean getUsmail() {
    return (value >> 3 & 1) == 1;
  }

  public UserMask setUsmail(boolean usmail) {
    if (usmail) value |= 0b0001000;
    else value &= 0b1110111;
    return this;
  }

  public boolean getUsname() {
    return (value >> 2 & 1) == 1;
  }

  public UserMask setUsname(boolean usname) {
    if (usname) value |= 0b0000100;
    else value &= 0b1111011;
    return this;
  }

  public boolean getUspswd() {
    return (value >> 1 & 1) == 1;
  }

  public UserMask setUspswd(boolean uspswd) {
    if (uspswd) value |= 0b0000010;
    else value &= 0b1111101;
    return this;
  }

  public boolean getUsstatus() {
    return (value >> 0 & 1) == 1;
  }

  public UserMask setUsstatus(boolean usstatus) {
    if (usstatus) value |= 0b0000001;
    else value &= 0b1111110;
    return this;
  }

  public UserMask(
      boolean usid,
      boolean usan8,
      boolean uscell,
      boolean usmail,
      boolean usname,
      boolean uspswd,
      boolean usstatus) {
    setUsid(usid);
    setUsan8(usan8);
    setUscell(uscell);
    setUsmail(usmail);
    setUsname(usname);
    setUspswd(uspswd);
    setUsstatus(usstatus);
  }

  public UserMask(long v) {
    value = v;
  }

  public UserMask() {}

  public UserMask all(boolean b) {
    setUsid(b);
    setUsan8(b);
    setUscell(b);
    setUsmail(b);
    setUsname(b);
    setUspswd(b);
    setUsstatus(b);
    return this;
  }

  public UserMask keys(boolean b) {
    setUsid(b);
    return this;
  }

  public UserMask attributes(boolean b) {
    setUsan8(b);
    setUscell(b);
    setUsmail(b);
    setUsname(b);
    setUspswd(b);
    setUsstatus(b);
    return this;
  }

  public UserMask physicals(boolean b) {
    setUsid(b);
    setUsan8(b);
    setUscell(b);
    setUsmail(b);
    setUsname(b);
    setUspswd(b);
    setUsstatus(b);
    return this;
  }

  public UserMask virtuals(boolean b) {
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "usid":
        return getUsid();
      case "usan8":
        return getUsan8();
      case "uscell":
        return getUscell();
      case "usmail":
        return getUsmail();
      case "usname":
        return getUsname();
      case "uspswd":
        return getUspswd();
      case "usstatus":
        return getUsstatus();
    }
    return false;
  }

  public UserMask set(String p, boolean b) {
    switch (p) {
      case "usid":
        setUsid(b);
        break;
      case "usan8":
        setUsan8(b);
        break;
      case "uscell":
        setUscell(b);
        break;
      case "usmail":
        setUsmail(b);
        break;
      case "usname":
        setUsname(b);
        break;
      case "uspswd":
        setUspswd(b);
        break;
      case "usstatus":
        setUsstatus(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
