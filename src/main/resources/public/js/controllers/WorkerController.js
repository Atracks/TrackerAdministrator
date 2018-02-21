'use strict';

var workerController = function($scope, $http, workerService, taskService) {
  $scope.worker = {};
  getWorkersList();

  $scope.redirectToNewWorker = function () {
    resetError();
    workerService.setEditMode(false);
    window.location.replace('#/new-worker');
  }

  $scope.updateWorker = function(worker) {
    resetError();
    workerService.saveWorker(worker).success(function () {
      getWorkersList();
      $scope.worker.description = '';
    }).error(function () {setError("Ошибка при обновлении данных работника");});
  };

  $scope.redirectToEditWorker = function(id) {
    resetError();
    workerService.setWorkerForEdit(id);
    workerService.setEditMode(true);
    window.location.replace('#/new-worker');
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
