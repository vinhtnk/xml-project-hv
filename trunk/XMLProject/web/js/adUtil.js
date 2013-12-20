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
    // window.location = "admin_page.jsp";
    }
}

function addProductXML(productid,productname,category,price,description,imagelink,newproduct){
    var np = newproduct.value;
    if(np == 'True'){
        np = 1;
    } else {
        np=0;
    }
    var productXMLDOM = "<products>";
    productXMLDOM += "<product>";
    productXMLDOM += "<productID>"+productid.value+"</productID>";
    productXMLDOM += "<productName>"+productname.value+"</productName>";
    productXMLDOM += "<categoryID>"+category.value+"</categoryID>";
    productXMLDOM += "<price>"+price.value+"</price>";
    productXMLDOM += "<description>"+description.value+"</description>";
    productXMLDOM += "<img_link>"+imagelink.value+"</img_link>";
    productXMLDOM += "<new_product>"+np+"</new_product>";
    productXMLDOM += "</product>";
    productXMLDOM += "</products>";
    return productXMLDOM;

   
}
function update(){
    var indent=0;
    indent= indent + 1;
    return indent;
}

function updateProduct(productid,productname,category,price,description,imagelink,newproduct, callback){
    //    var idbtn = document.getElementById('delP'+id).value;
    var req = {};
    req.type = "POST";
    req.param = "productID=" + productid.value +"&productName=" + productname.value+
        "&category=" + category.value+"&price=" + price.value+"&description=" + description.value+
        "&imagelink=" + imagelink.value+ "&newpro=" + newproduct.value +"&btnAction=update" ;
    req.servlet = "AdminController";
    var updateProductCallback = function(){
        callAjax.updateProductCallBack(callback)
    };
    ajaxFunction(req,updateProductCallback);

}
function deleteProduct(id, callback){
    //    var idbtn = document.getElementById('delP'+id).value;
    var r = confirm("Bạn muốn xóa sản phẩm này?");
    if(r==true){
        var req = {};
        req.type = "POST";
        req.param = "productID=" + id + "&btnAction=delete" ;
        req.servlet = "AdminController";
        //    var deleteProductCallback = function(){
        //        callAjax.deleteProductCallBack(callback)
        //    };
        ajaxFunction(req);
        window.location = "admin_page.jsp";
    }
}