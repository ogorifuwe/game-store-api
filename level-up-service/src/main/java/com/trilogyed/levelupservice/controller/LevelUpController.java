package com.trilogyed.levelupservice.controller;

import com.trilogyed.levelupservice.dao.LevelUpDao;
import com.trilogyed.levelupservice.exception.NotFoundException;
import com.trilogyed.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class LevelUpController {

  @Autowired
  private LevelUpDao levelUpDao;

  @RequestMapping(value = "/levelup", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {

    LevelUp newLevelUp = levelUpDao.createLevelUp(levelUp);
    return newLevelUp;
  }

  @RequestMapping(value = "levelup/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public LevelUp getLevelUp(@PathVariable(value = "id") int levelUpId) {

    LevelUp levelUp = levelUpDao.getLevelUp(levelUpId);
    if (levelUp == null) {
      throw new NotFoundException("LevelUp matching given level Up Id was found. ID: "+levelUpId);
    }

    return levelUp;
  }

  @RequestMapping(value = "/levelup/all", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<LevelUp> getAllLevelUps() {

    List<LevelUp> levelUps = levelUpDao.getAllLevelUps();
    if (levelUps != null && levelUps.size() == 0) {
      throw new NotFoundException("No Levelups were found in the System.");
    }
    return levelUps;
  }

  @RequestMapping(value = "/levelup/update/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateLevelUp(@PathVariable(value = "id") int levelUpId,
                            @RequestBody LevelUp levelUp) {

    if (!(levelUpId == levelUp.getLevelUpId())) {
      throw new IllegalArgumentException("Id on path must be the same as LevelUp Id");
    }

    levelUpDao.updateLevelUp(levelUp);
  }

  @RequestMapping(value = "/levelup/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteLevelUp(@PathVariable(value = "id") int levelUpId) {

    levelUpDao.deleteLevelUp(levelUpId);
  }
}