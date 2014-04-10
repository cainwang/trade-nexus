'use strict';

/* Directives */

angular.module('optionNexusDirectives', []).directive('nxEnter', function() {
    return function(scope, element, attrs) {
        element.bind('keypress', function(event) {
            if (event.which === 13) {
                scope.$apply(function() {
                    scope[attrs.nxEnter].call(scope, event);
                });

                event.preventDefault();
            }
        });
    };
}).directive('nxHoverFocus', function() {
    return function(scope, element) {
        element.bind('mouseover', function(event) {
            var target = event.target;
            if (target) {
                if (target.focus) {
                    target.focus();
                }
                if (target.select) {
                    target.select();
                }
            }
        });
    }
});