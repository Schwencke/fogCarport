window.onload = function (){
    console.log("førkald")
    if ((localStorage.getItem("carportwidth"))!==null){
        console.log("ikald")
        document.getElementById("carportwidth").value = localStorage.getItem("carportwidth");
        document.getElementById("carportlength").value = localStorage.getItem("carportlength");
    } else console.log("ikkegennemført")
}



function storedata(){
    if(typeof (Storage) !== "undefined"){
    localStorage.setItem("carportwidth",document.getElementById("carportwidth").value);
    localStorage.setItem("carportlength",document.getElementById("carportlength").value)
    console.log("all gut")
}else{
        console.log("not gut")
    }
}

function cleardata(){
    localStorage.clear();
}