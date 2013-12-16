<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/style.css" rel="stylesheet"/>


<script type="text/javascript" src="js/jsUtils.js"></script>


<script type="text/javascript" language="text/javascript">
    sessionStorage.cart = null;
    sessionStorage.totalPrice = null;

</script>

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
                        <div align="center">
                            <h3>Đặt hàng thành công</h3>
                            <br/> <a href="index.jsp">Quay lại trang chủ</a>
                        </div>
                        <br/>
                        
                    </td>

                </tr>

            </table>


        </div>
        <div id="footer">
            <jsp:include page="jsp/footer.jsp"/>
        </div>
    </body>
</html>
