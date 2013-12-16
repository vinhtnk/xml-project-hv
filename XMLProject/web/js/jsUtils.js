var jsUtils = function(){
    
}




jsUtils.sessionUser = function(uinfo){
    if(uinfo){
        
        this.uinfo = uinfo;
        return null;
    }else{
        return this.uinfo;
    }
}

jsUtils.currentPageId = function(){
    if(sessionStorage.getItem("currentPage") == null){
        jsUtils.setDefaultPageId();
    }
    return sessionStorage.getItem("currentPage");
}

jsUtils.id= function(id){
    return document.getElementById(id);
}



jsUtils.previousPageId = function(){
    return sessionStorage.getItem("previousPage");
}
jsUtils.showHidePage = function(thisPage){
    // var page = Pageid;
    var ele = document.getElementById(thisPage);
    if(this.hasClass(ele, "onHide")){
        jsUtils.removeClass(ele, "onHide" );
    }else{
        jsUtils.addClass(ele, "onHide" );
    }

};


jsUtils.hasClass = function(ele, className){
    return new RegExp(' ' + className + ' ').test(' ' + ele.className + ' ');
}

jsUtils.addClass = function(ele, className){
    if (!this.hasClass(ele, className)) {
        ele.className += ' ' + className;
    }
}

// removeClass
jsUtils.removeClass = function(ele, className){
    var newClass = ' ' + ele.className.replace( /[\t\r\n]/g, ' ') + ' ';
    if (this.hasClass(ele, className)) {
        while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
            newClass = newClass.replace(' ' + className + ' ', ' ');
        }
        ele.className = newClass.replace(/^\s+|\s+$/g, '');
    }

}


jsUtils.removeClassOfChild = function(idFather, className){
    var father = document.getElementById(idFather);
    var childLength = father.children.length;
    for(var i=0;i<childLength;i++){
        var removeFromChild = father.children[i];
        jsUtils.removeClass(removeFromChild,className) ;
    }
}

jsUtils.addClassOfChild = function(isFather,className){
    var father  = document.getElementById(isFather);
    var childLength  = father.children.length;
    for(var i=0;i<childLength;i++){
        var addToChild = father.children[i];
        jsUtils.addClass(addToChild,className) ;
    }
}


jsUtils.checkType = function(isSession){
    var classAdd = "onHide";
    if(isSession != "" && isSession != undefined){

        var login = document.getElementById("Uitem0");
        login.innerText = "Hi "+ isSession;
        login.textContent = "Hi " + isSession;

        jsUtils.removeClassOfChild("navBarUser",classAdd);
        jsUtils.addClassOfChild("navBarGuess", classAdd);

    }else{
        jsUtils.addClassOfChild("navBarUser",classAdd);
        jsUtils.removeClassOfChild("navBarGuess", classAdd);
    }
}
   



jsUtils.removeFromCart = function(itemNoId){
    var noId = itemNoId.split("-").pop();
    var moneyTotal = document.getElementById("moneyTotal");
    document.getElementById("NumberOfItem-"+noId).value = "0";
    jsUtils.addToCart(itemNoId,true);
    if(parseInt(moneyTotal.innerText,10) == 0){
        jsUtils.addClass(document.getElementById('cartTotal'), "onHide");
        document.getElementById("yourCart").className = "emptyCart";
    }
    var removeItem = document.getElementById("cart-"+itemNoId);
    return removeItem.parentNode.removeChild(removeItem);
    
}



function start(){
    if(typeof(sessionStorage) != "undefined"){
        if(sessionStorage.cart == null){
            document.getElementById('totalPrice').innerHTML = 0;
        }else{
            document.getElementById('totalPrice').innerHTML = Number(sessionStorage.totalPrice).formatMoney(0);
        }
    } else{
        alert("Your browser is not support storage.");

    }
}

function addToCartSes(id, name, price){
    if(typeof(sessionStorage) != "undefined"){
        
        
        var exist = false;
        var cart = eval(sessionStorage.cart) ||[];
        for(var i = 0; i < cart.length; i++){
            if(cart[i].id == id){
                cart[i].quantity++;                
                exist = true;
                cart[i].total = Number(cart[i].total) + Number(cart[i].price);
            }
            
        }
        if(!exist){
            cart.push({
                'id': id,
                'name': name,
                'price': price,
                'quantity': 1,
                'total': price
            });
        }
        sessionStorage.cart = JSON.stringify(cart);

        sessionStorage.totalPrice = Number(0);
        for(var i = 0; i < cart.length; i++){
            sessionStorage.totalPrice = Number(sessionStorage.totalPrice) + Number(cart[i].total);        
        }
        var ttp = sessionStorage.totalPrice;
       
        document.getElementById('totalPrice').innerHTML = Number(ttp).formatMoney(0);

    //tp.setAttribute("text", "abc");
        
   
    } else{
        alert("browser is not support storage!!!");
    }
}

function addRow(tableId, items){
    var tableElement = document.getElementById(tableId);
    var newCell;
    var col=0;
    var newRow;
    
    for(var i=0; i<items.length;i++){
        
        newRow = tableElement.insertRow(tableElement.rows.length);
        newRow.setAttribute("align", "center");
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = ++col;
        
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = items[i].id;
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = items[i].name;
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = "<input id='txtquantity' type='text' value='" + items[i].quantity 
        +"' style='max-width: 24px' onchange=\"updateCart('"+ items[i].id +"\')\"/>";
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = Number(items[i].price).formatMoney(0);
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = Number(items[i].total).formatMoney(0);
        newCell = newRow.insertCell(newRow.cells.length);
        newCheckbox = newCell.insert
        newCell.innerHTML = "<input type='checkbox' name='cbRemove' value='OFF' onclick=\"removeCartItem('"+ items[i].id +"\')\"/>";
        
    }
    newRow = tableElement.insertRow(tableElement.rows.length);

    newCell = newRow.insertCell(newRow.cells.length);
    newCell.setAttribute("colspan", "6");
    newCell.setAttribute("align", "right");
    newCell.innerHTML = "<span class='allPrice'>Thành tiền: " + Number(sessionStorage.totalPrice).formatMoney(0) + "</span>";

    return newRow;
}

function showCart(items, tableID){
    
    if(typeof(sessionStorage) != "undefined"){
        addRow(tableID, items);
    } else{
        alert("Your browser is not support storage.");

    }
}

function removeCartItem(id){
    //window.event.preventDefault ? window.event.preventDefault() : window.event.returnValue = false;
    
    var cart = eval(sessionStorage.cart);
    var ttp = eval(sessionStorage.totalPrice);
    for(var i = 0; i < cart.length; i++){
        if(cart[i].id == id){
            //totalPrice = 0;
            //totalQuantity = 0;
            cart.splice(i, 1);
            sessionStorage.cart = JSON.stringify(cart);
            
        }
    }
    
    sessionStorage.totalPrice = Number(0);
    for(var i = 0; i < cart.length; i++){
        sessionStorage.totalPrice = Number(sessionStorage.totalPrice) + Number(cart[i].total);
    }
    
    location.reload();
   
}


function updateCart(id){
    var e = window.event;
    var el = e.srcElement || e.target;
    var quantity = el.value;
    if(quantity <= 0 || quantity >= 1000){
        el.value = oldQuantity;
        return;
    }
    var cart = eval(sessionStorage.cart);
    for(var i = 0; i < cart.length; i++){
        if(cart[i].id == id){
            cart[i].quantity = quantity;
            cart[i].total = cart[i].quantity*cart[i].price;
            sessionStorage.cart = JSON.stringify(cart);
            
        }
    }
    sessionStorage.totalPrice = Number(0);
    for(var i = 0; i < cart.length; i++){
        sessionStorage.totalPrice = Number(sessionStorage.totalPrice) + Number(cart[i].total);
    }
    location.reload();
}



Number.prototype.formatMoney = function(c, d, t){
    var n = this,
    c = isNaN(c = Math.abs(c)) ? 2 : c,
    d = d == undefined ? "." : d,
    t = t == undefined ? "," : t,
    s = n < 0 ? "-" : "",
    i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
    j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};

jsUtils.showPopUp = function(itemNoId){
    // var selectId = document.getElementById(itemNoId);
    var copyData = document.getElementById(itemNoId).parentNode.innerHTML;
    document.getElementById("popUp").children[1].innerHTML = copyData;
    jsUtils.removeClass(document.getElementById('popUp'),"onHide");
    jsUtils.removeClass(jsUtils.id('popUp'),"onHide");
    var Desc = jsUtils.id('popUp').children[1].children[0].children[1].children[2];
    jsUtils.removeClass(Desc, "onHide");
}


function btnSearch_Click(){
    //txtSearch = document.getElementById("txtSearch");
    var key = document.getElementById('txtSearch').value;
    window.location.href="searchProduct.jsp?Condition=Search&Val="+key;

    
}

function parseXml(xmlString) {
    var dom = null;
    if (window.DOMParser) {
        try {
            dom = (new DOMParser()).parseFromString(xmlString, "text/xml");
        }
        catch (e) {
            dom = null;
        }
    } else if (window.ActiveXObject) {
        try {
            dom = new ActiveXObject('Microsoft.XMLDOM');
            dom.async = false;
            if (!dom.loadXML(xmlString)) // parse error ..
                throw dom.parseError.reason + dom.parseError.srcText;
        }
        catch (e) {
            dom = null;
        }
    } else throw "cannot parse xml string!";
    return dom;
}


function getXmlHttpObject(){
    var xmlHttp = null;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (exception) {
        try {
            xmlHttp = new ActiveXObject("Msxm12.XMLHTTP");
        } catch (exception) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

    }
    return xmlHttp;
}

function createOrderXML(){
    var orderXMLDOM = "<orders>";    
    orderXMLDOM += "<order>";
    orderXMLDOM += "<orderID></orderID>";
    orderXMLDOM += "<email>abc</email>";
    orderXMLDOM += "<price>" + sessionStorage.totalPrice + "</price>";
    orderXMLDOM += "</order>";
    orderXMLDOM += "</orders>";
    return orderXMLDOM;
}

function createOrderDetailsXML(){

    var orderDetailsXMLDOM = "<orderDetails>";
    
    var cart = eval(sessionStorage.cart);
    for(var i = 0; i < cart.length; i++){
        orderDetailsXMLDOM += "<orderDetail>";
        orderDetailsXMLDOM += "<orderID></orderID>";
        orderDetailsXMLDOM += "<productID>"+ cart[i].id +"</productID>";
        orderDetailsXMLDOM += "<productName>"+ cart[i].name +"</productName>";
        orderDetailsXMLDOM += "<quantity>"+ cart[i].quantity +"</quantity>";
        orderDetailsXMLDOM += "<price>"+ cart[i].price +"</price>";
        orderDetailsXMLDOM += "</orderDetail>";
    }
    orderDetailsXMLDOM += "</orderDetails>";
    return orderDetailsXMLDOM;
}

function checkout(callback){
    xmlHttp = getXmlHttpObject();
    if(xmlHttp == null){
        alert("Your browser does not support AJAX");
        return;
    }
    xmlHttp.open("POST", "CartController");
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var url = "email=abc&btnAction=checkout&orderDetailsXML=";
    url += createOrderDetailsXML();
    //url += "&orderXML=" + createOrderXML();
    
    xmlHttp.send(url);
    document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
    window.location.href="checkOutSuccess.jsp";
}

function checkoutCallback(callback){
    if(xmlHttp.status == 200){
        var resp = xmlHttp.response;
        if(resp == "checkout successful!"){
            alert("Thanks you!");
            console.log("checkOut OK!");
            if(callback != undefined){
                callback();
            }
            return true;
        }
    }else if (resp == "emtpy Cart or error."){
        console.log("else if");
        return false;
    }else{
        console.log("out of else if");
    }

}

function ajaxFunction(req,callback) {
    //this.LoginCallBack = callback;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Msxm12.XMLHTTP");
    }
    if(xmlhttp) {
        //  var txtname = username.value;
        // var txtpwd = document.getElementById(password);
        if(req.type =="GET"){
            var reqURL = req.servlet;
            if(req.param!=undefined){
                reqURL =reqURL+"?"+ req.param;
            }
            xmlhttp.open(req.type,reqURL,false); //getname will be the servlet name
            xmlhttp.onreadystatechange  = callback;
            xmlhttp.send(null); //Posting txtname to Servlet

        }else if(req.type =="POST"){
            xmlhttp.open(req.type,req.servlet,false); //getname will be the servlet name
            xmlhttp.onreadystatechange  = callback;
            // xmlhttp.setRequestHeader('Accept', 'text/html,application/xhtml');
            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xmlhttp.send(req.param); //Posting txtname to Servlet
        }else{
            console.log("no method");
        }

    }

}

function cout(){
    window.location.href="checkOutSuccess.jsp";
}