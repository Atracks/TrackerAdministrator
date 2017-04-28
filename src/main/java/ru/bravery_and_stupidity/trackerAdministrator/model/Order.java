package ru.bravery_and_stupidity.trackerAdministrator.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(
        name = Order.GET_ALL,
        query = "from Order"
    ),})
public class Order {
  public static final String GET_ALL = "orders.getAll";

  @Id
  @GeneratedValue
  @Column(name = "idOrder")
  private int idOrder;

  @Basic
  //FIXME
  //@Column(name = "description", columnDefinition = "text")
  @Column(name = "description")
  private String description;

  //@OneToMany(cascade = CascadeType.ALL)
  //@JoinTable(name = "relationshipstaskorder", joinColumns = @JoinColumn(name = "idOrder"),
  //    inverseJoinColumns = @JoinColumn(name = "idTask"))
  //private Set<Task> tasks = new LinkedHashSet<>();

  public int getIdOrder() {
    return idOrder;
  }

  public void setIdOrder(int idOrder) {
    this.idOrder = idOrder;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /*public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  };*/

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Order orders = (Order) o;

    if (idOrder != orders.idOrder) return false;
    if (description != null ? !description.equals(orders.description) : orders.description != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = idOrder;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
