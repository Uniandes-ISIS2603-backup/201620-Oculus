(function (ng) 
{
    var mod = ng.module("administradoresModule", ["ngMessages"]);
    mod.constant("administradoresContext", "api/administradores");
    mod.constant("equiposContext", "api/equipos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradores/';
            $urlRouterProvider.otherwise("/administradoresList");
     
            $stateProvider.state('administradores',
            {
                url:'/administradores',
                abstract:true,
                views:
                        {
                            'mainView':
                            {
                                controller: 'administradoresCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'administradores.html'
                            }
                        }
            })
                    .state('administradoresList', 
            {
                url: '/list',
                parent:'administradores',
                views: {
                    'administradorView': {
                        
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.list.html'
                    }
                }
            }).state('administradoresCreate', {
                url: '/administradores/create',
                views: {
                    'administradorView': {   
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    }
                }

            }).state('administradorEdit', {
                url: '/edit/{administradorId:int}',
                param: {
                    administradorId: null
                },
                parent:'administradores',
                views: {
                    'administradorView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'administradores.create.html'
                    },
                    'childsView':
                            {
                                templateUrl:basePath+'instance.html'
                            }
                }
            }).state('administradorEquiposList',
            {
                url:"/equipos/list",
                parent: 'administradorEdit',
                views:
                        {
                            'EquipoInstanceView':
                            {
                                controller: 'administradoresEquipoCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'administradoresEquipos/'+'equipos.list.html'
                            }
                        }
            }).state('administradorEquipoCreate',
            {
                url:"/equipos/create",
                parent: 'administradorEdit',
                views:
                        {
                            'EquipoInstanceView':
                            {
                                controller: 'administradoresEquipoCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'administradoresEquipos/'+'equipos.create.html'
                            }
                        }
            }).state('administradorEquipoDevolver',
            {
                url:"/equipos/devolver",
                parent: 'administradorEdit',
                views:
                        {
                            'EquipoInstanceView':
                            {
                                controller: 'administradoresEquipoCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'administradoresEquipos/'+'equipos.devolver.html'
                            }
                        }
            });
        }]);
})(window.angular);
//administradorEquipoCreate
//
