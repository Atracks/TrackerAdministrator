'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

App.config(['$routeProvider', function ($routeProvider) {
  $routeProvider.when('/projects', {
    templateUrl: 'projects/layout.html',
    controller: projectController
  });

  $routeProvider.when('/tasks', {
    templateUrl: 'tasks/layout.html',
    controller: taskController
  });
    
  $routeProvider.otherwise({redirectTo: '/projects'});
}]);


