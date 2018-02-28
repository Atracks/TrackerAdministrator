package ru.bravery_and_stupidity.trackerAdministrator.dto;


import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.util.Assert;
import ru.bravery_and_stupidity.trackerAdministrator.model.Worker;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@JsonNaming
final public class WorkerDto {
  private int idWorker;
  private String name;
  private String surname;
  private String patronymic;
  private String position;
  private String email;
  private String login;
  private String pass;
  private byte isGod;
  private String fullName;

  public int getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(int idWorker) {
    this.idWorker = idWorker;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public byte getIsGod() {
    return isGod;
  }

  public void setIsGod(byte isGod) {
    this.isGod = isGod;
  }

  public String getFullName() {
    if((name != null)&&(surname != null)&&(patronymic != null)
        &&(!name.isEmpty())&&(!surname.isEmpty())&&(!patronymic.isEmpty())) {
      fullName = surname + " " + name.substring(0,1) + "." + surname.substring(0,1) + ".";
    } else {fullName = surname;}
    return fullName;
  }

  public static Worker mapToModel(WorkerDto workerDto) {
    Worker worker = new Worker();
    worker.setLogin(workerDto.getLogin());
    worker.setPass(workerDto.getPass());
    worker.setEmail(workerDto.getEmail());
    worker.setName(workerDto.getName());
    worker.setSurname(workerDto.getSurname());
    worker.setPatronymic(workerDto.getPatronymic());
    worker.setPosition(workerDto.getPosition());
    worker.setIdWorker(workerDto.getIdWorker());
    worker.setIsGod(workerDto.getIsGod());
    return worker;
  }

  public static Set<Worker> mapToModels(List<WorkerDto> workersDto) {
    Set<Worker> workers = new LinkedHashSet<>();
    for (WorkerDto workerDto: workersDto) {
      workers.add(mapToModel(workerDto));
    }
    return workers;
  }

  public static Set<Worker> mapToModels(Set<WorkerDto> workersDto) {
    Set<Worker> workers = new LinkedHashSet<>();
    for (WorkerDto workerDto: workersDto) {
      workers.add(mapToModel(workerDto));
    }
    return workers;
  }

  public static WorkerDto mapFromModel(Worker worker) {
    WorkerDto workerDto = new WorkerDto();
    workerDto.setLogin(worker.getLogin());
    workerDto.setPass(worker.getPass());
    workerDto.setEmail(worker.getEmail());
    workerDto.setName(worker.getName());
    workerDto.setSurname(worker.getSurname());
    workerDto.setPatronymic(worker.getPatronymic());
    workerDto.setPosition(worker.getPosition());
    workerDto.setIdWorker(worker.getIdWorker());
    workerDto.setIsGod(worker.getIsGod());
    return workerDto;
  }

  public static List<WorkerDto> mapFromModels(List<Worker> workers) {
    List<WorkerDto> workersDto = new ArrayList<>();
    for (Worker worker: workers) {
      workersDto.add(mapFromModel(worker));
    }
    return workersDto;
  }

  public static List<WorkerDto> mapFromModels(Set<Worker> workers) {
    List<WorkerDto> workersDto = new ArrayList<>();
    for (Worker worker: workers) {
      workersDto.add(mapFromModel(worker));
    }
    return workersDto;
  }
}
