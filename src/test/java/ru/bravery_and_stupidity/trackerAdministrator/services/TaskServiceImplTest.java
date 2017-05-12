package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class TaskServiceImplTest {
  
  @Autowired
  private TaskService taskService;
  @PersistenceContext
  private EntityManager em;

  @Test
  public void getTasksByProject() throws Exception {
    List<TaskDto> tasks = taskService.getTasksByProject(3);
    assertEquals(tasks.get(0).getId(), 1);
    assertEquals(tasks.get(1).getId(), 2);
  }

  @Test
  public void saveTasks() throws Exception {
    List<TaskDto> tasks = taskService.getTasksByProject(3);
    tasks.get(0).setDescription("modified for test");

    taskService.saveTasks(TaskDto.mapToModels(tasks));

    Task taskFromDb1 = getTaskById(tasks.get(0).getId());
    assertEquals(taskFromDb1.getDescription(), "modified for test");
  }

  private Task getTaskById(int id) {
    List<Task> tasks = em.createQuery("select u from Task u where idTask = :id")
        .setParameter("id", id).getResultList();
    return tasks.get(0);
  }
}