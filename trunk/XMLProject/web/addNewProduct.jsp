<%-- 
    Document   : addNewProduct
    Created on : Dec 18, 2013, 6:24:32 PM
    Author     : Stork
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page session="true" %>

<jsp:useBean class="Utils.JAXBMarshalling" id="marshallerUtils" scope="request"/>

<%
            String webpath = application.getRealPath("/");
            marshallerUtils.marshallingProduct(webpath + "xml/products.xml");
%>

<link href="css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="js/adUtil.js"></script>
<script type="text/javascript" src="js/callAjax.js"></script>
<script type="text/javascript" src="js/jsUtils.js"></script>
<script type="text/javascript" src="js/pageTransfer.js"></script>
<script type="text/javascript" src="js/validateUtils.js"></script>

<script type="text/javascript" language="text/javascript">

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
    <body>
        <div id="header">
            <jsp:include page="jsp/headerAd.jsp"/>
        </div>
        <div align="center">
            <form action="" method="post" >
                <table>
                    <tr>
                        <td>Product ID</td>
                        <td><input id="productid" type="text" name="txtProID" /> </td>
                    </tr>
                    <tr>
                        <td>Product Name</td>
                        <td><input id="productname" type="text" name="txtProName" /></td>
                    </tr>
                    <tr>
                        <td>Category ID</td>
                        <td><input id="category" type="text" name="txtCateID" /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input id="price" type="text" name="txtPrice" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input id="description" type="text" name="txtDes" /></td>
                    </tr>
                    <tr>
                        <td>Image_Link</td>
                        <td><input id="imagelink" type="text" name="txtImg" /></td>
                    </tr>
                    <tr>
                        <td>New_Product</td>
                        <td><input id="newproduct" type="text" name="txtNewPro" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" onclick="addProduct(productid,productname,category,price,description,imagelink,newproduct);addProductXML(productid,productname,category,price,description,imagelink,newproduct)"/></td>
                    </tr>
                </table>
                
            </form>
        </div>
        <div id="footer">
            <jsp:include page="jsp/footer.jsp"/>
        </div>
    </body>
</html>
