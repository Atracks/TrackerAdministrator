package ru.bravery_and_stupidity.trackerAdministrator.services;

import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.util.List;

public interface WorkerService {
  List<Worker> getWorkers();
  void createWorker(Worker worker);
  void deleteWorker(int id);
  void updateWorker(Worker Worker);
  Worker getWorker(int id);
}
