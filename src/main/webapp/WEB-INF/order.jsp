<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Ordre
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <script>
            if (localStorage != null) {
                cleardata();
            }
        </script>
        <div class="container">
        <c:if test="${sessionScope.role == 'customer'}">
            <div class="row">
                <div class="col">
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%">Kundeoplysninger</th>
                        <th></th>
                        </thead>
                        <tr>
                            <td>Fulde navn:</td>
                            <td>${sessionScope.user.name}</td>
                        </tr>
                        <tr>
                            <td>Adresse:</td>
                            <td>${sessionScope.user.address}</td>
                        </tr>
                        <tr>
                            <td>Postnummer</td>
                            <td>${sessionScope.user.postalCode}</td>
                        </tr>
                        <tr>
                            <td>By</td>
                            <td>${applicationScope.cities.get(sessionScope.user.postalCode)}</td>
                        </tr>
                        <tr>
                            <td>Telefon nr.:</td>
                            <td>${sessionScope.user.phoneNo}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${sessionScope.user.email}</td>
                        </tr>
                    </table>
                </div>
                <div class="col">
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%">Specifikationer</th>
                        <th></th>
                        </thead>
                        <tr>
                            <td>Ref. nr.:</td>
                            <td>${sessionScope.order.orderId}</td>
                        </tr>
                        <tr>
                            <td>Oprettet:</td>
                            <td>${sessionScope.order.timeCreated}</td>
                        </tr>
                        <tr>
                            <td>Ændret:</td>
                            <td>${sessionScope.order.timeUpdated}</td>
                        </tr>
                        <tr>
                            <td>Status:</td>
                            <td>${applicationScope.status.get(sessionScope.order.statusId)}</td>
                        </tr>
                    </table>
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%">Carport</th>
                        <th></th>
                        </thead>
                        <tr>
                            <td>Bredde:</td>
                            <td>${sessionScope.order.carportWidth} cm</td>
                        </tr>
                        <tr>
                            <td>Længde:</td>
                            <td>${sessionScope.order.carportLength} cm</td>
                        </tr>
                        <tr>
                            <td>Tag:</td>
                            <td>${applicationScope.roofinglist.get(sessionScope.order.roofingId)}</td>
                        </tr>
                    </table>
                    <c:if test="${order.shedWidth > 0}">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Redskabsrum</th>
                            <td></td>
                            </thead>
                            <tr>
                                <td>Bredde:</td>
                                <td>${sessionScope.order.shedWidth} cm</td>
                            </tr>
                            <tr>
                                <td>Længde:</td>
                                <td>${sessionScope.order.shedLength} cm</td>
                            </tr>
                            <tr>
                                <td>Beklædning:</td>
                                <td>${applicationScope.claddinglist.get(sessionScope.order.claddingId)}</td>
                            </tr>
                        </table>
                    </c:if>
                    <table class="table table-striped table-sm">
                        <thead>
                        <th style="width: 40%"></th>
                        <td></td>
                        </thead>
                        <tr>
                            <td>Pris (Dkk):</td>
                            <td>
                                <c:choose>
                                    <c:when test="${sessionScope.order.price == 0}">Afventer tilbud</c:when>
                                    <c:otherwise>${sessionScope.order.price},-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                    <div align="right">
                        <c:if test="${sessionScope.order.statusId == 1}">
                            <button style="width: 20%" class="btn btn-success btn-sm" disabled>Acceptér
                            </button>
                            <button style="width: 20%" class="btn btn-outline-danger btn-sm" disabled>
                                Annullér
                            </button>
                        </c:if>
                        <c:if test="${sessionScope.order.statusId == 2}">
                            <form action="${pageContext.request.contextPath}/fc/customerupdatestatus"
                                  method="post">
                                <input type="hidden" name="orderid"
                                       value="${sessionScope.order.orderId}">
                                <button style="width: 20%" class="btn btn-success btn-sm" type="submit"
                                        name="statusid" value="4">Acceptér
                                </button>
                                <button style="width: 20%" class="btn btn-outline-danger btn-sm"
                                        type="submit"
                                        name="statusid" value="99">Annuller
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.order.statusId == 3}">
                            <button style="width: 100%" class="btn btn-outline-secondary btn-sm btn-block" disabled>
                                Betal
                            </button>
                        </c:if>
                        <c:if test="${sessionScope.order.statusId == 4}">
                            <form action="${pageContext.request.contextPath}/fc/checkout"
                                  method="post">
                                <input type="hidden" name="orderid"
                                       value="${sessionScope.order.orderId}">
                                <button style="width: 100%" class="btn btn-success btn-sm btn-block">
                                    Betal
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
            <c:if test="${sessionScope.order.statusId == 5}">
                <div class="row">
                    <div class="col-12">
                        <c:forEach items="${sessionScope.bommert}" var="materials">
                            <p>${materials}</p>
                        </c:forEach>
                    </div>
                </div>
                    ${sessionScope.svgcustomer}
            </c:if>
        </c:if>
        <c:if test="${sessionScope.role == 'salesperson'}">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Kundeoplysninger</th>
                            <th></th>
                            </thead>
                            <tr>
                                <td>Kunde nr.:</td>
                                <td>${sessionScope.orderuser.userId}</td>
                            </tr>
                            <tr>
                                <td>Fulde navn:</td>
                                <td>${sessionScope.orderuser.name}</td>
                            </tr>
                            <tr>
                                <td>Adresse:</td>
                                <td>${sessionScope.orderuser.address}</td>
                            </tr>
                            <tr>
                                <td>Postnummer</td>
                                <td>${sessionScope.orderuser.postalCode}</td>
                            </tr>
                            <tr>
                                <td>By</td>
                                <td>${applicationScope.cities.get(sessionScope.user.postalCode)}</td>
                            </tr>
                            <tr>
                                <td>Telefon nr.:</td>
                                <td>${sessionScope.orderuser.phoneNo}</td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td>${sessionScope.orderuser.email}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="col">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Specifikationer</th>
                            <th></th>
                            </thead>
                            <tr>
                                <td>Ref. nr.:</td>
                                <td>${sessionScope.order.orderId}</td>
                            </tr>
                            <tr>
                                <td>Oprettet:</td>
                                <td>${sessionScope.order.timeCreated}</td>
                            </tr>
                            <tr>
                                <td>Ændret:</td>
                                <td>${sessionScope.order.timeUpdated}</td>
                            </tr>
                            <tr>
                                <td>Status:</td>
                                <td>${applicationScope.status.get(sessionScope.order.statusId)}</td>
                            </tr>
                        </table>
                        <form action="${pageContext.request.contextPath}/fc/adminupdatemeasurements" method="post">
                            <table class="table table-striped table-sm">
                                <thead>
                                <th style="width: 40%">Carport</th>
                                <c:choose>
                                    <c:when test="${sessionScope.order.statusId >= 2}">
                                        <th><label for="lockCarportCheckDisabled" style="color: gray">Lås</label>
                                            <input type="checkbox" id="lockCarportCheckDisabled" name="lockCarportCheck"
                                                   disabled checked></th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <label for="lockCarportCheck">Lås</label>
                                            <input onclick="lockCarport()" type="checkbox" id="lockCarportCheck"
                                                   name="lockCarportCheck" checked>
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                                </thead>
                                <tr>
                                    <td>Bredde:</td>
                                    <td><select id="carportWidthDropDown" name="carportWidthDropDown" disabled
                                                type="text">
                                        <option value="${sessionScope.order.carportWidth}">${sessionScope.order.carportWidth}</option>
                                        <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                                            <option value="${carportWidth}">${carportWidth}</option>
                                        </c:forEach>
                                    </select> cm
                                    </td>
                                </tr>
                                <tr>
                                    <td>Længde:</td>
                                    <td><select id="carportLengthDropDown" name="carportLengthDropDown" disabled
                                                type="text">
                                        <option value="${sessionScope.order.carportLength}">${sessionScope.order.carportLength}</option>
                                        <c:forEach var="carportLength" items="${applicationScope.carportLength}">
                                            <option value="${carportLength}">${carportLength}</option>
                                        </c:forEach>
                                    </select> cm
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tag:</td>
                                    <td>${applicationScope.roofinglist.get(sessionScope.order.roofingId)}</td>
                                </tr>
                            </table>

                            <c:if test="${sessionScope.order.shedWidth > 0}">
                                <table class="table table-striped table-sm">

                                    <thead>
                                    <th style="width: 40%">Redskabsrum</th>
                                    <c:choose>
                                        <c:when test="${sessionScope.order.statusId >= 2}">
                                            <th><label for="lockShedCheckDisabled" style="color: gray">Lås</label>
                                                <input type="checkbox" id="lockShedCheckDisabled"
                                                       name="lockCarportCheck" disabled checked></th>
                                        </c:when>
                                        <c:otherwise>
                                            <th>
                                                <label for="lockShedCheck">Lås</label>
                                                <input onclick="lockShed()" type="checkbox" id="lockShedCheck"
                                                       name="lockShedCheck" checked>
                                            </th>
                                        </c:otherwise>
                                    </c:choose>
                                    </thead>
                                    <tr>
                                        <td>Bredde:</td>
                                        <td><select id="shedWidthDropDown" name="shedWidthDropDown" disabled
                                                    type="text">
                                            <option value="${sessionScope.order.shedWidth}">${sessionScope.order.shedWidth}</option>
                                            <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                                                <option value="${shedWidth}">${shedWidth}</option>
                                            </c:forEach>
                                        </select> cm
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Længde:</td>
                                        <td><select id="shedLengthDropDown" name="shedLengthDropDown" disabled
                                                    type="text">
                                            <option value="${sessionScope.order.shedLength}">${sessionScope.order.shedLength}</option>
                                            <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                                                <option value="${shedLength}">${shedLength}</option>
                                            </c:forEach>
                                        </select> cm
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Beklædning:</td>
                                        <td>${applicationScope.claddinglist.get(sessionScope.order.claddingId)}</td>
                                    </tr>

                                </table>
                            </c:if>
                            <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                            <button style="float: right" id="updatemeasurements" class="btn btn-outline-primary btn-sm"
                                    disabled type="submit">Opdater mål
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-6">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Pris</th>
                            <th style="width: 25%"></th>
                            <th></th>
                            </thead>
                            <tr>
                                <td>Indkøbspris ex. moms:</td>
                                <td>${sessionScope.bom.basePrice}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test="${sessionScope.order.statusId >= 2}">
                                        <form action="${pageContext.request.contextPath}/fc/admincalculate">
                                            <td>Dækningsgrad:</td>
                                            <td><input style="width: 45%" disabled type="number" name="margin"
                                                       value="${sessionScope.bom.margin}"> %
                                            </td>
                                            <td align="right">
                                                <button class="btn btn-outline-primary btn-sm m-0" disabled
                                                        type="submit">Opdater
                                                    dækningsgrad
                                                </button>
                                            </td>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/fc/admincalculate">
                                            <td>Dækningsgrad:</td>
                                            <td><input style="width: 45%" type="number" name="margin"
                                                       value="${sessionScope.bom.margin}"> %
                                            </td>
                                            <td align="right">
                                                <button class="btn btn-outline-primary btn-sm m-0" type="submit">Opdater
                                                    dækningsgrad
                                                </button>
                                            </td>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td>Dækningsbidrag</td>
                                <c:choose>
                                    <c:when test="${sessionScope.marginprice > sessionScope.baseprice * 0.20}">
                                        <td style="color: green">${sessionScope.marginprice}</td>
                                    </c:when>
                                    <c:when test="${sessionScope.marginprice >= sessionScope.baseprice * 0.11}">
                                        <td style="color: #ff4900">${sessionScope.marginprice}</td>
                                    </c:when>
                                    <c:when test="${sessionScope.marginprice <= sessionScope.baseprice * 0.109}">
                                        <td style="color: red">${sessionScope.marginprice}</td>
                                    </c:when>
                                </c:choose>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tilbudspris ex.moms:</td>
                                <td>${sessionScope.salesprice}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Tilbudspris incl. moms:</td>
                                <td>${sessionScope.vatprice}</td>
                                <td></td>
                            </tr>
                        </table>
                        <button class="btn btn-outline-primary btn-sm" type="button" data-toggle="collapse"
                                data-target="#collapseCarport" aria-expanded="false" aria-controls="collapseCarport">
                            Vis tegning over carport
                        </button>
                    </div>
                    <div class="collapse" id="collapseCarport">
                        <div class="card card-body">${sessionScope.svgdrawing}</div>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col">
                        <table class="table table-striped">
                            <thead>
                            <th>Varenummer</th>
                            <th>Vare</th>
                            <th>Beskrivelse</th>
                            <th>Længde</th>
                            <th>Antal</th>
                            <th>Enhed</th>
                            <th>Pris</th>
                            <th>Total</th>
                            </thead>
                            <c:forEach var="bom" items="${sessionScope.bom.materials}">
                                <tr>
                                    <td>${bom.materialID}</td>
                                    <td>${bom.name}</td>
                                    <td>${bom.description}</td>
                                    <td>${bom.length} mm</td>
                                    <td>${bom.quantity}</td>
                                    <td>${applicationScope.units.get(bom.unitId)}</td>
                                    <td>${bom.price},-</td>
                                    <td>${bom.quantity * bom.price}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row d-inline">
            <c:if test="${sessionScope.order.statusId == 1}">
                <form class="form-group" action="${pageContext.request.contextPath}/fc/adminupdatestatus" method="post">
                    <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                    <input type="hidden" name="statusid" value="99">
                    <button style="float: right; margin-left: 10px" class="btn btn-outline-danger btn-sm" type="submit">
                        Slet ordre
                    </button>
                </form>
                <form class="form-group" action="${pageContext.request.contextPath}/fc/adminupdatestatus" method="post">
                    <input type="hidden" name="orderprice" value="${sessionScope.vatprice}">
                    <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                    <input type="hidden" name="statusid" value="2">
                    <button style="float: right" class="btn btn-outline-success btn-sm" type="submit">Send tilbud
                    </button>
                </form>
                </div>
            </c:if>
            </div>
            <br>
        </c:if>
    </jsp:body>
</t:genericpage>

