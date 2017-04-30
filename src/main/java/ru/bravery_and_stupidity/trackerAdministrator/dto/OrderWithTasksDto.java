package ru.bravery_and_stupidity.trackerAdministrator.dto;

import ru.bravery_and_stupidity.trackerAdministrator.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderWithTasksDto {
  private int id;
  private String description;
  private List<TaskDto> tasks = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<TaskDto> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskDto> tasks) {
    this.tasks = tasks;
  }

  public void addTask(TaskDto task) {this.tasks.add(task);}

  public static OrderWithTasksDto mapFromModel(Order order) {
    OrderWithTasksDto orderWithTasksDto = new OrderWithTasksDto();
    orderWithTasksDto.setId(order.getIdOrder());
    orderWithTasksDto.setDescription(order.getDescription());
    orderWithTasksDto.setTasks(TaskDto.mapFromModels(order.getTasks()));
    return orderWithTasksDto;
  }

  public static Order mapToModel(OrderWithTasksDto orderWithTasksDto) {
    Order order = new Order();
    order.setIdOrder(orderWithTasksDto.getId());
    order.setDescription(orderWithTasksDto.getDescription());
    order.setTasks(TaskDto.mapToModels(orderWithTasksDto.getTasks()));
    return order;
  }

  public static List<OrderWithTasksDto> mapFromModels(List<Order> orders) {
    List<OrderWithTasksDto> ordersWithTasksDto = new ArrayList<>();
    for (Order order: orders) {
      ordersWithTasksDto.add(mapFromModel(order));
    }
    return ordersWithTasksDto;
  }
}
