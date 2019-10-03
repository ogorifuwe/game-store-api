package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {

  @RequestMapping(value = "/levelup", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  LevelUp createLevelUp(@RequestBody LevelUp levelUp);


  @RequestMapping(value = "levelup/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  LevelUp getLevelUp(@PathVariable(value = "id") int levelUpId);

  @RequestMapping(value = "/levelup/all-level-ups", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  List<LevelUp> getAllLevelUps();

  @RequestMapping(value = "/levelup/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void updateLevelUp(@PathVariable(value = "id") int levelUpId, @RequestBody LevelUp levelUp);

  @RequestMapping(value = "/levelup/update/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void deleteLevelUp(@PathVariable(value = "id") int levelUpId);
}
