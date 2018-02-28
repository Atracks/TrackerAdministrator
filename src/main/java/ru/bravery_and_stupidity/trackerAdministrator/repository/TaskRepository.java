package ru.bravery_and_stupidity.trackerAdministrator.repository;

import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import java.util.List;
import java.util.Set;


public interface TaskRepository {
  void saveTask(Task task);
  List<Task> getTasksByProject(int projectId);
  List<Task> getTasksByOrder(int orderId);
  List<Task> getTasksByResponsible(int workerId);
  List<Task> getTasksByAuthor(int workerId);
  List<Task> getTasksByCoexecutor(int workerId);
  Task getTaskById(int taskId);
}
