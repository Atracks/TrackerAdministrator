package ru.bravery_and_stupidity.trackerAdministrator.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.sql.Date;
import java.util.*;

@JsonNaming
final public class TaskDto {
  private int id;
  private String description;
  private Date creationDate;
  private Date deadlineDate;
  private int status;
  private byte isOverdue;
  private int importance;
  private int parentTaskId;
  private ProjectDto project;
  private OrderDto order;
  private Worker responsible;


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

  public Date getCreationDate(){
    return creationDate;
  }

  public void setCreationDate(Date creationDate){
    this.creationDate = creationDate;
  }

  public Date getDeadlineDate(){
    return deadlineDate;
  }

  public void setDeadlineDate(Date deadlineDate){
    this.deadlineDate = deadlineDate;
  }

  public int getStatus(){
    return status;
  }

  public void setStatus(int status){
    this.status = status;
  }

  public byte getIsOverdue(){
    return isOverdue;
  }

  public void setIsOverdue(byte isOverdue){
    this.isOverdue = isOverdue;
  }

  public int getImportance(){
    return importance;
  }

  public void setImportance(int importance){
    this.importance = importance;
  }

  public int getParentTaskId(){
    return parentTaskId;
  }

  public void setParentTaskId(int parentTaskId){
    this.parentTaskId = parentTaskId;
  }

  public ProjectDto getProject(){
    return project;
  }

  public void setProject(ProjectDto project){
    this.project = project;
  }

  public OrderDto getOrder() {
    return order;
  }

  public void setOrder(OrderDto order) {
    this.order = order;
  }

  public Worker getResponsible() {
    return responsible;
  }

  public void setResponsible(Worker responsible) {
    this.responsible = responsible;
  }

  public static TaskDto mapFromModel(Task task) {
    TaskDto taskDto = new TaskDto();
    taskDto.setId(task.getIdTask());
    taskDto.setDescription(task.getDescription());
    taskDto.setCreationDate(task.getCreationDate());
    taskDto.setDeadlineDate(task.getDeadlineDate());
    taskDto.setStatus(task.getStatus());
    taskDto.setImportance(task.getImportance());
    taskDto.setIsOverdue(task.getIsOverdue());
    taskDto.setParentTaskId(task.getParentTaskId());
    taskDto.setProject(ProjectDto.mapFromModel(task.getProject()));
    taskDto.setOrder(OrderDto.mapFromModel(task.getOrder()));
    taskDto.setResponsible(task.getResponsible());
    return taskDto;
  }

  public static Task mapToModel(TaskDto taskDto) {
    Task task = new Task();
    task.setIdTask(taskDto.getId());
    task.setDescription(taskDto.getDescription());
    task.setCreationDate(taskDto.getCreationDate());
    task.setDeadlineDate(taskDto.getDeadlineDate());
    task.setStatus(taskDto.getStatus());
    task.setImportance(taskDto.getImportance());
    task.setIsOverdue(taskDto.getIsOverdue());
    task.setParentTaskId(taskDto.getParentTaskId());
    task.setProject(ProjectDto.mapToModel(taskDto.getProject()));
    task.setOrder(OrderDto.mapToModel(taskDto.getOrder()));
    task.setResponsible(taskDto.getResponsible());
    return task;
  }

  public static List<TaskDto> mapFromModels(Set<Task> tasks) {
    List<TaskDto> tasksDto = new ArrayList<>();
    for (Task task: tasks) {
      tasksDto.add(mapFromModel(task));
    }
    return tasksDto;
  }

  public static Set<Task> mapToModels(List<TaskDto> tasksDto) {
    Set<Task> tasks = new LinkedHashSet<>();
    for (TaskDto task: tasksDto) {
      tasks.add(mapToModel(task));
    }
    return tasks;
  }
}
