var callAjax = function(){

}

callAjax.changePage = function(href,pageId,callBack){
    if(sessionStorage.getItem("lastGenId")!=null && sessionStorage.getItem("lastGenId") == pageId){
        return true;
    }
    var req = {};
    req.type = "GET";
    if(parseInt(pageId, 10)!= 1){
        //req.param = "?GenreId="+pageId;
        req.servlet = "http://localhost:8080/XMLProject/html/" + href.split("/").pop();
    
    }else{
        req.param = "";
        req.servlet = "http://localhost:8080/XMLProject/html/bCenterAllItems.jsp";
    }
    var changePageCallback = function(){
        callAjax.changePageCallback(callBack)
    };
    ajaxFunction(req,changePageCallback);
    sessionStorage.setItem("lastGenId",pageId);
    return true;
}

callAjax.changePageCallback = function(callBack){
    if(xmlhttp.status == 200){
        document.getElementById("listItem").innerHTML = xmlhttp.responseText.trim();
        // setupHistoryClicks();
        callBack();
        return true;
    }else{
        return false;
    }
    
}


callAjax.checkout = function(callback){
    var req = {};
    req.type = "POST";
    req.servlet  ="OrderController";
   
   
    var shopCart = jsUtils.id("listAdded").children;
    var xmlString ="<orderdetails xmlns%3D\"http://xml.netbeans.org/schema/orderdetails\">";
    for(var i =0; i <shopCart.length; i++){
        var NoId = shopCart[i].id.split("-").pop();
        var price =shopCart[i].children[1].children[1].innerText.replace("$","");
        xmlString+= "<OrderDetail>"
        xmlString+= "<OrderId></OrderId>"
        xmlString=xmlString+"<GameId>"+NoId+"</GameId>";
        xmlString =xmlString+"<Price>"+price+"</Price>";
        xmlString =xmlString+"<Quantity>"+jsUtils.id("NumberOfItem-"+NoId).value+"</Quantity>";
        xmlString += "</OrderDetail>";
    }
    xmlString += "</orderdetails>";


    req.param = "userId="+jsUtils.sessionUser().UID+"&btnAction=checkOut&xmlReq="+xmlString;

    /*var Quantity = jsUtils.Id("NumberOfItem-"+GameId)
   <?xml version="1.0" encoding="UTF-8"?>
<orderdetails xmlns="http://xml.netbeans.org/schema/orderdetails">
    <OrderDetail>
        <OrderId>1</OrderId>
        <GameId>1</GameId>
        <Price>24.9900</Price>
        <Quantity>4</Quantity>
    </OrderDetail>
     </orderdetails>*/
    
    var checkOut = function(){
        callAjax.checkoutCallback(callback);
    };
    ajaxFunction(req,checkOut);
    return true;

}


callAjax.checkoutCallback = function(callback){
    if(xmlhttp.status == 200){
        var resp = xmlhttp.response;
        if(resp == "checkout successful!"){
            console.log("checkOut OK!");
            if(callback != undefined){
                callback();
            }
            return true;
        }
    }else if (resp == "emtpy Cart or error."){
        console.log("else if");
        return false;
    }else{
        console.log("out of else if");
    }
   
}

callAjax.logOut = function(callback){
    var req = {};
    req.type = "POST";
    req.param = "btnAction=Logout";
    req.servlet = "UserController";
    var loginCallback = function(){
        callAjax.loginCallback(callback);
    };
    ajaxFunction(req,loginCallback);
}


callAjax.logoutCallback = function(){
    location.reload(false);
    return;
}


callAjax.login = function(username,password,callback){
    if(username!=null && password !=null){
        var req = {};
        req.type = "POST";
        req.param = "txtUsername=" + username.value+"&txtPassword=" +password.value+"&btnAction=Login";
        req.servlet = "UserController";
        var loginCallback = function(){
            callAjax.loginCallback(callback)
        };
        ajaxFunction(req,loginCallback);
    }
}



callAjax.loginCallback =  function(LoginCallBack) {
    console.log(xmlhttp.readyState);

    if (xmlhttp.readyState == 4) {
        console.log(xmlhttp.status);
        if(xmlhttp.status == 200) {
            var respContent = xmlhttp.response;
            
            if(respContent == "Login successful!"){
                LoginCallBack();
                location.reload(false);
            }else if(respContent =="wrong username or password. please try again"){
                jsUtils.id("loginStatus").innerText ="* "+respContent;
                jsUtils.removeClass(jsUtils.id("loginStatus"), "onHide");
                jsUtils.id("username").focus();

           
            }else if(respContent == "Login Admin successful!"){
                window.location.href = "http://localhost:8080/XMLProject/admin2.jsp";
            }else if(respContent =="logout successful!"){
                location.reload(false);
            }
            else{
                console.log("out of if");
            }

     
        }
        else {
            console.log("ERROR");
        }
    }
}

