package ru.bravery_and_stupidity.trackerAdministrator.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestDataInitializer;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ProjectDtoTest {
  @Test
  public void mapFromModel() throws Exception {
    Project project = TestDtoCreater.createProject(67, "project manheten");
    ProjectDto projectDto = ProjectDto.mapFromModel(project);
    ValidationUtils.isProjectsEquals(project, projectDto);
  }

  @Test
  public void mapToModel() throws Exception {
    ProjectDto projectDto = TestDtoCreater.createProjectDto(12, "prj");
    Project project = ProjectDto.mapToModel(projectDto);
    ValidationUtils.isProjectsEquals(project, projectDto);
  }

  @Test
  public void mapFromModels() throws Exception {
    final List<Project> projects = new ArrayList<>();
    projects.add(TestDtoCreater.createProject(78,"project 1"));
    projects.add(TestDtoCreater.createProject(11,"project 2"));
    projects.add(TestDtoCreater.createProject(34,"project 3"));
    List<ProjectDto> projectsDto = ProjectDto.mapFromModels(projects);
    ValidationUtils.isProjectsEquals(projects.get(0), projectsDto.get(0));
    ValidationUtils.isProjectsEquals(projects.get(1), projectsDto.get(1));
    ValidationUtils.isProjectsEquals(projects.get(2), projectsDto.get(2));
  }

}