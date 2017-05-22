package ru.bravery_and_stupidity.trackerAdministrator.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
  @Id
  @GeneratedValue
  private int idProject;

  @Basic
  @Column(name = "description")
  private String description;

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
