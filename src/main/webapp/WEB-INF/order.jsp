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
                                    <td> ${sessionScope.orderuser.postalCode}${applicationScope.cities.get(sessionScope.orderuser.postalCode)}</td>
                                    <td>${sessionScope.orderuser.phoneNo}</td>
                                    <td>${sessionScope.orderuser.email}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-6">

                        </div>
                    </div>
        </c:if>
    </jsp:body>

</t:genericpage>

