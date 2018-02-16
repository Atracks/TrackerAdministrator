'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

App.config(['$routeProvider', function ($routeProvider) {
  $routeProvider.when('/projects', {
    templateUrl: 'projects/layout.html',
    controller: projectController
  });

  $routeProvider.when('/orders', {
    templateUrl: 'orders/layout.html',
    controller: orderController
  });

  $routeProvider.when('/prjTasks', {
    templateUrl: 'tasks/prjTaskLayout.html',
    controller: tasksByProjectController
  });

  $routeProvider.when('/ordTasks', {
    templateUrl: 'tasks/ordTaskLayout.html',
    controller: tasksByOrderController
  });

  $routeProvider.when('/workers', {
    templateUrl: 'workers/layout.html',
    controller: workerController
  });

  $routeProvider.when('/new-worker', {
    templateUrl: 'workers/newWorkerLayout.html',
    controller: newWorkerController
  });

  //$routeProvider.otherwise({redirectTo: '/projects'});
}]);


