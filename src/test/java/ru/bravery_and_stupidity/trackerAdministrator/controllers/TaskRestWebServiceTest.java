package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.OrderDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.ProjectDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class TaskRestWebServiceTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void init()  {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getTasksByProject() throws Exception {
    mockMvc.perform(get("/tasks/getTasksByProject/3")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType("application/json;charset=UTF-8"))
    .andExpect(jsonPath("$.[0].['id']").value("1"))
    .andExpect(jsonPath("$.[0].['description']").value("Тестовое поручение1"))
    .andExpect(jsonPath("$.[0].['creationDate']").value("2015-12-31"))
    .andExpect(jsonPath("$.[0].['deadlineDate']").value("2017-02-12"))
    .andExpect(jsonPath("$.[0].['status']").value(0))
    .andExpect(jsonPath("$.[0].['isOverdue']").value(1))
    .andExpect(jsonPath("$.[0].['importance']").value(5))
    .andExpect(jsonPath("$.[0].['parentTaskId']").value(22))
    .andExpect(jsonPath("$.[0].['project'].id").value(3))
    .andExpect(jsonPath("$.[0].['project'].description").value("project 3"))
    .andExpect(jsonPath("$.[0].['order'].description").value("order 3"))
    //FIXME
    //.andExpect(jsonPath("$.[0].['responsible']").value())

    .andExpect(jsonPath("$.[1].['id']").value("2"))
    .andExpect(jsonPath("$.[1].['description']").value("Тестовое поручение2"))
    .andExpect(jsonPath("$.[1].['creationDate']").value("2013-11-22"))
    .andExpect(jsonPath("$.[1].['deadlineDate']").value("2016-08-05"))
    .andExpect(jsonPath("$.[1].['status']").value(0))
    .andExpect(jsonPath("$.[1].['isOverdue']").value(1))
    .andExpect(jsonPath("$.[1].['importance']").value(10))
    .andExpect(jsonPath("$.[1].['parentTaskId']").value(22))
    .andExpect(jsonPath("$.[1].['project'].id").value(3))
    .andExpect(jsonPath("$.[1].['project'].description").value("project 3"))
    .andExpect(jsonPath("$.[1].['order'].description").value("order 3"));
    //FIXME
    //.andExpect(jsonPath("$.[1].['responsible']").value())
  }

  @Test
  public void saveTasks() throws Exception {
    List<TaskDto> tasks = new ArrayList<>();
    tasks.add(TestDtoCreater.createTaskDto("test save tasks 1"));
    tasks.add(TestDtoCreater.createTaskDto("test save tasks 2"));
    ProjectDto projectDto = TestDtoCreater.createProjectDto("project for save task test");
    OrderDto orderDto = TestDtoCreater.createOrderDto("order for save task test");
    TaskDto taskDto = TestDtoCreater.createTaskDto("test save tasks 3");
    taskDto.setProject(projectDto);
    taskDto.setOrder(orderDto);
    tasks.add(taskDto);
    String jsonTasks = JsonMaper.mapToJson(tasks);

    mockMvc.perform(put("/tasks/saveTasks/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonTasks)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());

    findTaskByDescription("test save tasks 1");
    findTaskByDescription("test save tasks 2");
    Task task = findTaskByDescription("test save tasks 3");
    assertEquals(task.getProject().getDescription(),"project for save task test");
    assertEquals(task.getOrder().getDescription(),"order for save task test");
  }

  private Task findTaskByDescription(String description) {
    List<Task> tasks = em.createQuery("SELECT u FROM Task u WHERE description = :description")
      .setParameter("description", description).getResultList();
    Assert.notNull(tasks, "tasks is null");
    Assert.notEmpty(tasks,"task not found");
    return tasks.get(0);
  }

  @Test
  public void getTasksByProjectWithWrongId() throws Exception {
    mockMvc.perform(get("/tasks/getTasksByProject/0")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }

  @Test
  public void saveTasksWithoutBodyRequest() throws Exception {
    mockMvc.perform(put("/tasks/saveTasks/")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }
}