'use strict';

var tasksByOrderController = function($scope, taskService, orderService) {
  $scope.error = false;
  $scope.errorMessage = '';
  getTasksList();
  getOrdersList();

  $scope.selectOrderClicked = function (order, task) {
    task.order = order;
  }

  $scope.saveTasks = function() {
    taskService.saveTasks($scope.tasks).success(function () {
      window.location.replace('#/orders');
    }).error(function () {setError("Ошибка сохранения списка поручений")})
  }

  function getTasksList() {
    var id = orderService.getOrderIdForDelete();
    taskService.getTasksByOrder(id).success(function (tasks) {
      $scope.tasks = tasks;
    }).error(function () {setError("Ошибка получения списка поручений")});
  }

  function getOrdersList() {
    orderService.getOrders().success(function (orders) {
      $scope.orders = orders;
    }).error(function () {setError("Ошибка получения списка заказов");});
  }

  function setError (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  }
};


