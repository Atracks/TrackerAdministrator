package ru.bravery_and_stupidity.trackerAdministrator.dto;


import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

final public class ValidationUtils {

  public static void isTasksEquals(Task task, TaskDto taskDto) {
    assertEquals(task.getDescription(),taskDto.getDescription());
    assertEquals(task.getCreationDate(),taskDto.getCreationDate());
    assertEquals(task.getDeadlineDate(),taskDto.getDeadlineDate());
    assertEquals(task.getIdTask(),taskDto.getId());
    assertEquals(task.getImportance(),taskDto.getImportance());
    assertEquals(task.getIsOverdue(),taskDto.getIsOverdue());
    assertEquals(task.getParentTaskId(),taskDto.getParentTaskId());
    assertEquals(task.getStatus(),taskDto.getStatus());
    ValidationUtils.isOrdersEquals(task.getOrder(),taskDto.getOrder());
    ValidationUtils.isProjectsEquals(task.getProject(),taskDto.getProject());
    //FIXME
    //assertTrue(task.getResponsible().equals(taskDto.getResponsible()));
    assertEquals(task.getImportance(),taskDto.getImportance());
    //FIXME
    //assertEquals(task.getResponsible(),taskDto.getResponsible());
  }

  public static void isProjectsEquals(Project project, ProjectDto projectDto) {
    if((project.getIdProject() == projectDto.getId())
        &&(project.getDescription().equals(projectDto.getDescription()))) {
    } else {throw new AssertionError("projects not equals");}
  }

  public static void isOrdersEquals(Order order, OrderDto orderDto) {
    if((order.getIdOrder() == orderDto.getId())
        &&(order.getDescription().equals(orderDto.getDescription()))) {
    } else {throw new AssertionError("orders not equals");}
  }
}
