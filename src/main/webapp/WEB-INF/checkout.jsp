<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Betalingsgateway
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%">Faktureringsadresse</th>
                        <th></th>
                        </thead>
                        <tr>
                            <td><label for="deliveryName" class="form-control-plaintext">Fulde navn:</label></td>
                            <td><input style="width: 100%" class="form-control-plaintext" id="billingName" disabled
                                       value="${sessionScope.user.name}"></td>
                        </tr>
                        <tr>
                            <td><label for="deliveryAddress" class="form-control-plaintext">Adresse:</label></td>
                            <td><input style="width: 100%" class="form-control-plaintext" id="billingAddress" disabled
                                       value="${sessionScope.user.address}"></td>
                        </tr>
                        <tr>
                            <td><label for="deliveryPostalCode" class="form-control-plaintext">Postnummer:</label></td>
                            <td><input style="width: 100%" class="form-control-plaintext" id="billingPostalCode"
                                       disabled value="${sessionScope.user.postalCode}"></td>
                        </tr>
                        <tr>
                            <td><label for="deliveryCity" class="form-control-plaintext">By:</label></td>
                            <td><input style="width: 100%" class="form-control-plaintext" id="billingCity" disabled
                                       value="${applicationScope.cities.get(sessionScope.user.postalCode)}"></td>
                        </tr>
                        <tr>
                            <td><label for="deliveryPhoneNo" class="form-control-plaintext">Telefon nr.:</label></td>
                            <td><input style="width: 100%" class="form-control-plaintext" id="billingPhoneNo" disabled
                                       value="${sessionScope.user.phoneNo}"></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg">
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%">Leveringsadresse</th>
                        <td>
                            <input onclick="lockDeliveryAddress()" type="checkbox" id="lockDeliveryAddress"
                                   name="lockDeliveryAddressCheck" checked>
                            <label for="lockDeliveryAddress">Brug samme adresse til levering</label>
                        </td>
                        </thead>
                        <tr>
                            <td><label for="deliveryName" class="form-control-plaintext">Fulde navn:</label></td>
                            <td>
                                <input style="width: 100%" class="form-control-plaintext" id="deliveryName"
                                       name="deliveryName" disabled value="${sessionScope.user.name}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="deliveryAddress" class="form-control-plaintext">Adresse:</label></td>
                            <td>
                                <input style="width: 100%" class="form-control-plaintext" id="deliveryAddress"
                                       name="deliveryAddress" disabled value="${sessionScope.user.address}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="deliveryPostalCode" class="form-control-plaintext">Postnummer:</label></td>
                            <td>
                                <input style="width: 100%" class="form-control-plaintext" id="deliveryPostalCode"
                                       name="deliveryPostalCode" disabled value="${sessionScope.user.postalCode}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="deliveryCity" class="form-control-plaintext">By:</label></td>
                            <td>
                                <input style="width: 100%" class="form-control-plaintext" id="deliveryCity"
                                       name="deliveryCity" disabled
                                       value="${applicationScope.cities.get(sessionScope.user.postalCode)}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="deliveryPhoneNo" class="form-control-plaintext">Telefon nr.:</label></td>
                            <td>
                                <input style="width: 100%" class="form-control-plaintext" id="deliveryPhoneNo"
                                       name="deliveryPhoneNo" disabled value="${sessionScope.user.phoneNo}">
                            </td>
                        </tr>
                    </table>
                    </form>
                </div>
            </div>
            <div class="col">
                <table class="table table-striped table-sm">
                    <thead>
                    <th style="width: 40%">Ordreoversigt</th>
                    </thead>
                    <tr>
                    </tr>
                </table>
                <div class="w-100 p-3" style="background-color: #eee;">
                    1x Carport (model m. fladt tag)
                    <div style="margin-left: 4%">- Mål
                        (BxL): ${sessionScope.order.carportWidth}x${sessionScope.order.carportLength} cm
                    </div>
                    <div style="margin-left: 4%">-
                        Tag: ${applicationScope.roofinglist.get(sessionScope.order.roofingId)}</div>
                    <br>
                    <c:choose>
                        <c:when test="${sessionScope.order.shedWidth > 0}">
                            1x Redskabsrum
                            <div style="margin-left: 4%">- Mål
                                (BxL): ${sessionScope.order.shedWidth}x${sessionScope.order.shedLength} cm
                            </div>
                            <div style="margin-left: 4%">-
                                Beklædning: ${applicationScope.claddinglist.get(sessionScope.order.claddingId)}</div>
                            <br>
                        </c:when>
                        <c:otherwise>
                            <div style="font-style: italic">OBS: Redskabsrum fravalgt.</div>
                        </c:otherwise>
                    </c:choose>
                    <hr>
                    <div class="row">
                        <div class="col" style="font-weight: bold; color: gray">Subtotal</div>
                        <div class="col" style="font-weight: bold; color: gray"
                             align="right">${sessionScope.order.price} kr
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" style="font-weight: bold; color: gray">Fragt (Fri fragt over 2000 kr)</div>
                        <div class="col" style="font-weight: bold; color: gray" align="right">-</div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col" style="font-weight: bold; font-size: x-large">Total</div>
                        <div class="col" style="font-weight: bold; font-size: x-large"
                             align="right">${sessionScope.order.price} kr
                        </div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col" style="font-weight: bold; font-size: small; color: gray" align="right">Skat og
                            moms er inkluderet
                        </div>
                    </div>
                </div>
                <br>
                <form action="${pageContext.request.contextPath}/fc/payment" method="post">
                    <input type="hidden" name="orderid"
                           value="${sessionScope.order.orderId}">
                    <button style="width: 100%; font-size: x-large" class="btn btn-success btn-sm btn-block"
                            name="statusid" value="5">
                        Gennemfør køb
                    </button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

