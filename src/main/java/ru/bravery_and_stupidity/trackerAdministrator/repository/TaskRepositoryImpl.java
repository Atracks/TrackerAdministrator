package ru.bravery_and_stupidity.trackerAdministrator.repository;

import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public void saveTask(Task task) {
    em.merge(task);
  }

  @Override
  public List<Task> getTasksByProject(int projectId) {
    List<Task> tasks = em.createQuery("SELECT task FROM Task task JOIN task.project project " +
                                        "WHERE project.idProject = :projectId")
      .setParameter("projectId", projectId).getResultList();
    return tasks;
  }

  @Override
  public List<Task> getTasksByOrder(int orderId) {
    List<Task> tasks = em.createQuery("SELECT task FROM Task task JOIN task.order o " +
                                        "WHERE o.idOrder = :orderId")
      .setParameter("orderId", orderId).getResultList();
    return tasks;
  }

  @Override
  public List<Task> getTasksByWorker(int workerId) {
    List<Task> tasks = em.createQuery("SELECT task FROM Task task LEFT JOIN task.coexecutors coexecutor " +
                                        "WHERE task.responsible.idWorker = :workerId OR coexecutor.idWorker = :workerId")
      .setParameter("workerId", workerId).getResultList();
    return tasks;
  }
}
