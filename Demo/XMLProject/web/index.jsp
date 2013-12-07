<%-- 
    Document   : index
    Created on : Jun 5, 2013, 4:19:38 PM
    Author     : burningk
--%>

<%@page import="javax.jms.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ page session="true" %>

<jsp:useBean class="Utils.MarshallerUtils" id="marshallerUtils" scope="request"/>
<%
            String webPath = application.getRealPath("/");
            marshallerUtils.marshallingGames(webPath + "XML/games.xml");
            marshallerUtils.marshallingUsers(webPath + "XML/users.xml");
            marshallerUtils.marshallingGenres(webPath + "XML/genres.xml");
            marshallerUtils.marshallingOrderDetails(webPath + "XML/orderDetails.xml");


%>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/pure-skin-xml.css">
<link rel="stylesheet" href="css/pure-min.css">
<script type="text/javascript" src="js/jsUtils.js"></script>
<script type="text/javascript" src="js/pageTransfer.js"></script>
<script type="text/javascript" src="js/callAjax.js"></script>
<script type="text/javascript" src="js/validateUtils.js"></script>

<script type="text/javascript"  language="text/javascript">

    var realPath = '${pageContext.request.contextPath}';
    var urlXmlUsers = realPath + "/XML/users.xml";
    var xmlUsersDom = loadXML(urlXmlUsers);
    function loadXML(filePath)
    {

        xmlHttp=getXmlHTTP();
        if(xmlHttp==null)
        {
            alert("Your browser is not support AJAX");
            return;
        }
        else
        {
            xmlHttp.open("GET", filePath, false);
            xmlHttp.send(null);
            return xmlHttp.responseXML;
        }
    }
    window.onload = function(){
       


        var info = {};
        info.ssFullname = "${FULLNAME}";
        info.ssUsername = "${USER}";
        info.UID = "${UID}";

        jsUtils.sessionUser(info);
        jsUtils.setDefaultPageId();

       
        if(info.ssFullname!="" && info.ssFullname!=null){
            jsUtils.checkType(info.ssFullname);
        }

        jsUtils.currentPageId();
        jsUtils.paging("listItem",6);
        onLoadListener();
       
    };


    window.onbeforeunload = WindowCloseHanlder;
    function WindowCloseHanlder()
    {
        // alert('My Window is reloading');
    }


    var checkoutConfirm = function(e){
        var conf = confirm("Are you sure to checkout?");
        if(conf){
            callAjax.checkout();
        }else{
            e.preventDefault();
            return false;
        }
    }

    var hidePopUp =function(className){
        var ele = document.getElementById(className);
        jsUtils.addClass(ele, "onHide");
    }

    var logout = function(){
        sessionStorage.removeItem("USER");
        sessionStorage.removeItem("FullName");
    };

    var showHide = function(ThisPage){
        jsUtils.showHidePage(ThisPage);
    };
   
    var showNextPage = function(nextPage){
        jsUtils.showNewPage(nextPage);
    };
    

    var loginReg = function(className){
        var login = "loginForm";
        var reg = "regForm";
        if(className == reg){
            var ShouldHide = login;
        }
        else if(className == login){
            var ShouldHide = reg;
        }
        var elem = document.getElementById(ShouldHide);
        showHide(className);
        if(!jsUtils.hasClass(elem, "onHide")){
            showHide(ShouldHide);
        }
    
    };

    function checkExistElementByName(value, nameELement)
    {
        alert(xmlUsersDom + " " + value + " "+nameELement);

        searchElementNodeByName(xmlUsersDom, value, nameELement);
    }
    function searchElementNodeByName(node, value, nameElement)
    {
        if(node.nodeName==nameElement)
        {
            if(node.firstChild.nodeValue==value)
                alert("Trung "+ nameElement);
        }else
        {
            var childNodes = node.childNodes;

            for(var i = 0 ; i<childNodes.length; i++)
            {
                searchElementNodeByName(childNodes[i], value, nameElement);

            }
        }
        
    }

    function getXmlHTTP()
    {
        var xmlHttp = null;
        if (window.XMLHttpRequest)
        {
            xmlHttp=new XMLHttpRequest();
        }
        else
        {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlHttp;
    }


</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Welcome Page</title>
    </head>
    <body>

        <div id="header" class="hbf">
            <!-- include navBar --->
            <jsp:include page="html/navBar.html" flush="true"/>
            <!-- end include navBar --->

            <!-- include LoginForm -->
            <jsp:include page="html/loginForm.html"  flush="true"/>
            <!-- end include loginForm -->

            <!-- include regForm -->
            <jsp:include page="html/regForm.html"  flush="true"/>
            <!-- end include regForm -->

            <div id="hBanner">
                <img alt="banner"  align="center" src="img/The-hunger-games-banner.jpg"/>
            </div>
            <div id="hot-line-roll">
                <marquee behavior="scroll" direction="left" onmouseover="this.stop();" onmouseout="this.start();"><b>Please <a href="mailto:keygamepro@gmail.com">contact us</a> if you want to buy more than <font color="red">$500</font>.</b></marquee>

            </div>
        </div>
        <div id="mbody" class="hbf">
            <div id="b-left-panel" class="mainBody" >
                <jsp:include page="html/leftpanel.jsp" flush="true" />
            </div>

            <div id="b-center-panel" class="mainBody onShow" >
                <!--  include popup -->
                <jsp:include page="html/popup.html" flush="true" />
                <!-- end include popup -->


                <div id="top-center-line">
                    <div id="genreSelect">[ All ]</div>
                </div>
                <!--  include bCenterAllItems -->
                <div id="listItem">
                    <jsp:include page="html/bCenterAllItems.jsp" flush="true" />
                    <!-- end include bCenterAllItems -->
                </div>



                <!--paging -->
                <div id="bot-center-line">
                    <div id="paging-div">
                        <ul id="paging-ul" class="pure-skin-xml pure-paginator">
                        </ul>
                    </div>
                </div>
                <!--end Paging -->

                <!--  include shopCart -->
                <jsp:include page="html/shopCart.jsp" flush="true" />
                <!-- end include shopCart -->

            </div>

            <div id="b-right-panel" class="mainBody">
                <jsp:include page="html/rightPanel.html" flush="true" />
            </div>
        </div>

        <div id="footer" align="center" class="hbf" >
            <jsp:include page="html/footer.html" flush="true"/>
        </div>


    </body>
</html>


