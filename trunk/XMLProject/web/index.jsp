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
<script type="text/javascript" src="js/callAjax.js"/>
<script type="text/javascript" src="js/jsUtils.js"/>
<script type="text/javascript" src="js/pageTransfer.js"/>
<script type="text/javascript" src="js/validateUtils.js"/>

<script type="text/javascript" language="text/javascript">
      



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
    .borderMainMenu{
        border-left: 1px solid #114646;

    }
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>H2 Store</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="jsp/header.jsp"/>
        </div>
        <div>
            <table class="body5">
                <tr>
                    <td align="left" valign="top" width="180px">
                        <div id="b-left-panel" class="mainBody" align="center">
                            <jsp:include page="jsp/leftmenu.jsp"/>
                        </div>
                    </td>
                    <td valign="top" class="borderMainMenu">
                        <div id="listItem">
                            <jsp:include page="jsp/listProduct.jsp" flush="true" />

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
