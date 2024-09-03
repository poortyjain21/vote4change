
let userid, password ;

function connectUser()
{
    userid = $("#username").val() ;
    password = $("#password").val() ;
    
    if(validate()===false)
    {
        swal("Access Denied","Please enter userid/password","error");
        return ;
    }
    
    let data ={userid :userid,password : password};
    let xhr = $.post("LoginControllerServlet",data,processResponse);
    xhr.error(handleError);
   
}

function processResponse(responseText)
{
    
    if(responseText.trim()==="error")
    {
        console.log("Inside login.js processResponse method "+responseText);
        swal("Acess  Denied","Invalid userid/password","error");
    }
    
    else if(responseText.trim().indexOf("jsessionid") !== -1)
    {
        console.log("Inside login.js responseText function "+responseText);
        let pr = swal("Success","Login Successful","success"); //Used Promise here ....because we need to impelement that after clicking on 'ok' user will be redirected to the new page 
        pr.then((value)=>{
            
           window.location = responseText.trim() ; 
        });
    }
}

function handleError(xhr)
{
     swal("Error ! ","Problem in server communication: "+xhr.statusText ,"error");
}

function validate()
{
    if(userid==="" || password==="")
    {
         swal("Error ! ","ALL fields are mandatory","error");
         return false;
    }
    return true;
    
  
}
