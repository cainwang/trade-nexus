'use strict';

/* Controllers */

var controllers = angular.module('optionNexusControllers', []);

controllers.controller('ChromeCtrl', function($scope) {
});

function earningCtrl($scope, Earnings, Profiles, Langs, Dates) {
    $scope.goToWeekEarnings = function(date) {
        $scope.currentDate = date;
        $scope.earningDays = Earnings.findWeekEarnings(date);
    };

    $scope.goToThisWeekEarnings = function() {
        var today = new Date();
        this.goToWeekEarnings(today);
    };

    $scope.goToPreviousWeekEarnings = function() {
        var currentDate = $scope.currentDate;
        if (currentDate) {
            this.goToWeekEarnings(Dates.toPreviousWeek(currentDate));
        }
    };

    $scope.goToNextWeekEarnings = function() {
        var currentDate = $scope.currentDate;
        if (currentDate) {
            this.goToWeekEarnings(Dates.toNextWeek(currentDate));
        }
    };

    $scope.goToThisWeekEarnings();
    Profiles.findFuture(function(indexFutureProfile) {
        $scope.indexFutureProfile = indexFutureProfile;
    });

    $scope.calculateExpression = function(event) {
        if (event.which == 13) {
            var invalid = false;

            try {
                var result = eval($scope.calculatorExpression);
                if (isNaN(result)) {
                    invalid = true;
                } else {
                    $scope.calculatorExpression = result.toFixed(3);
                    event.target.classList.remove('invalid');
                }
            } catch (e) {
                invalid = true;
            }

            if (invalid) {
                event.target.classList.add('invalid');
            }
        }
    };
}
controllers.controller('EarningCtrl', [ '$scope', 'Earnings', 'Profiles', 'Langs', 'Dates', earningCtrl ]);
