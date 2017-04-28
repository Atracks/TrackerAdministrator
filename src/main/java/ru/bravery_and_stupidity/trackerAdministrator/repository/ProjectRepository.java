package ru.bravery_and_stupidity.trackerAdministrator.repository;

import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.List;

public interface ProjectRepository {
  List<Project> getProjects();
  void saveProject(Project project);
  void deleteProject(int id);
  Project getProjectById(int id);
}
