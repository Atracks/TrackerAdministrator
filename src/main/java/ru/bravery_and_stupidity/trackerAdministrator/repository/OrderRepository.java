package ru.bravery_and_stupidity.trackerAdministrator.repository;

import ru.bravery_and_stupidity.trackerAdministrator.model.Order;

import java.util.List;

public interface OrderRepository {
  List<Order> getOrders();
  void saveOrder(Order order);
  void deleteOrder(int id);
  Order getOrderById(int id);
}
