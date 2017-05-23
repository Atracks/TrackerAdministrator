package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bravery_and_stupidity.trackerAdministrator.Application;
import ru.bravery_and_stupidity.trackerAdministrator.config.TestConfiguration;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TestDtoCreater;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, Application.class})
public class OrderServiceImplTest {
  private static String TEST_ORDER_DESC = "test order";

  @Autowired
  private OrderService ordersService;

  @PersistenceContext
  private EntityManager em;

  @Test
  public void getOrders() throws Exception {
    List<Order> orders = ordersService.getOrders();
    assertEquals(orders.get(0).getDescription(),"order 1");
    assertEquals(orders.get(1).getDescription(),"order 2");
    assertEquals(orders.get(2).getDescription(),"order 3");
  }

  @Test
  @Transactional
  public void createOrder() throws Exception {
    ordersService.createOrder(TestDtoCreater.createOrder(TEST_ORDER_DESC));
    Order order = findOrderByDescription(TEST_ORDER_DESC);
    assertEquals(order.getDescription(),TEST_ORDER_DESC);
    ordersService.deleteOrder(order.getIdOrder());
  }

  private Order findOrderByDescription(String description) {
    List<Order> orders = em.createQuery("SELECT ord from Order ord WHERE description = :description")
      .setParameter("description", description).getResultList();
    return orders.get(0);
  }

  @Test
  @Transactional
  public void deleteOrder() throws Exception {
    ordersService.createOrder(TestDtoCreater.createOrder(TEST_ORDER_DESC));
    Order order = findOrderByDescription(TEST_ORDER_DESC);
    ordersService.deleteOrder(order.getIdOrder());
    List<Order> orders = ordersService.getOrders();
    assertFalse(orders.contains(order));
  }

  @Test
  @Transactional
  public void updateOrder() throws Exception {
    ordersService.createOrder(TestDtoCreater.createOrder(TEST_ORDER_DESC));
    Order order = findOrderByDescription(TEST_ORDER_DESC);
    order.setDescription("updated order");
    ordersService.updateOrder(order);
    order = findOrderByDescription("updated order");
    assertEquals(order.getDescription(), "updated order");
    ordersService.deleteOrder(order.getIdOrder());
  }

  @Test
  public void getOrder() throws Exception {
    Order order = ordersService.getOrder(1);
    assertEquals(order.getDescription(), "order 1");
    order = ordersService.getOrder(2);
    assertEquals(order.getDescription(), "order 2");
    order = ordersService.getOrder(3);
    assertEquals(order.getDescription(), "order 3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullOrder() {
    ordersService.createOrder(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithEmptyOrderDescription() {
    ordersService.createOrder(TestDtoCreater.createOrder(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithWrongId() {
    ordersService.deleteOrder(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithNullOrder() {
    ordersService.updateOrder(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithEmptyProjectDescription() {
    ordersService.updateOrder(TestDtoCreater.createOrder(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithWrongId() {
    ordersService.updateOrder(TestDtoCreater.createOrder(0, "order"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getOrderWithWrongIdNull() {
    ordersService.getOrder(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getOrderWithWrongIdNegative() {
    ordersService.getOrder(-10);
  }
}