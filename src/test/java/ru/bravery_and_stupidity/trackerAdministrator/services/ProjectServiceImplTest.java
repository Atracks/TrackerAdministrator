package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class ProjectServiceImplTest {
  private final static String TEST_PROJECT_DESC = "Test project";

  @Autowired
  private ProjectService projectsService;

  @PersistenceContext
  private EntityManager em;

  @Test
  public void getProjects() throws Exception {
    List<Project> projects = projectsService.getProjects();
    assertEquals(projects.size(), 3);
    assertEquals(projects.get(0).getDescription(),"project 1");
    assertEquals(projects.get(1).getDescription(),"project 2");
    assertEquals(projects.get(2).getDescription(),"project 3");
  }

  @Test
  public void createProject() throws Exception {
    projectsService.createProject(new Project(TEST_PROJECT_DESC));
    Project project = findProjectByDescription(TEST_PROJECT_DESC);
    assertEquals(project.getDescription(),TEST_PROJECT_DESC);
    projectsService.deleteProject(project.getIdProject());
  }

  private Project findProjectByDescription(String description) {
    List<Project> projects = em.createQuery("select u from Project u where description = :description")
      .setParameter("description", description).getResultList();
    return projects.get(0);
  }


  @Test
  public void deleteProject() throws Exception {
    projectsService.createProject(new Project(TEST_PROJECT_DESC));
    Project project = findProjectByDescription(TEST_PROJECT_DESC);
    projectsService.deleteProject(project.getIdProject());
    List<Project> projects = projectsService.getProjects();
    assertFalse(projects.contains(project));
  }

  @Test
  public void updateProject() throws Exception {
    projectsService.createProject(new Project(TEST_PROJECT_DESC));
    Project project = findProjectByDescription(TEST_PROJECT_DESC);
    project.setDescription("updated project");
    projectsService.updateProject(project);
    project = findProjectByDescription("updated project");
    assertEquals(project.getDescription(), "updated project");
    projectsService.deleteProject(project.getIdProject());
  }

  @Test
  public void getProjectById() {
    Project project = projectsService.getProject(1);
    assertEquals(project.getDescription(), "project 1");
    project = projectsService.getProject(2);
    assertEquals(project.getDescription(), "project 2");
    project = projectsService.getProject(3);
    assertEquals(project.getDescription(), "project 3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullProject() {
    projectsService.createProject(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithEmptyProjectDescription() {
    projectsService.createProject(new Project(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithWrongId() {
    projectsService.deleteProject(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithNullProject() {
    projectsService.updateProject(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithEmptyProjectDescription() {
    projectsService.updateProject(new Project(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithWrongId() {
    projectsService.updateProject(new Project(0, "project"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getProjectWithWrongIdNull() {
    projectsService.getProject(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getProjectWithWrongIdNegative() {
    projectsService.getProject(-10);
  }
}