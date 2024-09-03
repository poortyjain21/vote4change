function addvote()
{
   
   data = { candidateid : $('input[type=radio][name=flat]:checked').attr('id')  };
   $.post("AddVoteControllerServlet", data, processResponse);
          
}

 function processResponse(responseText)
 {
    responseText = responseText.trim();
    console.log(responseText);
    if(responseText === "success")
    {
        swal("Success","Voting done","success").then((value)=>{
            
        window.location = "votingresponse.jsp" ;   
        });
    }
    else{
        
       swal("Failure","Voting failed","error").then((value)=>{
            
        window.location = "votingresponse.jsp" ;   
        }); 
    }
       
 }
