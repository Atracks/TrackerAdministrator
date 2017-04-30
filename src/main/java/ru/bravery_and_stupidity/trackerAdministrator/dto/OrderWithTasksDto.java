package ru.bravery_and_stupidity.trackerAdministrator.dto;

import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.ArrayList;
import java.util.List;

public class OrderWithTaskDto {
  private int id;
  private String description;
  private List<TaskDto> tasks = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<TaskDto> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskDto> tasks) {
    this.tasks = tasks;
  }

  public void addTask(TaskDto task) {this.tasks.add(task);}

  public static OrderWithTaskDto mapFromModel(Project project) {
    ProjectWithTasksDto projectWithTasksDto = new ProjectWithTasksDto();
    projectWithTasksDto.setId(project.getIdProject());
    projectWithTasksDto.setDescription(project.getDescription());
    projectWithTasksDto.setTasks(TaskDto.mapFromModels(project.getTasks()));
    return projectWithTasksDto;
  }

  public static Project mapToModel(ProjectWithTasksDto projectWithTasksDto) {
    Project project = new Project();
    project.setIdProject(projectWithTasksDto.getId());
    project.setDescription(projectWithTasksDto.getDescription());
    project.setTasks(TaskDto.mapToModels(projectWithTasksDto.getTasks()));
    return project;
  }

  public static List<ProjectWithTasksDto> mapFromModels(List<Project> projects) {
    List<ProjectWithTasksDto> projectsWithTasksDto = new ArrayList<>();
    for (Project project: projects) {
      projectsWithTasksDto.add(mapFromModel(project));
    }
    return projectsWithTasksDto;
  }
}
