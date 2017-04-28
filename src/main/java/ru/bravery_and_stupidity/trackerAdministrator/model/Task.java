package ru.bravery_and_stupidity.trackerAdministrator.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue
  @Column(name = "idTasks")
  private int idTask;

  @Basic
  //FIXME
  //@Column(name = "description", columnDefinition = "mediumtext")
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
  //FIXME
  //@Column(name = "isOverdue", columnDefinition = "bit")
  @Column(name = "isOverdue")
  private byte isOverdue;

  @Basic
  @Column(name = "importance")
  private int importance;

  @Basic
  @Column(name = "parentTaskId")
  private int parentTaskId;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinTable(name = "relationshipstaskproject", joinColumns = @JoinColumn(name = "idTask"),
      inverseJoinColumns = @JoinColumn(name = "idProject"))
  private Project project;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinTable(name = "relationshipstaskorder", joinColumns = @JoinColumn(name = "idTask"),
      inverseJoinColumns = @JoinColumn(name = "idOrder"))
  private Order order;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "responsible")
  private Worker responsible;

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

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setResponsible(Worker responsible) {
    this.responsible = responsible;
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
