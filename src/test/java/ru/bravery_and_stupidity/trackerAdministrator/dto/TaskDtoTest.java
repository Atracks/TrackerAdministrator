package ru.bravery_and_stupidity.trackerAdministrator.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class TaskDtoTest {
  @Test
  public void mapFromModel() throws Exception {
    Task task = TestDtoCreater.createTask(11, "test task description 1");
    TaskDto taskDto = TaskDto.mapFromModel(task);
    ValidationUtils.isTasksEquals(task, taskDto);
  }

  @Test
  public void mapToModel() throws Exception {
    TaskDto taskDto = TestDtoCreater.createTaskDto(45, " Test task dto");
    Task task = TaskDto.mapToModel(taskDto);
    ValidationUtils.isTasksEquals(task, taskDto);
  }

  @Test
  public void mapFromModels() throws Exception {
    Set<Task> tasks = new LinkedHashSet<>();
    tasks.add(TestDtoCreater.createTask(6,"task 23"));
    tasks.add(TestDtoCreater.createTask(7,"task 11"));
    tasks.add(TestDtoCreater.createTask(8,"task 56"));

    List<TaskDto> tasksDto = TaskDto.mapFromModels(tasks);
    assertEquals(tasks.size(), tasksDto.size());
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[0], tasksDto.get(0));
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[1], tasksDto.get(1));
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[2], tasksDto.get(2));
  }

  @Test
  public void mapToModels() throws Exception {
    List<TaskDto> tasksDto = new ArrayList<>();
    tasksDto.add(TestDtoCreater.createTaskDto(6,"task 23"));
    tasksDto.add(TestDtoCreater.createTaskDto(7,"task 11"));
    tasksDto.add(TestDtoCreater.createTaskDto(8,"task 56"));

    Set<Task> tasks = TaskDto.mapToModels(tasksDto);
    assertEquals(tasks.size(), tasksDto.size());
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[0],tasksDto.get(0));
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[1],tasksDto.get(1));
    ValidationUtils.isTasksEquals((Task)tasks.toArray()[2],tasksDto.get(2));
  }
}