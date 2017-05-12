package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.repository.ProjectRepository;
import ru.bravery_and_stupidity.trackerAdministrator.repository.TaskRepository;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  TaskRepository taskRepository;
  @Autowired
  ProjectRepository projectRepository;

  @Override
  @Transactional
  public List<TaskDto> getTasksByProject(int projectId) {
    ValidationUtils.checkId(projectId);
    return TaskDto.mapFromModels(taskRepository.getTasksByProject(projectId));
  }

  @Override
  public List<TaskDto> getTasksByOrder(int orderId) {
    ValidationUtils.checkId(orderId);
    return TaskDto.mapFromModels(taskRepository.getTasksByOrder(orderId));
  }

  @Override
  @Transactional
  public void saveTasks(Set<Task> tasks) {
    for (Task task: tasks) {
      taskRepository.saveTask(task);
    }
  }
}
