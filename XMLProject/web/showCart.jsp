<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/style.css" rel="stylesheet"/>


<script type="text/javascript" src="js/jsUtils.js"></script>
<script type="text/javascript" src="js/callAjax.js"></script>

<script type="text/javascript" language="text/javascript">

    function start(tableID){
        var info = {};
        info.ssEmail = "${EMAIL}";
        info.ssUsername = "${USER}";
        info.UID = "${UID}";

        sessionUser(info);
        if(info.ssEmail!="" || info.ssEmail!=null){
            checkType(info.ssEmail);
        }
        if(typeof(sessionStorage) != "undefined"){
            if(sessionStorage.cart == null){
                document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
                document.getElementById('totalPrice').innerHTML = 0;                
            }else{
                document.getElementById('totalPrice').innerHTML = Number(sessionStorage.totalPrice).formatMoney(0);
            }
        } else{
            alert("browser is not support storage!!!");

        }
        var items = eval(sessionStorage.cart);
        showCart(items, tableID);
        if(items.length == 0){
            document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
            document.getElementById('itemCart').deleteRow(1);
        }
        
        
    }


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
    <body onload="start('itemCart')">
        <div id="header">
            <jsp:include page="jsp/login.jsp" flush="true"/>
            <jsp:include page="jsp/regAccount.jsp" flush="true"/>
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
                        <div class="categoryName" > Giỏ hàng của bạn
                        </div><br/>
                        <table id="itemCart" border="1" align="center">
                            <tr bgcolor="#9acd32" align="center" style="font-weight: bold">
                                <th>
                                    No.
                                </th>
                                <th>
                                    Mã sản phẩm
                                </th>
                                <th>
                                    Tên sản phẩm
                                </th>
                                <th>
                                    Số lượng
                                </th>
                                <th>
                                    Đơn giá
                                </th>
                                <th>
                                    Tổng cộng
                                </th>
                                <th>
                                    Xóa sản phẩm
                                </th>
                            </tr>

                        </table>
                        <br/>
                        <div align="center">
                            <input type="submit" id="btnCheckOut" value="Đặt hàng" name="btnCheckOut" onclick="checkout('${EMAIL}');"/>
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
