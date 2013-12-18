<%-- 
    Document   : index
    Created on : Dec 2, 2013, 10:35:48 PM
    Author     : Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page session="true" %>

<jsp:useBean class="Utils.JAXBMarshalling" id="marshallerUtils" scope="request"/>

<%
            String webpath = application.getRealPath("/");
            marshallerUtils.marshallingCategories(webpath + "xml/categories.xml");
            marshallerUtils.marshallingProduct(webpath + "xml/products.xml");
%>

<link href="css/style.css" rel="stylesheet"/>

<script type="text/javascript" src="js/callAjax.js"></script>
<script type="text/javascript" src="js/jsUtils.js"></script>
<script type="text/javascript" src="js/pageTransfer.js"></script>
<!--<script type="text/javascript" src="js/validateUtils.js"></script>-->

<script type="text/javascript" language="text/javascript">
    
    function start(){

        var info = {};
        info.ssEmail = "${EMAIL}";
        info.ssUsername = "${USER}";

        sessionUser(info);
        if(info.ssEmail!="" || info.ssEmail!=null){
            checkType(info.ssEmail);
        }
    
        if(typeof(sessionStorage) != "undefined"){
            if(sessionStorage.cart == null){
                document.getElementById('totalPrice').innerHTML = 0;
            }else{
                document.getElementById('totalPrice').innerHTML = Number(sessionStorage.totalPrice).formatMoney(0);
            }
        } else{
            alert("Your browser is not support storage.");

        }
    }
    

    var logout = function(){
        sessionStorage.removeItem("EMAIL");
        sessionStorage.removeItem("USERNAME");
    };
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>H2 Store</title>
    </head>
    <body onload="start()">
        <div id="header">
            <jsp:include page="jsp/login.jsp" flush="true"/>
            <jsp:include page="jsp/regAccount.jsp" flush="true"/>
            <jsp:include page="jsp/header.jsp"/>
        </div>
        <div>
            <table class="body5">
                <tr>
                    <td align="left" valign="top" width="200px">
                        <div id="b-left-panel" class="mainBody" align="center">
                            <jsp:include page="jsp/leftmenu.jsp"/>

                        </div>
                    </td>
                    <td valign="top" class="borderMainMenu">
                        <div id="listItem">                            
                            <jsp:include page="jsp/listProduct.jsp" flush="true"/>
                        </div>
                    </td>
                </tr>

            </table>


        </div>
        <div id="footer">
            <jsp:include page="jsp/footer.jsp"/>
        </div>
    </body>
</html>
