'use strict';

var taskController = function($scope, projectService, taskService) {
  $scope.error = false;
  $scope.errorMessage = '';
  getTasksList();
  getProjectsList();

  $scope.selectProjectClicked = function (project, task) {
    task.project = project;
  }

  $scope.saveTasks = function() {
    taskService.saveTasks($scope.tasks).success(function () {
      window.location.replace('#/projects');
    }).error(function () {setError("Ошибка сохранения списка поручений")})
  }

  function getTasksList() {
    var id = projectService.getProjectIdForDelete();
    taskService.getTasksByProject(id).success(function (tasks) {
      $scope.tasks = tasks;
    }).error(function () {setError("Ошибка получения списка поручений")});
  }

  function getProjectsList() {
    projectService.getProjects().success(function (projects) {
      $scope.projects = projects;
    }).error(function () {setError("Ошибка получения списка проектов");});
  }

  function setError (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  }
};

