var ngArkalys = angular.module('ngArkalys', ['ngSanitize', 'nouislider', 'ui.bootstrap', 'pascalprecht.translate', 'textAngular', 'chieffancypants.loadingBar', 'angularjs-gravatardirective', 'ngTable', 'feeds']);
var headerLoaded = false;

ngArkalys.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: '/assets/i18n/locale-',
        suffix: '.json'
    });

    //$translateProvider.preferredLanguage('fr');
    $translateProvider.fallbackLanguage('fr');
    $translateProvider.determinePreferredLanguage();
});

ngArkalys.directive("compileHtml", function ($parse, $sce, $compile) {
    return {
        restrict: "A",
        link: function (scope, element, attributes) {

            var expression = $sce.parseAsHtml(attributes.compileHtml);

            var getResult = function () {
                return expression(scope);
            };

            scope.$watch(getResult, function (newValue) {
                var linker = $compile(newValue);
                element.append(linker(scope));
            });
        }
    }
});




function navbarController($scope, $http, $location, $translate) {
    $scope.isFound = false;

    $scope.isActive = function (viewLocation) {
        if (window.location.pathname.indexOf(viewLocation) != -1) {
            $scope.isFound = true;
            return true;
        }

        return false;
    };
}



function request(url, functionData) {
    const request = new XMLHttpRequest();

    request.onreadystatechange = function (event) {
        if (request.readyState === XMLHttpRequest.DONE)
            functionData(request.responseText);
    };

    request.open('GET', url, true);
    request.send(null);

}

function selectServer(server) {
    post("..\\selectServer/", "server=" + server, function (secondResponseData) {
        if(secondResponseData === "true") {
           location.reload();
        }
    });
}

function logout() {
    request("..\\connect?logout=true", function (data) {
        if (data === "success") {
            setTimeout(function () {
                window.location.href = '..\\home\\';
            }, 2000);
        }
    });
}

function reconnect() {
    request("..\\connect?logout=true", function (data) {
        if (data === "success") {
            setTimeout(function () {
                window.location.href = '..\\login\\';
            }, 2000);
        }
    });
}


function post(url, formData, action) {
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200)
            action(request.responseText);
    };

    request.open('POST', url, true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(formData);
}
