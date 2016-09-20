(function (ng) 
{
    var mod = ng.module('profesoresModule', ["ngMessages"]);
    mod.constant("profesoresContext", "api/profesores");
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/profesores/';
            $urlRouterProvider.otherwise("/profesoresList");
            
            $stateProvider.state('profesores',
            {
                url:'/profesores',
                abstract:true,
                views:
                        {
                            'mainView':
                            {
                                controller: 'profesoresCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'profesores.html'
                            }
                        }
            }).state('profesoresList', 
            {
                url: '/list',
                parent:'profesores',
                views: {
                    'profesorView': {
                        
                        controller: 'profesoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesores.list.html'
                    }
                }
            }).state('profesorCreate', {
                url: '/profesores/create',
                views: {
                    'profesorView': {
                        controller: 'profesoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesores.create.html'
                    }
                }

            }).state('profesorEdit', {
                url: '/edit/{profesorId:int}',
                param: {
                    profesorId: null
                },
                parent:'profesores',
                views: {
                    'profesorView': {
                        controller: 'profesoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'profesores.create.html'
                    },
                    'childsView':
                            {
                                templateUrl:basePath+'instance.html'
                            }
                }
            }).state('profesorReservasList',
            {
                url:"/reservas/list",
                parent: 'profesorEdit',
                views:
                        {
                            'ReservaInstanceView':
                            {
                                controller: 'profesoresReservaCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'profesoresReservas/'+'reservas.list.html'
                            }
                        }
            }).state('profesorReservaCreate',
            {
                url:"/reservas/create",
                parent: 'profesorEdit',
                views:
                        {
                            'ReservaInstanceView':
                            {
                                controller: 'profesoresReservaCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'profesoresReservas/'+'reservas.create.html'
                            }
                        }
            });
    }]);
})(window.angular);
