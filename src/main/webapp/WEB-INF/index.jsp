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
        <c:choose>
            <c:when test="${sessionScope.role != 'salesperson'}">
                <h3><img style="float: right;" alt="" src="${pageContext.request.contextPath}/images/quickurejs.gif" height="87" width="165" />Quick-Byg tilbud - carport med fladt tag</h3>
                <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.</p>
                <p>Tilbud behandles af vores dygtige sælgere hurtigst muligt.<br />Ved bestilling medfølger standardbyggevejledning.</p>
                <p style="margin-bottom: 50px;"><strong>Udfyld nedenstående omhyggeligt og klik på "Send forspørgsel"</strong>
                <form method="post" action="${pageContext.request.contextPath}/fc/carportrequest">
                <div class="row justify-content-center">
                    <label for="carportwidth" class="fw-bold">Carport bredde</label>
                    <select id="carportwidth" name="carportwidth" type="text">
                        <option value="0">Vælg bredde</option>
                        <c:forEach var="carportWidth" items="${applicationScope.carportWidth}">
                            <option value="${carportWidth}">${carportWidth} cm</option>
                        </c:forEach>
                    </select>
                    <label for="carportlength" class="fw-bold">Carport længde</label>
                    <select id="carportlength" name="carportlength" type="text">
                        <option value="0">Vælg længde</option>
                        <c:forEach var="carportLength" items="${applicationScope.carportLength}">
                            <option value="${carportLength}">${carportLength} cm</option>
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
                    <label for="shedWidth" class="fw-bold mt-3">Redskabsrum bredde</label>
                    <select id="shedWidth" name="shedwidth" type="text">
                        <option value="0">Ønsker ikke redskabsrum</option>
                        <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                            <option value="${shedWidth}">${shedWidth} cm</option>
                        </c:forEach>
                    </select><br>
                    <label for="shedLength" class="fw-bold">Redskabsrum længde</label>
                    <select id="shedLength" name="shedlength" type="text">
                        <option value="0">Ønsker ikke redskabsrum</option>
                        <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                            <option value="${shedLength}">${shedLength} cm</option>
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
                        <button class="btn btn-outline-success mt-2" type="submit" onclick="storedata()">Send forspørgsel</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-primary" type="button" data-toggle="collapse"
                                data-target="#collapseLogin" aria-expanded="false" aria-controls="collapseLogin">
                            Du skal være logget ind for at sende din forspørgsel
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
                                        <input type="password" class="form-control" id="password" name="password"
                                               required
                                               placeholder="Indtast password">
                                    </div>
                                        <%--                            <c:set var="stayonindex" scope="session" value="false"/>--%>
                                    <button class="btn btn-primary mt-2" type="submit" onclick="storedata()">Login
                                    </button>
                                </form>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" onclick="storedata()" href="${pageContext.request.contextPath}/fc/signup">Ikke
                                    registeret endnu?</a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <br>
                <label class="mt-3">*Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle
                    210x330
                    cm.</label>
                <div class="col-sm-3 col-lg-1"></div>
            </c:when>
            <c:otherwise>
                Som salgsperson er det ikke tilladt at bygge carporte.<br>
                Brug i stedet tiden fornuftigt på dine kunders <a class="text-dark" href="${pageContext.request.contextPath}/fc/admin">forespørgsler og ordre</a>.
            </c:otherwise>
        </c:choose>
        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>