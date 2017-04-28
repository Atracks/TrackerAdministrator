package ru.bravery_and_stupidity.trackerAdministrator.repository;

import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import java.util.List;


public interface TaskRepository {
  void saveTask(Task task);
}
