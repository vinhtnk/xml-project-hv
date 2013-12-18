var callAjax = function(){

}



callAjax.checkoutCallback = function(callback){
    if (xmlhttp.readyState == 4) {
        if(xmlhttp.status == 200){
            var resp = xmlhttp.response;
            if(resp == "checkout successful!"){
                document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
                window.location.href="checkOutSuccess.jsp";
            }
        }
        else{
            console.log("error");
            alert("Có lỗi xảy ra, xin mời bạn thử lại!");
        }
    } else {
        console.log("error");
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

callAjax.loginCallback =  function(LoginCallBack) {
    console.log(xmlhttp.readyState);

    if (xmlhttp.readyState == 4) {
        console.log(xmlhttp.status);
        if(xmlhttp.status == 200) {
            var respContent = xmlhttp.response;
            
            if(respContent == "Login successful!"){
                LoginCallBack();
                //checkType("abc");
                location.reload(false);
            }else if(respContent =="Email or password invalid. please try again"){
                id("loginStatus").innerText ="* Email hoặc mật khẩu không đúng. Xin hãy thử lại";
                removeClass(id("loginStatus"), "onHide");
                id("username").focus();

           
            }else if(respContent == "Login Admin successful!"){
                window.location.href = "admin_page.jsp";
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

callAjax.regCallback = function(email, password){
    if (xmlhttp.readyState == 4) {
        console.log(xmlhttp.status);
        if(xmlhttp.status == 200) {
            var respContent = xmlhttp.response;

            if(respContent == "Register successful!"){
                login(email, password);
                window.location.href = "regAccSuccess.jsp";
            } else if (respContent == "Email existed!"){
                id("regStatus").innerText ="* Email này đã sử dụng";
                removeClass(id("regStatus"), "onHide");
            }
            else{
                console.log("ERROR");
            }
        }
        else {
            console.log("ERROR");
        }
    }
}

callAjax.deleteProductCallBack = function(callback){
    console.log(xmlhttp.readyState);

    if (xmlhttp.readyState == 4) {
        console.log(xmlhttp.status);
        if(xmlhttp.status == 200) {
            var respContent = xmlhttp.response;

            if(respContent == "delete success"){
                //
                window.location = "admin_page.jsp";
                //window.location = "admin_page.jsp";
                //window.location = "admin_page.jsp";
                //window.location = "admin_page.jsp";
                //location.reload();
               // alert("ok");
                //return false;
            }
            else{
                //alert("delete fail");
                console.log("delete fail");
            }
        }
        else {
           // alert("delete fail");
            console.log("ERROR");
        }
    }
}