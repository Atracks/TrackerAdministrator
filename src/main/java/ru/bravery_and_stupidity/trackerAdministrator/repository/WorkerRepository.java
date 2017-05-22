package ru.bravery_and_stupidity.trackerAdministrator.repository;

import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.util.List;

public interface WorkerRepository {
  List<Worker> getWorkers();
  void saveWorker(Worker worker);
  void deleteWorker(int id);
  Worker getWorkerById(int id);
}
