package ru.bravery_and_stupidity.trackerAdministrator.dto;


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
    assertTrue(task.getOrder().equals(taskDto.getOrder()));
    ValidationUtils.isProjectsEquals(task.getProject(),taskDto.getProject());
    assertTrue(task.getResponsible().equals(taskDto.getResponsible()));
    assertEquals(task.getImportance(),taskDto.getImportance());
    assertEquals(task.getResponsible(),taskDto.getResponsible());
  }

  public static void isProjectsEquals(Project project, ProjectDto projectDto) {
    if((project.getIdProject() == projectDto.getId())
        &&(project.getDescription().equals(projectDto.getDescription()))) {
    } else {throw new AssertionError("projects not equals");}
  }

  public static void isProjectsEquals(Project project, ProjectWithTasksDto projectDto) {
    if((project.getIdProject() == projectDto.getId())
        &&(project.getDescription().equals(projectDto.getDescription()))
        &&(project.getTasks().size() == projectDto.getTasks().size())) {
    } else {throw new AssertionError("projects not equals");}
    Set<Task> projectTasks = project.getTasks();
    List<TaskDto> projectDtoTasks = projectDto.getTasks();
    for (int i = 0; i < project.getTasks().size() ; i++) {
      isTasksEquals((Task)projectTasks.toArray()[i], projectDtoTasks.get(i));
    }
  }
}
