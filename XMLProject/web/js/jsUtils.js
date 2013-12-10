function jsUtils() {   
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

jsUtils.setDefaultPageId = function(){
    sessionStorage.setItem("currentPage","listItem");
    sessionStorage.setItem("previousPage","");
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

jsUtils.showNewPage = function(nextPageId){
    var currentPage = jsUtils.currentPageId();
    if(currentPage == nextPageId){
        return;
    }
    this.ele = document.getElementById(currentPage);
    jsUtils.removeClass(this.ele, "onShow" );
    jsUtils.addClass(this.ele, "onHide" );

    this.ele = document.getElementById(nextPageId);
    jsUtils.removeClass(this.ele, "onHide" );
    jsUtils.addClass(this.ele, "onShow");

    sessionStorage.setItem("previousPage",currentPage);
    sessionStorage.setItem("currentPage",nextPageId);
    

}

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
   
jsUtils.setGenreName = function(GenreName){
    document.getElementById("genreSelect").innerText = "[ "+GenreName+" ]";
    document.getElementById("genreSelect").textContent = "[ "+GenreName+" ]";
}


jsUtils.paging = function(divFather,numberItem){
    var childLength = document.getElementById(divFather).children.length;
    var ul = document.getElementById("paging-ul");
    if(ul.children.length >0){
        var divClear = document.getElementById("divClear");
        ul.removeChild(divClear);
    }
    var div = document.createElement("div");
    div.setAttribute("id", "divClear");
    ul.appendChild(div);
    if(childLength > numberItem){
        var pageNum = parseInt(childLength / numberItem,10) +1;
    }else {
        var pageNum = 1;
    }
    for(var i =1; i<= pageNum+1; i++){
        
        var li = document.createElement("li");
        li.setAttribute("id", "page"+i);
        var a = document.createElement("a");
        a.className = "pure-button";
        a.href ="";
        if(i==pageNum+1){
            a.className +=" pageStatus";
            a.innerHTML = "-[ 1 ]-";
            a.setAttribute("onclick", "return false;");

        }else{
            a.innerText = i;
            a.setAttribute("onclick", "jsUtils.gotoPage('"+divFather+"',"+numberItem+","+i+");return false;");
        }
        
        li.appendChild(a);
        div.appendChild(li);
        this.pageStatus = pageNum+1;
    }
    jsUtils.gotoPage(divFather,numberItem,1);

}

jsUtils.gotoPage = function(divFather,numberItem, pageNum){
    var childLength = document.getElementById(divFather).children.length;
    document.getElementById("page"+this.pageStatus).firstChild.innerText = "-[ "+pageNum+" ]-"
    if(childLength < numberItem){
        return;
    }else{
        var startShow = numberItem * (pageNum-1);
        var endShow = startShow + numberItem-1;
        if(endShow>childLength)
            endShow = childLength;
        for(i=0;i<childLength;i++){
            if(i<startShow || i>(endShow)){
                jsUtils.addClass(document.getElementById(divFather).children[i],"onHide");
            }else{
                jsUtils.removeClass(document.getElementById(divFather).children[i],"onHide");
            }
        }
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

jsUtils.saveCart=function(itemNoId,numOfSelectItem){
    if(!this.inCart){
        this.inCart = [];
        this.addedItem = [];
    }
    if(!this.inCart[itemNoId]){
        this.addedItem.push(itemNoId);
    }
    this.inCart[itemNoId] = numOfSelectItem;
    if(this.inCart[itemNoId] == 0){
        for(var i = 0; i < this.addedItem.length; i++ ){
            if(this.addedItem[i] == itemNoId){
                this.addedItem.splice(i,1);
            }
        }
    }
    
}
jsUtils.getSaveCartHTML = function(){
    var saveCart = document.getElementById("top-right-panel").innerHTML;  
}

jsUtils.saveNumberOfItem = function(itemNoId,numOfSelectItem){
    if(!this.numberItem){
        return;
    }
}


jsUtils.addToCart = function(itemNoId,isOnchange){
    if(jsUtils.sessionUser().UID=="" || jsUtils.sessionUser().UID==undefined){
        alert("You must login before add item(s) to cart.");
        return;
    }


    var rightPanel = document.getElementById("listAdded");
    var moneyTotal = document.getElementById("moneyTotal");
    if(moneyTotal.innerText != ""){
        var tempMoneyTotal =  parseFloat(moneyTotal.innerText);
        //if remove when not available item on list

        if(document.getElementById(itemNoId) == null){
            var priceOfItem = parseFloat(jsUtils.id("cart-"+itemNoId).children[1].children[1].innerText.replace("$",""));
        }else{
            var priceOfItem = parseFloat(jsUtils.id(itemNoId).children[1].children[1].innerText.replace("$",""));
        }
    
        var afterPrice = priceOfItem + tempMoneyTotal;
        if(tempMoneyTotal> 500 ||  afterPrice > 500 && !isOnchange ){
            alert("If you want to buy more than $500,please contact us follow info at the bottom of website.");
            return;
        }
    }else{
        var tempMoneyTotal =  0;
    }

    var noId = itemNoId.split("-").pop();
    //var noId = parseInt(itemNoId.replace("itemNo",""),10);
    console.log("AddToCart function of NoID : " +noId );
    if(document.getElementById(itemNoId)!= null){
        var listChildNode = document.getElementById(itemNoId).innerHTML;
    }else {
        var listChildNode = document.getElementById("cart-"+itemNoId).innerHTML;
    }
    //check if availd or not
    //also check if degree result less than 0
    
    if(!document.getElementById("cart-"+itemNoId)){
        var rightItem = document.createElement("div");
        rightItem.setAttribute("id", "cart-"+itemNoId);
        rightItem.setAttribute("class", "right-item");

        rightItem.innerHTML = listChildNode.trim();
        var descNode = rightItem.children[1].children[2];
        descNode.parentNode.removeChild(descNode);

        //leftItem.appendChild(clonelistChildNode[0]);
        // leftItem.appendChild(clonelistChildNode[0].children[1]);

        var closeButton = document.createElement("a");
        closeButton.setAttribute("href", "#");
        closeButton.setAttribute("onclick", "jsUtils.removeFromCart('"+itemNoId+"');return false;");
        closeButton.innerHTML  = "<img class='close-button' src='./img/button/closeButton.png'>";


        var addedNumber = document.createElement("input");
        addedNumber.setAttribute("type","number");
        addedNumber.setAttribute("id", "NumberOfItem-"+noId);
        addedNumber.setAttribute("class", "countNo");
        addedNumber.setAttribute("name", "txtNum");
        addedNumber.setAttribute("onclick", "this.select();");        
        addedNumber.setAttribute("onchange", "validUtils.itemCheck(this.value,"+noId+");");   
        addedNumber.value = 0;

        rightItem.appendChild(closeButton);
        rightItem.appendChild(addedNumber);
        rightPanel.appendChild(rightItem);
        if(jsUtils.hasClass(jsUtils.id('cartTotal'), "onHide")){
            jsUtils.removeClass(document.getElementById('cartTotal'), "onHide");
            document.getElementById("yourCart").className = "hasCart";
        }
    }
    var currentItemId = document.getElementById("cart-"+itemNoId);
    var totalOne  = 0;
    var selectItemPrice = parseFloat(currentItemId.children[1].children[1].innerText.replace("$",""));
    var numOfSelectItem = parseInt(currentItemId.children[3].value);
    if(isOnchange){
        var moneyTotalValue = 0;
        var listLength = rightPanel.children.length;
        for(var i = 0; i<listLength; i++ ){
            if(currentItemId != rightPanel.children[i]){
                var price = parseFloat(rightPanel.children[i].children[1].children[1].innerText.replace("$",""));
                var numOfItem = parseInt(rightPanel.children[i].children[3].value);
                totalOne = price*numOfItem;
                moneyTotalValue+= totalOne;
                totalOne  = 0;
            }
        }
        tempMoneyTotal = (selectItemPrice * numOfSelectItem) + moneyTotalValue ;
        if(tempMoneyTotal>500 ){
            numOfSelectItem = parseInt((500 - moneyTotalValue)/selectItemPrice,10);
            tempMoneyTotal = (selectItemPrice * numOfSelectItem) + moneyTotalValue;
            alert("If you want to buy more than $500,please contact us follow info at the bottom of website.");
           
        }

    }else{
        tempMoneyTotal += selectItemPrice;
        numOfSelectItem++;
    }
    tempMoneyTotal = tempMoneyTotal.toFixed(2);
    moneyTotal.innerText  = tempMoneyTotal;
    currentItemId.children[3].value = numOfSelectItem;
//  jsUtils.saveNumberOfItem(itemNoId,numOfSelectItem);
    
    
}

jsUtils.showPopUp = function(itemNoId){
    // var selectId = document.getElementById(itemNoId);
    var copyData = document.getElementById(itemNoId).parentNode.innerHTML;
    document.getElementById("popUp").children[1].innerHTML = copyData;
    jsUtils.removeClass(document.getElementById('popUp'),"onHide");
    jsUtils.removeClass(jsUtils.id('popUp'),"onHide");
    var Desc = jsUtils.id('popUp').children[1].children[0].children[1].children[2];
    jsUtils.removeClass(Desc, "onHide");
}

var ajaxFunction = function(req,callback) {
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
