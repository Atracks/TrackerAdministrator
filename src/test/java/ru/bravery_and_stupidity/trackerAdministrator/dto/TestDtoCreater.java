package ru.bravery_and_stupidity.trackerAdministrator.dto;


import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.sql.Date;

final public class TestDtoCreater {

  public static Task createTask(int id, String description) {
    Task task = new Task();
    task.setDescription(description);
    task.setIdTask(id);
    task.setCreationDate(new Date(123));
    task.setDeadlineDate(new Date(234));
    task.setImportance(10);
    task.setIsOverdue((byte)1);
    task.setOrder(createOrder(21, "Tested order 45"));
    task.setProject(createProject(44, "Tested project 77"));
    task.setResponsible(createWorker(67, "John", "Iovovich"));
    task.setParentTaskId(22);
    task.setStatus(2);
    return task;
  }

  public static Task createTask(String description) {
    return createTask(0,description);
  }

  public static Project createProject(int id, String description) {
    Project project = new Project();
    project.setIdProject(id);
    project.setDescription(description);
    return project;
  }

  public static Project createProject(String description) {
    return createProject(0,description);
  }

  public static Order createOrder(int id, String description) {
    Order order = new Order();
    order.setIdOrder(id);
    order.setDescription(description);
    return order;
  }

  public static Order createOrder(String description) {
    return createOrder(0, description);
  }

  public static Worker createWorker(int id, String surname, String name) {
    Worker worker = new Worker();
    worker.setIdWorker(id);
    worker.setEmail("User@mail.ru");
    worker.setIsGod((byte)1);
    worker.setLogin("Login");
    worker.setPass("password");
    worker.setPosition("Big Boss");
    worker.setName(surname);
    worker.setSurname(name);
    worker.setPatronymic("OfFather");
    return worker;
  }

  public static Worker createWorker(String surname, String name) {
    return createWorker(0,surname,name);
  }

  public static TaskDto createTaskDto(int id, String description) {
    TaskDto task = new TaskDto();
    task.setDescription(description);
    task.setId(id);
    task.setCreationDate(new Date(123));
    task.setDeadlineDate(new Date(234));
    task.setImportance(10);
    task.setIsOverdue((byte)1);
    task.setOrder(createOrderDto(16,"order 678"));
    task.setProject(createProjectDto(22, "Tested projectDto 47"));
    task.setResponsible(createWorkerDto(23, "Bill", "Footer", "Abramovich"));
    task.setParentTaskId(22);
    task.setStatus(2);
    return task;
  }

  public static TaskDto createTaskDto(String description) {
    return createTaskDto(0, description);
  }

  public static ProjectDto createProjectDto(int id, String description) {
    ProjectDto project = new ProjectDto();
    project.setId(id);
    project.setDescription(description);
    return project;
  }

  public static ProjectDto createProjectDto(String description) {
    return createProjectDto(0,description);
  }

  public static OrderDto createOrderDto(int id, String description) {
    OrderDto order = new OrderDto();
    order.setId(id);
    order.setDescription(description);
    return order;
  }

  public static OrderDto createOrderDto(String description) {
    return createOrderDto(0,description);
  }

  public static WorkerDto createWorkerDto(int id, String surname, String name, String position){
    WorkerDto worker = new WorkerDto();
    worker.setIdWorker(id);
    worker.setName(name);
    worker.setSurname(surname);
    worker.setPosition(position);
    worker.setLogin("login23");
    worker.setPass("pass22");
    worker.setEmail("email333@mnb.com");
    worker.setPatronymic("Zigizmundovich3345");
    return worker;
  }

  public static WorkerDto createWorkerDto(String surname, String name, String position){
    return createWorkerDto(0, surname, name, position);
  }

  public static Worker createWorker(int id, String surname, String name, String position){
    Worker worker = new Worker();
    worker.setIdWorker(id);
    worker.setName(name);
    worker.setSurname(surname);
    worker.setPosition(position);
    worker.setLogin("login23");
    worker.setPass("pass22");
    worker.setEmail("email333@mnb.com");
    worker.setPatronymic("Zigizmundovich3345");
    return worker;
  }

  public static Worker createWorker(String surname, String name, String position){
    return createWorker(0, surname, name, position);
  }
}
