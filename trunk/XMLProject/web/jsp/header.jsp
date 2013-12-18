<%-- 
    Document   : header
    Created on : Dec 7, 2013, 12:27:51 AM
    Author     : Hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
    .style1
    {
        color: #CCCCCC;
        font-size: small;
        width: 100%;
        background: url(Image/bg_grey.jpg) 0 0 repeat;

    }
    .style2{
        width: 150px;

    }
    .style27
    {
        width: 250px;
        height: 117px;
    }
    .style28
    {
        width: 500px;
        height: 150px;
    }
    .style18
    {
        color: #CCCCCC;
    }
    .styleTextSearch
    {
        color: #CCCCCC;
        font-size: small;
        background: url(Image/bg_grey.jpg) 0 0 repeat;
    }
    .textboxSearch
    {
        /*        color: #CCCCCC;*/
        /*        background-color: #E1ECF7;*/
        border-color: #333333;
        border-style: Double;
        height: 16px;
    }
</style>
<div id="navBar">
    <form class="pure-form" action="UserController" method="GET">
        <table class="style1">
            <tr>
                <td width="30%" rowspan="2" align="center">
                    <a href="index.jsp" />
                    <img alt="" class="style27" src="./Image/logo.png" />
                </td>
                <td align="center" width="40%">

                    <img alt="" class="style28" src="./Image/banner.png" name="slide" />
                    <script type="text/javascript">
                        var image1 = new Image();
                        image1.src = "./Image/banner.png";
                        var image2 = new Image();
                        image2.src = "./Image/Gioithieu.jpg";
                        var image3 = new Image();
                        image3.src = "./Image/ssip.jpg";
                        var image4 = new Image();
                        image4.src = "./Image/nokia.jpg";
                        var image5 = new Image();
                        image5.src = "./Image/lumia1520.jpg";
                        var image6 = new Image();
                        image6.src = "./Image/one.jpg";
                        var image7 = new Image();
                        image7.src = "./Image/htc.jpg";
                        var image8 = new Image();
                        image8.src = "./Image/slide2.jpg";

                        var step=1;
                        function slideit(){
                            document.images.slide.src = eval("image"+step+".src");
                            
                            if (step<8) step++;
                            else step=1;
                            setTimeout("slideit()", 3000);
                        }
                        slideit();
                    </script>
                </td>
                <td align="right" width="25%" style="padding-bottom: 100px;">
                    <ul id="top-ul">
                        <div id="navBarGuess">
                            <!--                            <div id="loginForm">-->
                            <!--                                <form class="pure-form"  method="POST">
                                                                Email <input id="email" type="text" name="txtEmail" class="style2"/><br/><br/>
                                                                Password <input id="password" type="password" name="txtPassword" class="style2"/><br/><br/>
                                                                <button type="submit" name="action" value="Login" class="pure-button notice">Đăng nhập</button>
                                                                <div id="loginStatus" class="onHide"></div>
                                                                <button type="submit">Đăng ký</button>
                                                            </form>-->

                            <img id="Gitem0" src="./Image/login.png" onclick="loginReg('loginForm')" />
                            <img id="Gitem1" src="./Image/reg.png" onclick="loginReg('regForm')" />

                            <%-- onclick="callAjax.login(email,password,function(){location.reload(false);});return false;" --%>
                        </div>


                        <div id="navBarUser">
                            <span id="Uitem0" class="onHide">${EMAIL}</span>

                            <a class="onHide" id="Uitem2"  href="#" onclick="callAjax.logOut();return false;" > Thoát</a>
                        </div>
                    </ul>
                </td>
            </tr>

        </table>
    </form>
    <div id="menu" class="style1">
        <ul id="menu-content" class="menu-content">
            <li><a href="index.jsp">Trang chủ</a></li>
            <li><a href="#">Giới thiệu</a></li>
            <li><a href="#">Liên hệ</a></li>


        </ul>
    </div>
    <div class="style1">
        <br/>
        <br/>
        <br/>
        <table >
            <tr>
                <td width="45%" />
                <td width="55%">
                    <form id="formSearch">
                        <span class="styleTextSearch">Tìm kiếm tên sản phẩm</span>
                        <input name="txtSearchBox" class="textboxSearch" type ="text" id="txtSearch"/>

                        <img ID="btnSearch" src="./Image/search.png" OnClick="btnSearch_Click();"
                             Style="vertical-align: bottom; cursor: pointer"/>

                    </form>

                </td>
                <td>
                    <table height="20" cellpadding="0" cellspacing="0" border="0" width="300px">
                        <tr>
                            <td align="left" width="5">
                                <img src="Image/cart1.png" />
                            </td>
                            <td align="center" width="40%" class="styleTextCart">
                                <a href="showCart.jsp" class="styleTextCart">GIỎ HÀNG</a>
                            </td>
                            <td align="left" width="5">
                                <img src="Image/Pay1.png" />
                            </td>
                            <td width="40%" align="center" class="styleTextCart">
                                <a href="showCart.jsp" style="font-weight: bold; text-decoration: none; color: #CCCCCC;
                                   font-size: small">
                                    <span id="totalPrice">
                                    </span>
                                </a>
                            </td>

                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>



</div>
