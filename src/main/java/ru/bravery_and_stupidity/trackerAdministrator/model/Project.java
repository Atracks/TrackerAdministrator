package ru.bravery_and_stupidity.trackerAdministrator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@NamedQueries({
    @NamedQuery(
        name = Project.GET_ALL,
        query = "from Project"
    ),})
public class Project {
  public static final String GET_ALL = "projects.getAll";
  @Id
  @GeneratedValue
  private int idProject;

  @Basic
  //FIXME
  //@Column(name = "description", columnDefinition = "text")
  @Column(name = "description")
  private String description;

  @OneToMany//(/*cascade = CascadeType.ALL*/)
  @JoinTable(name = "relationshipstaskproject", joinColumns = @JoinColumn(name = "idProject"),
             inverseJoinColumns = @JoinColumn(name = "idTask"))
  private Set<Task> tasks = new LinkedHashSet<>();

  public Project() {
  }

  public Project(int idProject, String description) {
    this.idProject = idProject;
    this.description = description;
  }

  public Project(String description) {
    this.description = description;
  }

  public int getIdProject() {
    return idProject;
  }

  public void setIdProject(int idProject) {
    this.idProject = idProject;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (idProject != project.idProject) return false;
    if (description != null ? !description.equals(project.description) : project.description != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = idProject;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
