'use strict';

/* Services */

var services = angular.module('optionNexusServices', [ 'ngResource' ]);

services.factory('Langs', [ function() {
    return {
        isNotEmpty: function(obj) {
            return obj && (angular.isArray(obj) && obj.length > 0);
        },

        first: function(list) {
            return list ? list[0] : null;
        }
    };
} ]);

services.factory('Dates', [ function() {
    return {
        TIME_DAY: 1000 * 60 * 60 * 24,

        /**
         * Finds the first day of the week that the specified date is in.
         */
        findFirstDayOfWeek: function(date) {
            return new Date(date.getTime() - date.getDay() * this.TIME_DAY);
        },

        /**
         * Finds the week dates of the week that the specified date is in.
         */
        findWeekDays: function(date) {
            var dates = [];
            var firstDay = this.findFirstDayOfWeek(date);
            var time = firstDay.getTime();

            for (var i = 0; i < 5; i ++) {
                time += this.TIME_DAY;
                dates.push(new Date(time));
            }

            return dates;
        },

        /**
         * Formats the date in the pattern of MM/dd/yyyy
         */
        formatDate: function(date) {
            return (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
        },

        /**
         * Go to the day of the previous week.
         */
        toPreviousWeek: function(date) {
            return new Date(date.getTime() - 7 * this.TIME_DAY);
        },

        /**
         * Go to the day of the previous week.
         */
        toNextWeek: function(date) {
            return new Date(date.getTime() + 7 * this.TIME_DAY);
        },

        /**
         * Checks if the formatted date is today.
         */
        isToday: function(date) {
            return this.formatDate(new Date()) == this.formatDate(date);
        }
    };
} ]);

services.factory('Earnings', [ 'Rests', 'Profiles', 'Dates', function(Rests, Profiles, Dates) {
    return {
        /**
         * Finds the earning reports of the week in which the specified date is
         * in.
         */
        findWeekEarnings: function(date) {
            var me = this;
            var weekDays = Dates.findWeekDays(date);

            var earningDays = [];
            for (var i = 0; i < weekDays.length; i ++) {
                var weekDay = weekDays[i];

                var earningDay = {
                    date: weekDay,
                    isToday: Dates.isToday(weekDay),
                    loading: true
                };
                earningDays.push(earningDay);
                this.findDayEarnings(earningDay);
            }
            return earningDays;
        },

        /**
         * Finds the earning report of the day.
         */
        findDayEarnings: function(earningDay) {
            var me = this;

            Rests.get('api/earning/calendar/:dateTime', {
                dateTime: earningDay.date.getTime()
            }, function(earningEntries) {
                earningDay.loading = false;
                earningDay.earningEntries = earningEntries;

                me.findStockProfiles(earningEntries);
            });
        },

        /**
         * Finds the Profiles of the stocks in the earning list.
         */
        findStockProfiles: function(earningEntries) {
            for (var i = 0; i < earningEntries.length; i ++) {
                var entry = earningEntries[i];
                Profiles.findNasdaqProfile(entry);
                Profiles.findNasdaqOptionProfile(entry);
                Profiles.findZacksProfile(entry);
                Profiles.findMarketWatchProfile(entry);
                Profiles.findEstimizeProfile(entry);
                Profiles.findShortInterest(entry);
            }
        }
    }
} ]);

services.factory('Rests', [ '$resource', '$rootScope', function($resource, $rootScope) {
    return {
        /**
         * Calls a REST API to get an object.
         */
        get: function(url, params, success, error) {
            var me = this;
            me.increaseRequestCount($rootScope);
            $resource(url).get(params, function(response) {
                me.processResponse(response, success);
                me.decreaseRequestCount($rootScope);
            }, this.error);
        },

        /**
         * Increases the global request count.
         */
        increaseRequestCount: function($scope) {
            var count = $scope.requestCount;
            if (isNaN(count)) {
                count = 0;
            }
            count ++;
            $scope.requestCount = count;
        },

        /**
         * Decreases the global request count.
         */
        decreaseRequestCount: function($scope) {
            var count = $scope.requestCount;
            if (isNaN(count)) {
                count = 0;
            }
            count --;
            $scope.requestCount = count;
        },

        /**
         * The global success handler.
         */
        processResponse: function(response, success) {
            if (response.successful && angular.isFunction(success)) {
                success(response.result);
            }
        },

        /**
         * The global error handler.
         */
        error: function(response) {
            console.log(response);
        }
    };
} ]);

services.factory('Profiles', [ 'Rests', function(Rests) {
    return {
        /**
         * Finds the Nasdaq profile for the specified stock.
         */
        findNasdaqProfile: function(stockEntry) {
            Rests.get('api/profile/nasdaq/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.nasdaqProfile = profile;
            });
        },

        /**
         * Finds the Nasdaq options profile for the specified stock.
         */
        findNasdaqOptionProfile: function(stockEntry) {
            Rests.get('api/profile/nasdaq/option/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.nasdaqOptionProfile = profile;
            });
        },

        /**
         * Finds Zacks profile for the specified stock.
         */
        findZacksProfile: function(stockEntry) {
            Rests.get('api/profile/zacks/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.zacksProfile = profile;
            });
        },

        /**
         * Finds MarketWatch profile for the stock.
         */
        findMarketWatchProfile: function(stockEntry) {
            Rests.get('api/profile/marketwatch/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.marketWatchProfile = profile;
            });
        },

        /**
         * Finds estimize profile for the stock.
         */
        findEstimizeProfile: function(stockEntry) {
            Rests.get('api/profile/estimize/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.estimizeProfile = profile;
            });
        },

        /**
         * Finds the US stock future index.
         */
        findFuture: function(callback) {
            return Rests.get('api/profile/us-future', {}, function(profile) {
                callback(profile);
            });
        },

        /**
         * Finds the stock short interest.
         */
        findShortInterest: function(stockEntry) {
            Rests.get('api/profile/nasdaq/short-interest/:symbol', {
                symbol: stockEntry.symbol
            }, function(profile) {
                stockEntry.shortInterestProfile = profile;
            });
        }
    };
} ]);
