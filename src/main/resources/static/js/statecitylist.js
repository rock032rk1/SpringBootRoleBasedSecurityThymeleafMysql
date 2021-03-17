$(document).ready(function(){

	$('#countryid').on('change', function(){
		
		var countryId = $('#countryid').val();
		
		$.ajax({
			type: 'GET',
			contentType : "application/json",
			url: '/loadStatesByCountry/' + countryId,
			success: function(result) {
				
				var result = JSON.parse(result);
				var s = '';
				$.each( result, function( key, value ) {
				    s += '<option value='+key+'>' + value + '</option>';
				});
//				for(var i = 0; i < result.length; i++) {
//					s += '<option value="' + result[i] + '">' + result[i] + '</option>';
//				}

				$('#statebox').html(s);
			}
		});
	});


	$('#statebox').on('change', function(){
		var stateId = $('#statebox').val();
		$.ajax({
			type: 'GET',
			contentType : "application/json",
			url: '/loadCitiesByState/' + stateId,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				$.each( result, function( key, value ) {
				    s += '<option value='+key+'>' + value + '</option>';
				});
//				for(var i = 0; i < result.length; i++) {
//					s += '<option value>' + result[i] + '</option>';
//				}
				$('#comboboxCity').html(s);
			}
		});
	});

});
