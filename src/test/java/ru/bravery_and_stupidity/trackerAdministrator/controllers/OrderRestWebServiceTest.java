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
import org.springframework.web.context.WebApplicationContext;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class OrderRestWebServiceTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private OrderRepository orderRepository;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void init()  {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getOrders() throws Exception {
    mockMvc.perform(get("/orders/ordersList.json")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType("application/json;charset=UTF-8"))
    .andExpect(jsonPath("$.[0].['description']").value("order 1")).andExpect(jsonPath("$.[0].['id']").value("1"))
    .andExpect(jsonPath("$.[1].['description']").value("order 2")).andExpect(jsonPath("$.[1].['id']").value("2"))
    .andExpect(jsonPath("$.[2].['description']").value("order 3")).andExpect(jsonPath("$.[2].['id']").value("3"))

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
}
