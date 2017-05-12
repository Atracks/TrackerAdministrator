'use strict';

var orderController = function($scope, $http, orderService, taskService) {
  $scope.order = {};
  $scope.editMode = false;
  getOrdersList();

  $scope.addNewOrder = function(newOrder) {
    resetError();
    orderService.addOrder(newOrder).success(function () {
      getOrdersList();
      $scope.order.description = '';
    }).error(function () {setError("Ошибка при добавлении заказа");});
  };

  $scope.updateOrder = function(order) {
    resetError();
    orderService.saveOrder(order).success(function () {
      getOrdersList();
      $scope.order.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при сохранении заказа");});
  };

  $scope.editOrder = function(order) {
    resetError();
    $scope.order = order;
    $scope.editMode = true;
  };

  $scope.checkOrderForTasks = function (id) {
    resetError();
    orderService.setOrderIdForDelete(id);
    taskService.getTasksByOrder(id).success(function (tasks) {
      if(tasks.length != 0) {window.location.replace('#/ordTasks');}
      else {deleteOrder(id);}
    }).error(function () {setError("Ошибка при удалении заказа")});
  };

  function deleteOrder(id) {
    resetError();
    orderService.deleteOrder(id).success(function () {
      getOrdersList();
      $scope.order.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при удалении заказа");});
  };

  function getOrdersList() {
    orderService.getOrders().success(function (orders) {
      $scope.orders = orders;
    }).error(function () {setError("Ошибка получения списка заказов");});
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


