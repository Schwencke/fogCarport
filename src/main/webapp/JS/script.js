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
    document.getElementById("updatemeasurements").disabled = true
    if (document.getElementById("lockCarportCheck").checked === true) {
        document.getElementById("carportLengthDropDown").disabled = true
        document.getElementById("carportWidthDropDown").disabled = true
    } else {
        document.getElementById("carportLengthDropDown").disabled = false
        document.getElementById("carportWidthDropDown").disabled = false
    }
}

function lockShed() {
    document.getElementById("updatemeasurements").disabled = true
    if (document.getElementById("lockShedCheck").checked === true) {
        document.getElementById("shedLengthDropDown").disabled = true
        document.getElementById("shedWidthDropDown").disabled = true
    } else {
        document.getElementById("shedLengthDropDown").disabled = false
        document.getElementById("shedWidthDropDown").disabled = false
    }
}

function lockDeliveryAddress() {
    if (document.getElementById("lockDeliveryAddress").checked === true) {
        document.getElementById("deliveryName").disabled = true
        document.getElementById("deliveryAddress").disabled = true
        document.getElementById("deliveryPostalCode").disabled = true
        document.getElementById("deliveryCity").disabled = true
        document.getElementById("deliveryPhoneNo").disabled = true
        document.getElementById("deliveryName").className = "form-control-plaintext"
        document.getElementById("deliveryAddress").className = "form-control-plaintext"
        document.getElementById("deliveryPostalCode").className = "form-control-plaintext"
        document.getElementById("deliveryCity").className = "form-control-plaintext"
        document.getElementById("deliveryPhoneNo").className = "form-control-plaintext"
        document.getElementById("deliveryName").value = document.getElementById("billingName").value;
        document.getElementById("deliveryAddress").value = document.getElementById("billingAddress").value;
        document.getElementById("deliveryPostalCode").value = document.getElementById("billingPostalCode").value;
        document.getElementById("deliveryCity").value = document.getElementById("billingCity").value;
        document.getElementById("deliveryPhoneNo").value = document.getElementById("billingPhoneNo").value;
    } else {
        document.getElementById("deliveryName").disabled = false
        document.getElementById("deliveryAddress").disabled = false
        document.getElementById("deliveryPostalCode").disabled = false
        document.getElementById("deliveryCity").disabled = false
        document.getElementById("deliveryPhoneNo").disabled = false
        document.getElementById("deliveryName").className = "form-control"
        document.getElementById("deliveryAddress").className = "form-control"
        document.getElementById("deliveryPostalCode").className = "form-control"
        document.getElementById("deliveryCity").className = "form-control"
        document.getElementById("deliveryPhoneNo").className = "form-control"
    }
}