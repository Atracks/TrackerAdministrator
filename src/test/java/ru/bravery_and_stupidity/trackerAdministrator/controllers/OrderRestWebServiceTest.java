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
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.OrderDto;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    .andExpect(jsonPath("$.[2].['description']").value("order 3")).andExpect(jsonPath("$.[2].['id']").value("3"));
  }

  @Test
  public void addOrder() throws Exception {
    mockMvc.perform(post("/orders/addOrder/add order test")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());
    assertNotNull("order not added", findOrderByDescription("add order test"));
  }

  private Order findOrderByDescription(String description) {
    List<Order> orders = em.createQuery("SELECT o FROM Order o WHERE description = :description")
      .setParameter("description", description).getResultList();
    Assert.notNull(orders,"orders can not be null");
    Assert.notEmpty(orders,"order not found");
    return orders.get(0);
  }

  @Test(expected = Exception.class)
  @Transactional
  public void deleteOrder() throws Exception {
    orderRepository.saveOrder(TestDtoCreater.createOrder("order22"));
    Order order = findOrderByDescription("order22");
    mockMvc.perform(delete("/orders/deleteOrder/" + String.valueOf(order.getIdOrder()))
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());
    findOrderByDescription("order22");
  }

  @Test
  @Transactional
  public void updateOrder() throws Exception {
    String orderDescription = "order update test 1234";
    String changedOrderDescription = "changed order description";

    orderRepository.saveOrder(TestDtoCreater.createOrder(orderDescription));
    Order order = findOrderByDescription(orderDescription);
    order.setDescription(changedOrderDescription);
    String jsonProject = JsonMaper.mapToJson(OrderDto.mapFromModel(order));

    mockMvc.perform(put("/orders/updateOrder/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonProject)
    .accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk());

    order = findOrderByDescription(changedOrderDescription);
    assertEquals(order.getDescription(), changedOrderDescription);
  }

  @Test
  @Transactional
  public void updateOrderWithoutBodyRequest() throws Exception {
    mockMvc.perform(put("/orders/updateOrder/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void deleteOrderWithWrongId() throws Exception {
    mockMvc.perform(delete("/orders/deleteOrder/0")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void addOrderWithoutDescription() throws Exception {
    mockMvc.perform(post("/orders/addOrder")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
