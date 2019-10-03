package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.LevelUp;

import java.util.List;

public interface LevelUpDao {

  LevelUp createLevelUp(LevelUp levelUp);

  LevelUp getLevelUp(int levelUpId);

  List<LevelUp> getAllLevelUps();

  void updateLevelUp(LevelUp levelUp);

  void deleteLevelUp(int levelUpId);

}
