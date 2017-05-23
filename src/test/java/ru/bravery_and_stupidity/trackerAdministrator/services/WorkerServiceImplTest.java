package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class WorkerServiceImplTest {
  private static final String NAME = "Kirk";
  private static final String SURNAME = "Hamet";
  private static final String POSITION = "Legend of Metallica";

  @Autowired
  private WorkerService workerService;

  @PersistenceContext
  private EntityManager em;

  @Test
  public void getWorkers() throws Exception {
    List<Worker> workers = workerService.getWorkers();
    assertEquals(workers.get(0).getName(), "John");
    assertEquals(workers.get(0).getSurname(), "Lenon");
    assertEquals(workers.get(0).getPosition(), "Legend of rock");
    assertEquals(workers.get(1).getName(), "Seed");
    assertEquals(workers.get(1).getSurname(), "Vishes");
    assertEquals(workers.get(1).getPosition(), "Legend of punk rock");
  }

  @Test
  public void createWorker() throws Exception {
    workerService.createWorker(TestDtoCreater.createWorker(SURNAME,NAME,POSITION));
    Worker worker = findWorker(NAME, SURNAME, POSITION);
    assertEquals(worker.getName(), NAME);
    assertEquals(worker.getSurname(), SURNAME);
    assertEquals(worker.getPosition(), POSITION);
    workerService.deleteWorker(worker.getIdWorker());
  }

  private Worker findWorker(String name, String surname, String position) {
    List<Worker> workers = em.createQuery("SELECT worker FROM Worker worker WHERE name = :name " +
                                            "AND surname = :surname " +
                                            "AND position = :position")
      .setParameter("name", name)
      .setParameter("surname", surname)
      .setParameter("position", position).getResultList();
    return workers.get(0);
  }

  @Test
  public void deleteWorker() throws Exception {
    workerService.createWorker(TestDtoCreater.createWorker(SURNAME, NAME, POSITION));
    Worker worker = findWorker(NAME, SURNAME, POSITION);
    workerService.deleteWorker(worker.getIdWorker());
    List<Worker> workers = workerService.getWorkers();
    assertFalse(workers.contains(worker));
  }

  @Test
  public void updateWorker() throws Exception {
    final String GOD_OF_MUSIC = "GOD OF MUSIC";
    workerService.createWorker(TestDtoCreater.createWorker(SURNAME, NAME, POSITION));
    Worker worker = findWorker(NAME, SURNAME,POSITION);
    worker.setPosition(GOD_OF_MUSIC);
    workerService.updateWorker(worker);
    worker = findWorker(NAME, SURNAME, GOD_OF_MUSIC);
    assertEquals(worker.getPosition(), GOD_OF_MUSIC);
    workerService.deleteWorker(worker.getIdWorker());
  }

  @Test
  public void getWorker() throws Exception {
    Worker worker = workerService.getWorker(1);
    assertEquals(worker.getName(), "John");
    assertEquals(worker.getSurname(), "Lenon");
    assertEquals(worker.getPosition(), "Legend of rock");
    worker = workerService.getWorker(2);
    assertEquals(worker.getName(), "Seed");
    assertEquals(worker.getSurname(), "Vishes");
    assertEquals(worker.getPosition(), "Legend of punk rock");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullWorker() {
    workerService.createWorker(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithEmptyWorker() {
    workerService.createWorker(TestDtoCreater.createWorker("","",""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithWrongId() {
    workerService.deleteWorker(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithNullWorker() {
    workerService.updateWorker(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithEmptyWorker() {
    workerService.updateWorker(TestDtoCreater.createWorker("","",""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithWrongId() {
    workerService.updateWorker(TestDtoCreater.createWorker(0,"","",""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getWorkerWithWrongIdNull() {
    workerService.getWorker(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getWorkerWithWrongIdNegative() {
    workerService.getWorker(-10);
  }
}