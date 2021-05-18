<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Forsiden
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="${pageContext.request.contextPath}/fc/carportrequest">
        <div class="row justify-content-center">
            <label for="carportwidth" class="fw-bold">Carport bredde</label>
            <select id="carportwidth" name="carportwidth" type="text">
                <option value="0">Vælg bredde</option>
                <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                    <option value="${carportWidth}">${carportWidth}</option>
                </c:forEach>
            </select>
            <label for="carportlength" class="fw-bold">Carport længde</label>
            <select id="carportlength" name="carportlength" type="text">
                <option value="0">Vælg længde</option>
                <c:forEach var="carportLength" items="${applicationScope.carportLength}">
                    <option value="${carportLength}">${carportLength}</option>
                </c:forEach>
            </select><br>

            <label for="roof" class="fw-bold">Tag</label>
            <select id="roof" name="roofing" type="text">
                <c:forEach var="roofing" items="${applicationScope.roofinglist}">
                    <option value="${roofing.key}">${roofing.value}</option>
                </c:forEach>
            </select><br>

            <label class="fw-bold"><br>Redskabsrum:</label>
            <label>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</label>
            <label for="shedWidth" class="fw-bold">Redskabsrum bredde</label>
            <select id="shedWidth" name="shedwidth" type="text">
                <option value="0">Ønsker ikke redskabsrum</option>
                <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                    <option value="${shedWidth}">${shedWidth}</option>
                </c:forEach>
            </select><br>
            <label for="shedLength" class="fw-bold">Redskabsrum længde</label>
            <select id="shedLength" name="shedlength" type="text">
                <option value="0">Ønsker ikke redskabsrum</option>
                <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                    <option value="${shedLength}">${shedLength}</option>
                </c:forEach>
            </select><br>
            <label for="cladding" class="fw-bold">Beklædning</label>
            <select id="cladding" name="cladding" type="text">
                <c:forEach var="cladding" items="${applicationScope.claddinglist}">
                    <option value="${cladding.key}">${cladding.value}</option>
                </c:forEach>
            </select><br>
        </div>
        <br>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <button class="btn-outline-success mt-5" type="submit">Send forspørgsel</button>
            </c:when>

            <c:otherwise>
                <button class="btn btn-outline-primary" type="button" data-toggle="collapse"
                        data-target="#collapseLogin" aria-expanded="false" aria-controls="collapseLogin">
                    Du skal være logget ind for at sende en forspørgsel
                </button>
                </form>
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
<%--                            <c:set var="stayonindex" scope="session" value="false"/>--%>
                            <button type="submit" onclick="storedata()" class="btn btn-primary mt-2">Login
                            </button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/fc/signup">Ikke
                            registeret endnu?</a>
                    </div>
                </div>

            </c:otherwise>
        </c:choose>
        <br>
        <label>*Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330
            cm.</label>
        <div class="col-sm-3 col-lg-1"></div>
        <%--        <c:if test="${requestScope.msg != null }">--%>
        <%--            <p style="color:green">--%>
        <%--                <script>cleardata()</script>--%>
        <%--                    ${requestScope.msg}--%>
        <%--            </p>--%>
        <%--        </c:if>--%>
        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>