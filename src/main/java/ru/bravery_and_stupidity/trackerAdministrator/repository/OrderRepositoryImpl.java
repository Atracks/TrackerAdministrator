package ru.bravery_and_stupidity.trackerAdministrator.repository;

import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Order> getOrders() {
    List<Order> orders = em.createNamedQuery(Order.GET_ALL, Order.class).getResultList();
    return orders;
  }

  @Override
  public void saveOrder(Order order) {
    em.merge(order);
  }

  @Override
  public void deleteOrder(int id) {
    Order order = em.find(Order.class, id);
    em.remove(order);
  }

  @Override
  public Order getOrderById(int id) {
    return em.find(Order.class, id);
  }
}
