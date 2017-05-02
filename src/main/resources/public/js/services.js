var AppServices = angular.module('AngularSpringApp.services', []);

AppServices.value('version', '0.1');

AppServices.service('projectService', ['$http','$q', function($http) {
  var projectIdForDelete;
  return {
    getProjects: function () {return $http.get('projects/projectsList.json');},
    addProject: function (newProject) {return $http.post('projects/addProject/' + newProject)},
    deleteProject: function (id) {return $http.delete('projects/deleteProject/' + id)},
    saveProject: function (project) {return $http.put('projects/updateProject', project)},
    setProjectIdForDelete: function (id) {projectIdForDelete = id},
    getProjectIdForDelete: function () { return projectIdForDelete}
  }
}]);

AppServices.service('taskService', ['$http','$q', function($http) {
  return {
    getTasksByProject: function (projectId) { return $http.get('tasks/getTasksByProject/' + projectId);},
    saveTasks: function (tasks) { return $http.put('tasks/saveTasks/',tasks);}
  }
}]);

AppServices.service('orderService', ['$http','$q', function($http) {
  var orderIdForDelete;
  return {
    getOrders: function () {return $http.get('orders/ordersList.json');},
    addOrder: function (newOrder) {return $http.post('orders/addOrder/' + newOrder)},
    deleteOrder: function (id) {return $http.delete('orders/deleteOrder/' + id)},
    saveOrder: function (order) {return $http.put('orders/updateOrder', order)},
    setOrderIdForDelete: function (id) {orderIdForDelete = id},
    getOrderIdForDelete: function () { return orderIdForDelete}
  }
}]);
