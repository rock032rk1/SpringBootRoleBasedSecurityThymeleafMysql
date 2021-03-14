$(document).ready(function(event) {
	       //event.preventDefault();
			$('#name').keyup(function(e) {
				var enterName = $('#name').val();
				//starting Ajax Call
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/wish",
					data : "name=" + enterName,
					success : function(result) {
						$('#responseDiv').html(result);
					},
					error : function() {
						alert('error occured');
					}
				})
			});
		});