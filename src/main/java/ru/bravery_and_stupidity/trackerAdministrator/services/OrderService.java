package ru.bravery_and_stupidity.trackerAdministrator.services;

import ru.bravery_and_stupidity.trackerAdministrator.model.Order;

import java.util.List;

public interface OrderService {
  List<Order> getOrders();
  void createOrder(Order order);
  void deleteOrder(int id);
  void updateOrder(Order order);
  Order getOrder(int id);
}
