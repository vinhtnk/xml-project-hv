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
//    var idbtn = document.getElementById('delP'+id).value;
    var req = {};
        req.type = "POST";
        req.param = "productID=" + id + "&btnAction=delete" ;
        req.servlet = "AdminController";
        var deleteProductCallback = function(){
            callAjax.deleteProductCallBack(callback)
        };
        ajaxFunction(req,deleteProductCallback);

}