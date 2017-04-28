package ru.bravery_and_stupidity.trackerAdministrator.services;

import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.List;

public interface ProjectService {
  List<Project> getProjects();
  void createProject(Project project);
  void deleteProject(int id);
  void updateProject(Project project);
  Project getProject(int id);
}
