package ru.bravery_and_stupidity.trackerAdministrator.repository;


import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Project> getProjects() {
    List<Project> projects = em.createNamedQuery(Project.GET_ALL, Project.class).getResultList();
    return projects;
  }

  @Override
  public void saveProject(Project project) {
    em.merge(project);
  }

  @Override
  public void deleteProject(int id) {
    Project project = em.find(Project.class, id);
    em.remove(project);
  }

  @Override
  public Project getProjectById(int id) {
    return em.find(Project.class, id);
  }
}
