package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.services.TaskService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
final public class TaskController {
  private static final Logger logger = Logger.getLogger(TaskController.class);

  @Autowired
  private TaskService taskService;

  @RequestMapping(value = "/getTasksByProject/{projectId}", method = RequestMethod.GET)
  @ResponseBody
  public List<TaskDto> getTasksByProject(@PathVariable("projectId") int projectId) {
    return taskService.getTasksByProject(projectId);
  }

  @RequestMapping(value = "/getTasksByOrder/{orderId}", method = RequestMethod.GET)
  @ResponseBody
  public List<TaskDto> getTasksByOrder(@PathVariable("orderId") int orderId) {
    return taskService.getTasksByOrder(orderId);
  }

  @RequestMapping(value = "/saveTasks/", method = RequestMethod.PUT)
  @ResponseBody
  public void saveTasks(@RequestBody ArrayList<TaskDto> tasks) {
    taskService.saveTasks(TaskDto.mapToModels(tasks));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> errorHandler(Exception exc) {
    logger.error(exc.getMessage(), exc);
    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
