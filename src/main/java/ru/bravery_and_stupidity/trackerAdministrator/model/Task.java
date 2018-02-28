package ru.bravery_and_stupidity.trackerAdministrator.model;

import com.sun.istack.internal.Nullable;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue
  @Column(name = "idTasks")
  private int idTask;

  @Basic
  @Column(name = "description")
  private String description;

  @Basic
  @Column(name = "creationDate")
  private Date creationDate;

  @Basic
  @Column(name = "deadlineDate")
  private Date deadlineDate;

  @Basic
  @Column(name = "status")
  private int status;

  @Basic
  @Column(name = "isOverdue")
  private byte isOverdue;

  @Basic
  @Column(name = "importance")
  private int importance;

  @Basic
  @Column(name = "parentTaskId")
  private int parentTaskId;

  @Nullable
  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinTable(name = "relationshipstaskproject", joinColumns = @JoinColumn(name = "idTask"),
    inverseJoinColumns = @JoinColumn(name = "idProject"))
  private Project project;

  @Nullable
  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinTable(name = "relationshipstaskorder", joinColumns = @JoinColumn(name = "idTask"),
    inverseJoinColumns = @JoinColumn(name = "idOrder"))
  private Order order;

  @ManyToOne()
  @JoinColumn(name = "responsible")
  private Worker responsible;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "author")
  private Worker author;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name ="relationshipstaskaccomplices", joinColumns = @JoinColumn(name = "idTask"),
    inverseJoinColumns = @JoinColumn(name = "idWorker"))
  private Set<Worker> coexecutors = new LinkedHashSet<>();

  public int getIdTask() {
    return idTask;
  }

  public void setIdTask(int idTask) {
    this.idTask = idTask;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getDeadlineDate() {
    return deadlineDate;
  }

  public void setDeadlineDate(Date deadlineDate) {
    this.deadlineDate = deadlineDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public byte getIsOverdue() {
    return isOverdue;
  }

  public void setIsOverdue(byte isOverdue) {
    this.isOverdue = isOverdue;
  }

  public int getImportance() {
    return importance;
  }

  public void setImportance(int importance) {
    this.importance = importance;
  }

  public int getParentTaskId() {
    return parentTaskId;
  }

  public void setParentTaskId(int parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Worker getResponsible() {
    return responsible;
  }

  public Worker getAuthor() {
    return author;
  }

  public void setAuthor(Worker author) {
    this.author = author;
  }

  public Set<Worker> getCoexecutors() {
    return coexecutors;
  }

  public void setCoexecutors(Set<Worker> coexecutors) {
    this.coexecutors = coexecutors;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setResponsible(Worker responsible) {
    this.responsible = responsible;
  }

  public void addCoexecutor(Worker worker) {
    Assert.notNull(worker," worker is null");
    coexecutors.add(worker);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Task tasks = (Task) o;

    if (idTask != tasks.idTask) return false;
    if (status != tasks.status) return false;
    if (isOverdue != tasks.isOverdue) return false;
    if (importance != tasks.importance) return false;
    if (parentTaskId != tasks.parentTaskId) return false;
    if (description != null ? !description.equals(tasks.description) : tasks.description != null) return false;
    if (creationDate != null ? !creationDate.equals(tasks.creationDate) : tasks.creationDate != null) return false;
    if (deadlineDate != null ? !deadlineDate.equals(tasks.deadlineDate) : tasks.deadlineDate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = idTask;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
    result = 31 * result + (deadlineDate != null ? deadlineDate.hashCode() : 0);
    result = 31 * result + status;
    result = 31 * result + (int) isOverdue;
    result = 31 * result + importance;
    result = 31 * result + parentTaskId;
    return result;
  }
}
