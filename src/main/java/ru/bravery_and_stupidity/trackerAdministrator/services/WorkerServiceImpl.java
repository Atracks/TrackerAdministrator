package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;
import ru.bravery_and_stupidity.trackerAdministrator.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

  @Autowired
  WorkerRepository repository;

  @Override
  @Transactional
  public List<Worker> getWorkers() {
    return repository.getWorkers();
  }

  @Override
  @Transactional
  public void createWorker(Worker worker) {
    Assert.notNull(worker,"Worker argument can not be null");
    worker.isValid();
    repository.saveWorker(worker);
  }

  @Override
  @Transactional
  public void deleteWorker(int id) {
    ValidationUtils.checkId(id);
    repository.deleteWorker(id);
  }

  @Override
  @Transactional
  public void updateWorker(Worker worker) {
    Assert.notNull(worker, "Worker argument can not be null");
    ValidationUtils.checkId(worker.getIdWorker());
    worker.isValid();
    repository.saveWorker(worker);
  }

  @Override
  public Worker getWorker(int id) {
    ValidationUtils.checkId(id);
    return repository.getWorkerById(id);
  }
}
