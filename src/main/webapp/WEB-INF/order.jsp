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
        ${sessionScope.totalPrice}
        <c:if test="${sessionScope.role == 'customer'}">
            <c:choose>
                <c:when test="${sessionScope.orderlist != null}">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <table class="table table-striped table-sm">
                                    <thead>
                                    <th style="width: 40%">Kontaktoplysninger</th>
                                    <th></th>
                                    </thead>
                                    <tr>
                                        <td>Navn:</td>
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
                                                    name="statusid" value="3">Acceptér
                                            </button>
                                            <button style="width: 20%" class="btn btn-outline-danger btn-sm"
                                                    type="submit"
                                                    name="statusid" value="99">Annuller
                                            </button>
                                        </form>
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
                    </div>
                </c:when>
                <c:otherwise>
                    Der blev ikke fundet nogen tidligere eller igangværende ordre.
                    Byg din første carport <a class="text-dark" href="${pageContext.request.contextPath}">her</a>.
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${sessionScope.role == 'salesperson'}">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Kontaktoplysninger</th>
                            <th></th>
                            </thead>
                            <tr>
                                <td>Bruger ID:</td>
                                <td>${sessionScope.orderuser.userId}</td>
                            </tr>
                            <tr>
                                <td>Navn:</td>
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
                                <th>
                                    <label for="lockCarportCheck">Lås</label>
                                    <input onclick="lockCarport()" type="checkbox" id="lockCarportCheck"
                                           name="lockCarportCheck" checked>
                                </th>
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
                                    <th>
                                        <label for="lockShedCheck">Lås</label>
                                        <input onclick="lockShed()" type="checkbox" id="lockShedCheck"
                                               name="lockShedCheck" checked>
                                    </th>
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
                            <button style="float: right" class="btn btn-outline-primary" type="submit">Opdater mål</button>
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
                    <th></th>
                    </thead>
                    <tr>
                        <td>indkøbspris ex. moms:</td>
                        <td>${sessionScope.bom.basePrice}</td>
                    </tr>
                    <tr>
                    <form action="${pageContext.request.contextPath}/fc/admincalculate">
                        <td>Dækningsgrad:</td>
                        <td><input type="number" name="margin" value="${sessionScope.bom.margin}">%</td>
                        <button type="submit">tryk</button>
                    </form>
                    </tr>
                    <tr>
                        <td>Dækningsbidrag</td>
                        <td>${sessionScope.marginprice}</td>
                    </tr>
                    <tr>
                        <td>Tilbudspris ex.moms:</td>
                        <td>${sessionScope.salesprice}</td>
                    </tr>
                    <tr>
                        <td>Tilbudspris incl. moms:</td>
                        <td>${sessionScope.vatprice}</td>
                    </tr>
                </table>
            </div></div></div>
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

            <form action="${pageContext.request.contextPath}/fc/adminupdatestatus" method="post">
                <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                <input type="hidden" name="statusid" value="99">
                <button class="btn btn-outline-danger" type="submit">Slet ordre</button>
            </form>

            <a href="${pageContext.request.contextPath}/fc/adminsvgdraw">klik her for tegning</a>
            <br>${requestScope.svgdrawing}

        </c:if>
    </jsp:body>
</t:genericpage>

