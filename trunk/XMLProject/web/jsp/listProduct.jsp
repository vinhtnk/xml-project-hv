<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<c:import var="productsXML" url="xml/products.xml" charEncoding="UTF-8"/>
<c:import var="productsXSL" url="xsl/products.xsl" charEncoding="UTF-8"/>
<x:transform xml="${productsXML}" xslt="${productsXSL}"/>

