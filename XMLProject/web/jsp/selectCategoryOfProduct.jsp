<%-- 
    Document   : selectCategoryOfProduct
    Created on : Dec 11, 2013, 3:42:13 PM
    Author     : Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="Condition" value="${param.Condition}" scope="page" />
<c:set var="Val" value="${param.Val}" scope="page" />

<c:import var="selectProductXML" url="/XML/products.xml"  charEncoding="UTF-8"/>
<x:parse var="doc" doc="${fn:trim(fn:replace(selectProductsXML, ' xmlns=\"http://xml.netbeans.org/schema/products\"',''))}" scope="page"/>

<c:choose>
    <c:when test="${Condition =='categoryID'}">
        <c:if test="${not empty Val}">
            <x:forEach var="root" select="$doc/products/product[categoryID=$Val]">
                <div class="main-item">
                    <div id="itemNo-<x:out select='$root/productId'/>" class="roundItem">
                        <img src="./Image/<x:out select="$root/img_link"/>" onclick="jsUtils.showPopUp('itemNo-<x:out select='$root/productId'/>')" title="<x:out select="$root/productName"/>">
                        <div class="itemText">
                            <div class="gamePrice">$<x:out select="$root/price"/></div>
                            <div class="GameDesc onHide"><x:out select="$root/description"/></div>
                        </div>
                    </div>
                    <a class="btnAddCart" href="#" onclick="jsUtils.addToCart('itemNo-<x:out select='$root/productId'/>');return false;">
                        <img src="./Image/cart1.png" />
                    </a>

            </div>
        </x:forEach>
    </c:if>
</c:when>


<c:when test="${Condition =='Search'}">
    <x:set var="xPathCon" select="$doc/products/product[productName=$Val]"/>
</c:when>

</c:choose>