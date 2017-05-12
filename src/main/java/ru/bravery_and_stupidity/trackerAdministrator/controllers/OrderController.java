package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.trackerAdministrator.dto.OrderDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
final public class OrderController {
  private static final Logger logger = Logger.getLogger(ProjectController.class);

  @Autowired
  private OrderService orderService;


  @RequestMapping(value = "/ordersList.json", method = RequestMethod.GET)
  @ResponseBody
  public List<OrderDto> getOrders() {
    return OrderDto.mapFromModels(orderService.getOrders()) ;
  }

  @RequestMapping(value = "/addOrder/{orderName}", method = RequestMethod.POST)
  @ResponseBody
  public void addOrder(@PathVariable("orderName") String orderName) {
    Order order = new Order(orderName);
    orderService.createOrder(order);
  }

  @RequestMapping(value = "/deleteOrder/{orderId}", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteOrder(@PathVariable int orderId) {
    orderService.deleteOrder(orderId);
  }

  @RequestMapping(value = "/updateOrder", method = RequestMethod.PUT)
  @ResponseBody
  public void updateOrder(@RequestBody OrderDto orderDto) {
    orderService.updateOrder(OrderDto.mapToModel(orderDto));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> errorHandler(Exception exc) {
    logger.error(exc.getMessage(), exc);
    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
