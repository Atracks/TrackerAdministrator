package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.trackerAdministrator.dto.WorkerDto;
import ru.bravery_and_stupidity.trackerAdministrator.services.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/workers")
final public class WorkerController {
  private static final Logger logger = Logger.getLogger(WorkerController.class);

  @Autowired
  private WorkerService workerService;


  @RequestMapping(value = "/workersList.json", method = RequestMethod.GET)
  @ResponseBody
  public List<WorkerDto> getWorkers() {
    return WorkerDto.mapFromModels(workerService.getWorkers()) ;
  }

  @RequestMapping(value = "/addWorker", method = RequestMethod.POST)
  @ResponseBody
  public void addWorker(@RequestBody WorkerDto worker) {
    workerService.createWorker(WorkerDto.mapToModel(worker));
  }

  @RequestMapping(value = "/deleteWorker/{workerId}", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteWorker(@PathVariable int workerId) {
    workerService.deleteWorker(workerId);
  }

  @RequestMapping(value = "/updateWorker", method = RequestMethod.PUT)
  @ResponseBody
  public void updateWorker(@RequestBody WorkerDto worker) {
    workerService.updateWorker(WorkerDto.mapToModel(worker));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> errorHandler(Exception exc) {
    logger.error(exc.getMessage(), exc);
    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
