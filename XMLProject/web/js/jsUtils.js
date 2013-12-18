var jsUtils = function(){
    
}




function sessionUser(uinfo){
    if(uinfo){
        
        this.uinfo = uinfo;
        return null;
    }else{
        return this.uinfo;
    }
}

function id(id){
    return document.getElementById(id);
}




function showHidePage(thisPage){
    // var page = Pageid;
    var ele = document.getElementById(thisPage);
    if(hasClass(ele, "onHide")){
        removeClass(ele, "onHide" );
    }else{
        addClass(ele, "onHide" );
    }

}


function loginReg(className){
    var login = "loginForm";
    var reg = "regForm";
    if(className == reg){
        var ShouldHide = login;
    }
    else if(className == login){
        var ShouldHide = reg;
    }
    var elem = document.getElementById(ShouldHide);
    showHidePage(className);
    if(!hasClass(elem, "onHide")){
        showHidePage(ShouldHide);
    }

}


function hasClass(ele, className){
    return new RegExp(' ' + className + ' ').test(' ' + ele.className + ' ');
}

function addClass(ele, className){
    if (!hasClass(ele, className)) {
        ele.className += '' + className;
    }
}

// removeClass
function removeClass(ele, className){
    var newClass = ' ' + ele.className.replace( /[\t\r\n]/g, ' ') + ' ';
    if (hasClass(ele, className)) {
        while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
            newClass = newClass.replace(' ' + className + ' ', ' ');
        }
        ele.className = newClass.replace(/^\s+|\s+$/g, '');
    }

}


function removeClassOfChild(idFather, className){
    var father = document.getElementById(idFather);
    var childLength = father.children.length;
    for(var i=0;i<childLength;i++){
        var removeFromChild = father.children[i];
        removeClass(removeFromChild,className) ;
    }
}

function addClassOfChild(isFather,className){
    var father  = document.getElementById(isFather);
    var childLength  = father.children.length;
    for(var i=0;i<childLength;i++){
        var addToChild = father.children[i];
        addClass(addToChild,className) ;
    }
}


function checkType(isSession){

    var classAdd = "onHide";
    //var user = document.getElementById("navBarUser");
    //var guess = document.getElementById("navBarGuess");
    if(isSession != "" && isSession != undefined){

        var login = document.getElementById("Uitem0");
        login.innerText = "Chào "+ isSession + ",";
        //login.textContent = "Hi " + isSession;
       
        removeClassOfChild("navBarUser",classAdd);
        addClassOfChild("navBarGuess", classAdd);

    }else{
        removeClassOfChild("navBarGuess",classAdd);
        addClassOfChild("navBarUser", classAdd);
    }
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
    var cart = eval(sessionStorage.cart);
    var ttp = eval(sessionStorage.totalPrice);
    for(var i = 0; i < cart.length; i++){
        if(cart[i].id == id){            
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

function checkout(email, callback){
    if(email==''||email==null){
        id("loginStatus").innerText ="* Vui lòng đăng nhập trước khi đặt hàng";
        removeClass(id("loginStatus"), "onHide");
        loginReg("loginForm");
    } else{
        addClass(id("loginStatus"), "onHide");
        var req = {};
        req.type = "POST";
        req.param = "email=" + email + "&btnAction=checkout&orderDetailsXML=" + createOrderDetailsXML();
        req.servlet = "CartController";
        var checkoutCallback = function(){
            callAjax.checkoutCallback(callback)
        };
        ajaxFunction(req,checkoutCallback);
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


function login(email,password,callback){
    if(email!=null && password !=null){
        var req = {};
        req.type = "POST";
        req.param = "txtEmail=" + email.value+"&txtPassword=" +password.value+"&btnAction=Login";
        req.servlet = "UserController";
        var loginCallback = function(){
            callAjax.loginCallback(callback)
        };
        ajaxFunction(req,loginCallback);
    }
}

function regAcc(username,gender,address,phone,email,password,callback){
    if(checkRegAcc(username.value, address.value, phone.value, email.value, password.value)){
        var req = {};
        req.type = "POST";
        req.param = "txtUsername=" + username.value + "&txtGender=" + gender.value + "&txtAddress=" 
        + address.value + "&txtPhone=" + phone.value + "&txtEmail=" + email.value
        + "&txtPassword=" + password.value +"&btnAction=Register";
        req.servlet = "UserController";
        var regCallback = function(){
            callAjax.regCallback(email, password);
        };
        ajaxFunction(req,regCallback);
    };
}

function checkRegAcc(username,address,phone,email,password){
    var errors = [];
    var RE_EMAIL = /^(\w+[\-\.])*\w+@(\w+\.)+[A-Za-z]+$/;
    if (trim(username) == "") {
        errors[errors.length] = "Bạn chưa nhập họ tên";
    }

    if (trim(address) == "") {
        errors[errors.length] = "Bạn chưa nhập địa chỉ";
    }

    if (trim(phone) == "") {
        errors[errors.length] = "Bạn chưa nhập số điện thoại";
    }

    if (trim(email) == "") {
        errors[errors.length] = "Bạn chưa nhập email";
    }

    if (!RE_EMAIL.test(email)) {
        errors[errors.length] = "Bạn nhập email không đúng mẫu";
    }

    if (trim(password) == "") {
        errors[errors.length] = "Bạn chưa nhập mật khẩu";
    }

    if (errors.length > 0) {
        var msg = "Xin vui lòng điền đầy đủ thông tin bắt buộc: ";
        for (var i = 0; i < errors.length; i++) {
            var numErr = i + 1;
            msg += "\n" + numErr + ". " + errors[i];
        }
        id("regStatus").innerText ="* "+ msg;
        removeClass(id("regStatus"), "onHide");
        
        return false;
    }
    addClass(id("regStatus"), "onHide");
    return true;
}

function trim(str) {
    var start = 0;
    var end = str.length;
    while (start < str.length && str.charAt(start) == " ")
        start++;
    while (end > 0 && str.charAt(end - 1) == " ")
        end--;
    return str.substr(start, end - start);
}