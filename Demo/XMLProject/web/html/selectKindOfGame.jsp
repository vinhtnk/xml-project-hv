<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="Condition" value="${param.Condition}" scope="page" />
<c:set var="Val"value="${param.Val}" scope="page" />

<c:import var="selectGamesXML" url="/XML/games.xml"  charEncoding="UTF-8"/>
<x:parse var="doc" doc="${fn:trim(fn:replace(selectGamesXML, ' xmlns=\"http://xml.netbeans.org/schema/games\"',''))}" scope="page"/>

<c:choose>
    <c:when test="${Condition =='GenreId'}">
        <c:if test="${not empty Val}">
            <x:forEach var="root" select="$doc/games/Game[GenreId=$Val]">
                <div class="main-item">
                    <div id="itemNo-<x:out select='$root/GameId'/>" class="roundItem">
                        <img src="./img/games/<x:out select="$root/Image_URL"/>" onclick="jsUtils.showPopUp('itemNo-<x:out select='$root/GameId'/>')" title="<x:out select="$root/Pinboard"/>">
                        <div class="itemText">
                            <div class="gameName"><x:out select="$root/Name"/></div>
                            <div class="gamePrice">$<x:out select="$root/Price"/></div>
                            <div class="GameDesc onHide"><x:out select="$root/Description"/></div>
                        </div>
                    </div>
                    <a class="btnAddCart" href="#" onclick="jsUtils.addToCart('itemNo-<x:out select='$root/GameId'/>');return false;">
                        <img src="./img/button/addtocart.jpg" />
                    </a>

            </div>
        </x:forEach>
    </c:if>
</c:when>


<c:when test="${Condition =='Search'}">
    <x:set var="xPathCon" select="$doc/games/Game[Name=$Val]"/>
</c:when>

</c:choose>
