<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="${pageContext.request.contextPath}/fc/carportrequest">
        <div class="col-sm-3 col-lg-1"></div>
        <div class="col-sm-6 col-lg-10">
            <div class="row justify-content-center">
                <label for="carportwidth">Carport bredde</label>
                <select id="carportwidth" name="carportwidth" type="text">
                    <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                        <option value="${carportWidth}">${carportWidth}</option>
                    </c:forEach>
                </select>

                <label for="carportlength">Carport længde</label>
                <select id="carportlength" name="carportlength" type="text">
                    <c:forEach var="carportLength" items="${applicationScope.carportLength}">
                        <option value="${carportLength}">${carportLength}</option>
                    </c:forEach>
                </select><br>

                <label for="roof">Tag</label>
                <select id="roof" name="roofing" type="text">
                    <c:forEach var="roofing" items="${applicationScope.roofinglist}">
                        <option value="${roofing.key}">${roofing.value}</option>
                    </c:forEach>
                </select><br>
                <label for="cladding">Beklædning</label>
                <select id="cladding" name="cladding" type="text">
                    <c:forEach var="cladding" items="${applicationScope.claddinglist}">
                        <option value="${cladding.key}">${cladding.value}</option>
                    </c:forEach>
                </select><br>

                <label>Redskabsrum: NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</label>
                <label for="shedWidth">Redskabsrum bredde</label>
                <select id="shedWidth" name="shedwidth" type="text">
                    <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                        <option value="${shedWidth}">${shedWidth}</option>
                    </c:forEach>
                </select><br>
                <label for="shedLength">Redskabsrum længde</label>
                <select id="shedLength" name="shedlength" type="text">
                    <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                        <option value="${shedLength}">${shedLength}</option>
                    </c:forEach>
                </select><br>

<%--                <label for="name">Navn</label>--%>
<%--                <input id="name" name="name" type="text"><br>--%>
<%--                <label for="address">Adresse</label>--%>
<%--                <input id="address" name="address" type="text"><br>--%>
<%--                <label for="postalCode">Postnummer</label>--%>
<%--                <input id="postalCode" name="postalCode">--%>
<%--                <label for="phoneNo">Telefon</label>--%>
<%--                <input id="phoneNo" type="text"><br>--%>
<%--                <label for="email">E-mail adresse</label>--%>
<%--                <input id="email" type="text"><br>--%>
<%--                <label for="etc">Evt. bemærkninger</label>--%>
<%--                <input id="etc" type="text"><br>--%>
            </form>
                <c:choose>
                <c:when test="${sessionScope.user != null}">
                <button class="btn-outline-success mt-5" onclick="cleardata()" type="submit">Send forspørgsel</button>
                </c:when>
                <c:otherwise>
                        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#collapseLogin" aria-expanded="false" aria-controls="collapseExample">
                           Du skal logge ind før du kan sende en forspørgsel
                        </button>
                    <div class="collapse" id="collapseLogin">
                        <div class="card card-body">
                            <form class="px-4 py-3" action="${pageContext.request.contextPath}/fc/commandlogin"
                                  method="post">
                                <div class="form-group">
                                    <label for="email">Email address</label>
                                    <input type="email" class="form-control" id="email" name="email" required
                                           placeholder="Indtast email">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" required
                                           placeholder="Indtast password">
                                </div>
                                <button type="submit" onclick="storedata()" <c:set var="front"  scope="session" value="1"/> class="btn btn-primary mt-2">Login</button>
                            </form>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/fc/registerpage">Ikke
                                registeret endnu?</a>
                        </div>
                    </div>

                    --
                </c:otherwise>
                </c:choose>
                <p>* Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330
                    cm.</p>
        <div class="col-sm-3 col-lg-1"></div>

    </jsp:body>
</t:genericpage>