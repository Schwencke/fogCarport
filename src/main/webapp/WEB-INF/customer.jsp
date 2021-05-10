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
                    <th>Ordre nr.</th>
                    <th>Oprettet</th>
                    <th>Opdateret</th>
                    <th>Pris</th>
                    <th>Status</th>
                    </thead>
                    <c:forEach var="orderlist" items="${sessionScope.orderlist}">
                        <c:if test="${orderlist.statusId != 5}">
                            <tr>
                                <td>${orderlist.orderId}</td>
                                <td>${orderlist.timeCreated}</td>
                                <td>${orderlist.timeUpdated}</td>
                                <td>${orderlist.price}</td>
                                <td>${applicationScope.status.get(orderlist.statusId)}</td>
<%--                                <c:if test="${orderlist.statusId == 1}">--%>
<%--                                    <td>--%>
<%--                                        <button type="submit" name="delete" title="Tryk her for at slette ordren"--%>
<%--                                                value="${orderlist.orderId}">Slet ordre--%>
<%--                                        </button>--%>
<%--                                    </td>--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${orderlist.statusId == 2}">--%>
<%--                                    <td>--%>
<%--                                        <button type="submit" name="delete"--%>
<%--                                                title="Du kan ikke slette en gennemført ordre" disabled--%>
<%--                                                value="${orderlist.orderId}">Slet--%>
<%--                                            ordre--%>
<%--                                        </button>--%>
<%--                                    </td>--%>
<%--                                </c:if>--%>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                Der blev ikke fundet nogen tidligere eller igangværende ordre.
                Byg din første carport <a class="text-dark" href="${pageContext.request.contextPath}">her</a>.
            </c:otherwise>
        </c:choose>
        <c:if test="${requestScope.msg != null }">
            <p style="color:green">
                <script>cleardata()</script>
                    ${requestScope.msg}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>

