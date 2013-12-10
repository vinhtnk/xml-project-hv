function validUtils(){

}

validUtils.itemCheck = function(val,noId){
    //var noId = itemNoId.split("-").pop();
    var itemNoId = "itemNo-"+noId;
    var checkInt = parseInt(val, 10);
    // var oninputValue = "'"+itemNoId+"'";
    if(checkInt < 0 || isNaN(checkInt)){
        document.getElementById("NumberOfItem-"+noId).value = "0";
        jsUtils.addToCart(itemNoId,true);
        return;
    //return false;
    }else if(checkInt > 100){
        document.getElementById("NumberOfItem-"+noId).value = "99";
        jsUtils.addToCart(itemNoId,true);
        return;
    }
    else{
        jsUtils.addToCart(itemNoId,true);
        return;
    // return true;
    }

}

