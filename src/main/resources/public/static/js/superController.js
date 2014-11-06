app.controller('superController', function($scope, $http, user){
	console.dir(user);
	$scope.getRequests = function(){
		$http.get('/api/groups/' + user.group + '/requests')
			.success(function(data){
				$scope.requests = data;
			});
	};
	$scope.approveRequest = function(request){
		
	};
	$scope.denyRequest = function(request){
		
	};
	
	$scope.format = "dd MMM yyyy";
	$scope.statuses = {
		'1': 'Pending',
		'2': 'Approved',
		'3': 'Denied'
	}
	$scope.userName = user.firstName;
	$scope.getRequests();
});