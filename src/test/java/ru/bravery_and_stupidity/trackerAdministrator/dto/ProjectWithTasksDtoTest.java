package ru.bravery_and_stupidity.trackerAdministrator.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ProjectWithTasksDtoTest {

  @Test
  public void mapFromModel() throws Exception {
    Project project = TestDtoCreater.createProject(67, "prjprj");
    ProjectWithTasksDto projectWithTasksDto = ProjectWithTasksDto.mapFromModel(project);
    ValidationUtils.isProjectsEquals(project, projectWithTasksDto);
  }

  @Test
  public void mapToModel() throws Exception {
    ProjectWithTasksDto projectWithTasksDto = TestDtoCreater.createProjectWithTasksDto(12,"prprp");
    Project project = ProjectWithTasksDto.mapToModel(projectWithTasksDto);
    ValidationUtils.isProjectsEquals(project, projectWithTasksDto);
  }

  @Test
  public void mapFromModels() throws Exception {
    List<Project> projects = new ArrayList<>();
    projects.add(TestDtoCreater.createProject(78,"project 1"));
    projects.add(TestDtoCreater.createProject(11,"project 2"));
    projects.add(TestDtoCreater.createProject(34,"project 3"));
    List<ProjectWithTasksDto> projectsWithTasksDto = ProjectWithTasksDto.mapFromModels(projects);
    ValidationUtils.isProjectsEquals(projects.get(0), projectsWithTasksDto.get(0));
    ValidationUtils.isProjectsEquals(projects.get(1), projectsWithTasksDto.get(1));
    ValidationUtils.isProjectsEquals(projects.get(2), projectsWithTasksDto.get(2));
  }

}