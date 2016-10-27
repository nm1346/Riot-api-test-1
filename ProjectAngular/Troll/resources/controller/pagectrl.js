//페이지별 컨트롤러
myApp.controller('indexCtrl', function($scope){

});

myApp.controller('searchCtrl',function($scope,OptionInject){
	$scope.option=OptionInject.mainOptions;  //예제
});