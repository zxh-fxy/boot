package com.zxh.blog.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import java.io.Serializable;
public enum AnimalType implements IEnum {
  CAT("1", "猫"), DOG("2", "狗"), TIGER("3", "虎"), MOUSE("4", "鼠"), MONKEY("5", "猴"), LOAN("6",
      "狮"), OTHER("7", "其他");
  private final String value;
  private final String desc;

  AnimalType(final String value, final String desc) {
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
