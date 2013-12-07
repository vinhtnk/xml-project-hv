<%-- 
    Document   : testMarshallGame
    Created on : Jun 7, 2013, 1:43:41 AM
    Author     : Quaikiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<jsp:useBean class="Utils.MarshallerUtils" id="marshallerUtils" scope="request"/>
<jsp:useBean class="Utils.ConvertDate" id="ConvertDateUtils" scope="request"/>
<%
            String webPath = application.getRealPath("/");
            marshallerUtils.marshallingGames(webPath + "XML/games.xml");
            marshallerUtils.marshallingUsers(webPath + "XML/users.xml");
             marshallerUtils.marshallingOrders(webPath + "XML/orders.xml");


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
     



        <script type="text/javascript" language="text/javascript">
            function validateEmail()
            {
                //var regular_exp_email= registerForm.txtEmail.value;
                var regular_exp_email = /^(\w+[\-\.]*)+\w@(\w+\.)+\w+$/;
                var email = registerForm.txtEmail.value;
                if(!regular_exp_email.test(email))
                {
                    alert("Wrong email");
                    return false;
                }
                else {
                    alert("True email");
                    return true;

                }
              
            }
            function validatePassword()
            {
                var password = registerForm.txtPassword.value;
                var regular_exp_password = /^[A-Za-z\d]{6,12}$/;
                if(!regular_exp_password.test(password))
                {
                    alert("Password sai");
                    return false;
                }
                var rePassword = registerForm.txtRePassword.value;

                if(password==rePassword)
                {
                    alert("Trung password");
                    return true;
                }
                else
                {
                    alert("Not match password");
                    return false;
                }
               
            }
        
            var isExistedAccount= function()
            {
                var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
                xmlDom.async = false;
                xmlDom.load("XML/users.xml");
                if(xmlDom.parseError.errorCode!=0)
                {
                    alert("Error:" + xmlDoc.parseError.reason);
                }
                else {
                    
                    searchNode(xmlDom, registerForm.txtUsername.value, registerForm.txtEmail.value);
                }
            };

            function validate()
            {


            }

            function searchNode(node, username, email)
            {
                if(node==null)
                {
                    return false;
                }
                else
                {
                    if(node.nodeName=="UserName")
                    {
                        if(node.firstChild.nodeValue==username)
                            alert("Trung username");
                    }else
                        if(node.nodeName=="Email")
                    {
                        if(node.firstChild.nodeValue==email)
                            alert("Trung email");
                    }
                    

                    var childNodes = node.childNodes;
                    
                    for(var i = 0 ; i<childNodes.length; i++)
                    {
                        searchNode(childNodes[i], username, email);

                    }
                }
            }

        </script>
    </head>

    <body>

        <h1>MAMAMMAMAMA</h1>
        <form name="registerForm" action="GameController" method="POST" >

            <table border="1">
                <tbody>
                    <tr>
                        <td>User Name:</td>
                        <td><input type="text" name="txtUsername" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPassword" value="" /></td>
                    </tr>
                    <tr>
                        <td>Re-Type Password:</td>
                        <td><input type="password" name="txtRePassword" value="" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="txtEmail" /></td>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="txtFirstName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Middle Name:</td>
                        <td><input type="text" name="txtMiddleName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="txtLastName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Phone:</td>
                        <td><input type="text" name="txtPhone" value="" /></td>
                    </tr>
                    <tr>
                        <td>Role:</td>
                        <td><input type="text" name="txtRole" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Login" name="btnAction" onclick="isExistedAccount()"/>
                            <input type="reset" value="Reset" name="btnReset">
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <c:import var="gamesXML" url="/XML/games.xml" charEncoding="UTF-8"/>
        <c:import var="gamesXSL" url="/WEB-INF/games.xsl" charEncoding="UTF-8"/>
        <x:transform xml="${gamesXML}" xslt="${gamesXSL}"/>


    </body>
</html>

