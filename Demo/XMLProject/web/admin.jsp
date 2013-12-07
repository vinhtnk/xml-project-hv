<%-- 
    Document   : admin
    Created on : Jun 12, 2013, 9:12:12 PM
    Author     : Quaikiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<jsp:useBean class="Utils.MarshallerUtils" id="marshaller" scope="request"/>
<jsp:useBean class="Utils.DOMUtils" id="parser" scope="request"/>
<c:if test="${Role!='Admin'}"><c:redirect url="index.jsp" /></c:if>
<%
            String webPath = application.getRealPath("/").replace("\\", "/");
            marshaller.marshallingOrders(webPath + "XML/orders.xml");
            marshaller.marshallingUsers(webPath + "XML/users.xml");
            marshaller.marshallingOrderDetails(webPath + "XML/orderdetails.xml");
            marshaller.marshallingGames(webPath + "XML/games.xml");
            marshaller.marshallingGenres(webPath + "XML/genres.xml");
            parser.createCustomOrderXML(webPath);
%>


<script type="text/javascript">

    var realPath = '${pageContext.request.contextPath}';
    var urlXmlOrders = realPath+ "/XML/orders.xml";
    var xmlOrdersDom = loadXML(urlXmlOrders);
    var urlXslOrders = realPath+"/stylesheet/manage_orders.xsl";
    var xslOrders = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
    xslOrders.load(urlXslOrders);
    var urlXmlGames = realPath + "/XML/games.xml";
    var xmlGamesDom = loadXML(urlXmlGames);
    var urlXslGames = realPath+"/stylesheet/manage_games.xsl";
    var xslGames = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
    xslGames.load(urlXslGames);
    var xmlOrdersString="";
    var xmlGamesString="";

    function getXmlDom(path)
    {
        var xmlDoc;
        if (window.DOMParser)
        {
            parser=new DOMParser();
            xmlDoc=parser.parseFromString(txt,"text/xml");
        }
        else // Internet Explorer
        {
            xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async=false;
            xmlDoc.loadXML("XML/orders.xml");
        }
        return xmlDoc;
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
        }
    }
    function loadXMLOrderDetail()
    {
        var xmlDomOrderDetail = new ActiveXObject("Microsoft.XMLDOM");
        xmlDomOrderDetail.async = false;
        xmlDomOrderDetail.load("XML/orderdetails.xml");
        if(xmlDomOrderDetail.parseError.errorCode != 0)
        {
            alert("Error: "+ xmlDomOrderDetail.parseError.reason);
        }
        else
        {
            var listValueGame = new Array(0);
            countNumberGame(xmlDomOrderDetail, listValueGame);
            var cboTopGame = document.getElementById("cboTopGameId");
            for(var i = 0; i < listValueGame.length ; i++)
            {
                var option = document.createElement("option");
                cboTopGame.options.add(option);
                option.text=i+1;
                option.value=i+1;

            }
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
    function traverseTreeDomOrders(node)
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
                traverseTreeDomOrders(node[i]);
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
        traverseTreeDomOrders(node);
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
                location.reload(false);
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

    function validateInputDate()
    {
        var FromMonthList = document.getElementById("FromMonthListId");
        var FromYearList = document.getElementById("FromYearListId");
        var ToMonthList = document.getElementById("ToMonthListId");
        var ToYearList = document.getElementById("ToYearListId");
        var fromMonth = FromMonthList.selectedIndex;
        var fromYear = FromYearList.options[FromYearList.selectedIndex].value;
        var toMonth =ToMonthList.selectedIndex;
        var toYear = ToYearList.options[ToYearList.selectedIndex].value;
      
        if(fromYear>toYear)
        {
            valid =false;
            errorCause ="FromYear can not bigger than ToYear";
        }
        else if(fromYear==toYear)
        {
            if(fromMonth>toMonth)
            {
                valid =false;
                errorCause = "FromMonth can not bigger toMonth (While FromYear=ToYear)";
            }
        }

        if(!valid)
        {
            alert("Error:"+errorCause);
        }
        else{
            PrintPDF();
        }
    }
   
    function PrintPDF()
    {
        var FromMonthListId = document.getElementById("FromMonthListId");
        var ToMonthListId = document.getElementById("ToMonthListId");
        var FromYearListId = document.getElementById("FromYearListId");
        var ToYearListId = document.getElementById("ToYearListId");
        var PrintPDFLink = document.getElementById("PrintPDFId");
        var cboTopGameId = document.getElementById("cboTopGameId");

        var href = "PDFController?btnAction=PrintListTopGame&"+"cboFromMonth="+FromMonthListId.options[FromMonthListId.selectedIndex].value;
        href+="&cboFromYear="+FromYearListId.options[FromYearListId.selectedIndex].value;
        href+="&cboToMonth="+ToMonthListId.options[ToMonthListId.selectedIndex].value;
        href+="&cboToYear="+ToYearListId.options[ToYearListId.selectedIndex].value;
        href+="&cboTopGame="+cboTopGameId.options[cboTopGameId.selectedIndex].value;
        PrintPDFLink.setAttribute("href", href);
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





    function applyXslTemplateWithXmlOrders(searchValue)
    {
        var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
        xslTemplate.stylesheet = xslOrders;
        var xsltProcessor = xslTemplate.createProcessor();
        xsltProcessor.input=xmlOrdersDom;
        xsltProcessor.addParameter("searchValue", searchValue);
        xsltProcessor.transform();
        document.getElementById('outputOrders').innerHTML=xsltProcessor.output;
    }

    function applyXslTemplateWithXmlGames(searchValue, fromPrice, toPrice, sortName, sortOrder)
    {
        var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate");
       
        xslTemplate.stylesheet = xslGames;
        
        var xsltProcessor = xslTemplate.createProcessor();
         
        xsltProcessor.input = xmlGamesDom;
       
        xsltProcessor.addParameter("searchValue", searchValue);
        xsltProcessor.addParameter("fromPrice", fromPrice);
        xsltProcessor.addParameter("toPrice", toPrice);
        xsltProcessor.addParameter("sortName", sortName);
   
        if(sortOrder=='ascending')
        {
            sortOrder='descending';
        }
        else
        {
            sortOrder= 'ascending';
        }
        
        xsltProcessor.addParameter("sortOrder", sortOrder);
        xsltProcessor.transform();
        document.getElementById('outputGames').innerHTML = xsltProcessor.output;
    }
    function checkPrice(price)
    {
        var expReg = /^(?!0\d)\d*(\.\d+)?$/;
        if(!expReg.test(price))
        {
            return false;
        }
        else {
            return true;
        }
    }
   
    function createOptionForGenres()
    {
        
        var xmlGenreDom = new ActiveXObject("Microsoft.XMLDOM");
        xmlGenreDom.async = false;
        xmlGenreDom.load("XML/genres.xml");
        if(xmlGenreDom.parseError.errorCode!=0)
        {
            alert("Error:" + xmlGenreDom.parseError.reason);
        }
        else {
            
            seekGenreNode(xmlGenreDom);
        }
    
        
        
    }

    function validateAll()
    {
        checkPrice(document.getElementById("txtPriceId").value);
        checkDuplicateName(document.getElementById("txtGameNameId").value);

    }
    
    function seekGenreNode(node)
    {
        if(node==null)
        {
            return;
        }
        else
        {
            if(node.nodeName=="GenreId")
            {
                 
                var option = document.createElement("option");
                var cboGenreNamesList = document.getElementById("cboGenreNamesId");
                cboGenreNamesList.options.add(option);
                option.value=node.firstChild.nodeValue;
                node = getNextSiblingOfNode(node);
                option.text = node.firstChild.nodeValue;
                
            }
            var childNodes = node.childNodes;
            for(var i = 0 ; i<childNodes.length; i++)
            {
                seekGenreNode(childNodes[i]);

            }
        }
    }
    function checkDuplicateName(name)
    {
        var xmlGamesDom = new ActiveXObject("Microsoft.XMLDOM");
        xmlGamesDom.async = false;
        xmlGamesDom.load("XML/games.xml");
        if(xmlGamesDom.parseError.errorCode!=0)
        {
            alert("Error:" + xmlGamesDom.parseError.reason);
        }
        else {
            
            checkGameNameNode(xmlGamesDom, name);
        }
    }
    function checkGameNameNode(node, name){

        if(node==null)
        {
            return;
        }
        else
        {

            if(node.nodeName=="Name")
            {
               
                if(node.firstChild.nodeValue==name)
                {
                    alert("Trung Game Name");
                    return false;
                }
                else{
                
                    return true;
                }
            }
            var childNodes = node.childNodes;
            for(var i = 0 ; i<childNodes.length; i++)
            {
                checkGameNameNode(childNodes[i], name);

            }
        }
    }

    function getFilename(){
        var pathComponents = document.getElementById("urlImageFileId").value.split('\\'),
        fileName = pathComponents[pathComponents.length - 1];
        alert(fileName);
       
        
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
                    alert(node.nodeName +" : " +node.firstChild.nodeValue);
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
                    alert(node.nodeName +" : " +node.firstChild.nodeValue);
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
                    alert(node.nodeName +" : " +node.firstChild.nodeValue);
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
                    alert(node.nodeName +" : " +node.firstChild.nodeValue);
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
            updateDesciptionItemByGameId(xmlGamesDom, gameId);
        }
        else if(fieldName=="Pinboard")
        {
            updatePinboardItemByGameId(xmlGamesDom, gameId);
        }
    }
    function searchNodeToDeleteItem(node,gameId)
    {
        if(node.nodeName=="GameId")
            {
                if(node.firstChild.nodeValue==gameId)
                    {
                        node=node.parentNode;
                        node.parentNode.removeChild(node);
                        listDeletedGameInDom+=gameId+";";
                    }
            }
            else
                {
                    node = node.childNodes;
                    for(var i= 0; i<node.length; i++)
                        {
                            searchNodeToDeleteItem(node[i], gameId);
                        }
                }
        
    }
    function onloadPage()
    {
        createMonthYearDropDownList();
        createOptionForGenres();
        loadXMLOrderDetail();
        applyXslTemplateWithXmlOrders("");
        applyXslTemplateWithXmlGames("", 0, 500, 'Price', 'descending');
    }
    window.onload = onloadPage;
    
   

    
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>

        <!--        Form danh cho manage Orders-->
        <form action="AdminController" method="POST">


            From:
            <select name="cboFromMonth" id="FromMonthListId">
                <option></option>
            </select>

            <select name="cboFromYear" id="FromYearListId">
                <option></option>
            </select>
            <br/>To:
            <select name="cboToMonth"  id="ToMonthListId">
                <option></option>
            </select>
            <select name="cboToYear" id="ToYearListId">
                <option></option>
            </select>
            <br/>
            <br/>


            TopGame: <select name="cboTopGame" id="cboTopGameId"></select><br/>
            <a id="PrintPDFId" href="" target="_blank" onclick="validateInputDate()" >Print PDF</a><br/>
            Search:<input name="txtSearch" value="" type="text"/>
            <input name="btnSearch" value="Search" type="button" onclick="applyXslTemplateWithXmlOrders(this.form.txtSearch.value)"/>


            <div id="outputOrders"></div>
            <input type="button" name="btnAction"  value="Save Changes" onclick="synchronizeListOrders()"></input>
        </form>


        <!--        From danh cho manage Games-->
        <form name="ManageGamesForm" bgcolor="ryan" action="AdminController" method="POST">
            <input name="txtSearchValue" value="" type="text" id="txtSearchId"/>
            Price: From=<input name="txtFromPrice" value="" type="text" id="txtFromPriceId"/>
            To=<input name="txtToPrice" value="" type="text" id="txtToPriceId"/>
            <input name="btnSearchGames" type="button" value="Search" id="btnSearchGameId" onclick="applyXslTemplateWithXmlGames(this.form.txtSearchValue.value, this.form.txtFromPrice.value, this.form.txtToPrice.value, 'Name', 'descending')"/>
            <div id="outputGames"></div>
            <input name="btnUpdates" value="Savechanges" type ="button" onclick="synchronizeListGames()"/>
            <br/>


            <!--            Phan tao san pham moi-->
            Name: <input name="txtName" type="text" id="txtNameId" onchange="checkDuplicateName(this.value)"/><br/>
            Price: <input name="txtPrice" type="text" id="txtPriceId" onchange="checkPrice(this.value)"/><br/>
            PinBoard: <input name="txtPinBoard" type="text"/><br/>
            Description: <input name="txtDescription" type="text"/><br/>
            Image: <input name="urlImageFile" type="file" id="urlImageFileId" onchange="getFilename()"/><br/>
            Genre: <select name="cboGenreName" id="cboGenreNamesId"></select><br/>
            <input name="btnAction" type="submit" value="Create New Game"/><br/>
        </form>

    </body>
</html>
