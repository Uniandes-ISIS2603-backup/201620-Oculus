(function(ng){
    var mod = ng.module("administradoresModule", ['ui.router']);
    mod.constant("Contexto de administradores","api/administradores");
    mode.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var direccionInicial='src/modules/administradores';
            $urlRouterProvide.otherwise("/listaAdministradores");
            
  $stateProvider.state('administradoresGet',{
                url:'/administradores',
                views: {
                    'mainView':{
                        controller: 'administradoresCtrl',
                        controllerAd: 'ctrl',
                        templateUrl: direccionInicial + 'administradores.get.html'//Falta crear 
                    }
                   }
          }).state('administradorGet',{
                url: "/administradores/:administradorId",
                param: {administradorId: null},
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: direccionInicial + 'administradores.getAdministrador.html'
                        }
                       }
          }).state('administradorCreate', {
                url: '/administradores/create',
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: direccionInicial + 'administradores.create.html'
                        }
                       }
          }).state('administradorUpdate', {
                url: '/administradores/update/:administradordId',
                param: {administradorId: null},
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: direccionInicial + 'administradores.update.html' 
                    }
                   }
          }).state('administradorDelete', {
                url: '/administradores/delete/:administradorId',
                param: {administradorId: null},
                views: {
                    'mainView': {
                        controller: 'administradoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: direccionInicial + 'administradores.delete.html' 
                    }
                   }
            });  
        }
    ]);
});
(window.angular);