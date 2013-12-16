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
<link href="css/style.css" rel="stylesheet"/>

<script type="text/javascript" src="js/callAjax.js"></script>
<script type="text/javascript" src="js/jsUtils.js"></script>
<script type="text/javascript" src="js/pageTransfer.js"></script>
<script type="text/javascript" src="js/validateUtils.js"></script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>H2 Store</title>
    </head>
    <body onload="start()">
        <div id="header">
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
                            <c:set var="Condition" value="${param.Condition}" scope="page" />
                            <c:set var="Val" value="${param.Val}" scope="page" />

                            <c:import var="selectProductXML" url="/xml/products.xml"  charEncoding="UTF-8"/>
                            <x:parse var="doc" doc="${selectProductXML}" scope="page"/>

                            <c:choose>
                                <c:when test="${Condition =='Search'}">
                                    <c:if test="${not empty Val}">
                                        <x:forEach var="root"
                                                   select="$doc//products/product[contains(translate(productName, 'ABCDEFGHJIKLMNOPQRSTUVWXYZ', 'abcdefghjiklmnopqrstuvwxyz'), translate($Val, 'ABCDEFGHJIKLMNOPQRSTUVWXYZ', 'abcdefghjiklmnopqrstuvwxyz'))]">
                                            <div class="main-item">
                                                <ol>
                                                    <li>
                                                        <table class="tableItem">
                                                            <tr >
                                                                <td rowspan="5" width="200px">
                                                                    <div>
                                                                        <div id="itemNo-<x:out select='$root/productId'/>" >
                                                                            <img class="imgItem" src="./Image/<x:out select="$root/img_link"/>" onclick="jsUtils.showPopUp('itemNo-<x:out select='$root/productId'/>')" title="<x:out select="$root/productName"/>">

                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>

                                                                <td>
                                                                    <div class="itemText">
                                                                        <div class="productName"><x:out select="$root/productName"/></div>
                                                                    </div>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <div class="price">
                                                                        <script type="text/javascript">
                                                                            var priceOfProduct = <x:out select="$root/price"/>;
                                                                            document.write(Number(priceOfProduct).formatMoney(0));
                                                                        </script>
                                                                        VND</div>

                                                                </td>
                                                            </tr>



                                                            <tr>
                                                                <td>
                                                                    <div>
                                                                        <a  href="#" onclick="addToCartSes('<x:out select='$root/productID'/>', '<x:out select='$root/productName'/>', '<x:out select='$root/price'/>')">
                                                                            <img class="imgCart" src="./Image/btn_buy.gif" style="min-height: 20px;">
                                                                        </a>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                        <div class="ProductDesc onHide">
                                                            <x:out select="$root/description"/></div>

                                                    </li>
                                                </ol>
                                            </div>
                                        </x:forEach>
                                    </c:if>
                                </c:when>


                                

                            </c:choose>
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
