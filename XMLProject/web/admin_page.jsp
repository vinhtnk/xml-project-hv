<%-- 
    Document   : admin_page
    Created on : Dec 16, 2013, 2:21:37 PM
    Author     : Stork
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
<script type="text/javascript" src="js/adUtil.js"></script>
<script type="text/javascript" src="js/callAjax.js"></script>
<script type="text/javascript" src="js/jsUtils.js"></script>


<script type="text/javascript" language="text/javascript">

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
    <body>
        <div id="header">
            <jsp:include page="jsp/header.jsp"/>
        </div>
        <div>
            <form action="AdminController" method="Post">
                <table class="body5" >
                    <tr>
                      
                        <td valign="top" class="borderMainMenu" width="600px">
                            <jsp:include page="jsp/admin.jsp" flush="true"/>
                            <br/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="footer">
            <jsp:include page="jsp/footer.jsp"/>
        </div>
    </body>
</html>
