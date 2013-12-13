<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<style type="text/css">
    .menuHeading
    {
        font-size: large;
        font-weight: bold;
        border-bottom: 1px solid #5e5e5e;

    }
</style>
<form class="pure-skin-xml">
    <div class="pure-menu pure-menu-open">
        <span class="menuHeading">Danh mục sản phẩm</span>
        <ul id="leftPanel" class="leftPanel">
            <c:import var="categoryXML" url="xml/categories.xml" charEncoding="UTF-8"/>
            <c:import var="categoryXSL" url="xsl/categories.xsl" charEncoding="UTF-8"/>

            <x:transform xml="${categoryXML}" xslt="${categoryXSL}"/>

        </ul>
    </div>
</form>
