<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<div id="loginForm" class="onHide">
    <form method="POST" class="pure-form">
        <table align="center">
            <tr>
                <td>Email: </td>
                <td><input id="email" type="text" name="txtEmail" /></td>
            </tr>
            <tr>
                <td>Mật khẩu: </td>
                <td><input id="password" type="password" name="txtPassword" /></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit" onclick="login(email,password,function(){location.reload(false);});return false;">Đăng nhập</button>
                    <div id="loginStatus" class="onHide"></div>
                </td>
            </tr>
        </table>
    </form>
</div>