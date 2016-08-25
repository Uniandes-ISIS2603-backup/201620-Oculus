var mod = ng.module("equiposModule", ["ngMessages"]);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        $stateProvider.state('estado',{url:"/estado", templateUrl: basePath+"equipos.html"})
}]);