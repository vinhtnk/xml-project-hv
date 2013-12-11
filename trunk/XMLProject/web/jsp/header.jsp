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
        width: 290px;
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
        color: #CCCCCC;
        background-color: #E1ECF7;
        border-color: #333333;
        border-style: Double;
        height: 16px;
    }
</style>
<div id="navBar">
    <form class="pure-form" method="GET">
        <table class="style1">
            <tr>
                <td width="30%" rowspan="2" align="center">
                    <a href="index.jsp" />
                    <img alt="" class="style27" src="./Image/logo.png" />
                </td>
                <td align="center" width="40%">

                    <img alt="" class="style28" src="./Image/banner.png" />
                </td>
                <td align="right">
                    <ul id="top-ul">
                        <div id="navBarGuess">
                            <div id="loginForm">
                                <form class="pure-form"  method="POST">

                                    Email <input id="email" type="text" name="txtEmail" class="style2"/><br/><br/>
                                    Password <input id="password" type="password" name="txtPassword" class="style2"/><br/><br/>
                                    <button type="submit" onclick="callAjax.login(email,password,function(){location.reload(false);});return false;" class="pure-button notice">Đăng nhập</button>
                                    <div id="loginStatus" class="onHide"></div>
                                    <button type="submit">Đăng ký</button>
                                </form
                                
                            </div>
                            
                        </div>
                        <div id="navBarUser">
                            <li class="onHide" ><a id="Uitem0" href="#">${USERNAME}</a></li>

                            <li class="onHide"><a type="submit" id="Uitem2"  href="#" onclick="callAjax.logOut();return false;" >Logout</a></li>
                        </div>
                    </ul>
                </td>
            </tr>

        </table>

        <div id="menu" class="style1">
            <ul id="menu-content" class="menu-content">
                <li><a href="#">Trang chủ</a></li>
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

                        <span class="styleTextSearch">Tìm kiếm tên sản phẩm</span>
                        <input ID="txtSearch" class="textboxSearch" />
                        <img ID="btnSearch" src="./Image/search.png" OnClick="btnSearch_Click"
                             Style="vertical-align: inherit"/>


                    </td>
                    <td>
                        <table height="20" cellpadding="0" cellspacing="0" border="0" width="300px">
                            <tr>
                                <td align="left" width="5">
                                    <img src="Image/cart1.png" />
                                </td>
                                <td align="center" width="40%" class="styleTextCart">
                                    <a href="CheckOut.aspx" class="styleTextCart">GIỎ HÀNG</a>
                                </td>
                                <td align="left" width="5">
                                    <img src="Image/Cost1.png" />
                                </td>
                                <td width="40%" align="center" class="styleTextCart">
                                    <a href="CheckOut.aspx" style="font-weight: bold; text-decoration: none; color: #CCCCCC;
                                       font-size: small">
                                        <label runat="server" ID="label1" Text=""/>
                                    </a>
                                </td>
                                <td align="left" width="5">
                                    <img src="Image/Pay1.png" />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>    

    </form>

</div>
