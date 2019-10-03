package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.LevelUp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {

  @Autowired
  private LevelUpDao levelUpDao;

  @Before
  public void setUp() throws Exception {

    List<LevelUp> levelUps = levelUpDao.getAllLevelUps();
    levelUps
            .stream()
            .forEach(levelUp -> levelUpDao.deleteLevelUp(levelUp.getLevelUpId()));

  }

  @After
  public void tearDown() throws Exception {

    levelUpDao = null;

  }

  @Test
  public void createGetLevelUp() {

    LevelUp levelUp = new LevelUp();
    levelUp.setCustomerId(1);
    levelUp.setPoints(10);
    levelUp.setMemberDate(LocalDate.of(2019, 9, 12));

    LevelUp newLevelUp = levelUpDao.createLevelUp(levelUp);
    LevelUp testLevelUp = levelUpDao.getLevelUp(newLevelUp.getLevelUpId());

    assertNotNull(testLevelUp);
    assertEquals(newLevelUp, testLevelUp);

  }

  @Test
  public void getAllLevelUps() {

    LevelUp levelUp = new LevelUp();
    levelUp.setCustomerId(1);
    levelUp.setPoints(10);
    levelUp.setMemberDate(LocalDate.of(2019, 9, 12));

    LevelUp newLevelUp = levelUpDao.createLevelUp(levelUp);

    LevelUp levelUp2 = new LevelUp();
    levelUp2.setCustomerId(1);
    levelUp2.setPoints(10);
    levelUp2.setMemberDate(LocalDate.of(2019, 9, 12));

    LevelUp newLevelUp2 = levelUpDao.createLevelUp(levelUp);

    List<LevelUp> levelUps = levelUpDao.getAllLevelUps();

    assertNotNull(levelUps);
    assertTrue(levelUps.size() == 2);

  }

  @Test
  public void updateLevelUp() {

    LevelUp levelUp = new LevelUp();
    levelUp.setCustomerId(1);
    levelUp.setPoints(10);
    levelUp.setMemberDate(LocalDate.of(2019, 9, 12));

    LevelUp newLevelUp = levelUpDao.createLevelUp(levelUp);

    levelUp.setCustomerId(2);
    levelUp.setPoints(20);

    levelUpDao.updateLevelUp(levelUp);
    LevelUp updatedLevelUp = levelUpDao.getLevelUp(newLevelUp.getLevelUpId());

    assertNotNull(updatedLevelUp);
    assertEquals(newLevelUp, updatedLevelUp);

  }

  @Test
  public void deleteLevelUp() {

    LevelUp levelUp = new LevelUp();
    levelUp.setCustomerId(1);
    levelUp.setPoints(10);
    levelUp.setMemberDate(LocalDate.of(2019, 9, 12));

    LevelUp newLevelUp = levelUpDao.createLevelUp(levelUp);

    levelUpDao.deleteLevelUp(newLevelUp.getLevelUpId());

    LevelUp deletedLevelUp = levelUpDao.getLevelUp(newLevelUp.getLevelUpId());

    assertNull(deletedLevelUp);

  }
}
