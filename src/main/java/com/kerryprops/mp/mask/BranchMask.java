package com.kerryprops.mp.mask;

public class BranchMask {
  protected long value = 0b0000;

  public boolean getBpmcu() {
    return (value >> 3 & 1) == 1;
  }

  public BranchMask setBpmcu(boolean bpmcu) {
    if (bpmcu) value |= 0b1000;
    else value &= 0b0111;
    return this;
  }

  public boolean getBpdl01() {
    return (value >> 2 & 1) == 1;
  }

  public BranchMask setBpdl01(boolean bpdl01) {
    if (bpdl01) value |= 0b0100;
    else value &= 0b1011;
    return this;
  }

  public boolean getBpwcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public BranchMask setBpwcmcu(boolean bpwcmcu) {
    if (bpwcmcu) value |= 0b0010;
    else value &= 0b1101;
    return this;
  }

  public boolean getBpwcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public BranchMask setBpwcdl01(boolean bpwcdl01) {
    if (bpwcdl01) value |= 0b0001;
    else value &= 0b1110;
    return this;
  }

  public BranchMask(boolean bpmcu, boolean bpdl01, boolean bpwcmcu, boolean bpwcdl01) {
    setBpmcu(bpmcu);
    setBpdl01(bpdl01);
    setBpwcmcu(bpwcmcu);
    setBpwcdl01(bpwcdl01);
  }

  public BranchMask(long v) {
    value = v;
  }

  public BranchMask() {}

  public BranchMask all(boolean b) {
    setBpmcu(b);
    setBpdl01(b);
    setBpwcmcu(b);
    setBpwcdl01(b);
    return this;
  }

  public BranchMask keys(boolean b) {
    setBpmcu(b);
    return this;
  }

  public BranchMask attributes(boolean b) {
    setBpdl01(b);
    setBpwcmcu(b);
    return this;
  }

  public BranchMask physicals(boolean b) {
    setBpmcu(b);
    setBpdl01(b);
    setBpwcmcu(b);
    return this;
  }

  public BranchMask virtuals(boolean b) {
    setBpwcdl01(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "bpmcu":
        return getBpmcu();
      case "bpdl01":
        return getBpdl01();
      case "bpwcmcu":
        return getBpwcmcu();
      case "bpwcdl01":
        return getBpwcdl01();
    }
    return false;
  }

  public BranchMask set(String p, boolean b) {
    switch (p) {
      case "bpmcu":
        setBpmcu(b);
        break;
      case "bpdl01":
        setBpdl01(b);
        break;
      case "bpwcmcu":
        setBpwcmcu(b);
        break;
      case "bpwcdl01":
        setBpwcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
