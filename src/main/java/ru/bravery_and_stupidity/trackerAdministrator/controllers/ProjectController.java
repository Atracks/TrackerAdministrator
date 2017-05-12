package ru.bravery_and_stupidity.trackerAdministrator.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.trackerAdministrator.dto.ProjectDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.services.ProjectService;
import java.util.List;

@RestController
@RequestMapping("/projects")
final public class ProjectController {
  private static final Logger logger = Logger.getLogger(ProjectController.class);

  @Autowired
  private ProjectService projectService;


  @RequestMapping(value = "/projectsList.json", method = RequestMethod.GET)
  @ResponseBody
  public List<ProjectDto> getProjects() {
    return ProjectDto.mapFromModels(projectService.getProjects()) ;
  }

  @RequestMapping(value = "addProject/{projectName}", method = RequestMethod.POST)
  @ResponseBody
  public void addProject(@PathVariable("projectName") String projectName) {
    Project project = new Project(projectName);
    projectService.createProject(project);
  }

  @RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteProject(@PathVariable int projectId) {
    projectService.deleteProject(projectId);
  }

  @RequestMapping(value = "/updateProject", method = RequestMethod.PUT)
  @ResponseBody
  public void updateProject(@RequestBody ProjectDto projectDto) {
    projectService.updateProject(ProjectDto.mapToModel(projectDto));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> errorHandler(Exception exc) {
    logger.error(exc.getMessage(), exc);
    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
