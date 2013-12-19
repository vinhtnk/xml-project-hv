/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function addProduct(productid,productname,category,price,description,imagelink,newproduct){
    if(productid==null){
        alert("productid must be inputed");
    }else
    if(productid!=null && category !=null && productname!=null){
        var req = {};
        req.type = "POST";
        req.param = "txtProID=" + productid.value+"&txtProName=" +productname.value+
        "&txtCateID=" +category.value+"&txtPrice=" +price.value+"&txtDes=" +description.value+
        "&txtImg=" +imagelink.value+"&txtNewPro=" +newproduct.value+"&btnAction=Addproduct&addProductXML"+addProductXML();
        req.servlet = "AdminController";
        var loginCallback = function(){
            callAjax.loginCallback(callback)
        };
        ajaxFunction(req,loginCallback);
    }
}

function addProductXML(productid,productname,category,price,description,imagelink,newproduct){
        var productXMLDOM = "<Products>";
        productXMLDOM += "<Product>";
        productXMLDOM += "<ProductI>"+productid.value+"</ProductId>";
        productXMLDOM += "<ProductName>"+productname.value+"</ProductName>";
        productXMLDOM += "<CategoryId>"+category.value+"</CategoryId>";
        productXMLDOM += "<Price>"+price.value+"</Price>";
        productXMLDOM += "<Description>"+description.value+"</Description>";
        productXMLDOM += "<Img_Link>"+imagelink.value+"</Img_Link>";
        productXMLDOM += "<New_Product>"+newproduct.value+"</New_Product>";
        productXMLDOM += "</Product>";
        productXMLDOM += "</Products>";
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