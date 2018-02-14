'use strict';

var workerController = function($scope, $http, workerService, taskService) {
  $scope.worker = {};
  $scope.editMode = false;
  getWorkersList();

  $scope.addNewWorker = function(newWorker) {
    resetError();
    workerService.addWorker(newWorker).success(function () {
      getWorkersList();
      //FIXME очистка отображенных данных текущего работника
      //$scope.project.description = '';
    }).error(function () {setError("Ошибка при добавлении работника");});
  };

  $scope.updateWorker = function(worker) {
    resetError();
    workerService.saveWorker(worker).success(function () {
      getWorkersList();
      $scope.worker.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при обновлении данных работника");});
  };

  $scope.editWorker = function(worker) {
    resetError();
    $scope.worker = worker;
    $scope.editMode = true;
  };

  $scope.checkWorkerForTasks = function (id) {
    resetError();
    workerService.setWorkerIdForDelete(id);
    taskService.getTasksByWorker(id).success(function (tasks) {
      if(tasks.length != 0) {window.location.replace('#/workerTasks');}
      else {deleteWorker(id);}
    }).error(function () {setError("Ошибка при удалении работника")});
  };

  function deleteWorker(id) {
    resetError();
    workerService.deleteWorker(id).success(function () {
      getWorkersList();
      //FIXME очистка отображенных данных текущего работника
      //$scope.worker.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при удалении работника");});
  };

  function getWorkersList() {
    workerService.getWorkers().success(function (workers) {
      $scope.workers = workers;
    }).error(function () {setError("Ошибка получения списка работников");});
  }

  function resetError() {
    $scope.error = false;
    $scope.errorMessage = '';
  };

  function setError (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  }
};
