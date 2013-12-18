<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<div id="regForm" class="onHide" >
    <form class="pure-form" method="POST">
        <table align="center">
            <tr >
                <td align="right">Họ tên (*): </td>
                <td ><input id="username" type="text" name="txtUsername" /></td>
                <td align="right">Giới tính :</td>
                <td ><select name="txtgender" id="gender">
                        <option>Nam</option>
                        <option>Nữ</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">Địa chỉ (*): </td>
                <td><input id="address" type="text" name="txtAddress" /></td>
                <td align="right">Điện thoại (*): </td>
                <td><input id="phone" type="text" name="txtPhone" /></td>
            </tr>
            <tr>
                <td align="right">Email (*): </td>
                <td><input id="email" type="text" name="txtEmail" placeholder="someone@example.com"/></td>
                <td align="right">Mật khẩu (*): </td>
                <td><input id="password" type="password" name="txtPassword" /></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit" onclick="regAcc(username,gender,address,phone,email,password,
                        function(){location.reload(false);});return false;">Đăng ký</button>
                    
                </td>
            </tr>
        </table>
        <div id="regStatus" class="onHide" align="center"></div>
    </form>
</div>