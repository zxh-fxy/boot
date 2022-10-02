package com.zxh.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxh.blog.mapper.UserRepository;
import com.zxh.blog.entity.User;
import com.zxh.blog.enums.WrapperType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {

  private UserRepository userRepository;
  public UserService(UserRepository userRepository){
    this.userRepository=userRepository;
  }
  //增
  public ResponseEntity<User> addUser(final User user) {
    userRepository.insert(user);
    return ResponseEntity.ok(user);
  }

  //删
  public ResponseEntity<Integer> deleteUserById(final int id){
    return ResponseEntity.ok(userRepository.deleteById(id));
  }

  public ResponseEntity<Integer> deleteUsers(final User user){
    return ResponseEntity.ok(userRepository.delete(packWrapper(user, WrapperType.QUERY)));
  }

  public ResponseEntity<Integer> deleteUsersByIds(List<Integer> ids){
    return ResponseEntity.ok(userRepository.deleteBatchIds(ids));
  }

  public ResponseEntity<Integer> deleteUsersByMap(final User user){
    Map<String, Object> params = new HashMap<>();
    if(Objects.nonNull(user.getId())){
      params.put("ID",user.getId());
    }
    if(StringUtils.isNotEmpty(user.getAccountid())){
      params.put("NAME", user.getAccountid());
    }
    if(Objects.nonNull(user.getPassword())){
      params.put("TYPE", user.getPassword());
    }
    if(Objects.nonNull(user.getSalt())){
      params.put("SEX", user.getSalt());
    }
    if (StringUtils.isNotEmpty(user.getMobile())){
      params.put("MASTER", user.getMobile());
    }
    return ResponseEntity.ok(userRepository.deleteByMap(params));
  }

  //改
  public ResponseEntity<Integer> updateUsers(final User user, final User condition){
    return ResponseEntity.ok(userRepository.update(user, packWrapper(condition, WrapperType.UPDATE)));
  }

  public ResponseEntity<Integer> updateUser(final User user){
    Wrapper<User> userWrapper = new UpdateWrapper<>();
    ((UpdateWrapper<User>) userWrapper).eq("id",user.getId());
    return ResponseEntity.ok(userRepository.update(user, userWrapper));
  }

  //查
  public ResponseEntity<User> getUserById(final int id){
    return ResponseEntity.ok(userRepository.selectById(id));
  }

  public ResponseEntity<User> getOneUser(final User user){
    return ResponseEntity.ok(userRepository.selectOne(packWrapper(user, WrapperType.QUERY)));
  }

  public ResponseEntity<List<User>> getUsers(final User user){
    return ResponseEntity.ok(userRepository.selectList(packWrapper(user, WrapperType.QUERY)));
  }

  public ResponseEntity<List<User>> getUsersByIds(List<Integer> ids){
    return ResponseEntity.ok(userRepository.selectBatchIds(ids));
  }

  public ResponseEntity<List<User>> getUsersByMap(final User user){
    Map<String, Object> params = new HashMap<>();
    if(Objects.nonNull(user.getId())){
      params.put("ID",user.getId());
    }
    if(StringUtils.isNotEmpty(user.getAccountid())){
      params.put("NAME", user.getAccountid());
    }
    if(Objects.nonNull(user.getPassword())){
      params.put("TYPE", user.getPassword());
    }
    if(Objects.nonNull(user.getSalt())){
      params.put("SEX", user.getSalt());
    }
    if (StringUtils.isNotEmpty(user.getMobile())){
      params.put("MASTER", user.getMobile());
    }
    return ResponseEntity.ok(userRepository.selectByMap(params));
  }

  public ResponseEntity<List<Map<String, Object>>> getUserMaps(final User user){
    return ResponseEntity.ok(userRepository.selectMaps(packWrapper(user, WrapperType.QUERY)));
  }

  //查个数
  public ResponseEntity<Integer> getCount(final User user){
    return ResponseEntity.ok(userRepository.selectCount(packWrapper(user, WrapperType.QUERY)));
  }

  //分页查询
  public ResponseEntity<Page<User>> getUserPage(final User user,final int pageId,final int pageSize){
    Page<User> page = new Page<>();
    page.setCurrent(pageId);
    page.setSize(pageSize);
    return ResponseEntity.ok((Page<User>) userRepository.selectPage(page,packWrapper(user, WrapperType.QUERY)));
  }

  private Wrapper<User> packWrapper(final User user, WrapperType wrapperType){
    switch (wrapperType){
      case QUERY:
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(user.getId()))
          wrapper.eq("ID", user.getId());
        if (StringUtils.isNotEmpty(user.getAccountid()))
          wrapper.eq("accountid", user.getAccountid());
        if (Objects.nonNull(user.getPassword()))
          wrapper.eq("password", user.getPassword());
        if (Objects.nonNull(user.getSalt()))
          wrapper.eq("salt", user.getSalt());
        if (StringUtils.isNotEmpty(user.getMobile()))
          wrapper.eq("mobile", user.getMobile());
        return wrapper;
      case UPDATE:
        UpdateWrapper<User> wrapper2 = new UpdateWrapper<>();
        if (Objects.nonNull(user.getId()))
          wrapper2.eq("ID", user.getId());
        if (StringUtils.isNotEmpty(user.getAccountid()))
          wrapper2.eq("accountid", user.getAccountid());
        if (Objects.nonNull(user.getPassword()))
          wrapper2.eq("password", user.getPassword());
        if (Objects.nonNull(user.getSalt()))
          wrapper2.eq("salt", user.getSalt());
        if (StringUtils.isNotEmpty(user.getMobile()))
          wrapper2.eq("mobile", user.getMobile());
        return wrapper2;
      case QUERYLAMBDA:
        LambdaQueryWrapper<User> wrapper3 = new QueryWrapper<User>().lambda();
        if (Objects.nonNull(user.getId()))
          wrapper3.eq(User::getId, user.getId());
        if (StringUtils.isNotEmpty(user.getAccountid()))
          wrapper3.eq(User::getAccountid, user.getAccountid());
        if (Objects.nonNull(user.getPassword()))
          wrapper3.eq(User::getPassword, user.getPassword());
        if (Objects.nonNull(user.getSalt()))
          wrapper3.eq(User::getSalt, user.getSalt());
        if (StringUtils.isNotEmpty(user.getMobile()))
          wrapper3.eq(User::getMobile, user.getMobile());
        return wrapper3;
      case UPDATELAMBDA:
        LambdaUpdateWrapper<User> wrapper4 = new UpdateWrapper<User>().lambda();
        if (Objects.nonNull(user.getId()))
          wrapper4.eq(User::getId, user.getId());
        if (StringUtils.isNotEmpty(user.getAccountid()))
          wrapper4.eq(User::getAccountid, user.getAccountid());
        if (Objects.nonNull(user.getPassword()))
          wrapper4.eq(User::getPassword, user.getPassword());
        if (Objects.nonNull(user.getSalt()))
          wrapper4.eq(User::getSalt, user.getSalt());
        if (StringUtils.isNotEmpty(user.getMobile()))
          wrapper4.eq(User::getMobile, user.getMobile());
        return wrapper4;
      default:return null;
    }
  }
}
