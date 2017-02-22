var app = angular.module('myApp', ['ngRoute','angularUtils.directives.dirPagination','ngAnimate', 'ngSanitize', 'ui.bootstrap','ngStorage'
]);
//var app = angular.module('myApp', ['ngRoute']);

//var app = angular.module('myApp', ['angularUtils.directives.dirPagination','ui.codemirror','ngRoute']);
//https://gist.githubusercontent.com/Miserlou/c5cd8364bf9b2420bb29/raw/2bf258763cdddd704f8ffd3ea9a3e81d25e2c6f6/cities.json
//tab1 controller
app.controller("tab1Controller", function ($scope, $http, $localStorage, $rootScope,$window) {
	$scope.viewdetails="";
	var term_start;
	var term_end;

	$http.get('hw8server.php?all=all').then(function (response) {
		$scope.total = response.data.length;
		$.each(response.data, function (i, d) {
			if(d.district==null){
				d.district = "N.A.";
			}
			else{
				d.district= "District "+d.district;
			}

			if(d.party==="D"){
				d.party="img/d.png";
				d.fullpartyname="Democrat";
			}else{d.party="img/r.png";
			d.fullpartyname="Republican";
		}

		if(d.chamber=="house"){d.chimage="img/h.png";
	}else{d.chimage="img/s.svg";}

	d.population = parseInt(d.population);
});
		$scope.population = response.data;


	});
//code for localstorage
// if($localStorage.favoriteLegislators===undefined){
// $scope.favoriteLegislator= [];
// }
// else
// {
// 	$scope.favoriteLegislator= $localStorage.favoriteLegislators;
// }
$scope.favoriteLegislator= $localStorage.favoriteLegislators;
$scope.class = "glyphicon glyphicon-star-empty";
$scope.save = function(fav) {
	var index = $scope.favoriteLegislator.indexOf(fav);
	//alert("index of is: "+index);


	if(index==-1){
		$scope.favoriteLegislator.push(fav);
		$scope.class = "glyphicon glyphicon-star yellow";

	}
	else{
		$scope.favoriteLegislator.splice(index, 1); 
		$scope.class = "glyphicon glyphicon-star-empty";
	}

	$localStorage.favoriteLegislators = $scope.favoriteLegislator;

}

	$scope.details = function(x) {
//alert("you clicked"+x.last_name);
$scope.favoriteLegislator= $localStorage.favoriteLegislators;
if($localStorage.favoriteLegislators===undefined){
$scope.favoriteLegislator= [];
}
else
{
	$scope.favoriteLegislator= $localStorage.favoriteLegislators;
}
//$scope.favoriteLegislator= $localStorage.favoriteLegislators;
var index = $scope.favoriteLegislator.indexOf(x);
if(index==-1){
		
		
	$scope.class = "glyphicon glyphicon-star-empty";

	}
	else{
		
		$scope.class = "glyphicon glyphicon-star yellow";
	}
//$window.location.href="#activebills";

$scope.viewdetails=x;
term_end=x.term_end;
term_start=x.term_start;

$http.get("hw8server.php?member_ids="+x.bioguide_id).then(function (response) {
	$scope.total = response.data.length;
	$.each(response.data, function (i, d) {



	});





	$scope.topcommittee = response.data;


});
$http.get("hw8server.php?memberbills="+x.bioguide_id).then(function (response) {
	$scope.total = response.data.length;
	$.each(response.data, function (i, d) {



	});





	$scope.topbills = response.data;


});

}

 
               

$scope.getPercentage = function() {
//alert("you clicked"+$scope.viewdetails.term_start);
var d1 = new Date(term_start);
var d2 = new Date();
var nowMiliseconds = d2-d1;
var d3 = new Date(term_end);

var endMiliseconds = d3-d1;	
var duration = (nowMiliseconds/endMiliseconds)*100;
return Math.trunc( duration );;
}

}); // end tab1 controller


//tab2 controller
app.controller("tab2Controller", function ($scope, $http, $localStorage) {
	$scope.viewdetails="";
	var term_start;
	var term_end;
	

	$http.get('hw8server.php?bills=bills').then(function (response) {
		$scope.total = response.data.length;
		$.each(response.data, function (i, d) {
			if(d['history'].active==false){
				d.activebills=false;
			}

			

		if(d.chamber=="house")
			{d.chimage="img/h.png";
	}else{d.chimage="img/s.svg";}

	
});
		$scope.population = response.data;


	});


// if($localStorage.favoriteBills===undefined){
// $scope.favoriteBill= [];
// }
// else
// {
// 	$scope.favoriteBill= $localStorage.favoriteBills;
// }
$scope.favoriteBill= $localStorage.favoriteBills;
$scope.class = "glyphicon glyphicon-star-empty";
$scope.savebills = function(fav) {
	var index = $scope.favoriteBill.indexOf(fav);
	//alert("index of is: "+index);


	if(index==-1){
		$scope.favoriteBill.push(fav);
		$scope.class = "glyphicon glyphicon-star yellow";

	}
	else{
		$scope.favoriteBill.splice(index, 1); 
		$scope.class = "glyphicon glyphicon-star-empty";
	}

	$localStorage.favoriteBills = $scope.favoriteBill;

}

$scope.details = function(x) {
//alert("you clicked"+x.last_name);
if($localStorage.favoriteBills===undefined){
$scope.favoriteBill= [];
}
else
{
	$scope.favoriteBill= $localStorage.favoriteBills;
}
//alert("fav bill is: "+$localStorage.favoriteBills);

//$scope.favoriteBill= $localStorage.favoriteBills;
var index = $scope.favoriteBill.indexOf(x);
if(index==-1){
		
		
	$scope.class = "glyphicon glyphicon-star-empty";

	}
	else{
		
		$scope.class = "glyphicon glyphicon-star yellow";
	}


$scope.viewdetails=x;





}



}); // end tab2 controller

//tab3 controller
app.controller("tab3Controller", function ($scope, $http, $localStorage) {
	$scope.viewdetails="";
	var term_start;
	var term_end;

	if($localStorage.favoriteComm===undefined){
$scope.favoriteComm= [];
}
else
{
	$scope.favoriteComm= $localStorage.favoriteComm;
	
}


//	$scope.favoriteComm= $localStorage.favoriteComm;
//$scope.class = "glyphicon glyphicon-star-empty";
$scope.savecomm = function(fav,rownumber) {
	
	var index2 = $scope.favoriteComm.indexOf(fav);
	//alert("index of is: "+index2);


	if(index2==-1){
		$scope.favoriteComm.push(fav);
		$scope.class = "glyphicon glyphicon-star yellow";
		fav.fave=true;

	}
	else{
		$scope.favoriteComm.splice(index2, 1); 
		$scope.class = "glyphicon glyphicon-star-empty";
		fav.fave=false;
	}

	$localStorage.favoriteComm = $scope.favoriteComm;

}

	$http.get('hw8server.php?committee=all').then(function (response) {
		$scope.total = response.data.length;
		$.each(response.data, function (i, d) {
			
			if(d.phone==null){
				d.phone = "N.A.";
			}
			if(d.office==null){
				d.office = "N.A.";
			}

		

		if(d.chamber=="house"){d.chimage="img/h.png";
	}else{d.chimage="img/s.svg";}
	d.population = parseInt(d.population);

	
});
		$scope.population = response.data;


	});

	

}); // end tab3 controller

//tab4 controller
app.controller("tab4Controller", function ($scope, $http, $localStorage, $rootScope) {
	$scope.viewdetails="";
	$scope.population = $localStorage.favoriteLegislators;
 		$scope.population2 = $localStorage.favoriteBills;
 		$scope.population3 = $localStorage.favoriteComm;
	var term_start;
	var term_end;



$.each($scope.population, function (i, d) {
			
			if(d.phone==null){
				d.phone = "N.A.";
			}
			if(d.office==null){
				d.office = "N.A.";
			}

		

		if(d.chamber=="house"){d.chimage="img/h.png";
	}else{d.chimage="img/s.svg";}
	

	
});


$scope.detailsf = function(x) {
//alert("you clicked"+x.last_name);
// if($localStorage.favoriteLegislators===undefined){
// $scope.favoriteLegislator= [];
// }
// else
// {
// 	$scope.favoriteLegislator= $localStorage.favoriteLegislators;
// }



$scope.viewdetails=x;
term_end=x.term_end;
term_start=x.term_start;




}	
$scope.detailsb = function(x) {
//alert("you clicked"+x.last_name);

// if($localStorage.favoriteBills==undefined){
// $scope.favoriteBill= [];
// }
// else
// {
// 	$scope.favoriteBill= $localStorage.favoriteBills;
// }
//alert("fav bill is: "+$localStorage.favoriteBills);

 
	
		
		
	


$scope.viewdetails=x;





}	

$scope.save = function(fav) {
	var index = $scope.population.indexOf(fav);
	//alert("index of is: "+index);


	if(index==-1){
		$scope.population.push(fav);
		$scope.class = "glyphicon glyphicon-star yellow";

	}
	else{
		$scope.population.splice(index, 1); 
		$scope.class = "glyphicon glyphicon-star-empty";
	}

	$localStorage.favoriteLegislators = $scope.population;

}

$scope.savebills = function(fav) {
	var index = $scope.population2.indexOf(fav);
	//alert("index of is: "+index);


	if(index==-1){
		$scope.population2.push(fav);
		$scope.class = "glyphicon glyphicon-star yellow";

	}
	else{
		$scope.population2.splice(index, 1); 
		$scope.class = "glyphicon glyphicon-star-empty";
	}

	$localStorage.favoriteBills = $scope.population2;

}

$scope.getPercentage = function() {
//alert("you clicked"+$scope.viewdetails.term_start);
var d1 = new Date(term_start);
var d2 = new Date();
var nowMiliseconds = d2-d1;
var d3 = new Date(term_end);

var endMiliseconds = d3-d1;	
var duration = (nowMiliseconds/endMiliseconds)*100;
return Math.trunc( duration );;
}

 $rootScope.load = function() {
 		
 		$scope.population = $localStorage.favoriteLegislators;
 		$scope.population2 = $localStorage.favoriteBills;
 		$scope.population3 = $localStorage.favoriteComm;
	 
	//alert("alert called"+$scope.population);
	$.each($scope.population, function (i, d) {
			
			if(d.phone==null){
				d.phone = "N.A.";
			}
			if(d.office==null){
				d.office = "N.A.";
			}

		

		if(d.chamber=="house"){d.chimage="img/h.png";
	}else{d.chimage="img/s.svg";}
	

	
});
                    
                    //alert("data is :"+$scope.data);
                }
	$scope.removeRow = function(index, content) { 
   if(index != -1){
   	//alert("removed");
   	

	$scope.population.splice(index,1);
	//$scope.message = content+' removed successfully.';
	// do other stuffs such as perform ajax request if want to delete data from 
	//server
	}
 };
 $scope.removeRow2 = function(index, content) { 
   if(index != -1){
   	//alert("removed");
   	

	$scope.population2.splice(index,1);
	//$scope.message = content+' removed successfully.';
	// do other stuffs such as perform ajax request if want to delete data from 
	//server
	}
 };
$scope.removeRow3 = function(index, content) { 
   if(index != -1){
   	//alert("removed");
   	

	$scope.population3.splice(index,1);
	//$scope.message = content+' removed successfully.';
	// do other stuffs such as perform ajax request if want to delete data from 
	//server
	}
 };
	

}); // end tab4 controller

app.filter('unique', function () {

	return function (items, filterOn) {

		if (filterOn === false) {
			return items;
		}

		if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
			var hashCheck = {}, newItems = [];

			var extractValueToCompare = function (item) {
				if (angular.isObject(item) && angular.isString(filterOn)) {
					return item[filterOn];
				} else {
					return item;
				}
			};

			angular.forEach(items, function (item) {
				var valueToCheck, isDuplicate = false;

				for (var i = 0; i < newItems.length; i++) {
					if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					newItems.push(item);
				}

			});
			items = newItems;
		}
		return items;
	};
});

app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/legislator.html',
		controller  : 'legislatorController'
	})

	.when('/bill', {
		templateUrl : 'pages/bill.html',
		controller  : 'billController'
	})

	.when('/committee', {
		templateUrl : 'pages/committee.html',
		controller  : 'committeeController'
	})
	.when('/favorite', {
		templateUrl : 'pages/favorite.html',
		controller  : 'favoriteController'
	})
	.when('/table', {
		templateUrl : 'pages/table.html',
		controller  : 'tableController'
	})

	.otherwise({redirectTo: '/'});
});

app.controller('legislatorController', function($scope) {
	$scope.message = 'Hello from legislatorController';
});

app.controller('billController', function($scope) {
	$scope.message = 'Hello from billController';
});

app.controller('committeeController', function($scope) {
	$scope.message = 'Hello from committeeController';
});

app.controller('favoriteController', function($scope) {
	$scope.message = 'Hello from favoriteController';
});

$(document).ready(function() {


	$('[data-toggle=offcanvas]').click(function() {
		$('.row-offcanvas').toggleClass('active');
		$('#sidebar').toggleClass('inactive');
	});


	$('#myTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});

//$('#carousel-example-generic').carousel('pause');
$('#carousel-example-generic').carousel({
	pause: true,
	interval: false
});



$('#tabs').each(function(){
// For each set of tabs, we want to keep track of
// which tab is active and its associated content
var $active, $content, $links = $(this).find('a');

// If the location.hash matches one of the links, use that as the active tab.
// If no match is found, use the first link as the initial active tab.
$active = $($links.filter('[href="'+location.hash+'"]')[0] || $links[0]);
$active.addClass('active');

$content = $($active[0].hash);

// Hide the remaining content
$links.not($active).each(function () {
	$(this.hash).hide();
});

// Bind the click event handler
$(this).on('click', 'a', function(e){
// Make the old tab inactive.
$active.removeClass('active');
$content.hide();

// Update the variables with the new link and content
$active = $(this);
$content = $(this.hash);

// Make the tab active.
$active.addClass('active');
$content.show();

// Prevent the anchor's default click action
e.preventDefault();
});
});


});

