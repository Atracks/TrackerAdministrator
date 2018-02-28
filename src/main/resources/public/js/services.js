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
    getTasksByProject: function (projectId) {return $http.get('tasks/getTasksByProject/' + projectId);},
    getTasksByOrder: function (orderId) {return $http.get('tasks/getTasksByOrder/' + orderId);},
    getTasksByWorker: function (workerId) {return $http.get('tasks/getTasksByWorker/' + workerId)},
    saveTasks: function (tasks) {return $http.put('tasks/saveTasks/',tasks);},
    deleteCoexecutorFromTasks: function (workerId) {return $http.delete('tasks/deleteCoexecutorFromTasks/' + workerId)}
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

AppServices.service('workerService', ['$http','$q', function($http) {
  var workerIdForDelete;
  var workerIdForEdit;
  var isEditMode = false;
  return {
    getWorkers: function () {return $http.get('workers/workersList.json');},
    addWorker: function (worker) {
      return $http.post('workers/addWorker', worker);
    },
    deleteWorker: function (id) {return $http.delete('workers/deleteWorker/' + id)},
    saveWorker: function (worker) {return $http.put('workers/updateWorker', worker)},
    setWorkerIdForDelete: function (id) {workerIdForDelete = id},
    getWorkerIdForDelete: function () { return workerIdForDelete},
    setWorkerForEdit: function (id) {workerIdForEdit = id},
    getWorkerForEdit: function () { return $http.get('workers/getWorker/' + workerIdForEdit);},
    setEditMode: function (mode) {isEditMode = mode},
    isEditMode: function () {return isEditMode},
    deleteSlavesByWorker: function (id) {return $http.delete('workers/deleteSlavesByWorker/' + id)}
  }
}]);
