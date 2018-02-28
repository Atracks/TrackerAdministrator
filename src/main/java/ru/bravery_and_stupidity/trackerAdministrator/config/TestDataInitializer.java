package ru.bravery_and_stupidity.trackerAdministrator.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bravery_and_stupidity.trackerAdministrator.model.Order;
import ru.bravery_and_stupidity.trackerAdministrator.model.Project;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.util.HashSet;

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

    Worker workerJohn = createWorker("Lenon", "John", "Legend of rock");
    Worker workerBoss = createWorker("Boss", "Big", "Legend of world");
    workerBoss.setIsGod((byte)1);
    workerBoss.setLogin("Boss");

    HashSet<Worker> slaves = new HashSet<Worker>();
    slaves.add(workerJohn);

    order = createOrder("order 3");
    project = createProject("project 3");
    Worker workerSid = createWorker("Vishes", "Seed", "Legend of punk rock");
    slaves.add(workerSid);

    workerBoss.setSlaves(slaves);

    Task task = createTaskWithoutOuterEntity("Тестовое поручение1", Date.valueOf("2015-12-31"), Date.valueOf("2017-02-12"), 5);
    task.setProject(project);
    task.setOrder(order);
    task.setResponsible(workerJohn);
    task.setAuthor(workerBoss);
    session.persist(task);

    task = createTaskWithoutOuterEntity("Тестовое поручение2", Date.valueOf("2013-11-22"), Date.valueOf("2016-08-05"), 10);
    task.setProject(project);
    task.setOrder(order);
    task.setResponsible(workerSid);
    task.addCoexecutor(workerJohn);
    task.setAuthor(workerBoss);
    session.persist(task);

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

  private Order createOrder(int id, String description) {
    Order order = new Order();
    order.setIdOrder(id);
    order.setDescription(description);
    return order;
  }

  private Order createOrder(String description) {
    return createOrder(0, description);
  }

  private Worker createWorker(String surname, String name, String position){
    Worker worker = new Worker();
    worker.setName(name);
    worker.setSurname(surname);
    worker.setPosition(position);
    worker.setLogin("login");
    worker.setPass("pass");
    worker.setEmail("email@mnb.com");
    worker.setPatronymic("Zigizmundovich");
    return worker;
  }
}
