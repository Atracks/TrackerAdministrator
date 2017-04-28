package ru.bravery_and_stupidity.trackerAdministrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository repository;

  @Override
  @Transactional
  public List<Order> getOrders() {
    return repository.getOrders();
  }

  @Override
  @Transactional
  public void createOrder(Order order) {
    Assert.notNull(order,"Order argument can not be null");
    Assert.hasLength(order.getDescription(),"Order description can not be empty");
    repository.saveOrder(order);
  }

  @Override
  public void deleteOrder(int id) {
    ValidationUtils.checkId(id);
    repository.deleteOrder(id);
  }

  @Override
  public void updateOrder(Order order) {
    Assert.notNull(order, "Order argument can not be null");
    ValidationUtils.checkId(order.getIdOrder());
    Assert.hasLength(order.getDescription(),"Project description can not be empty");
    repository.saveOrder(order);
  }

  @Override
  public Order getOrder(int id) {
    ValidationUtils.checkId(id);
    return repository.getOrderById(id);
  }
}
