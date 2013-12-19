/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function addProduct(productid,productname,category,price,description,imagelink,newproduct,callback){
    if(productid==null){
        alert("productid must be inputed");
    }else
    if(productid!=null && category !=null && productname!=null){
        var req = {};
        req.type = "POST";
        req.param = "btnAction=Addproduct&addProductXML="+addProductXML(productid,productname,category,price,description,imagelink,newproduct);
        req.servlet = "AdminController";
        var addProductCallBack = function(){
            callAjax.addProductCallBack(callback)
        };
        ajaxFunction(req,addProductCallBack);
    }
}

function addProductXML(productid,productname,category,price,description,imagelink,newproduct){
        var productXMLDOM = "<products>";
        productXMLDOM += "<product>";
        productXMLDOM += "<productID>"+productid.value+"</productID>";
        productXMLDOM += "<productName>"+productname.value+"</productName>";
        productXMLDOM += "<CategoryId>"+category.value+"</CategoryId>";
        productXMLDOM += "<price>"+price.value+"</price>";
        productXMLDOM += "<categoryID>"+description.value+"</categoryID>";
        productXMLDOM += "<img_link>"+imagelink.value+"</img_link>";
        productXMLDOM += "<new_product>"+newproduct.value+"</new_product>";
        productXMLDOM += "</product>";
        productXMLDOM += "</products>";
        return productXMLDOM;

   
}
function update(){
    var indent=0;
    indent= indent + 1;
    return indent;
}

function updateProduct(id, callback){
    //    var idbtn = document.getElementById('delP'+id).value;
    var req = {};
    req.type = "POST";
    req.param = "productID=" + id + "&btnAction=update" ;
    req.servlet = "AdminController";
    var updateProductCallback = function(){
        callAjax.updateProductCallBack(callback)
    };
    ajaxFunction(req,updateProductCallback);

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