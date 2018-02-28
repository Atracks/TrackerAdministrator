package ru.bravery_and_stupidity.trackerAdministrator.repository;

import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WorkerRepositoryImpl implements WorkerRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Worker> getWorkers() {
    List<Worker> workers = em.createQuery("FROM Worker").getResultList();
    return workers;
  }

  @Override
  public void saveWorker(Worker worker) {
    em.merge(worker);
  }

  @Override
  public void deleteWorker(int id) {
    Worker worker = em.find(Worker.class, id);
    em.remove(worker);
  }

  @Override
  public Worker getWorkerById(int id) {
    return em.find(Worker.class, id);
  }

  @Override
  public List<Worker> getMasterWorkersBySlaveWorker(int workerId) {
    List<Worker> workers = em.createQuery("SELECT worker FROM Worker worker LEFT JOIN worker.slaves slave " +
        "WHERE slave.idWorker = :workerId")
        .setParameter("workerId", workerId).getResultList();
    return workers;
  }

  @Override
  public List<Worker> getWorkersIsGod() {
    byte isGod = 1;
    List<Worker> workers = em.createQuery("SELECT worker FROM Worker worker WHERE worker.isGod = :isGod")
        .setParameter("isGod", isGod).getResultList();
    return workers;
  }
}


