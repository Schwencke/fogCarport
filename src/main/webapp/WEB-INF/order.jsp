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
        <!--
        You are now logged in as a CUSTOMER.<br>
        Role: ${sessionScope.role}<br>
        Role ID: ${sessionScope.user.roleId}<br>
        User: ${sessionScope.user}<br>
        Name: ${sessionScope.user.name}<br>
        Email: ${sessionScope.email}<br>
        Address: ${sessionScope.user.address}<br>
        PostalCode: ${sessionScope.user.postalCode}<br>
        City: ${sessionScope.city}<br>
        PhoneNo: ${sessionScope.user.phoneNo}<br>
        -->
        <c:if test="${sessionScope.role == 'customer'}">
        <c:choose>
            <c:when test="${sessionScope.orderlist != null}">
                <table class="table table-striped">
                    <thead>
                    <th>Ordre nr.</th>
                    <th>Oprettet</th>
                    <th>Opdateret</th>
                    <th>Pris</th>
                    <th>Status</th>
                    </thead>
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.timeCreated}</td>
                        <td>${order.timeUpdated}</td>
                        <td>${order.price}</td>
                        <td>${applicationScope.status.get(order.statusId)}</td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                Der blev ikke fundet nogen tidligere eller igangværende ordre.
                Byg din første carport <a class="text-dark" href="${pageContext.request.contextPath}">her</a>.
            </c:otherwise>
        </c:choose>
        </c:if>
        <c:if test="${sessionScope.role == 'salesperson'}">

                    <div class="row">
                        <div class="col-6">
                            <table class="table table-striped">
                                <thead>
                                <th>Bruger ID</th>
                                <th>Navn</th>
                                <th>Adresse</th>
                                <th>By</th>
                                <th>Telefon</th>
                                <th>Email</th>
                                </thead>
                                <tr>
                                    <td> ${sessionScope.orderuser.userId}</td>
                                    <td> ${sessionScope.orderuser.name}</td>
                                    <td> ${sessionScope.orderuser.address}</td>
                                    <td> ${sessionScope.orderuser.postalCode} ${applicationScope.cities.get(sessionScope.orderuser.postalCode)}</td>
                                    <td>${sessionScope.orderuser.phoneNo}</td>
                                    <td>${sessionScope.orderuser.email}</td>
                                </tr>
                            </table>
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
                                <button class="btn btn-outline-success" type="submit">Beregn</button>
                            </form>
                            <button class="btn btn-outline-primary" type="button" data-toggle="collapse"
                                    data-target="#collapseUpdate" aria-expanded="false" aria-controls="collapseUpdate">
                                Rediger ordre
                            </button>
                            <form action="#" method="post">
                            <input type="hidden" value="${sessionScope.order.orderId}">
                            <button class="btn btn-outline-danger" type="submit">Slet ordre</button>
                            </form>
                            <div class="collapse" id="collapseUpdate">
                                <div class="card card-body">
                                    <form class="px-4 py-3" action="${pageContext.request.contextPath}/fc/updatemeasurements"
                                          method="post">
                                        <table class="table table-striped">
                                            <thead>
                                            <th>Carport Længde</th>
                                            <th>Carport Bredde</th>
                                            </thead>
                                            <tr>
                                                <td><select id="carportlength" name="carportlength" type="text">
                                                    <option value="${sessionScope.order.carportLength}">${sessionScope.order.carportLength}</option>
                                                    <c:forEach var="carportLength" items="${applicationScope.carportLength}">
                                                        <option value="${carportLength}">${carportLength}</option>
                                                    </c:forEach>
                                                </select></td>
                                                <td><select id="carportwidth" name="carportwidth" type="text">
                                                    <option value="${sessionScope.order.carportWidth}">${sessionScope.order.carportWidth}</option>
                                                    <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                                                        <option value="${carportWidth}">${carportWidth}</option>
                                                    </c:forEach>
                                                </select></td>
                                            </tr>
                                        </table>
                                        <table class="table table-striped">
                                            <thead>
                                            <th>Skur Længde</th>
                                            <th>Skur Bredde</th>
                                            </thead>
                                            <tr>
                                                <td><select id="shedLength" name="shedlength" type="text">
                                                    <option value="${sessionScope.order.shedLength}">${sessionScope.order.shedLength}</option>
                                                    <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                                                        <option value="${shedLength}">${shedLength}</option>
                                                    </c:forEach>
                                                </select></td>
                                                <td><select id="shedWidth" name="shedwidth" type="text">
                                                    <option value="${sessionScope.order.shedWidth}">${sessionScope.order.shedWidth}</option>
                                                    <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                                                        <option value="${shedWidth}">${shedWidth}</option>
                                                    </c:forEach>
                                                </select></td>
                                            </tr>
                                        </table>
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

