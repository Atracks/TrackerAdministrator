package ru.bravery_and_stupidity.trackerAdministrator.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@Table(name = "workers")
@JsonNaming
public class Worker {
  private int idWorker;
  private String name;
  private String surname;
  private String patronymic;
  private String position;
  private String email;
  private String login;
  private String pass;
  private byte isGod;

  //@OneToMany(mappedBy = "responsible")
  //private Set<Task> tasks = new HashSet<>();

  @Id
  @GeneratedValue
  @Column(name = "idWorker")
  public int getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(int idWorker) {
    this.idWorker = idWorker;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "surname")
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Basic
  @Column(name = "patronymic")
  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  @Basic
  @Column(name = "position")
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "login")
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Basic
  @Column(name = "pass")
  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  @Basic
  @Column(name = "isGod")
  public byte getIsGod() {
    return isGod;
  }

  public void setIsGod(byte isGod) {
    this.isGod = isGod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Worker workers = (Worker) o;

    if (idWorker != workers.idWorker) return false;
    if (isGod != workers.isGod) return false;
    if (name != null ? !name.equals(workers.name) : workers.name != null) return false;
    if (surname != null ? !surname.equals(workers.surname) : workers.surname != null) return false;
    if (patronymic != null ? !patronymic.equals(workers.patronymic) : workers.patronymic != null) return false;
    if (position != null ? !position.equals(workers.position) : workers.position != null) return false;
    if (email != null ? !email.equals(workers.email) : workers.email != null) return false;
    if (login != null ? !login.equals(workers.login) : workers.login != null) return false;
    if (pass != null ? !pass.equals(workers.pass) : workers.pass != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = idWorker;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
    result = 31 * result + (position != null ? position.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (pass != null ? pass.hashCode() : 0);
    result = 31 * result + (int) isGod;
    return result;
  }
}
