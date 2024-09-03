let username,password,cpassword,city,address,adhar,email,mobile;
function addUser()
{
   username=$("#username").val(); 
   password=$("#password").val(); 
   cpassword=$("#cpassword").val(); 
   city=$("#city").val(); 
   address=$("#address").val(); 
   adhar=$("#adhar").val(); 
   email=$("#email").val(); 
   mobile=$("#mobile").val();
   
   if(validateUser())
   {
       if(password !== cpassword)
       {
            swal("Error ! ","Passwords do not match ! ","error");
            return;
       }
       else
       {
           if(checkEmail()==false)
               return ;
           
           //do mobile validation yourself
           
           let data={
               
               username:username,
               password:password,
               userid:adhar,
               mobile:mobile,
               email:email,
               address:address,
               city:city
               
               
               
           };
           
          let xhr= $.post("RegistrationServlet",data,processResponse);//processResponse will run only when everything is right
          console.log("XHR OBJ",xhr);
//            xhr.fail(handleError); // run when there any kind of error is generated 
          //or use fail(handleError)
          console.log("IN REGISTRATION.JS");
           
       }
       
   }
}

function processResponse(responseText)
{
    let str = responseText.trim();
    console.log("inside processResponse function"+str);
    if(str==="success"){
         swal("Success ! ","Registration Successfully! You can now login","success");
         setTimeout(redirectUser,3000);
    }
   
    
    else if(str==="uap")
    swal("Error ! ","Sorry! the userid is already present","error");
    
    else
    swal("Error ! ","Registration Failed! Try again","error");
    
}

function handleError(xhr)
{
     swal("Error ! ","Problem in server communication: "+xhr.statusText ,"error");
}

function validateUser()
{
    if(username==="" || password==="" ||cpassword===""||city==="" || address==="" || adhar==="" ||email==="" || mobile==="")
    {
         swal("Error ! ","ALL fields are mandatory","error");
         return false;
    }
    return true;
    
  
}

function checkEmail()
{
    let attheratepos = email.indexOf("@");
    let dotpos = email.indexOf(".");
    
    if(attheratepos < 1 || dotpos < attheratepos+2 || dotpos+2 >= email.length)
    {
         swal("Error ! ","Please Enter Valid Email","error");
         return false;
    }
    return true;
}

function checkMobile()
{
    
}

function redirectUser()
{
    window.location="login.html";
}
