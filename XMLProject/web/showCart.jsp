<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/style.css" rel="stylesheet"/>


<script type="text/javascript" src="js/jsUtils.js"></script>


<script type="text/javascript" language="text/javascript">

    function start(tableID){
        if(typeof(sessionStorage) != "undefined"){
            if(sessionStorage.cart == null){
                document.getElementById('totalPrice').innerHTML = 0;
            }else{
                document.getElementById('totalPrice').innerHTML = Number(sessionStorage.totalPrice).formatMoney(0);
            }
        } else{
            alert("browser is not support storage!!!");

        }
        var items = eval(sessionStorage.cart);
        
        if(items.length == 0 || sessionStorage.cart == null){
            document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
            document.getElementById('itemCart').deleteRow(1);
        }
        if(sessionStorage.totalPrice == 0){
            document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
        }
        showCart(items, tableID);
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
                        <table id="itemCart" border="1" align="center">
                            <tr bgcolor="#9acd32" align="center" style="font-weight: bold">
                                <td>
                                    No.
                                </td>
                                <td>
                                    Mã sản phẩm
                                </td>
                                <td>
                                    Tên sản phẩm
                                </td>
                                <td>
                                    Số lượng
                                </td>
                                <td>
                                    Đơn giá
                                </td>
                                <td>
                                    Tổng cộng
                                </td>
                                <td>
                                    Xóa sản phẩm
                                </td>
                            </tr>

                        </table>
                        <br/>
                        <div align="center">
                            <form>
                                <input type="submit" id="btnCheckOut" value="Đặt hàng" name="btnCheckOut" onclick="checkout();"/>
                            </form>
                            
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