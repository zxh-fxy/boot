package com.zxh.blog.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import java.io.Serializable;

public enum AnimalSex implements IEnum {
  MALE("1", "公"), FEMALE("2", "母");
  private final String value;
  private final String desc;

  AnimalSex(final String value, final String desc) {
    this.value = value;
    this.desc = desc;
  }

  @Override
  public Serializable getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }
}
