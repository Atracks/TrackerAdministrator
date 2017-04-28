package ru.bravery_and_stupidity.trackerAdministrator.services;

final public class ValidationUtils {
  public static void checkId(int id) {
    if(id <= 0) {
      throw new IllegalArgumentException("Id must be positive");
    }
  }
}
