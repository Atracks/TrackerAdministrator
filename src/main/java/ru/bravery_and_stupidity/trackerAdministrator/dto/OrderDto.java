package ru.bravery_and_stupidity.trackerAdministrator.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import java.util.ArrayList;
import java.util.List;

@JsonNaming
final public class OrderDto {
  private int id;
  private String description;

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public static OrderDto mapFromModel(Order order) {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(order.getIdOrder());
    orderDto.setDescription(order.getDescription());
    return orderDto;
  }

  public static Order mapToModel(OrderDto orderDto) {
    Order order = new Order();
    order.setIdOrder(orderDto.getId());
    order.setDescription(orderDto.getDescription());
    return order;
  }

  public static List<OrderDto> mapFromModels(List<Order> orders) {
    List<OrderDto> ordersDto = new ArrayList<>();
    for (Order order: orders) {
      ordersDto.add(mapFromModel(order));
    }
    return ordersDto;
  }
}
