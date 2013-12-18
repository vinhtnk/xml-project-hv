<%-- 
    Document   : admin
    Created on : Dec 17, 2013, 2:33:07 AM
    Author     : Stork
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<c:import var="productsXML" url="xml/products.xml" charEncoding="UTF-8"/>
<c:import var="adProductXsl" url="xsl/add_products.xsl" charEncoding="UTF-8"/>
<x:transform xml="${productsXML}" xslt="${adProductXsl}"/>
