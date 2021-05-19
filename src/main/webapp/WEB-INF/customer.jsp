<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Oversigt
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
        <c:choose>
            <c:when test="${sessionScope.orderlist != null}">
                <table class="table table-striped">
                    <thead>
                    <th style="width: 7%">Ref. nr.</th>
                    <th style="width: 20%">Seneste ændring</th>
                    <th style="width: 12%">Carport bredde</th>
                    <th style="width: 12%">Carport længde</th>
                    <th style="width: 16%">Tag</th>
                    <th style="width: 7%">Skur</th>
                    <th>Status</th>
                    <th style="width: 10%"></th>
                    </thead>
                    <c:forEach var="orderlist" items="${sessionScope.orderlist}">
                        <c:if test="${orderlist.statusId != 99}">
                            <form action="${pageContext.request.contextPath}/fc/customerorder" method="post">
                                <tr>
                                    <td>${orderlist.orderId}</td>
                                    <td>${orderlist.timeUpdated}</td>
                                    <td>${orderlist.carportWidth} cm</td>
                                    <td>${orderlist.carportLength} cm</td>
                                    <td>${applicationScope.roofinglist.get(orderlist.roofingId)}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${orderlist.shedWidth > 0}">Ja
                                            </c:when>
                                            <c:otherwise>Nej</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${applicationScope.status.get(orderlist.statusId)}</td>
                                    <td align="right">
                                        <input type="hidden" value="${orderlist.orderId}" name="order">
                                        <button type="submit" class="btn btn-outline-primary btn-sm"
                                                title="Tryk her for at se den valgte ordre"
                                                value="${orderlist.orderId}">Se bestilling
                                        </button>
                                    </td>
                                </tr>
                            </form>
                        </c:if>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                Der blev ikke fundet nogen tidligere eller igangværende ordre.
                Byg din første carport <a class="text-dark" href="${pageContext.request.contextPath}">her</a>.
            </c:otherwise>
        </c:choose>
        <c:if test="${requestScope.msg != null}">
            <p style="color:green">
                <script>cleardata()</script>
                    ${requestScope.msg}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>
