<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/pure-min.css">
<script type="text/javascript" src="js/jsUtils.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<jsp:useBean class="Utils.MarshallerUtils" id="marshaller" scope="request"/>
<jsp:useBean class="Utils.DOMUtils" id="parser" scope="request"/>

<%
            String webPath = application.getRealPath("/").replace("\\", "/");
            marshaller.marshallingOrders(webPath + "XML/orders.xml");
            marshaller.marshallingUsers(webPath + "XML/users.xml");
            marshaller.marshallingOrderDetails(webPath + "XML/orderdetails.xml");
            marshaller.marshallingGames(webPath + "XML/games.xml");
            parser.createCustomOrderXML(webPath);
%>


<script type="text/javascript">
    var maxPrice= 99999999999;
    var switchPage = function(id){

        var gamePage = jsUtils.id("adminGameManage");
        var orderPage = jsUtils.id("adminOrderManage")

        if(!jsUtils.hasClass(jsUtils.id(id), "onHide")){
            return;
        }
        if(jsUtils.hasClass(gamePage, "onHide")){
            jsUtils.removeClass(gamePage, "onHide");
            jsUtils.addClass(orderPage, "onHide");
            jsUtils.addClass(jsUtils.id("GOLi"),"pure-menu-selected");
            jsUtils.removeClass(jsUtils.id("MOLi"),"pure-menu-selected");
        }else{
            jsUtils.removeClass(orderPage, "onHide");
            jsUtils.addClass(gamePage, "onHide");
            jsUtils.addClass(jsUtils.id("MOLi"),"pure-menu-selected");
            jsUtils.removeClass(jsUtils.id("GOLi"),"pure-menu-selected");
        }


    };


    var xmlOrdersDom = new ActiveXObject("Microsoft.XMLDOM");
    var xmlGamesDom = new ActiveXObject("Microsoft.XMLDOM");
    xmlGamesDom.async = false;
    xmlGamesDom.load("XML/games.xml");
    xmlOrdersDom.async = false;
    xmlOrdersDom.load("XML/orders.xml");
    var xmlHttp;
    var xmlOrdersString="";

    function getXmlHTTP()
    {
        var xmlHttp = null;
        try{
            xmlHttp = new XMLHttpRequest();
        } catch(e){
            try {
                xmlHttp = new ActiveXObject("Msxm12.XMLHTTP");
            } catch(e){
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return xmlHttp;
    }

    if(xmlOrdersDom.parseError.errorCode!=0)
    {
        alert("Error: "+ xmlOrdersDom.parseError.reason);
    }

    function changeBtnStatus(btnStatusId)
    {
        var btnStatus="btnStatusID"+btnStatusId;
        var valueBtn = document.getElementById(btnStatus).value;

        if(valueBtn=="UnDelivered")
        {

            document.getElementById(btnStatus).value="Delivered";
            saveStatusOfEachOrders(xmlOrdersDom, btnStatusId, 'true');
        }
        else
        {
            document.getElementById(btnStatus).value="UnDelivered";
            saveStatusOfEachOrders(xmlOrdersDom, btnStatusId, 'false');
        }

    }

    function saveStatusOfEachOrders(node, orderid, isDelivered)
    {
        if(node==null)
        {
            return;
        }
        else
        {
            if(node.nodeName=="OrderId")
            {
                if(node.firstChild.nodeValue==orderid){

                    node.parentNode.setAttribute("Delivered", isDelivered);
                    return;
                }
            }
            else
            {
                var childNodes = node.childNodes;
                for(var i=0; i<childNodes.length;i++)
                {
                    saveStatusOfEachOrders(childNodes[i], orderid, isDelivered);
                }
            }
        }
    }

    function createMonthYearDropDownList()
    {
        var months = new Array("January", "February", "March",
        "April", "May", "June", "July", "August", "September",
        "October", "November", "December");
        var FromMonthList= document.getElementById("FromMonthListId");
        var ToMonthList= document.getElementById("ToMonthListId");
        for(var i=0 ; i<12 ; i++)
        {
            var optionFrom = document.createElement("option");
            var optionTo = document.createElement("option");
            FromMonthList.options.add(optionFrom);
            ToMonthList.options.add(optionTo);
            optionFrom.text=months[i];
            optionFrom.value=i+1;
            optionTo.text=months[i];
            optionTo.value=i+1;
        }

        var years = new Array("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008"
        , "2009", "2010", "2011", "2012", "2013");
        var FromYearList = document.getElementById("FromYearListId");
        var ToYearList = document.getElementById("ToYearListId");
        for(var i=0; i<14; i++)
        {
            var optionFrom = document.createElement("option");
            var optionTo = document.createElement("option");
            FromYearList.options.add(optionFrom);
            ToYearList.options.add(optionTo);
            optionFrom.text=years[i];
            optionTo.text=years[i];
            optionFrom.value=years[i];
            optionTo.value=years[i];
        }
    }
    function loadXMLOrderDetail()
    {
        var xmlDomOrderDetail = new ActiveXObject("Microsoft.XMLDOM");
        xmlDomOrderDetail.async = false;
        xmlDomOrderDetail.load("XML/orderDetails.xml");
        if(xmlDomOrderDetail.parseError.errorCode != 0)
        {
            alert("Error: "+ xmlDomOrderDetail.parseError.reason);
        }
        else
        {
            var listValueGame = new Array(0);
            countNumberGame(xmlDomOrderDetail, listValueGame);
            number.innerText = listValueGame.length;
        }
    }

    
    function countNumberGame(node, listValueGame)
    {
        if(node==null)
        {
            return;
        }
        else
        {
            if(node.nodeName=="GameId")
            {
                for(var i =0; i < listValueGame.length; i++)
                {
                    if(node.firstChild.nodeValue==listValueGame[i])
                    {
                        return;
                        break;
                    }
                }
                listValueGame.push(node.firstChild.nodeValue);

            }
            else
            {
                var childNodes = node.childNodes;
                for(var i = 0; i<childNodes.length; i++)
                {
                    countNumberGame(childNodes[i], listValueGame);
                }
            }
        }

    }


    function getNextSiblingOfNode(node)
    {
        var x = node.nextSibling;
        while(x.nodeType!=1)
        {
            x=x.nextSibling;
        }
        return x;
    }
    function traverseTreeDom(node)
    {
        if(node.tagName=="OrderId")
        {
            xmlOrdersString+="<Order Delivered='";
            if(node.parentNode.getAttribute("Delivered")=='false')
            {
                xmlOrdersString+="false"+"'> ";
            }
            else
            {
                xmlOrdersString+="true"+"'> ";
            }
            xmlOrdersString+="<OrderId>"+node.firstChild.nodeValue+"</OrderId>";
            node=getNextSiblingOfNode(node);
            xmlOrdersString+="<UserId>"+node.firstChild.nodeValue+"</UserId>";
            node=getNextSiblingOfNode(node);
            xmlOrdersString+="<OrderDate>"+node.firstChild.nodeValue+"</OrderDate>";
            node=getNextSiblingOfNode(node);
            xmlOrdersString+="<Invoice>"+node.firstChild.nodeValue+"</Invoice>";
            xmlOrdersString+="</Order> ";
            return;
        }
        else{
            node = node.childNodes;
            for(var i=0;i<node.length; i++)
            {
                traverseTreeDom(node[i]);
            }
        }



    }

    function synchronizeListOrder()
    {
        xmlHttp=getXmlHTTP();
        xmlHttp.open("POST", "AdminController", false);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xmlOrdersString = "<orders xmlns=\"http://xml.netbeans.org/schema/orders\">";

        var node = xmlOrdersDom;
        traverseTreeDom(node);
        xmlOrdersString+="</orders>";
        var url = "btnAction=updateOrders&xmlContent=";
        url += xmlOrdersString;
        xmlHttp.onreadystatechange = function(){
            if(xmlHttp.status == 200){
                location.reload(false);
            }
        }
        xmlHttp.send(url);
    }
    function validateInputDate(event)
    {
        
        var FromMonthList = document.getElementById("FromMonthListId");
        var FromYearList = document.getElementById("FromYearListId");
        var ToMonthList = document.getElementById("ToMonthListId");
        var ToYearList = document.getElementById("ToYearListId");
        if(FromMonthList.selectedIndex==0||FromYearList.selectedIndex==0||ToMonthList.selectedIndex==0||ToYearList.selectedIndex==0)
        {
            divValidateDurationReport.innerText="Must input specific duration to report";
            event.preventDefault();
        }
        var fromMonth = FromMonthList.selectedIndex;
        var fromYear = FromYearList.selectedIndex;
        var toMonth =ToMonthList.selectedIndex;
        var toYear = ToYearList.selectedIndex;
        
        if(fromYear>toYear)
        {
            divValidateDurationReport.innerText="FromYear must < ToYear";
            event.preventDefault();
          
        }
        else if(fromYear==toYear)
        {
           
            if(fromMonth>toMonth)
            {
                divValidateDurationReport.innerText= "FromMonth must < toMonth (While FromYear=ToYear)";
                event.preventDefault();
            }
        }
        var valueTopGame= manageGamesForm.txtTopGameNeedReport.value;
        if(isNaN(valueTopGame)||valueTopGame=='')
        {
            divValidateDurationReport.innerText="TopGame must a number";
            event.preventDefault();
        }
        else 
           
        if(valueTopGame < 1 )
        
    {
        divValidateDurationReport.innerText=("TopGame must > 1");
        event.preventDefault();
    }
    else{
         
        if(Number(valueTopGame)>(Number(number.innerText)  ))

        {
            divValidateDurationReport.innerText=("Number must < " + number.innerText);
            event.preventDefault();
        }
    }
        
        
    divValidateDurationReport.innerText="";
       
    PrintPDF();
    return true;
}

function PrintPDF()
{

        
    var FromMonthListIndex = document.getElementById("FromMonthListId").selectedIndex;
    var ToMonthListIndex = document.getElementById("ToMonthListId").selectedIndex;
    var FromYearListIndex = document.getElementById("FromYearListId").selectedIndex;
    var ToYearListIndex = document.getElementById("ToYearListId").selectedIndex;
    var PrintPDFLink = document.getElementById("PrintPDFId");
    var txtTopGameNeedReport = document.getElementById("txtTopGameNeedReportId");
        
    var href = "PDFController?btnAction=PrintListTopGame&"+"cboFromMonth="+FromMonthListIndex;
    href+="&cboFromYear="+document.getElementById("FromYearListId").options[FromYearListIndex].value;
    href+="&cboToMonth="+ToMonthListIndex;
    href+="&cboToYear="+document.getElementById("ToYearListId").options[ToYearListIndex].value;
    href+="&txtTopGameNeedReport="+txtTopGameNeedReport.value;
    PrintPDFLink.setAttribute("href", href);
    PrintPDFLink.setAttribute("target", "_blank");
}

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

var realPath = '${pageContext.request.contextPath}';
var urlXmlOrders = realPath+ "/XML/orders.xml";
var filexmlOrders = loadXML(urlXmlOrders);
var urlXslOrders = realPath+"/stylesheet/orders.xsl";
var xslOrders = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
xslOrders.load(urlXslOrders);


var urlXmlGames = realPath + "/XML/games.xml";
var filexmlGames = loadXML(urlXmlGames);
var urlXslGames = realPath+"/stylesheet/manage_games.xsl";
var xslGames = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
xslGames.load(urlXslGames);



function applyXslTemplateWithXmlOrders(searchValue)
{
    var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
    xslTemplate.stylesheet = xslOrders;
    var xsltProcessor = xslTemplate.createProcessor();
    xsltProcessor.input=filexmlOrders;
    xsltProcessor.addParameter("searchValue", searchValue);
    xsltProcessor.transform();
    document.getElementById('outputOrders').innerHTML=xsltProcessor.output;
}

function validateFromPrice(value)
{
    if(isNaN(value))
    {
        divValidatePrice.innerText="FromPrice is not number. ";
        return false;
    }
    else if(value<0& value!=''){
        divValidatePrice.innerText="FromPrice must > 0. ";
        return false;
    }
    return true;
}
function validateToPrice(value)
{
    if(isNaN(value))
    {
        divValidatePrice.innerText ="ToPrice is not number.";
        return false;
    }
    else if(value<1&& value!=''){
        divValidatePrice.innerText ="ToPrice is not number.";
        return false;
    }
    return true;
}

function  validateFromAndToPrice(fromPrice, toPrice)
{
    if(validateFromPrice(fromPrice)&& validateToPrice(toPrice))
    {
        if(fromPrice=='')
        {
            divValidatePrice.innerText="";
            return true;
        }
        else if(toPrice=='')
        {
            divValidatePrice.innerText="";
            return true;
        }
        else if(toPrice>=fromPrice)
        {
            divValidatePrice.innerText="";
            return true;
        }
        divValidatePrice.innerText="FromPrice must < ToPrice";
    }

       
    return false;
}
function applyXslTemplateWithXmlGames(searchValue, fromPrice, toPrice, sortName, sortOrder)
{
    if(!validateFromAndToPrice(fromPrice, toPrice))
    {
        return;
    }
       
    if(sortOrder=='ascending')
    {
        sortOrder='descending';
    }
    else
    {
        sortOrder= 'ascending';
    }

    if(toPrice=='')
    {
        toPrice=maxPrice;
    }
       
    if(fromPrice=='')
    {
        fromPrice=0;
    }
    var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");

    xslTemplate.stylesheet = xslGames;

    var xsltProcessor = xslTemplate.createProcessor();

    xsltProcessor.input = filexmlGames;

    xsltProcessor.addParameter("searchValue", searchValue);
    xsltProcessor.addParameter("fromPrice", fromPrice);
    xsltProcessor.addParameter("toPrice", toPrice);
    xsltProcessor.addParameter("sortName", sortName);

        
    xsltProcessor.addParameter("sortOrder", sortOrder);
    xsltProcessor.transform();
    document.getElementById('outputGames').innerHTML = xsltProcessor.output;
}

function synchronizeListGames()
{
    xmlHttp = getXmlHTTP();
    xmlHttp.open("POST", "AdminController", false);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlGamesString = "<games xmlns=\"http://xml.netbeans.org/schema/games\">";
    var node = xmlGamesDom;
    traverseTreeDomGames(node);
    xmlGamesString+="</games>";
    var url = "btnAction=updateGames&xmlContent=";
    url += xmlGamesString;
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.status ==200)
        {
            xmlGamesDom=loadXML(urlXmlGames);
        }
    }
    xmlHttp.send(url);
}

function  traverseTreeDomGames(node){
    if(node.tagName=='GameId')
    {
        xmlGamesString += "<Game><GameId>";
        xmlGamesString += node.firstChild.nodeValue + "</GameId>";
        node = getNextSiblingOfNode(node);
        xmlGamesString +="<Name>"+ node.firstChild.nodeValue + "</Name>";
        node = getNextSiblingOfNode(node);
        xmlGamesString +="<Price>" +node.firstChild.nodeValue + "</Price>";
        node = getNextSiblingOfNode(node);
        xmlGamesString +="<Pinboard>" +node.firstChild.nodeValue + "</Pinboard>";
        node = getNextSiblingOfNode(node);
        xmlGamesString +="<Description>" +node.firstChild.nodeValue + "</Description>";
        xmlGamesString +="<Image_Url></Image_Url>";
        xmlGamesString +="<CreatedDate></CreatedDate>";
        xmlGamesString +="<GenreId></GenreId>";
        xmlGamesString += "</Game>";
        return;
    }
    else{
        node = node.childNodes;
        for(var i=0;i<node.length; i++)
        {
            traverseTreeDomGames(node[i]);
        }
    }
}
function updateNameItemByGameId(node, gameId)
{

    if(node==null)
    {
        return false;
    }
    else
    {
        if(node.nodeName=="GameId")
        {
            if(node.firstChild.nodeValue==gameId)
            {
                do
                {
                    node =getNextSiblingOfNode(node);
                }
                while(node.nodeName!='Name');
                var btnUpdateNameId = "btnUpdateGameName"+gameId;
                node.firstChild.nodeValue=document.getElementById(btnUpdateNameId).value;
                   
            }
        }
        else
        {
            var childNodes = node.childNodes;
            for(var i=0; i<childNodes.length ; i++)
            {
                updateNameItemByGameId(childNodes[i], gameId);
            }
        }
    }
}
function updatePriceItemByGameId(node, gameId)
{

    if(node==null)
    {
        return false;
    }
    else
    {
        if(node.nodeName=="GameId")
        {
            if(node.firstChild.nodeValue==gameId)
            {
                do
                {
                    node =getNextSiblingOfNode(node);
                }
                while(node.nodeName!='Price');
                var btnUpdatePriceId = "btnUpdateGamePrice"+gameId;
                node.firstChild.nodeValue=document.getElementById(btnUpdatePriceId).value;
                    
            }

        }
        else
        {
            var childNodes = node.childNodes;
            for(var i=0; i<childNodes.length ; i++)
            {
                updatePriceItemByGameId(childNodes[i], gameId);
            }
        }
    }
}
function updatePinboardItemByGameId(node, gameId)
{

    if(node==null)
    {
        return false;
    }
    else
    {
        if(node.nodeName=="GameId")
        {
            if(node.firstChild.nodeValue==gameId)
            {
                do
                {
                    node =getNextSiblingOfNode(node);
                }
                while(node.nodeName!='Pinboard');
                var btnUpdatePinboardId = "btnUpdateGamePinboard"+gameId;
                node.firstChild.nodeValue=document.getElementById(btnUpdatePinboardId).value;
                    
            }
        }
        else
        {
            var childNodes = node.childNodes;
            for(var i=0; i<childNodes.length ; i++)
            {
                updatePinboardItemByGameId(childNodes[i], gameId);
            }
        }
    }
}
function updateDescriptionItemByGameId(node, gameId)
{
    if(node==null)
    {
        return false;
    }
    else
    {
        if(node.nodeName=="GameId")
        {
            if(node.firstChild.nodeValue==gameId)
            {
                do
                {
                    node =getNextSiblingOfNode(node);
                }
                while(node.nodeName!='Description');
                var btnUpdateDescriptionId = "btnUpdateGameDescription"+gameId;
                node.firstChild.nodeValue=document.getElementById(btnUpdateDescriptionId).value;
                    
            }
        }
        else
        {
            var childNodes = node.childNodes;
            for(var i=0; i<childNodes.length ; i++)
            {
                updateDescriptionItemByGameId(childNodes[i], gameId);
            }
        }
    }
}
function updateItemByGameIdAndFieldName(gameId, fieldName)
{

    if(fieldName=="Name")
    {
        updateNameItemByGameId(xmlGamesDom, gameId);
    }
    else if(fieldName=="Price")
    {
        updatePriceItemByGameId(xmlGamesDom, gameId);
    }
    else if(fieldName=="Description")
    {
        updateDescriptionItemByGameId(xmlGamesDom, gameId);
    }
    else if(fieldName=="Pinboard")
    {
        updatePinboardItemByGameId(xmlGamesDom, gameId);
    }
}

function onloadPage()
{
    loadXMLOrderDetail();
    createMonthYearDropDownList();
    applyXslTemplateWithXmlOrders("");
    applyXslTemplateWithXmlGames("", 0, maxPrice, 'Name', 'descending');
}
window.onload = onloadPage;




</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="adminHead">
            <form action="UserController" method="Post">
                <a id="btnAction" onclick="" href="AdminController?btnAction=Logout" >Logout</a>
            </form>
            <div id="topAdminText">ADMIN PANEL</div>



        </div>
        <div id="adminMain" >
            <div id="adminTopPanel" align="center">
                <div class="pure-menu pure-menu-open pure-menu-horizontal">
                    <ul>

                        <li id="MOLi" class="pure-menu-selected">
                            <a href="#" onclick="switchPage('adminOrderManage');return false;">Manage Order</a></li>
                        <li id="GOLi"><a href="#" onclick="switchPage('adminGameManage');return false;">Manage Game</a></li>

                    </ul>
                </div>


            </div>
            <div id="adminCenterPanel">
                <div id="adminOrderManage" class="adminPage">
                    <form action="AdminController" method="POST">
                        Search user :   <input name="txtSearch" value="" type="text" />
                        <input class="orderBtn pure-button" name="btnSearch" value="Search" type="button" onclick="applyXslTemplateWithXmlOrders(this.form.txtSearch.value)"/>
                        <input class="orderBtn pure-button" type="button" name="btnAction"  value="Save Changes" onclick="synchronizeListOrder()" />
                        <br/>
                        <br/>
                        <br/>
                        <div id="outputOrders"></div>

                    </form>



                </div>

                <div id="adminGameManage" class="adminPage onHide">
                    <form action="AdminController" method="POST" name="manageGamesForm">
                        <div>
                            <div id="dateSearch">
                                <fieldset>
                                    <legend><h4> Print report Top-sell games </h4></legend>
                                    From:
                                    <select name="cboFromMonth" id="FromMonthListId" class="cboDate">
                                        <option></option>
                                    </select>
                                    <select name="cboFromYear" id="FromYearListId" class="cboDate">
                                        <option></option>
                                    </select>
                                    To:
                                    <select name="cboToMonth"  id="ToMonthListId" class="cboDate">
                                        <option></option>
                                    </select>
                                    <select name="cboToYear" id="ToYearListId" class="cboDate">
                                        <option></option>
                                    </select><br/>
                                    <div style="padding:10px 10px 0px 79px;height: 79px;">
                                        <table>
                                            <tr>
                                                <td>
                                        Top-seller Game: <input name="txtTopGameNeedReport" id="txtTopGameNeedReportId" style="padding-right:20px"/>  In the <label id="number"> </label> issued Games
                                        </td>
                                        <td>
                                        <a  id="PrintPDFId" href="#" onclick="validateInputDate(event);return true;" class="imgPDF"> <img  src="img/button/printPDF.png" title="Print report" /></a>
                                        </td>
                                        </tr>
                                        </table>
                                        </div>
                                    <div id="divValidateDurationReport">asdasd</div>
                                    <br/>
                                </fieldset>
                            </div>
                            <br/>
                            <fieldset>
                                <legend><h4>Search by name</h4></legend>
                                Key Name :<input name="txtSearchValue" value="" type="text"/>
                            </fieldset>
                            <br/>
                            <fieldset>
                                <legend><h4> Search by Price </h4></legend>
                                From :<input name="txtFromPrice" value="" type="text" id="txtFromPriceId"/>
                                To: <input name="txtToPrice" value="" type="text" id="txtToPriceId"/>
                                <input class="pure-button" name="btnSearchGames" type="button" value="Search" id="btnSearchGameId" onclick="applyXslTemplateWithXmlGames(this.form.txtSearchValue.value, this.form.txtFromPrice.value, this.form.txtToPrice.value, 'Name', 'descending')"/>
                                <div id="divValidatePrice"></div> 
                                <br/>
                            </fieldset>
                            <br/><br/>
                        </div>
                        <div id="tblGamelist">
                            <fieldset>
                                <legend><h4> Games Manage </h4></legend>
                                <input style="float:right; margin: 5px 5px 20px 5px;" class="pure-button" name="btnUpdates" type="button" value="Save changes" onclick="synchronizeListGames()"/>
                                <input style="float:right; margin: 5px 5px 20px 5px;" class="pure-button" name="btnNewGame" type="button" value="New" onlick="newGame()"/>
                                <br/>
                                <br/>
                                <div id="outputGames"/>
                            </fieldset>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
