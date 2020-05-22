package com.kerryprops.mp.mask;

public class BuildingMask {
  protected long value = 0b000000;

  public boolean getMcmcu() {
    return (value >> 5 & 1) == 1;
  }

  public BuildingMask setMcmcu(boolean mcmcu) {
    if (mcmcu) value |= 0b100000;
    else value &= 0b011111;
    return this;
  }

  public boolean getMcdl01() {
    return (value >> 4 & 1) == 1;
  }

  public BuildingMask setMcdl01(boolean mcdl01) {
    if (mcdl01) value |= 0b010000;
    else value &= 0b101111;
    return this;
  }

  public boolean getMcbpmcu() {
    return (value >> 3 & 1) == 1;
  }

  public BuildingMask setMcbpmcu(boolean mcbpmcu) {
    if (mcbpmcu) value |= 0b001000;
    else value &= 0b110111;
    return this;
  }

  public boolean getMcbpdl01() {
    return (value >> 2 & 1) == 1;
  }

  public BuildingMask setMcbpdl01(boolean mcbpdl01) {
    if (mcbpdl01) value |= 0b000100;
    else value &= 0b111011;
    return this;
  }

  public boolean getMcwcmcu() {
    return (value >> 1 & 1) == 1;
  }

  public BuildingMask setMcwcmcu(boolean mcwcmcu) {
    if (mcwcmcu) value |= 0b000010;
    else value &= 0b111101;
    return this;
  }

  public boolean getMcwcdl01() {
    return (value >> 0 & 1) == 1;
  }

  public BuildingMask setMcwcdl01(boolean mcwcdl01) {
    if (mcwcdl01) value |= 0b000001;
    else value &= 0b111110;
    return this;
  }

  public BuildingMask(
      boolean mcmcu,
      boolean mcdl01,
      boolean mcbpmcu,
      boolean mcbpdl01,
      boolean mcwcmcu,
      boolean mcwcdl01) {
    setMcmcu(mcmcu);
    setMcdl01(mcdl01);
    setMcbpmcu(mcbpmcu);
    setMcbpdl01(mcbpdl01);
    setMcwcmcu(mcwcmcu);
    setMcwcdl01(mcwcdl01);
  }

  public BuildingMask(long v) {
    value = v;
  }

  public BuildingMask() {}

  public BuildingMask all(boolean b) {
    setMcmcu(b);
    setMcdl01(b);
    setMcbpmcu(b);
    setMcbpdl01(b);
    setMcwcmcu(b);
    setMcwcdl01(b);
    return this;
  }

  public BuildingMask keys(boolean b) {
    setMcmcu(b);
    return this;
  }

  public BuildingMask attributes(boolean b) {
    setMcdl01(b);
    setMcbpmcu(b);
    return this;
  }

  public BuildingMask physicals(boolean b) {
    setMcmcu(b);
    setMcdl01(b);
    setMcbpmcu(b);
    return this;
  }

  public BuildingMask virtuals(boolean b) {
    setMcbpdl01(b);
    setMcwcmcu(b);
    setMcwcdl01(b);
    return this;
  }

  public boolean get(String p) {
    switch (p) {
      case "mcmcu":
        return getMcmcu();
      case "mcdl01":
        return getMcdl01();
      case "mcbpmcu":
        return getMcbpmcu();
      case "mcbpdl01":
        return getMcbpdl01();
      case "mcwcmcu":
        return getMcwcmcu();
      case "mcwcdl01":
        return getMcwcdl01();
    }
    return false;
  }

  public BuildingMask set(String p, boolean b) {
    switch (p) {
      case "mcmcu":
        setMcmcu(b);
        break;
      case "mcdl01":
        setMcdl01(b);
        break;
      case "mcbpmcu":
        setMcbpmcu(b);
        break;
      case "mcbpdl01":
        setMcbpdl01(b);
        break;
      case "mcwcmcu":
        setMcwcmcu(b);
        break;
      case "mcwcdl01":
        setMcwcdl01(b);
        break;
    }
    return this;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
