(function(ng){
    var mod = ng.module("ModuloLocaciones", ["ngMessages"]);
    mod.constant("ContextoDeLocaciones","api/locaciones");
    mode.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var direccionInicial ='src/modules/locaciones/';
            $urlRouterProvide.otherwise("/ListaLocaciones");
            
            $stateProvider.state('ListaLocaciones',{
                url:'locaciones',
                views: {
                    'mainView':{
                        templateUrl: direccionInicial + 'locaciones.listaLocaciones.html'//Falta crear locaciones.listaLocaciones.html
                        
                    } 
                }
                
            }).state('CrearLocacion',{
                url: 'crear',
                views: {
                    'mainView':{
                        templateUrl: direccionInicial + 'locaciones.crearLocacion.html'//falta crear locaciones.crearLocacion.html
                    }
                }
            }).state('EditarLocacion',{
                url: 'locaciones/:locacionId',
                param:{
                    locacionId:null
                },
                views:{
                'mainView':{
                    templateUrl: direccionInicial + 'locaciones.crearLocacion.html'//Falta crear locaciones.crearLocacion.html
                }   
                }
            });
            
    }
        
    ]);
});
(window.angular);