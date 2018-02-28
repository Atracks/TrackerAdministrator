package ru.bravery_and_stupidity.trackerAdministrator.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.internal.Nullable;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

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
  @Nullable
  private ProjectDto project;
  @Nullable
  private OrderDto order;
  private WorkerDto responsible;
  private WorkerDto author;
  private List<WorkerDto> coexecutors = new ArrayList<>();
  private List<WorkerDto> availableResponsible = new ArrayList<>();

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

  public WorkerDto getResponsible() {
    return responsible;
  }

  public void setResponsible(WorkerDto responsible) {
    this.responsible = responsible;
  }

  public WorkerDto getAuthor() {
    return author;
  }

  public void setAuthor(WorkerDto author) {
    this.author = author;
  }

  public List<WorkerDto> getCoexecutors() {
    return coexecutors;
  }

  public void setCoexecutors(List<WorkerDto> coexecutors) {
    this.coexecutors = coexecutors;
  }

  public List<WorkerDto> getAvailableResponsible() {
    return availableResponsible;
  }

  public void setAvailableResponsible(List<WorkerDto> availableResponsible) {
    this.availableResponsible = availableResponsible;
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
    if(task.getProject() != null) {
      taskDto.setProject(ProjectDto.mapFromModel(task.getProject()));
    }
    if(task.getOrder() != null) {
      taskDto.setOrder(OrderDto.mapFromModel(task.getOrder()));
    }
    taskDto.setResponsible(WorkerDto.mapFromModel(task.getResponsible()));
    taskDto.setAuthor(WorkerDto.mapFromModel(task.getAuthor()));
    if(task.getCoexecutors() != null) {
      taskDto.setCoexecutors(WorkerDto.mapFromModels(task.getCoexecutors()));
    }
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
    if(taskDto.getProject() != null) {
      task.setProject(ProjectDto.mapToModel(taskDto.getProject()));
    }
    if(taskDto.getOrder() != null) {
      task.setOrder(OrderDto.mapToModel(taskDto.getOrder()));
    }
    task.setResponsible(WorkerDto.mapToModel(taskDto.getResponsible()));
    task.setAuthor(WorkerDto.mapToModel(taskDto.getAuthor()));
    if(taskDto.getCoexecutors() != null) {
      task.setCoexecutors(WorkerDto.mapToModels(taskDto.getCoexecutors()));
    }
    return task;
  }

  public static List<TaskDto> mapFromModels(Set<Task> tasks) {
    List<TaskDto> tasksDto = new ArrayList<>();
    for (Task task: tasks) {
      tasksDto.add(mapFromModel(task));
    }
    return tasksDto;
  }

  public static List<TaskDto> mapFromModels(List<Task> tasks) {
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
