<%-- 
    Document   : createXML
    Created on : Jun 12, 2013, 2:33:47 PM
    Author     : burningk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd"><%-- 
    Document   : index
    Created on : Jun 5, 2013, 4:19:38 PM
    Author     : burningk
--%>

<%@page import="javax.jms.Session"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ page session="true" %>

<jsp:useBean class="Utils.MarshallerUtils" id="marshallerUtils" scope="request"/>
<%
            String webPath = application.getRealPath("/");
            marshallerUtils.marshallingGames(webPath + "XML/games.xml");
            marshallerUtils.marshallingUsers(webPath + "XML/users.xml");
            marshallerUtils.marshallingGenres(webPath + "XML/genres.xml");


%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
