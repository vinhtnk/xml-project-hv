function supports_history_api() {
    return !!(window.history && history.pushState);
}

function addClicker(link) {
    link.addEventListener("click", function(e) {
        if(callAjax.changePage(link.href, link.id, function(){
            showNextPage('listItem');
            jsUtils.setGenreName(link.innerText);
            jsUtils.paging("listItem",6);
        })){
            e.preventDefault();
        }
   
    }, true);
}

function setupHistoryClicks() {
    var childClicker = document.getElementById("leftPanel").children;
    for(var i =0; i < childClicker.length;i++){
        addClicker(childClicker[i].firstElementChild);
    }

}

function onLoadListener() {
    if (!supports_history_api()) {
        return;
    }
    setupHistoryClicks();

}

