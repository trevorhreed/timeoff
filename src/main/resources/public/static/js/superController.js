app.controller('superController', function($scope, $http, user){
	console.dir(user);
	$scope.getRequests = function(){
		$http.get('/api/groups/' + user.group + '/requests')
			.success(function(data){
				$scope.requests = data;
			});
	};
	$scope.approveRequest = function(request){
		$http.post('/api/groups/approver/' + request.id)
			.success(function(){
				request.status = 2;
			});
	};
	$scope.denyRequest = function(request){
		$http.post('/api/groups/denier/' + request.id)
			.success(function(){
				request.status = 2;
			});
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