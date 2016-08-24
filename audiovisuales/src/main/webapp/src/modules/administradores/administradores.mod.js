(function (ng) {
    var mod = ng.module("administradoresModule", ["ngMessages"]);
    mod.constant("administradoresContext", "api/administradores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/administradores/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('administradoresList', {
                url: '/administradores',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.list.html'
                    }
                }
            }).state('cityCreate', {
                url: '/administradores/create',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    }
                }

            }).state('cityEdit', {
                url: '/administradores/:cityId',
                param: {
                    cityId: null
                },
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    }
                }
            });
        }]);
})(window.angular);