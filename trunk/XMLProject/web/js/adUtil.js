/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function deleteProductXML(){
    var deleteProductXML = "<Products>";
    orderXMLDOM += "<Product>";
    orderXMLDOM += "<ProductI>aaaa</ProductId>";
    orderXMLDOM += "<ProductName></ProductName>";
    orderXMLDOM += "<CategoryId></CategoryId>";
    orderXMLDOM += "<Price></Price>";
    orderXMLDOM += "<Description></Description>";
    orderXMLDOM += "<Img_Link></Img_Link>";
    orderXMLDOM += "<New_Product></New_Product>";
    orderXMLDOM += "</Product>";
    orderXMLDOM += "</Products>";
    return deleteProductXML;
}

function deleteProduct(id, callback){
    xmlHttp = getXmlHttpObject();
    if(xmlHttp == null){
        alert("Your browser does not support AJAX");
        return;
    }
    xmlHttp.open("POST", "AdminController");
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var url = "productID=" + id +"&btnAction=delete";
    //url += createOrderDetailsXML();
    //url += "&orderXML=" + createOrderXML();

    xmlHttp.send(url);
    //document.getElementById('btnCheckOut').setAttribute('disabled', 'true');
   // window.location.href="checkOutSuccess.jsp";
}