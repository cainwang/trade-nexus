'use strict';

/* Controllers */

var controllers = angular.module('optionNexusControllers', []);

controllers.controller('ChromeCtrl', function($scope) {
});

function earningCtrl($scope, Earnings, Langs, Dates) {
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
}
controllers.controller('EarningCtrl', [ '$scope', 'Earnings', 'Langs', 'Dates', earningCtrl ]);
