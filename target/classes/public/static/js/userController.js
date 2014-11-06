app.controller('userController', function($scope, $http, $filter, user){
	$scope.getRequests = function(){
		$http.get('/api/requests')
			.success(function(data){
				$scope.requests = data;
			});	
	};
	$scope.saveRequest = function(){
		if(typeof $scope.request.startdate != 'number'){
			$scope.request.startdate = Date.parse($scope.request.startdate);
		}
		if(typeof $scope.request.enddate != 'number'){
			$scope.request.enddate = Date.parse($scope.request.enddate);
		}
		if($scope.request.id){
			$http.put('/api/requests/' + $scope.request.id, $scope.request)
				.success(function(data){
					for(var i=0; i < $scope.requests.length; i++){
						if($scope.requests[i].id == $scope.request.id){
							$scope.requests[i] = $scope.request;
							break;
						}
					}
					$scope.clearRequest();
				});
		}else{
			$http.post('/api/requests', $scope.request)
				.success(function(data){
					$scope.requests.push(data.entity);
					$scope.clearRequest();
				});
		}
	};
	$scope.editRequest = function(request){
		$scope.request = angular.copy(request);
	};
	$scope.clearRequest = function(){
		$scope.request = {
			"type": "Vacation",
			"status": 1
		};
	};
	$scope.cancelRequest = function(request, index){
		$http.delete('/api/requests/' + request.id)
			.success(function(){
				$scope.requests.splice(index, 1);
			});
	};
	$scope.open = function(prop, $event){
		if($event){
			$event.preventDefault();
    	$event.stopPropagation();
		}
		$scope['openEnd'] = false;
		$scope['openStart'] = false;
		$scope[prop] = true;
	}
	$scope.format = "dd MMM yyyy";
	$scope.statuses = {
		'1': 'Pending',
		'2': 'Approved',
		'3': 'Denied'
	}
	$scope.userName = user.firstName;
	$scope.clearRequest();
	$scope.getRequests();
});