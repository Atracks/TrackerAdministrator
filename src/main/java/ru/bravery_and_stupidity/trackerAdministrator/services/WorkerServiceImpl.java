package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;
import ru.bravery_and_stupidity.trackerAdministrator.repository.TaskRepository;
import ru.bravery_and_stupidity.trackerAdministrator.repository.WorkerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WorkerServiceImpl implements WorkerService {

  @Autowired
  WorkerRepository workerRepository;

  @Autowired
  TaskRepository taskRepository;

  @Override
  @Transactional
  public List<Worker> getWorkers() {
    return workerRepository.getWorkers();
  }

  @Override
  @Transactional
  public void createWorker(Worker worker) {
    Assert.notNull(worker,"Worker argument can not be null");
    worker.isValid();
    workerRepository.saveWorker(worker);
  }

  @Override
  @Transactional
  public void deleteWorker(int id) {
    checkIsGod(id);
    ValidationUtils.checkId(id);
    deleteCoexecutorFromTasks(id);
    deleteSlavesByWorker(id);
    deleteFromMasterWorker(id);
    rebindTaskAuthorByWorker(id);
    workerRepository.deleteWorker(id);
  }

  private void checkIsGod(int id) {
    Worker worker = workerRepository.getWorkerById(id);
    if((byte)1 == worker.getIsGod()) {
      throw new IllegalArgumentException("Can't delete master worker");
    }
  }

  @Override
  @Transactional
  public void updateWorker(Worker worker) {
    Assert.notNull(worker, "Worker argument can not be null");
    ValidationUtils.checkId(worker.getIdWorker());
    worker.isValid();
    workerRepository.saveWorker(worker);
  }

  @Override
  @Transactional
  public Worker getWorker(int id) {
    ValidationUtils.checkId(id);
    return workerRepository.getWorkerById(id);
  }

  private void deleteSlavesByWorker(int id) {
    Worker worker = workerRepository.getWorkerById(id);
    worker.setSlaves(new HashSet<Worker>());
    workerRepository.saveWorker(worker);
  }

  private void deleteFromMasterWorker(int id) {
    Worker worker = workerRepository.getWorkerById(id);
    List<Worker> workers = workerRepository.getMasterWorkersBySlaveWorker(id);
    for (Worker w: workers) {
      w.getSlaves().remove(worker);
      workerRepository.saveWorker(w);
    }
  }

  private void deleteCoexecutorFromTasks(int id) {
    List<Task> tasks = taskRepository.getTasksByCoexecutor(id);
    Worker worker = workerRepository.getWorkerById(id);
    for (Task task: tasks) {
      task.getCoexecutors().remove(worker);
      taskRepository.saveTask(task);
    }
  }

  private void rebindTaskAuthorByWorker(int id) {
    Worker newAuthor;
    List<Task> tasks = taskRepository.getTasksByAuthor(id);
    List<Worker> workers = workerRepository.getWorkersIsGod();
    if(!workers.isEmpty()) {newAuthor = workers.get(0);}
    else {return;}
    for (Task task: tasks) {
      task.setAuthor(newAuthor);
      taskRepository.saveTask(task);
    }
  }
}
