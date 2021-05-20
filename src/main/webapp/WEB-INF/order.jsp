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
                                        <th style="width: 40%">Skur</th>
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
                        <form action="${pageContext.request.contextPath}/fc/updatemeasurements" method="post">
                        <table class="table table-striped table-sm">
                            <thead>
                            <th style="width: 40%">Carport</th>
                            <th>
                                <label for="lockCarportCheck">Lås</label>
                                <input onclick="lockCarport()" type="checkbox" id="lockCarportCheck" name="lockCarportCheck" checked>
                            </th>
                            </thead>
                            <tr>
                                <td>Bredde:</td>
                                <td><select id="carportWidthDropDown" name="carportWidthDropDown" disabled type="text">
                                    <option value="${sessionScope.order.carportWidth}">${sessionScope.order.carportWidth}</option>
                                    <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                                        <option value="${carportWidth}">${carportWidth}</option>
                                    </c:forEach>
                                </select> cm
                                </td>
                            </tr>
                            <tr>
                                <td>Længde:</td>
                                <td><select id="carportLengthDropDown" name="carportLengthDropDown" disabled type="text">
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
                                <th style="width: 40%">Skur</th>
                                <th>
                                    <label for="lockShedCheck">Lås</label>
                                    <input onclick="lockShed()" type="checkbox" id="lockShedCheck" name="lockShedCheck" checked>
                                </th>
                                </thead>
                                <tr>
                                    <td>Bredde:</td>
                                    <td><select id="shedWidthDropDown" name="shedWidthDropDown" disabled type="text">
                                        <option value="${sessionScope.order.shedWidth}">${sessionScope.order.shedWidth}</option>
                                        <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                                            <option value="${shedWidth}">${shedWidth}</option>
                                        </c:forEach>
                                    </select> cm</td>
                                </tr>
                                <tr>
                                    <td>Længde:</td>
                                    <td><select id="shedLengthDropDown" name="shedLengthDropDown" disabled type="text">
                                        <option value="${sessionScope.order.shedLength}">${sessionScope.order.shedLength}</option>
                                        <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                                            <option value="${shedLength}">${shedLength}</option>
                                        </c:forEach>
                                    </select> cm</td>
                                </tr>
                                <tr>
                                    <td>Beklædning:</td>
                                    <td>${applicationScope.claddinglist.get(sessionScope.order.claddingId)}</td>
                                </tr>

                            </table>
                        </c:if>
                            <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                            <button class="btn btn-outline-primary" type="submit">Opdater mål</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <form action="#" method="post">
                        <table class="table table-striped">
                            <thead>
                            <th>Carport Bredde</th>
                            <th>Carport Længde</th>
                            <th>Tag</th>
                            <th>Skur Bredde</th>
                            <th>Skur Længde</th>
                            <th>Beklædning</th>
                            </thead>
                            <tr>
                                <td> ${sessionScope.order.carportWidth}</td>
                                <td> ${sessionScope.order.carportLength}</td>
                                <td>${sessionScope.order.roofingId}</td>
                                <td> ${sessionScope.order.shedWidth}</td>
                                <td> ${sessionScope.order.shedLength}</td>
                                <td>${sessionScope.order.claddingId}</td>
                            </tr>
                        </table>
                        <button class="btn btn-outline-success" type="button" data-toggle="collapse"
                                data-target="#collapseCalculate" aria-expanded="false"
                                aria-controls="collapseCalculate">
                            Materialeliste
                        </button>
                        <div class="collapse" id="collapseCalculate">
                            <table>
                                <thead>
                                <th>Materiale ID</th>
                                <th>Beskrivelse</th>
                                <th>Beskrivelse</th>
                                <th>Længde</th>
                                <th>Antal</th>
                                <th>Enhed</th>
                                <th>a'pris</th>
                                <th>Total</th>
                                </thead>
                                <c:forEach var="rafter" items="${sessionScope.rafterList}">
                                    <tr>
                                        <td>${rafter.materialID}</td>
                                        <td>${rafter.name}</td>
                                        <td>${rafter.description}</td>
                                        <td>${rafter.length}</td>
                                        <td>${rafter.quantity}</td>
                                        <td>${applicationScope.units.get(rafter.unitId)}</td>
                                        <td>${rafter.price}</td>
                                        <td>${rafter.quantity * rafter.price}</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="post" items="${sessionScope.postList}">
                                    <tr>
                                        <td>${post.materialID}</td>
                                        <td>${post.name}</td>
                                        <td>${post.description}</td>
                                        <td>${post.length}</td>
                                        <td>${post.quantity}</td>
                                        <td>${applicationScope.units.get(post.unitId)}</td>
                                        <td>${post.price}</td>
                                        <td>${post.quantity * post.price}</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="beam" items="${sessionScope.beamList}">
                                    <tr>
                                        <td>${beam.materialID}</td>
                                        <td>${beam.name}</td>
                                        <td>${beam.description}</td>
                                        <td>${beam.length}</td>
                                        <td>${beam.quantity}</td>
                                        <td>${applicationScope.units.get(beam.unitId)}</td>
                                        <td>${beam.price}</td>
                                        <td>${beam.quantity * beam.price}</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="stern" items="${sessionScope.sternList}">
                                    <tr>
                                        <td>${stern.materialID}</td>
                                        <td>${stern.name}</td>
                                        <td>${stern.description}</td>
                                        <td>${stern.length}</td>
                                        <td>${stern.quantity}</td>
                                        <td>${applicationScope.units.get(stern.unitId)}</td>
                                        <td>${stern.price}</td>
                                        <td>${stern.quantity * stern.price}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </form>
                    <button class="btn btn-outline-primary" type="button" data-toggle="collapse"
                            data-target="#collapseUpdate" aria-expanded="false" aria-controls="collapseUpdate">
                        Rediger ordre
                    </button>
                    <form action="${pageContext.request.contextPath}/fc/updatestatus" method="post">
                        <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                        <input type="hidden" name="statusid" value="99">
                        <button class="btn btn-outline-danger" type="submit">Slet ordre</button>
                    </form>
                    <div class="collapse" id="collapseUpdate">
                        <div class="card card-body">
                            <form class="px-4 py-3" action="${pageContext.request.contextPath}/fc/updatemeasurements"
                                  method="post">
                                <input type="hidden" name="orderid" value="${sessionScope.order.orderId}">
                                <button type="submit">Opdater</button>
                            </form>
                            <div class="dropdown-divider"></div>
                        </div>
                    </div>
                </div>
            </div>

        </c:if>
    </jsp:body>
</t:genericpage>

