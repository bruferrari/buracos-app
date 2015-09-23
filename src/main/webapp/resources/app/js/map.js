var map;
var geocoder = new google.maps.Geocoder();
var localizationText = 'Você está aqui!';
var info, lat, lng, email, geocoderResult;
var infowindow = new google.maps.InfoWindow();

function initialize() {
	var latlng = new google.maps.LatLng(-22.9786984, -49.8674522);
 
    var options = {
        zoom: 14,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    map = new google.maps.Map(document.getElementById("map"), options);
    getAllMarkers();
}

function getMarkers() {
	$.getJSON('http://localhost:8080/BuracosApp/service/getMarkers', function(response) {
		$.each(response.markers, function(index, marker) {
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(marker.latitude, marker.longitude),
				title: "teste",
				map: map
			});
		});
	});
}

function getAllMarkers() {
	jQuery.ajax({
		type: "GET",
		url: "http://localhost:8080/BuracosApp/service/markers",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(response){
			$.each(response.markers, function(index, marker) {
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(marker.latitude, marker.longitude),
					animation: google.maps.Animation.DROP,
					title: "marker",
					map: map
				},

				lat = marker.latitude, 
				lng = marker.longitude, 
				email = marker.email, 
				info = '<b>' + lat + '</b>' + ' ' + '<b>' + lng + '</b>',
				geocoderResult = codeLatLng(marker.latitude, marker.longitude));
				
				console.log(geocoderResult);
				
				marker.infowindow = new google.maps.InfoWindow({
				      content: info
				  });

				  google.maps.event.addListener(marker, 'click', function() {
					marker.infowindow.open(map,marker);
//				    marker.infowindow.setContent(info);
				  });
			});
		}
	})
}

function handleNoGeolocation(errorFlag) {
	  if (errorFlag) {
	    var content = 'Error: The Geolocation service failed.';
	  } else {
	    var content = 'Error: Your browser doesn\'t support geolocation.';
	  }

	  var options = {
	    map: map,
	    position: new google.maps.LatLng(60, 105),
	    content: content
	  };

	  var infowindow = new google.maps.InfoWindow(options);
	  map.setCenter(options.position);
	}

function geolocation() {
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position){
			var pos = new google.maps.LatLng(position.coords.latitude,
											 position.coords.longitude);
			var infowindow = new google.maps.InfoWindow({
				map: map,
				position: pos,
				content: localizationText
			});
			
			map.setCenter(pos);
		}, function() {
			handleNoGeolocation(true);
		});
	} else {
		handleNoGeolocation(false);
	}
}

function toggleBounce() {
	if(marker.animation() != null) {
		marker.setAnimation(null);
	} else {
		marker.setAnimation(google.maps.Animation.BOUNCE);
	}
}

function codeLatLng(lat, lng) {
	var input = lat + ' ' + lng;
	console.log(input);
	var latlngString = input.split(' ', 2);
	var latlng = new google.maps.LatLng(latlngString[0], latlngString[1]);
	geocoder.geocode({'location': latlng}, function(results, status) {
		if(status == google.maps.GeocoderStatus.OK) {
			var result = results[1].formatted_address;
			console.log('codeLatLng' + result);
			return result;
		} else {
			window.alert('Não foi possível recuperar o endereço.');
			return;
		}
	});
}

google.maps.event.addDomListener(window, 'load', initialize);