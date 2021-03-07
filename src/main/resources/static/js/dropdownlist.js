
    $(document).ready(function(){
    	//After Login Sign-In dropdown lable will be disable
    	 var user=$('#us').html();
    	 var role=$('#role').html();
    	 if(user!=null){
    		 $('.ev').hide();
    		 $('.sign-up').hide();
    	 }
    	 else
    	 {
    		 $('.ev').show(); 
    	 }
    	 // Only Admin can see USER List
    	 if(role=='[USER]' || role=='[EDITOR]'){
    		 $('.user-list').hide();
    	 }
    	 else
    	 {
    		 $('.user-list').show(); 
    	 }
    
    });