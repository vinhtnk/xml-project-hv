<%-- 
    Document   : index
    Created on : Jun 5, 2013, 4:19:38 PM
    Author     : burningk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/pure-skin-xml.css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Welcome Page</title>
    </head>
    <body>
        <form action="GameController" method="POST">
            <
            <!--            <button name="btnAction" value="btnAction">Login</button>-->
            <table border="1">
                <tbody>
                    <tr>
                        <td>User name:</td>
                        <td><input type="text" name="txtUsername" value="" /></td>

                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPassword" value="" /></td>

                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Login" name="btnAction" />
                            <input type="reset" value="Reset" name="btnReset">
                        </td>

                    </tr>

                </tbody>
            </table>
        </form>
        <div id="header">
            <div id="hBanner">
                <img align="center" src="img/banner.jpg"/>
            </div>
        </div>
        <div id="mbody">
            <div id="b-left-panel" class="mainBody" >
                <ul id="panelUl" class="panelUl">
                    <li class="ulItems">test LI</li>
                    <li class="ulItems">test LI</li>
                    <li class="ulItems">test LI</li>
                    <li class="ulItems">test LI</li>

                </ul>
            </div>
            <div id="b-center-panel" class="mainBody">
                <div id="hot-line-roll">
                    <marquee>Your slide-in text goes here</marquee>
                </div>
                <div id="listItem">
                    <div class="main-item"></div>
                    <div class="main-item"></div>
                    <div class="main-item"></div>

                    <div class="main-item"></div>
                    <div class="main-item"></div>
                    <div class="main-item"></div>

                    <div class="main-item"></div>
                    <div class="main-item"></div>
                    <div class="main-item"></div>

                    <div class="main-item"></div>
                    <div class="main-item"></div>
                    <div class="main-item"></div>
                </div>
            </div>
            <div id="b-right-panel" class="mainBody">
                <div class="main-item"></div>
                <div class="main-item"></div>
                <div class="main-item"></div>

            </div>
        </div>
        <div id="footer">

        </div>

    </body>
</html>

