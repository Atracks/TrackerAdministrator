package ru.bravery_and_stupidity.trackerAdministrator.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository repository;

  @Override
  @Transactional
  public List<Project> getProjects() {
    return repository.getProjects();
  }

  @Override
  @Transactional
  public void createProject(Project project) {
    Assert.notNull(project, "Project argument can not be null");
    Assert.hasLength(project.getDescription(),"Project description can not be empty");
    repository.saveProject(project);
  }

  @Override
  @Transactional
  public void deleteProject(int id) {
    ValidationUtils.checkId(id);
    repository.deleteProject(id);
  }

  @Override
  @Transactional
  public void updateProject(Project project) {
    Assert.notNull(project, "Project argument can not be null");
    ValidationUtils.checkId(project.getIdProject());
    Assert.hasLength(project.getDescription(),"Project description can not be empty");
    repository.saveProject(project);
  }

  @Override
  @Transactional
  public Project getProject(int id) {
    ValidationUtils.checkId(id);
    return repository.getProjectById(id);
  }
}
