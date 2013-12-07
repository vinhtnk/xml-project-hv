function supports_history_api() {
    return !!(window.history && history.pushState);
}
/*
function swapPhoto(href,id) {
    var req = new XMLHttpRequest();
    req.open("GET",
        "http://localhost:8080/XMLProject/html/" +
        href.split("/").pop() +"?GenreId="+id,
        false);
    req.send(null);
    if (req.status == 200) {
        document.getElementById("b-center-panel").innerHTML = req.responseText.trim();
        setupHistoryClicks();
        return true;
    }
    return false;
}
*/

function addClicker(link) {
    link.addEventListener("click", function(e) {
        if(callAjax.changePage(link.href, link.id, function(){
            showNextPage('listItem');
            jsUtils.setGenreName(link.innerText);
            jsUtils.paging("listItem",6);
        })){
            e.preventDefault();
        }
    /*
     if (swapPhoto(link.href,link.id)) {
     // history.pushState(null, null, link.href);
      e.preventDefault();
    }*/
    }, true);
}

function setupHistoryClicks() {
    var childClicker = document.getElementById("leftPanel").children;
    for(var i =0; i < childClicker.length;i++){
        addClicker(childClicker[i].firstElementChild);
    }
//addClicker(document.getElementById("photonext"));
//addClicker(document.getElementById("photoprev"));
}

function onLoadListener() {
    if (!supports_history_api()) {
        return;
    }
    setupHistoryClicks();
/*
  window.setTimeout(function() {
    window.addEventListener("popstate", function(e) {
      swapPhoto(location.pathname);
    }, false);
  }, 1);*/
}

