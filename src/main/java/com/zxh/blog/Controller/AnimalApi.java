package com.zxh.blog.Controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxh.blog.entity.Animal;
import com.zxh.blog.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("animal")
@Api(value = "动物管理", tags = "动物管理相关的接口")
@Slf4j
public class AnimalApi {
  @Autowired
  private AnimalService animalService;
  @RequestMapping(value = "addAnimal",method = RequestMethod.PUT)
  @ApiOperation(value = "添加动物",notes = "添加动物",httpMethod = "PUT")
  public ResponseEntity<Animal> addAnimal(final Animal animal){
    return animalService.addAnimal(animal);
  }
  @RequestMapping(value = "deleteAnimalById", method = RequestMethod.DELETE)
  @ApiOperation(value = "删除一个动物",notes = "根据ID删除动物",httpMethod = "DELETE")
  public ResponseEntity<Integer> deleteAnimalById(final int id){
    return animalService.deleteAnimalById(id);
  }
  @RequestMapping(value = "deleteAnimalsByIds",method = RequestMethod.DELETE)
  @ApiOperation(value = "删除多个动物",notes = "根据Id删除多个动物",httpMethod = "DELETE")
  public ResponseEntity<Integer> deleteAnimalsByIds(Integer[] ids){
    return animalService.deleteAnimalsByIds(Arrays.asList(ids));
  }
  @RequestMapping(value = "deleteAnimals", method = RequestMethod.DELETE)
  @ApiOperation(value = "删除动物",notes = "根据条件删除动物",httpMethod = "DELETE")
  public ResponseEntity<Integer> deleteAnimalsByMaps(final Animal animal){
    return animalService.deleteAnimalsByMap(animal);
  }
  @RequestMapping(value = "deleteAnimals2", method = RequestMethod.DELETE)
  @ApiOperation(value = "删除动物",notes = "根据条件删除动物",httpMethod = "DELETE")
  public ResponseEntity<Integer> deleteAnimals(final Animal animal){
    return animalService.deleteAnimals(animal);
  }
  @RequestMapping(value = "getAnimalById",method = RequestMethod.GET)
  @ApiOperation(value = "获取一个动物",notes = "根据ID获取一个动物",httpMethod = "GET")
  public ResponseEntity<Animal> getAnimalById(final int id){
    return animalService.getAnimalById(id);
  }
  // 注意，这里参数animal不能用RequstBody标注，否则接收不到参数
  // @RequestBody只能用在只有一个参数模型的方法中，用于将所有请求体中携带的参数全部映射到这个请求参数模型中
  @RequestMapping(value = "getAnimalsByPage")
  @ApiOperation(value = "分页获取动物们",notes = "分页获取所有动物", httpMethod = "GET")
  public ResponseEntity<Page<Animal>> getAnimalsByPage(@RequestParam final int pageId, @RequestParam final int pageSize, final Animal animal) {
    return animalService.getAnimalPage(animal==null?Animal.builder().build():animal, pageId, pageSize);
  }
  @RequestMapping(value = "updateAnimal")
  @ApiOperation(value = "更新动物", notes = "根据条件更新",httpMethod = "POST")
  public ResponseEntity<Integer> updateAnimals(final Animal animal){
    return animalService.updateAnimal(animal);
  }
}