var ATM = angular.module('DiscoveryATMApp', ['ngRoute']);

ATM.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'partials/login.html',
        controller: 'ATMController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/menu', {
        templateUrl: 'partials/menu.html',
        controller: 'ATMMenuController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/withdraw-cash', {
        templateUrl: 'partials/withdraw-cash.html',
        controller: 'ATMWithdrawalController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/withdraw/accounts', {
        templateUrl: 'partials/withdrawal-accounts.html',
        controller: 'ATMAccountController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/transactional-accounts', {
        templateUrl: 'partials/transactional-accounts.html',
        controller: 'ATMAccountController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/currency-accounts', {
        templateUrl: 'partials/currency-accounts.html',
        controller: 'ATMAccountController',
        controllerAs: "vm"
    });
});

ATM.config(function($routeProvider) {
    $routeProvider.when('/collect', {
        templateUrl: 'partials/collect-cash.html',
        controller: 'ATMCashController',
        controllerAs: "vm"
    });
});

ATM.controller('ATMAccountController', function(ATMService, $location, ClientModel) {
    var vm = this;

    vm.transactionalAccounts = ClientModel.getTransactionalAccounts();
    vm.currencyAccounts = ClientModel.getCurrencyAccounts();

    vm.goToWithdraw = function(account) {
        ClientModel.setSelectedAccount(account);
        $location.path("/withdraw-cash");
    }

    vm.goToMenu = function() {
        $location.path("/menu");
    };
});

ATM.controller('ATMController', function(ATMService, $location, ClientModel) {
    var vm = this;

    vm.cardProfile = {
        number: undefined,
        pin: undefined
    };

    ATMService.initATM().then(function(response) {
        ClientModel.setATM(response.data);
        vm.atm = ClientModel.getATM();
    }).catch(function(error) {
        alert('Something went wrong!');
    });

    vm.doLogin = function() {
        ATMService.doLogin(vm.cardProfile).then(function(response) {

            ClientModel.setClient(response)
            $location.path('/menu');

        }).catch(function(error) {
            alert('Something went wrong!');
        });
    };
});


ATM.controller('ATMWithdrawalController', function(ATMService, $location, ClientModel) {
    var vm = this;
    vm.selectedAccount = ClientModel.getSelectedAccount();
    vm.amount = undefined;

    vm.goToWithdrawalAccounts = function() {
        $location.path('/withdraw/accounts');
    };

    vm.withdraw = function() {

        var request = {
            atmId: ClientModel.getATM().atmID,
            clientId: ClientModel.getClient().clientId,
            accountNumber: vm.selectedAccount.accountNumber,
            amount: vm.amount
        };

        ATMService.withdrawCash(request).then(function(response) {
             ClientModel.setFunds(response);
             $location.path('/collect');
        }).catch(function(error) {
            alert('Something went wrong!');
        });

    };

});

ATM.controller('ATMCashController', function(ATMService, $location, ClientModel) {
    var vm = this;
    vm.funds = ClientModel.getFunds();
    vm.done = function() {
       $location.path('/').replace();
    };
});

ATM.controller('ATMMenuController', function(ATMService, $location, ClientModel) {
    var vm = this;

    vm.client = ClientModel.getClient();

    vm.gotToWithdrawal = function() {

        ATMService.getTransactionalAccounts(vm.client.clientId).then(function(response) {

            ClientModel.setTransactionalAccounts(response);
            $location.path('/withdraw/accounts');


        }).catch(function(error) {
            alert('Something went wrong!');
        });

    };

    vm.goToTransactionalAccounts = function() {
        ATMService.getTransactionalAccounts(vm.client.clientId).then(function(response) {

            ClientModel.setTransactionalAccounts(response);
            $location.path('/transactional-accounts');


        }).catch(function(error) {
            alert('Something went wrong!');
        });

    };

    vm.goToCurrencyAccounts = function() {

        ATMService.getCurrencyAccounts(vm.client.clientId).then(function(response) {

            ClientModel.setCurrencyAccounts(response);
            $location.path('/currency-accounts').replace();


        }).catch(function(error) {
            alert('Something went wrong!');
        });

    };

    vm.logOut = function() {
        $location.path('/').replace();
    };

});

ATM.factory('ClientModel', function() {
    var ATM;
    var client;
    var transactionalAccounts;
    var currencyAccounts;
    var selectedAccount;
    var funds;

    function setClient(data) {
        client = data;
    }

    function getClient() {
        return client;
    }

    function setFunds(data) {
        funds = data;
    }

    function getFunds() {
        return funds;
    }

    function setATM(data) {
        ATM = data;
    }

    function getATM() {
        return ATM;
    }

    function getTransactionalAccounts() {
        return transactionalAccounts;
    }

    function setTransactionalAccounts(data) {
        transactionalAccounts = data;
    }

    function getCurrencyAccounts() {
        return currencyAccounts;
    }

    function setCurrencyAccounts(data) {
        currencyAccounts = data;
    }

    function getSelectedAccount() {
        return selectedAccount;
    }

    function setSelectedAccount(data) {
        selectedAccount = data;
    }

    return {
        setClient: setClient,
        getClient: getClient,
        getTransactionalAccounts: getTransactionalAccounts,
        setTransactionalAccounts: setTransactionalAccounts,
        getCurrencyAccounts: getTransactionalAccounts,
        setCurrencyAccounts: setTransactionalAccounts,
        setSelectedAccount: setSelectedAccount,
        getSelectedAccount: getSelectedAccount,
        setATM: setATM,
        getATM: getATM,
        getFunds: getFunds,
        setFunds: setFunds
    };
});


ATM.factory("ATMService", function($http) {
    function initATM() {
        var request = {
            method: "GET",
            url: "ATM/allocate",
            headers: {}
        };
        return $http(request)
            .then(function(response) {
                return response;
            }).catch(function(response) {
                return $q.reject(response);
            });
    }

    function doLogin(cardProfile) {
        var request = {
            method: "POST",
            url: "ATM/login",
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                timeStamp: new Date(),
                cardProfile: cardProfile
            }
        };
        return $http(request)
            .then(function(response) {
                return response.data;
            }).catch(function(response) {
                return $q.reject(response);
            });
    }

    function withdrawCash(withdrawCashRequest) {
        withdrawCashRequest.timeStamp = new Date();
        var request = {
            method: "POST",
            url: "ATM/account/withdraw",
            headers: {
                'Content-Type': 'application/json'
            },
            data: withdrawCashRequest
        };
        return $http(request)
            .then(function(response) {
                console.log(response);
                return response.data;
            }).catch(function(response) {
                return $q.reject(response);
            });
    }


    function getTransactionalAccounts(clientId) {
        var request = {
            method: "GET",
            url: "ATM/account/transactional/" + clientId,
            headers: {}
        };
        return $http(request)
            .then(function(response) {
                return response.data;
            }).catch(function(response) {
                return $q.reject(response);
            });
    }

    function getCurrencyAccounts(clientId) {
        var request = {
            method: "GET",
            url: "ATM/account/currency/" + clientId,
            headers: {}
        };
        return $http(request)
            .then(function(response) {
                return response.data;
            }).catch(function(response) {
                return $q.reject(response);
            });
    }

    return {
        initATM: initATM,
        doLogin: doLogin,
        getTransactionalAccounts: getTransactionalAccounts,
        getCurrencyAccounts: getCurrencyAccounts,
        withdrawCash: withdrawCash
    };
});