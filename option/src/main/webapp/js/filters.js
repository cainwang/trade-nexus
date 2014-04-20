'use strict';

/* Filters */

angular.module('optionNexusFilters', []).filter('reportTime', function() {
    return function(input) {
        if (input == 'Before Market Open') {
            return 'BMO';
        } else if (input == 'After Market Close') {
            return 'AMO';
        }

        return input;
    };
}).filter('zacksRankColor', function() {
    return function(input) {
        return {
            'Strong Buy': 'rating-buy',
            'Buy': 'rating-overweight',
            'Hold': 'rating-hold',
            'Sell': 'rating-underweight',
            'Strong Sell': 'rating-sell'
        }[input];
    };
}).filter('zacksESPColor', function() {
    return function(input) {
        var inputNum = parseFloat(input);
        if (inputNum > 0) {
            return 'rating-buy';
        } else if (inputNum < 0) {
            return 'rating-sell';
        }
    };
}).filter('percentage', function() {
    return function(input) {
        if (!isNaN(input)) {
            return (input * 100).toFixed(2) + '%';
        }

        return '';
    };
}).filter('weekDay', function() {
    return function(input) {
        if (input) {
            if (input.toDateString) {
                return input.toDateString();
            } else if (!isNaN(input)) {
                return new Date(input).toDateString();
            }
        }

        return '';
    };
});
