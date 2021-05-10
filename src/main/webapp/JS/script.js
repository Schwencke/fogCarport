window.onload = function (){

    if ((localStorage.getItem("carportwidth"))!==null){

        document.getElementById("carportwidth").value = localStorage.getItem("carportwidth");
        document.getElementById("carportlength").value = localStorage.getItem("carportlength");
        document.getElementById("shedWidth").value = localStorage.getItem("shedwidth");
        document.getElementById("shedLength").value = localStorage.getItem("shedlength");
    }
}



function storedata(){
    if(typeof (Storage) !== "undefined"){
    localStorage.setItem("carportwidth",document.getElementById("carportwidth").value);
    localStorage.setItem("carportlength",document.getElementById("carportlength").value);
    localStorage.setItem("shedwidth",document.getElementById("shedWidth").value);
    localStorage.setItem("shedlength",document.getElementById("shedLength").value)
}
}

function cleardata(){
    localStorage.clear();
}