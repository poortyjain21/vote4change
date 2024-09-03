
function redirectadministratorpage()
{
    console.log("INSIDE ADMINOPTIONS.JS ");
   let pr = swal("Admin","Redirecting To Admin Actions Page!","success");
   pr.then((value)=>{
       
        window.location = "adminactions.jsp";
    });
}



function redirectvotingpage()
{
     swal("Admin","Redirecting To Voting Controller Page!","success").then(value=>{
        window.location = "VotingControllerServlet";
    });
}

function manageuser()
{
    swal("Admin","Redirecting To User Management Page!","success").then(value=>{
        window.location = "manageuser.jsp";
    });
}

function managecandidate()
{
    swal("Admin","Redirecting To Candidate Management Page!","success").then((value)=>{
        window.location = "managecandidate.jsp";
    });
}

//function showaddcandidateform()
//{
//    removeCandidateForm();
//    removeUpdateCandidateForm();
//    
//    var newdiv = document.createElement("div") ;
//    newdiv.setAttribute("id","candidateform") ;
//    newdiv.setAttribute("float","left") ;
//    newdiv.setAttribute("padding-left","12px");
//    newdiv.setAttribute("border","solid 2px red") ;
//    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
//    
//    newdiv.innerHTML = newdiv.innerHTML + 
//                     "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
//         <table><tr><th>Candidate Id  </th><td><input type='text' id='cid'></td></tr>\n\
//          <tr><th>User Id </th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
//          <tr><th>Candidate Name  </th><td><input type='text' id='cname'</td></tr>\n\
//           <tr><th>City </th><td><select id='city'></select></td></tr>\n\
//           <tr><th>Party  </th><td><input type='text' id='party'></td></tr>\n\
//           <tr><td colspan='2'><input type='file' name='files' value='Select Image'</td></tr>\n\
//           <tr><th><input type='button' value='Add Candidate' onclick='addCandidate()' id='addcandidate'</th></tr>\n\
//           <tr><th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
//           
//           newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>" ;
//           var addcand = $("#result")[0] ;
//           addcand.appendChild(newdiv);
//           $("#candidateform").hide();
//           $("#candidateform").fadeIn(3500);
//           
//           data = {id :"getid"};
//           $.post("AddCandidateControllerServlet",data,function(responseText){
//               $("#cid").val(responseText);
//               $("#cid").prop("disabled",true);
//           });
//   
//}


function getdetails(e)
{
    if(e.keyCode===13)
    {
        
        var data = {uid : $("#uid").val()};
        $.post("AddCandidateControllerServlet",data,function(responseText){
            
           let details = JSON.parse(responseText);
           console.log("Inside adminoptions.js"+details);
           
           let city = details.city ;
           let uname = details.username ;
           console.log("Printing cities "+city);
           
           if(uname==="wrong")
               swal("Wrong Adhar No!","Adhar no is invalid!","error");
           
           else{
               
               $("#cname").val(uname);
               $("#city").empty();
               $("#city").append(city) ;
               $("#cname").prop("disabled",true);
               $("#cname").prop("opacity",0.7);
               $("#cname").prop("cursor","not-allowed");
           }
            
            
        });
    }
}


function addCandidate()
{
    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);
    alert(data);
    
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val() ;
    var party = $("#party").val();
    var uid = $("#uid").val();
    
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    
    $.ajax({
        
       type:"POST",
       enctype:'multipart/form-data',
       url:"AddNewCandidateControllerServlet",
       data: data,
       processData : false,
       contentType:false,
       cache:false,
       timeout:600000,
       success:function(data){
      
        swal("Admin!","Candidate Added!","success").then((value)=>{
            showaddcandidateform();
        });
       },
       
       error:function(e){
           
           swal("Admin!",e,"error");
       }
     
    });
            
}

function removeCandidateForm()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform");
    if(formdiv!==null)
    {
        console.log("INSIDE REMOVE CANDIDATE FUNCTION ");
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
    else
        contdiv.innerHTML="";
}

function showCandidate()
{
    removeCandidateForm();
    removeUpdateCandidateForm();
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
    newdiv.innerHTML = "<h3>Show Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'>Candidate Id:</div><div id='hello'><select id='cid'></select></div> " ;
    newdiv.innerHTML = newdiv.innerHTML+"<br> <span id='addresp'></span>";
    var addcand = $("#result")[0] ;
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
           
    data = {id :"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
      var candidlist = JSON.parse(responseText);
      $("#cid").append(candidlist.cid);
       
     });
     
     $("#cid").change(function(){
         var cid = $("#cid").val();
         if(cid==='')
         {
             swal("No Selection!", "Please select an id", "error");
             return;
         }
          data = {id :cid};
          $.post("ShowCandidateControllerServlet",data,function(responseText){
         clearText();
          var details = JSON.parse(responseText);
          $("#addresp").append(details.subdetails);
       
         });
         
     });
}

function electionresult()
{
  
        
        $.post("ElectionResultControllerServlet",null,function(responseText){
            console.log("received!");
            swal("Result fetched!","Success","success");
            let resultdiv = document.getElementById("result");
            resultdiv.innerHTML=responseText.trim();
            
        });
   
}

function clearText()
{
   $("#addresp").html("");
   $("#city").html("");
    
}

function deletecandidate(){
    


     removeCandidateForm();
     removeUpdateCandidateForm();
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
    newdiv.innerHTML = "<h3>Delete Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'>Candidate Id:</div><div id='hello'><select id='cid'></select></div> " ;
     newdiv.innerHTML = newdiv.innerHTML+"<br> <span id='addresp'></span>";
    
    var addcand = $("#result")[0] ;
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
           
    data = {id :"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
      var candidlist = JSON.parse(responseText);
      $("#cid").append(candidlist.cid);
       
     });
     
//     $("#cid").change(function(){
//         var cid = $("#cid").val();
//         if(cid==='')
//         {
//             swal("No Selection!", "Please select an id", "error");
//             return;
//         }
//          data = {id :cid};
//          $.post("DeleteControllerServlet",data,function(responseText){
//          if(responseText.trim()==="success")
//          {
//              swal("Candidate Deleted!","...","success");
//              $("#cid").val("Choose Id");
//          }
//          else{
//              swal("Error!","Candidate not removed","error");
//          }
//       
//         });
//             
//     });

    $("#cid").change(function(){
         var cid = $("#cid").val();
         if(cid==='')
         {
             swal("No Selection!", "Please select an id", "error");
             return;
         }
          data = {remove :cid};
          $.post("ShowCandidateControllerServlet",data,function(responseText){
           clearText();
           var details = JSON.parse(responseText);
           $("#addresp").append(details.subdetails);
         });
             
    });
     
       
   
}

//function removeUser()
//{  
//    removeCandidateForm();
//    
//    var newdiv = document.createElement("div") ;
//    newdiv.setAttribute("id","candidateform") ;
//    newdiv.setAttribute("float","left") ;
//    newdiv.setAttribute("padding-left","12px");
//    newdiv.setAttribute("border","solid 2px red") ;
//    newdiv.innerHTML = "<h3>User-ID</h3>";
//    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'></div><div id='hello'><select id='cid'></select></div> " ;
//    
//    
//    var addcand = $("#result")[0] ;
//    addcand.appendChild(newdiv);
//    $("#candidateform").hide();
//    $("#candidateform").fadeIn(2500);
//           
//    data = {id :"uid"};
//    $.post("ShowUserDetailsControllerServlet",data,function(responseText){
//    var userlist = JSON.parse(responseText);
//    $("#cid").append(userlist.usid);
//       
//     });
//     
//      $("#cid").change(function(){
//          
//         var usid = $("#cid").val();
//         console.log(usid);
//         if(usid==='')
//         {
//             swal("No Selection!", "Please select an id", "error");
//             return;
//         }
//          data = {id :usid};
//          $.post("RemoveUserControllerServlet",data,function(responseText){
//          if(responseText.trim()==="success")
//          {
//              swal("User Removed!","...","success");
//              $("#cid").html("<option value='' >Choose ID</option>");
//          }
//          else{
//              swal("Error!","User not removed","error");
//          }
//       
//         });
//             
//     });
//     
//}

function showUser()
{
    removeCandidateForm();
   $.post("ShowUserDetailsControllerServlet",null,function(responseText){
       
       var resultdiv = document.getElementById("result");
       
       resultdiv.innerHTML = responseText.trim();
   });
}


//function showupdatecandidateform()
//{
//        removeCandidateForm();
//    
//    var newdiv = document.createElement("div") ;
//    newdiv.setAttribute("id","candidateform") ;
//    newdiv.setAttribute("float","left") ;
//    newdiv.setAttribute("padding-left","12px");
//    newdiv.setAttribute("border","solid 2px red") ;
//    newdiv.innerHTML = "<h3>Select Candidate</h3>";
//    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'>Candidate Id:</div><div id='hello'><select id='cid'></select></div> " ;
//    newdiv.innerHTML = newdiv.innerHTML+"<br> <span id='addresp'></span>";
//    var addcand = $("#result")[0] ;
//    addcand.appendChild(newdiv);
//    $("#candidateform").hide();
//    $("#candidateform").fadeIn(3500);
//           
//    data = {id :"cid"};
//    $.post("ShowCandidateControllerServlet",data,function(responseText){
//      var candidlist = JSON.parse(responseText);
//      $("#cid").append(candidlist.cid);
//       
//     });
//     
//     $("#cid").change(function(){
//         
//      removeUpdateCandidateForm()     
//    var cid = $("#cid").val() ;
//    console.log(cid) ; 
//    var newdiv = document.createElement("div") ;
//    newdiv.setAttribute("id","candidateform1") ;
//    newdiv.setAttribute("float","left") ;
//    newdiv.setAttribute("padding-left","12px");
//    newdiv.setAttribute("border","solid 2px red") ;
//    newdiv.innerHTML = "<h3>Update Candidate</h3>";
//    
//    newdiv.innerHTML = newdiv.innerHTML + 
//"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
//    <div class='Container'>\n\
//        <div class='Container2'>\n\
//            <img src='images/candidate.jpg' class='C1'>\n\
//            <div class='C2'>   \n\
//                <div class='Couple'>\n\
//                    <div class='Label'>Candidate Id </div> \n\
//                    <input type='text' id='cid1' class='InputArea'>\n\
//                </div>\n\
//                <div class='Couple'>\n\
//                    <div class='Label'>Aadhar Id</div>\n\
//                    <input type='text' id='uid' onkeypress='return getdetails(event)' class='InputArea'>\n\
//                </div>\n\
//                <div class='Couple'>\n\
//                    <div class='Label'>Candidate Name</div> \n\
//                    <input type='text' id='cname' class='InputArea'>\n\
//                </div>\n\
//            </div>\n\
//            <div class='C3'>\n\
//                <div class='Couple'>\n\
//                    <div class='Label'>City</div>  \n\
//                    <select id='city' class='SelectOptions2'></select>\n\
//                </div>\n\
//                <div class='Couple'>\n\
//                    <div class='Label'>Party</div> \n\
//                    <input type='text' id='party' class='SelectOptions'>\n\
//                </div>\n\
//                <input type='file' name='files' value='Select Image'>\n\
//            </div>\n\
//            <input type='button' value='Update Candidate' onclick='updateCandidate()' id='updatecandidate' class='Button'>\n\
//        </div>\n\
//    </div>\n\
//</form>";
//           
//           newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>" ;
//           var addcand = $("#result")[0] ;
//           addcand.appendChild(newdiv);
//           $("#candidateform1").hide();
//           $("#candidateform1").fadeIn(3500);
//           
//           data ={id : cid};
//           $.post("UpdateCandidateControllerServlet",data,function(responseText){
//               
//               var data = JSON.parse(responseText) ;
//               $("#cid1").val(cid) ;
//               $("#cid1").prop("disabled",true);
//               $("#uid").val(data.adhar_no);
//               $("#uid").prop("disabled",true);
//               $("#cname").val(data.cname);
//               $("#cname").prop("disabled",true) ; 
//               $("#city").append(data.city);
//               
//               
//           });
//         
//     });
//       
//}

function removeUpdateCandidateForm()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform1");
    if(formdiv!==null)
    {
        console.log("INSIDE REMOVE UPDATE CANDIDATE FUNCTION ");
        $("#candidateform1").fadeOut("2500");
        contdiv.removeChild(formdiv);
    }
    
}

function updateCandidate()
{
    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);
    
    var cid = $("#cid1").val();
    var city = $("#city").val();
    var party = $("#party").val();
    
    data.append("cid",cid);
    data.append("city", city);
    data.append("party",party);
    
    if(cid==="" || city==="" || party==="" || form==null)
    {
        swal("Error","Please fill all the details!","error"); 
        return ; 
    }
    
       $.ajax({
        
       type:"POST",
       enctype:'multipart/form-data',
       url:"UpdateDetailsCandidateControllerServlet",
       data: data,
       processData : false,
       contentType:false,
       cache:false,
       timeout:600000,
       success:function(data){
        
        swal("Admin!","Candidate Details Updated!","success").then((value)=>{
            removeUpdateCandidateForm();
            showupdatecandidateform();
        });
       },
       
       error:function(e){
           
           swal("Admin!",e,"error");
       }
     
       });
}


function removeUser()
{
    removeCandidateForm();
    
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
    newdiv.innerHTML = "<h3>User-ID</h3>";
    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'></div><div id='hello'><select id='cid'></select></div> " ;
    newdiv.innerHTML = newdiv.innerHTML+"<br> <span id='addresp'></span>";
    
    var addcand = $("#result")[0] ;
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(2500);
           
    data = {id :"uid"};
    $.post("ShowUserDetailsControllerServlet",data,function(responseText){
    var userlist = JSON.parse(responseText);
    $("#cid").append(userlist.usid);
       
     });
     
     $("#cid").change(function(){
         
        
         var usid = $("#cid").val() ;
         console.log(usid);
         data ={details : usid} ;
         $.post("RemoveUserControllerServlet",data,function(responseText){
             clearText();
             $("#addresp").append(responseText.trim());
         });
     });
}

function delUser()
{
   var uid = $('input[type=button][name=btn]').first().attr('id');
   console.log("hello " + uid);
   data = { id : uid  };
   $.post("RemoveUserControllerServlet",data,function(responseText){
       
       console.log(responseText);
       if(responseText === "success")
           swal("Admin","User Removed!","success");
       else if(responseText === "failed")
           swal("Admin","Failed to remove User!","error");
      removeUser();
   });
    
}


function showaddcandidateform()
{
    removeCandidateForm();
    removeUpdateCandidateForm();
    
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
//    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
    
    newdiv.innerHTML = newdiv.innerHTML + 
                     "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\<div class='container1'>\n\
             <div class='container11'>\n\
             <div class='couple1'><div class='label'>Candidate ID</div><input type='text' class='InputArea1' id='cid' class='extra'/></div><div class='couple1'><div class='label'>Aadhar ID</div><input type='text' class='InputArea1' id='uid' onkeypress='return getdetails(event)' /></div>\n\
             <div class='couple1'><div class='label'>Candidate Name</div><input type='text' class='InputArea1' id='cname' class='extra'/></div><div class='couple1'><div class='label'>City</div><select  class='InputArea1' id='city'/></select></div><div class='couple1'><div class='label'>Party</div><input type='text' class='InputArea1' id='party'/></div><div class='couple1'><input type='file' name='files' id='file' value='Select Image'/></div><div class='btn'><input type='button' value='Add Candidate' id='addcandidate' onclick='addCandidate()' />\n\
             <input type='reset' value='clear' onclick='clearText()' id='clear'/></div></div></div>\n\</form>" ;
     
           
           newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>" ;
           var addcand = $("#result")[0] ;
           addcand.appendChild(newdiv);
           $("#candidateform").hide();
           $("#candidateform").fadeIn(3500);
           
           data = {id :"getid"};
           $.post("AddCandidateControllerServlet",data,function(responseText){
               $("#cid").val(responseText);
               $("#cid").prop("disabled",true);
           });
   
}


function showupdatecandidateform()
{
        removeCandidateForm();
    
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
    newdiv.innerHTML = "<h3>Select Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML +"<div style='color:white; font-weight:bold' id='hi'>Candidate Id:</div><div id='hello'><select id='cid'></select></div> " ;
    newdiv.innerHTML = newdiv.innerHTML+"<br> <span id='addresp'></span>";
    var addcand = $("#result")[0] ;
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
           
    data = {id :"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
      var candidlist = JSON.parse(responseText);
      $("#cid").append(candidlist.cid);
       
     });
     
     $("#cid").change(function(){
         
      removeUpdateCandidateForm()     
    var cid = $("#cid").val() ;
    console.log(cid) ; 
    var newdiv = document.createElement("div") ;
    newdiv.setAttribute("id","candidateform1") ;
    newdiv.setAttribute("float","left") ;
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red") ;
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    
    newdiv.innerHTML = newdiv.innerHTML + 
 "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\<div class='container1'>\n\
             <div class='container11'>\n\
             <div class='couple1'><div class='label'>Candidate ID</div><input type='text' class='InputArea1' id='cid1' class='extra'/></div><div class='couple1'><div class='label'>Aadhar ID</div><input type='text' class='InputArea1' id='uid' onkeypress='return getdetails(event)' /></div>\n\
             <div class='couple1'><div class='label'>Candidate Name</div><input type='text' class='InputArea1' id='cname' class='extra'/></div><div class='couple1'><div class='label'>City</div><select  class='InputArea1' id='city'/></select></div><div class='couple1'><div class='label'>Party</div><input type='text' class='InputArea1' id='party'/></div><div class='couple1'><input type='file' name='files' id='file' value='Select Image'/></div><div class='btn'><input type='button' value='Update Candidate' id='addcandidate' onclick='addCandidate()' />\n\
             </div></div></div>\n\</form>" ;
           
           newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>" ;
           var addcand = $("#result")[0] ;
           addcand.appendChild(newdiv);
           $("#candidateform1").hide();
           $("#candidateform1").fadeIn(3500);
           
           data ={id : cid};
           $.post("UpdateCandidateControllerServlet",data,function(responseText){
               
               var data = JSON.parse(responseText) ;
               $("#cid1").val(cid) ;
               $("#cid1").prop("disabled",true);
               $("#uid").val(data.adhar_no);
               $("#uid").prop("disabled",true);
               $("#cname").val(data.cname);
               $("#cname").prop("disabled",true) ; 
               $("#city").append(data.city);
               
               
           });
         
     });
       
}

function delCandidate()
{
    var uid = $('input[type=button][name=btn]').first().attr('id');
    console.log(uid);
    data = {id : uid};
   $.post("DeleteControllerServlet", data, function(responseText){
       
//       if(responseText === "success")
//       {
//           swal("Admin","Candidate Removed!","success");
//       }
//       else{
//           swal("Error","Candidate not Removed!","error");
    //   }
    
     swal("Admin","Candidate Removed!","success");
     clearText();
   });
        
    
}