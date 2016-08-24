(function (ng) {
    var mod = ng.module("profesorModule", ["ngMessages"]);
    mod.constant("profesorContext", "api/profesor");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/profesor/';
            $urlRouterProvider.otherwise("/profesorList");
     
            $stateProvider.state('profesorList', {
                url: '/profesor',
                views: {
                    'mainView': {
                        controller: 'profesorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesor.list.html'
                    }
                }
            }).state('cityCreate', {
                url: '/profesor/create',
                views: {
                    'mainView': {
                        controller: 'profesorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesor.create.html'
                    }
                }

            }).state('cityEdit', {
                url: '/profesor/:cityId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'profesorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesor.create.html'
                    }
                }
            });
        }]);
})(window.angular);