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
@TableName(value = "user")
public class User {
  @TableId(value = "ID",type = IdType.AUTO)
  private Integer id;
  @TableField(value = "accountid",exist = true)
  private String accountid;
  @TableField(value = "password",exist = true)
  private String password;
  @TableField(value = "salt",exist = false)
  private String salt;
  @TableField(value = "mobile",exist = false)
  private String mobile;
}
