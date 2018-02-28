'use strict';

var tasksByWorkerController = function($scope, workerService, taskService) {
  $scope.error = false;
  $scope.errorMessage = '';
  getTasksList();
  //getWorkersList();

  $scope.selectWorkerClicked = function (worker, task) {
    task.responsible = worker;
  }

  $scope.saveTasks = function() {
    taskService.saveTasks($scope.tasks).success(function () {
      window.location.replace('#/workers');
    }).error(function () {setError("Ошибка сохранения списка поручений")})
  }

  function getTasksList() {
    var id = workerService.getWorkerIdForDelete();
    taskService.getTasksByWorker(id).success(function (tasks) {
      $scope.tasks = tasks;
    }).error(function () {setError("Ошибка получения списка поручений")});
  }

  function getWorkersList() {
    workerService.getWorkers().success(function (workers) {
      $scope.workers = workers;
    }).error(function () {setError("Ошибка получения списка работников");});
  }

  function setError (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  }
};
