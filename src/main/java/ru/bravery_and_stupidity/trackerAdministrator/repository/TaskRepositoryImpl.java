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
}
