<%-- 
    Document   : index
    Created on : Dec 2, 2013, 10:35:48 PM
    Author     : Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link href="css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="js/callAjax.js"/>
<script type="text/javascript" src="js/jsUtils.js"/>
<script type="text/javascript" src="js/pageTransfer.js"/>
<script type="text/javascript" src="js/validateUtils.js"/>

<script type="text/javascript" language="text/javascript">
      
    window.onload = function(){
       


        var info = {};
        info.Username = "${USERNAME}";
        info.Email = "${EMAIL}";
        info.UID = "${UID}";

        jsUtils.sessionUser(info);
        //jsUtils.setDefaultPageId();

       
        if(info.Username!="" && info.Username!=null){
            jsUtils.checkType(info.Username);
        }

        
        onLoadListener();
       
    };


    var logout = function(){
        sessionStorage.removeItem("EMAIL");
        sessionStorage.removeItem("USERNAME");
    };
</script>
<style type="text/css" >
    .body5
    {
        width: 100%;
        background: url(Image/bg_main.jpg) 0 0 repeat;

    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="header.jsp"/>
        </div>
        <div>
            <table class="body5">
                <tr>
                    <td align="left" valign="top" >
                        <div id="b-left-panel" class="mainBody" >
                            <jsp:include page="leftmenu.jsp" />
                        </div>
                    </td>
                    <td valign="top">
                        <div id="b-center-panel" >
                            <h1>Hello World!a â cầ</h1>
                        </div>
                    </td>
                </tr>

            </table>


        </div>
        <div id="footer">
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>
