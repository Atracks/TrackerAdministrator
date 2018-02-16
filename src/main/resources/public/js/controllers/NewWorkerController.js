'use strict';

var newWorkerController = function($scope, $http, workerService) {
  $scope.worker = {};
  resetError();

  $scope.onCreateWorker = function () {
    resetError();
    if(checkName($scope.worker.surname)
      &&checkName($scope.worker.name)
      &&checkName($scope.worker.patronymic)
      &&checkPosition()
      &&checkLogin()
      &&checkEmail()
      &&checkPassword()
      &&checkPasswordsMatch()) {
        $scope.worker.pass = $scope.password;
        workerService.addWorker($scope.worker).success(function () {
          window.location.replace('#/workers');
        }).error(function () {
          var errorMessage = "Ошибка при добавлении работника в БД";
          setError(errorMessage);
          return false;
        })
    }
 }

  function setError(errorMessage) {
    $scope.error = true;
    $scope.errorMessage = errorMessage;
  }

  function resetError() {
    $scope.error = false;
    $scope.errorMessage = '';
  }

  function checkLogin () {
    var pattern = /^[\w]+$/;
    if(($scope.worker.login === undefined) || (!pattern.test($scope.worker.login))) {
      var errorMessage = "Логин должен состоять только из английских букв и цифр";
      setError(errorMessage);
      return false;
    }
    resetError();
    return true;
  }

  function checkName(name) {
    var pattern = /^([\w]+|[а-яА-я]+)$/;
    if((name === undefined)||(name.trim() === "")|| (!pattern.test(name))) {
      var errorMessage = "В имени, фамилии и отчестве допускаются только буквы";
      setError(errorMessage);
      return false;
    } else {
      resetError();
      return true;
    }
  }
  
  function checkPosition() {
    var pattern = /^[а-яА-я ]+$/;
    if(($scope.worker.position === undefined)||($scope.worker.position.trim() === "")|| (!pattern.test($scope.worker.position))) {
      var errorMessage = "Поле должность может содержать только русские буквы и пробелы";
      setError(errorMessage);
      return false;
    } else {
      resetError();
      return true;
    }
  }
  
  function checkEmail() {
    var pattern = /^[0-9a-z]+@[0-9a-z]\.[a-z]$/;
    if(($scope.worker.email === undefined)||($scope.worker.email.trim() === "")||(pattern.test($scope.worker.email))) {
      var errorMessage = "Некорректный адрес электронной почты";
      setError(errorMessage);
      return false;
    } else {
      resetError();
      return true;
    }
  }

  function checkPassword() {
    if(checkPasswordSecurity() && checkPasswordForbiddenSymbols()) {
      return true;
    }
    return false;
  }

  function checkPasswordSecurity() {
    if(($scope.password === undefined) || ($scope.password.length < 6)) {
      var errorMessage = "Длина пароля должна быть больше 6 знаков";
      setError(errorMessage);
      return false;
    } else {
      resetError();
      return true;
    }
  }

  function checkPasswordForbiddenSymbols() {
    var forbiddenPattern = /[/"'\\;:]+/;
    if((forbiddenPattern.test($scope.password))) {
      var errorMessage = "Пароль не должен содержать символы / \" ' \\ ; :";
      setError(errorMessage);
      return false;
    } else {
      resetError();
      return true;
    }
  }

  function checkPasswordsMatch() {
    if(($scope.password === undefined) ||($scope.password != $scope.confirmPassword)) {
      setError("Пароли не совпадают");
      return false;
    }
    resetError();
    return true;
  }
}
