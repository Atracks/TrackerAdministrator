package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.WorkerDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;
import ru.bravery_and_stupidity.trackerAdministrator.repository.TaskRepository;
import ru.bravery_and_stupidity.trackerAdministrator.repository.WorkerRepository;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  WorkerRepository workerRepository;

  @Override
  @Transactional
  public List<TaskDto> getTasksByProject(int projectId) {
    ValidationUtils.checkId(projectId);
    return TaskDto.mapFromModels(taskRepository.getTasksByProject(projectId));
  }

  @Override
  @Transactional
  public List<TaskDto> getTasksByOrder(int orderId) {
    ValidationUtils.checkId(orderId);
    return TaskDto.mapFromModels(taskRepository.getTasksByOrder(orderId));
  }

  @Override
  @Transactional
  public List<TaskDto> getTasksByResponsible(int workerId) {
    ValidationUtils.checkId(workerId);
    List<TaskDto> tasksDto = TaskDto.mapFromModels(taskRepository.getTasksByResponsible(workerId));
    for (TaskDto task: tasksDto) {
      task.setAvailableResponsible(WorkerDto.mapFromModels(getAvailableResponsibleListByTask(task.getId())));
    }
    return tasksDto;
  }

  @Override
  @Transactional
  public void saveTasks(Set<Task> tasks) {
    for (Task task: tasks) {
      taskRepository.saveTask(task);
    }
  }

  @Transactional
  private List<Worker> getAvailableResponsibleListByTask(int idTask) {
    ValidationUtils.checkId(idTask);
    Task task = taskRepository.getTaskById(idTask);
    Assert.notNull(task,"Task not found");
    Set<Worker> coexecutors = task.getCoexecutors();
    List<Worker> workers = workerRepository.getWorkers();
    for (Worker coexecutor: coexecutors) {
      workers.remove(coexecutor);
    }
    //workers.remove(task.getAuthor());
    return workers;
  }
}
