package ru.bravery_and_stupidity.trackerAdministrator.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.ArrayList;
import java.util.List;

@JsonNaming
final public class ProjectDto {
  private int id;
  private String description;

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public static ProjectDto mapFromModel(Project project) {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setId(project.getIdProject());
    projectDto.setDescription(project.getDescription());
    return projectDto;
  }

  public static Project mapToModel(ProjectDto projectDto) {
    Project project = new Project();
    project.setIdProject(projectDto.getId());
    project.setDescription(projectDto.getDescription());
    return project;
  }

  public static List<ProjectDto> mapFromModels(List<Project> projects) {
    List<ProjectDto> projectsDto = new ArrayList<>();
    for (Project project: projects) {
      projectsDto.add(mapFromModel(project));
    }
    return projectsDto;
  }
}
