$(document).ready(function(){
	
	 //Updating Existing User
	 $('.table .eBtn').on('click', function(event) {

			event.preventDefault();

			var href = $(this).attr('href');

			$.get(href, function(st, status) {
				$('#editid').val(st.id);
				$('#editusername').val(st.username);
				$('#editpassword').val(st.password);
				//$('#countryid').val(st.country_Name);
				//$('#country_Name').val(st.country_Name);

			});
			$('#editModal').modal();
		});
	 
	//Delete the User
	 $('.table .dBtn').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');

			$('#deleteModal #delBtn').attr('href', href);
			$('#deleteModal').modal();
		});
});