package ru.bravery_and_stupidity.trackerAdministrator.dto;


import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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
    isOrdersEquals(task.getOrder(),taskDto.getOrder());
    isProjectsEquals(task.getProject(),taskDto.getProject());
    isWorkersEquals(task.getResponsible(), taskDto.getResponsible());
    isCoexecutorsEquals(task.getCoexecutors(), taskDto.getCoexecutors());
    assertEquals(task.getImportance(),taskDto.getImportance());
  }

  static void isProjectsEquals(Project project, ProjectDto projectDto) {
    if((project.getIdProject() == projectDto.getId())
        &&(project.getDescription().equals(projectDto.getDescription()))) {
    } else {throw new AssertionError("projects not equals");}
  }

  static void isOrdersEquals(Order order, OrderDto orderDto) {
    if(!((order.getIdOrder() == orderDto.getId())
        &&(order.getDescription().equals(orderDto.getDescription())))) {
      throw new AssertionError("orders not equals");
    }
  }

  private static void isWorkersEquals(Worker worker, WorkerDto workerDto) {
    if(worker.getIdWorker() == workerDto.getIdWorker()&&
      (worker.getName().equals(workerDto.getName()))&&
      (worker.getSurname().equals(workerDto.getSurname()))&&
      (worker.getPatronymic().equals(workerDto.getPatronymic()))&&
      (worker.getEmail().equals(workerDto.getEmail()))&&
      (worker.getLogin().equals(workerDto.getLogin()))&&
      (worker.getPass().equals(workerDto.getPass()))&&
      (worker.getPosition().equals(workerDto.getPosition()))&&
      (worker.getIsGod() == workerDto.getIsGod())) {
    } else {throw new AssertionError("workers not equals");}
  }

  private static void isCoexecutorsEquals(Set<Worker> workers, List<WorkerDto> workersDto) {
    assertEquals(workers.size(),workersDto.size());
    for (WorkerDto workerDto: workersDto) {
      if(!workers.contains(WorkerDto.mapToModel(workerDto))) {
        throw new AssertionError("coexecutors not equals");
      }
    }
  }
}
