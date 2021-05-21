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
        <c:choose>
            <c:when test="${sessionScope.orderlist != null}">
                <table class="table table-striped">
                    <thead>
                    <th style="width: 8%">Ref. nr.</th>
                    <th style="width: 8%">Kunde nr.</th>
                    <th style="width: 12%">Telefon nr.</th>
                    <th style="width: 20%">Fulde navn</th>
                    <th style="width: 18%">Oprettelse</th>
                    <th style="width: 18%">Seneste ændring</th>
                    <th>Status</th>
                    <th style="width: 10%"></th>
                    </thead>
                    <c:forEach var="orderlist" items="${sessionScope.orderlist}">
                        <c:if test="${orderlist.statusId != 99}">
                            <form action="${pageContext.request.contextPath}/fc/adminorder" method="post">
                                <tr>
                                    <td>${orderlist.orderId}</td>
                                    <td>${orderlist.userId}</td>
                                    <td>${sessionScope.userlist.get(orderlist.userId).phoneNo}</td>
                                    <td>${sessionScope.userlist.get(orderlist.userId).name}</td>
                                    <td>${orderlist.timeCreated}</td>
                                    <td>${orderlist.timeUpdated}</td>
                                    <td>${applicationScope.status.get(orderlist.statusId)}</td>
                                    <td align="right">
                                        <input type="hidden" value="${orderlist.userId}" name="user_id">
                                        <input type="hidden" value="${orderlist.orderId}" name="order">
                                        <button type="submit" class="btn btn-outline-primary btn-sm"
                                                title="Tryk her for at se den valgte ordre"
                                                value="${orderlist.orderId}">Se ordre
                                        </button>
                                    </td>
                                </tr>
                            </form>
                        </c:if>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                Der blev ikke fundet nogen ordre.
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>
