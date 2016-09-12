(function () {
    var mod = angular.module('profesoresModule', ['ui.router']);
    mod.constant("profesoresContext", "api/profesores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/profesores/';
            $urlRouterProvider.otherwise("/profesoresList");
            $stateProvider.state('profesoresList',{
                url: "/profesores",
                views: {
                'mainView': {
                    controller: 'profesoresCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'profesores.list.html'
                }
            }
            }).state('profesorCreate', {
                url: '/profesores/create',
                views: {
                    'mainView': {
                        controller: 'profesoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesores.create.html'
                    }
                }

            }).state('profesorEdit', {
                url: '/profesores/edit/{profesorId:int}',
                param: {
                    profesorId: null
                },
                views: {
                    'mainView': {
                        controller: 'profesoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesores.create.html'
                    }
                }
            }).state('profesorGet',{
                url: "/profesores/:profesorId",
                param: {
                    cityId: null
                },
                views: {
                'mainView': {
                    controller: 'profesoresCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'profesores.getProfesor.html'
                }
            }
            });
    }]);
})(window.angular);
