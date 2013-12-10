<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<form class="pure-skin-xml">
    <div class="pure-menu pure-menu-open">
        <a class="pure-menu-heading">Danh mục sản phẩm</a>
        <ul id="leftPanel">
            <c:import var="categoryXML" url="/XML/categories.xml" charEncoding="UTF-8"/>
            <c:import var="categoryXSL" url="/WEB-INF/categories.xsl" charEncoding="UTF-8"/>
            <x:transform xml="${categoryXML}" xslt="${categoryXSL}"/>

        </ul>
    </div>
</form>
