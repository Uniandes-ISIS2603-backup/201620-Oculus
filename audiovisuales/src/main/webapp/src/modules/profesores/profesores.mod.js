    /* 
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
(function(ng){
    
    /**
     * Se genera el modulo respectivo mod que maneje la funcionalidad de los profesores
     * @type angular.module.angular-1_3_6_L1749.moduleInstance
     */
    var mod = angular.module('ProfesoresModule', ['ui.router']);
    
    /**
     * Constantes
     */
    mod.constant("profesoresContext", "api/profesores");

    /**
     * Generamos la configuracion del modulo creado según la arquitectura REST
     */
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
                
                //Direccion base
                var base = 'src/modules/profesores/';
                $urlRouterProvider.otherwise("/profesores");
                $stateProvider
                
                        //Se genera el estado principal
                        .state('profesores', {
                            url: "/profesores",
                            views: {
                                //Caso Base: Vista no definida
                                "": {templateUrl: base+"profesores.html"},
                                //Vista de prueba
                                "vista1": {template: "Esta vista permite dejar un mensaje de prueba."},
                                //Vista actual con controladores respectivos
                                "vista2": {
                                    templateUrl: base+"profesores.html",
                                    controller: 'profesoresCtrl',
                                    controllerAs: 'ctrl',
                                }
                            }
                        })
                        
                        //Se generan los estados para CRUD
                        
                        .state('profesores.get', {
                            url: "/profesores/:id",
                            param: { profeId: null},
                            views: {
                                //Caso Base: Vista no definida
                                "": {templateUrl: base+"profesores.html"},
                                //Vista de prueba
                                "vista1": {template: "Esta vista permite dejar un mensaje de prueba."},
                                //Vista actual con controladores respectivos
                                "vista2": {
                                    templateUrl: base+"profesores.html",
                                    controller: 'profesoresCtrl',
                                    controllerAs: 'ctrl',
                                }
                            }
                        })
                        .state('profesores.update', {
                            url: "/profesores/update/:id",
                            param: { profeId: null},
                            views: {
                                //Caso Base: Vista no definida
                                "": {templateUrl: base+"profesores.update.html"},
                                //Vista de prueba
                                "vista1": {template: "Esta vista permite dejar un mensaje de prueba."},
                                //Vista actual con controladores respectivos
                                "vista2": {
                                    templateUrl: base+"profesores.update.html",
                                    controller: 'profesoresCtrl',
                                    controllerAs: 'ctrl',
                                }
                            }
                            
                        })
                        .state('profesores.delete', {
                            url: "/profesores/delete/:id",
                            param: { profeId: null},
                            views: {
                                //Caso Base: Vista no definida
                                "": {templateUrl: base+"profesores.delete.html"},
                                //Vista de prueba
                                "vista1": {template: "Esta vista permite dejar un mensaje de prueba."},
                                //Vista actual con controladores respectivos
                                "vista2": {
                                    templateUrl: base+"profesores.delete.html",
                                    controller: 'profesoresCtrl',
                                    controllerAs: 'ctrl',
                                }
                            }
                        })
                        .state('profesores.create', {
                            url: "/profesores/create",
                            views: {
                                //Caso Base: Vista no definida
                                "": {templateUrl: base+"profesores.create.html"},
                                //Vista de prueba
                                "vista1": {template: "Esta vista permite dejar un mensaje de prueba."},
                                //Vista actual con controladores respectivos
                                "vista2": {
                                    templateUrl: base+"profesores.create.html",
                                    controller: 'profesoresCtrl',
                                    controllerAs: 'ctrl',
                                }
                            }
                        })
            }]);
    });
    
(window.angular);