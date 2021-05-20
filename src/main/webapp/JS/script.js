window.onload = function () {
    if ((localStorage.getItem("carportwidth")) !== null) {
        document.getElementById("carportwidth").value = localStorage.getItem("carportwidth");
        document.getElementById("carportlength").value = localStorage.getItem("carportlength");
        document.getElementById("shedWidth").value = localStorage.getItem("shedwidth");
        document.getElementById("shedLength").value = localStorage.getItem("shedlength");
    }
}

function storedata() {
    if (typeof (Storage) !== "undefined") {
        localStorage.setItem("carportwidth", document.getElementById("carportwidth").value);
        localStorage.setItem("carportlength", document.getElementById("carportlength").value);
        localStorage.setItem("shedwidth", document.getElementById("shedWidth").value);
        localStorage.setItem("shedlength", document.getElementById("shedLength").value);
    }
}

function cleardata() {
    localStorage.clear();
}

function lockCarport() {
    if (document.getElementById("lockCarportCheck").checked === true) {
        document.getElementById("carportLengthDropDown").disabled = true
        document.getElementById("carportWidthDropDown").disabled = true
    } else {
        document.getElementById("carportLengthDropDown").disabled = false
        document.getElementById("carportWidthDropDown").disabled = false
    }
}

function lockShed() {
    if (document.getElementById("lockShedCheck").checked === true) {
        document.getElementById("shedLengthDropDown").disabled = true
        document.getElementById("shedWidthDropDown").disabled = true
    } else {
        document.getElementById("shedLengthDropDown").disabled = false
        document.getElementById("shedWidthDropDown").disabled = false
    }
}
