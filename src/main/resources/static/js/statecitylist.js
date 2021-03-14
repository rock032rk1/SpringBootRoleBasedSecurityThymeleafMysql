$(document).ready(function(){

	$('#countryid').on('change', function(){
		var countryId = $(this).val();
		$.ajax({
			type: 'GET',
			contentType : "application/json",
			url: '/loadStatesByCountry/' + countryId,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				for(var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i].id + '">' + result[i].state_name + '</option>';
				}
				$('#statebox').html(s);
			}
		});
	});

	$('#statebox').on('change', function(){
		var stateId = $(this).val();
		$.ajax({
			type: 'GET',
			contentType : "application/json",
			url: '/loadCitiesByState/' + stateId,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				for(var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i].id + '">' + result[i].city_name + '</option>';
				}
				$('#comboboxCity').html(s);
			}
		});
	});

});
