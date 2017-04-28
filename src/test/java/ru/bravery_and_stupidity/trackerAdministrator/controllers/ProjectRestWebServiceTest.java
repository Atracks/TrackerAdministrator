package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.ProjectWithTasksDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.repository.ProjectRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class ProjectRestWebServiceTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ProjectRepository projectRepository;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void init()  {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getProjects() throws Exception {
    mockMvc.perform(get("/projects/projectsList.json")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType("application/json;charset=UTF-8"))
    .andExpect(jsonPath("$.[0].['description']").value("project 1")).andExpect(jsonPath("$.[0].['id']").value("1"))
    .andExpect(jsonPath("$.[1].['description']").value("project 2")).andExpect(jsonPath("$.[1].['id']").value("2"))
    .andExpect(jsonPath("$.[2].['description']").value("project 3")).andExpect(jsonPath("$.[2].['id']").value("3"))

    .andExpect(jsonPath("$.[2].['tasks'].[0].['id']").value("1"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['description']").value("Тестовое поручение1"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['creationDate']").value("2015-12-31"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['deadlineDate']").value("2017-02-12"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['status']").value("0"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['isOverdue']").value("1"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['importance']").value("5"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['parentTaskId']").value("22"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['project'].['id']").value("3"))
    .andExpect(jsonPath("$.[2].['tasks'].[0].['project'].['description']").value("project 3"))
    //FIXME
    //.andExpect(jsonPath("$.[2].['tasks'].[0].['order']").value())
    //.andExpect(jsonPath("$.[2].['tasks'].[0].['responsible']").value())

    .andExpect(jsonPath("$.[2].['tasks'].[1].['id']").value("2"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['description']").value("Тестовое поручение2"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['creationDate']").value("2013-11-22"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['deadlineDate']").value("2016-08-05"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['status']").value("0"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['isOverdue']").value("1"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['importance']").value("10"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['parentTaskId']").value("22"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['project'].['id']").value("3"))
    .andExpect(jsonPath("$.[2].['tasks'].[1].['project'].['description']").value("project 3"));
    //FIXME
    //.andExpect(jsonPath("$.[2].['tasks'].[0].['order']").value())
    //.andExpect(jsonPath("$.[2].['tasks'].[0].['responsible']").value())
  }

  @Test
  public void addProject() throws Exception {
    mockMvc.perform(post("/projects/addProject/add project test")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());
    assertNotNull("project not added", findProjectByDescription("add project test"));
  }

  private Project findProjectByDescription(String description) {
    List<Project> projects = em.createQuery("select u from Project u where description = :description")
        .setParameter("description", description).getResultList();
    if(projects.isEmpty()) {
      return null;
    }
    return projects.get(0);
  }

  @Test
  @Transactional
  public void deleteProject() throws Exception {
    projectRepository.saveProject(TestDtoCreater.createProject("project200"));
    Project project = findProjectByDescription("project200");
    mockMvc.perform(delete("/projects/deleteProject/" + String.valueOf(project.getIdProject()))
     .contentType(MediaType.APPLICATION_JSON)
     .accept(MediaType.APPLICATION_JSON))
     .andDo(print())
     .andExpect(status().isOk());
     assertNull("project not deleted", findProjectByDescription("project200"));
  }

  @Test
  @Transactional
  public void updateProject() throws Exception {
    String prjDescription = "project update test 345";
    String changedPrjDescription = "changed project description";

    projectRepository.saveProject(TestDtoCreater.createProject(prjDescription));
    Project project = findProjectByDescription(prjDescription);
    project.setDescription(changedPrjDescription);
    String jsonProject = JsonMaper.mapToJson(ProjectWithTasksDto.mapFromModel(project));

    mockMvc.perform(put("/projects/updateProject/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonProject)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());

     project = findProjectByDescription(changedPrjDescription);
     assertEquals(project.getDescription(), changedPrjDescription);
  }

  @Test
  @Transactional
  public void updateProjectWithoutBodyRequest() throws Exception {
    mockMvc.perform(put("/projects/updateProject/")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }

  @Test
  public void deleteProjectWithWrongId() throws Exception {
    mockMvc.perform(delete("/projects/deleteProject/0")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }

  @Test
  public void addProjectWithoutDescription() throws Exception {
    mockMvc.perform(post("/projects/addProject")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isNotFound());
  }
}