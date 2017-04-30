package ru.bravery_and_stupidity.trackerAdministrator.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class TestDataInitializer {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  public void init() throws Exception {

    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    Project project = createProject("project 1");
    session.persist(project);

    project = createProject("project 2");
    session.persist(project);

    Order order = createOrder("order 1");
    session.persist(order);

    order = createOrder("order 2");
    session.persist(order);

    order = createOrder("order 3");

    project = createProject("project 3");
    Task task = createTaskWithoutOuterEntity("Тестовое поручение1", Date.valueOf("2015-12-31"), Date.valueOf("2017-02-12"), 5);
    session.persist(task);
    project.addTask(task);
    order.addTask(task);

    task = createTaskWithoutOuterEntity("Тестовое поручение2", Date.valueOf("2013-11-22"), Date.valueOf("2016-08-05"), 10);
    session.persist(task);
    project.addTask(task);
    order.addTask(task);

    session.persist(project);
    session.persist(order);
    transaction.commit();
  }

  private Project createProject(int id, String description) {
    Project project = new Project();
    project.setIdProject(id);
    project.setDescription(description);
    return project;
  }

  private Project createProject(String description) {
    return createProject(0, description);
  }

  private Task createTaskWithoutOuterEntity(String description, Date creationDate, Date deadlineDate, int importance) {
    Task task = new Task();
    task.setDescription(description);
    task.setIdTask(0);
    task.setCreationDate(creationDate);
    task.setDeadlineDate(deadlineDate);
    task.setImportance(importance);
    task.setIsOverdue((byte) 1);
    task.setParentTaskId(22);
    return task;
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
}
