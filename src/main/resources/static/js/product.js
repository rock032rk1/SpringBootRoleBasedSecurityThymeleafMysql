$(document).ready(function(){
	// Adding New Product
	 $('.nBtn').on('click',function(){
		    
		    $('#id').val('');
			$('#pname').val('');
			$('#categary').val('');
			$('#price').val('');
			
			$('#addModal').modal();
	  });
	 
	 //Updating Existing Product
	 $('.table .eBtn').on('click', function(event) {

			event.preventDefault();

			var href = $(this).attr('href');

			$.get(href, function(st, status) {
				$('#editid').val(st.id);
				$('#editpname').val(st.pname);
				$('#editcategary').val(st.categary);
				$('#editprice').val(st.price);

			});
			$('#editModal').modal();
		});
	 
	 //Delete the Product
	 $('.table .dBtn').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');

			$('#deleteModal #delBtn').attr('href', href);
			$('#deleteModal').modal();
		});
});