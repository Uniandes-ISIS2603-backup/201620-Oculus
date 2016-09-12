(function (ng) 
{
    var mod = ng.module("administradoresModule", ["ngMessages"]);
    mod.constant("equiposContext", "api/administrador");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/administradores/';
            $urlRouterProvider.otherwise("/administradorListEquipos");
     
            $stateProvider
                .state('administradorListEquipos', 
            {
                url: 'administrador/equipos',
                views: {
                    'mainView': {
                        
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equiposAdministrador.list.html'
                    }
                }
            })  .state('administradorEquipoCreate', 
            {
                url: 'administrador/equipo/create',
                views: {
                    'mainView': {
                        
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'equipoAdministrador.create.html'
                         //Se mejorará con un Select con ng-repeat... 
                        //https://docs.angularjs.org/api/ng/directive/select
                    }
                }
            });
        }]);
})(window.angular);
//