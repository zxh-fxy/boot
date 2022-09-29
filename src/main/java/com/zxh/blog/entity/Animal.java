package com.zxh.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxh.blog.enums.AnimalSex;
import com.zxh.blog.enums.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "animal")
public class Animal {
  @TableId(value = "ID",type = IdType.AUTO)
  private Integer id;
  @TableField(value = "NAME",exist = true)
  private String name;
  @TableField(value = "TYPE",exist = true)
  private AnimalType type;
  @TableField(value = "SEX",exist = true)
  private AnimalSex sex;
  @TableField(value = "MASTER",exist = true)
  private String master;
}
