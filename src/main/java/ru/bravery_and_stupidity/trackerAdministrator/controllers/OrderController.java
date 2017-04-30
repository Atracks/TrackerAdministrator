package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bravery_and_stupidity.trackerAdministrator.dto.OrderWithTasksDto;
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
  public List<OrderWithTasksDto> getOrders() {
    return OrderWithTasksDto.mapFromModels(orderService.getOrders()) ;
  }
}
