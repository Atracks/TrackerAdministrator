package ru.bravery_and_stupidity.trackerAdministrator.repository;

import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    List<Task> tasks = em.createQuery("SELECT DISTINCT t FROM Task t JOIN t.project p where p.idProject = :projectId")
      .setParameter("projectId", projectId).getResultList();
    return tasks;
  }

  @Override
  public List<Task> getTasksByOrder(int orderId) {
    List<Task> tasks = em.createQuery("SELECT DISTINCT t FROM Task t JOIN t.order o where o.idOrder = :orderId")
      .setParameter("orderId", orderId).getResultList();
    return tasks;
  }
}
