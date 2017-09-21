package ru.bravery_and_stupidity.trackerAdministrator.controllers;

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
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.dto.WorkerDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;
import ru.bravery_and_stupidity.trackerAdministrator.repository.WorkerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class WorkerRestWebServiceTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private WorkerRepository workerRepository;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void init()  {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getWorkers() throws Exception {
    mockMvc.perform(get("/workers/workersList.json")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))

        .andExpect(jsonPath("$.[0].['idWorker']").value("1")).andExpect(jsonPath("$.[0].['name']").value("John"))
        .andExpect(jsonPath("$.[0].['surname']").value("Lenon")).andExpect(jsonPath("$.[0].['patronymic']").value("Zigizmundovich"))
        .andExpect(jsonPath("$.[0].['position']").value("Legend of rock")).andExpect(jsonPath("$.[0].['email']").value("email@mnb.com"))
        .andExpect(jsonPath("$.[0].['login']").value("login")).andExpect(jsonPath("$.[0].['pass']").value("pass"))
        .andExpect(jsonPath("$.[0].['isGod']").value("0"))

        .andExpect(jsonPath("$.[1].['idWorker']").value("2")).andExpect(jsonPath("$.[1].['name']").value("Seed"))
        .andExpect(jsonPath("$.[1].['surname']").value("Vishes")).andExpect(jsonPath("$.[1].['patronymic']").value("Zigizmundovich"))
        .andExpect(jsonPath("$.[1].['position']").value("Legend of punk rock")).andExpect(jsonPath("$.[1].['email']").value("email@mnb.com"))
        .andExpect(jsonPath("$.[1].['login']").value("login")).andExpect(jsonPath("$.[1].['pass']").value("pass"))
        .andExpect(jsonPath("$.[1].['isGod']").value("0"));;
  }

  @Test
  public void addWorker() throws Exception {
    Worker worker = TestDtoCreater.createWorker(3, "Donald", "Trump");
    String jsonWorker = JsonMaper.mapToJson(worker);

    mockMvc.perform(post("/workers/addWorker/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonWorker)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());

    worker = findWorker("Donald", "Trump");
    assertEquals(worker.getIdWorker(), 3);
  }

  private Worker findWorker(String name, String surname) {
    List<Worker> workers = em.createQuery("SELECT worker FROM Worker worker WHERE name = :name " +
      "AND surname = :surname")
      .setParameter("name", name)
      .setParameter("surname", surname).getResultList();
    return workers.get(0);
  }

  @Test(expected = Exception.class)
  @Transactional
  public void deleteWorker() throws Exception {
    workerRepository.saveWorker(TestDtoCreater.createWorker(3, "Donald", "Trump"));
    Worker worker = findWorker("Donald", "Trump");
    mockMvc.perform(delete("/workers/deleteWorker/" + String.valueOf(worker.getIdWorker()))
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());
    findWorker("Donald", "Trump");
  }

  @Test
  @Transactional
  public void updateWorker() throws Exception {
    String workerSurname = "Trump";
    String changedWorkerSurname = "Klinton";

    workerRepository.saveWorker(TestDtoCreater.createWorker(3, "Donald", workerSurname));
    Worker worker = findWorker("Donald", "Trump");
    worker.setSurname(changedWorkerSurname);
    String jsonWorker = JsonMaper.mapToJson(WorkerDto.mapFromModel(worker));

    mockMvc.perform(put("/workers/updateWorker/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonWorker)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());

    worker = findWorker("Donald", changedWorkerSurname);
    assertEquals(worker.getSurname(), changedWorkerSurname);
    workerRepository.deleteWorker(3);
  }
}
