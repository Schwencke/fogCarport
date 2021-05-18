<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/script.js"></script>
    <meta name="theme-color" content="#7952b3">
</head>
<body>
<!--
    This header is inspired by this bootstrap
    example: https://getbootstrap.com/docs/5.0/examples/pricing/
-->
<header class="d-flex flex-column flex-md-row align-items-center p-3 pb-0 px-md-4 mb-4 bg-white border-bottom shadow-sm">
    <div class="h5 my-0 me-md-auto fw-normal">
        <p>Fog® - Byg selv carport</p>
        <p style="font-size: larger">
            <jsp:invoke fragment="header"/>
        </p>
    </div>
    <nav class="my-2 my-md-0 me-md-3">
        <c:if test="${addHomeLink == null }">
            <a class="p-2 text-dark" href="<%=request.getContextPath()%>">Forside</a>
        </c:if>
        <c:if test="${sessionScope.role == 'customer'}">
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/customer">Oversigt</a>
        </c:if>
        <c:if test="${sessionScope.role == 'salesperson'}">
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/admin">Oversigt</a>
        </c:if>
    </nav>

    <div>
        <c:if test="${sessionScope.user != null }">
            ${sessionScope.user.email}&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>

        <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
        <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'login.jsp')}"/>
        <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'signup.jsp')}"/>

        <c:if test="${isNotLoginPage}">
            <c:if test="${sessionScope.user != null }">
                <a type="button" class="btn btn-sm  btn-outline-secondary"
                   href="${pageContext.request.contextPath}/fc/commandlogout">Log ud</a>
            </c:if>

            <c:if test="${sessionScope.user == null}">
                <!--Dynamisk login/signup-->
                <div class="dropdown-menu-left">
                <button class="btn btn-sm btn-outline-secondary" type="button" id="dropdownLogin"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Login
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownLogin">
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
                        <button type="submit" class="btn btn-primary mt-2">Login</button>
                    </form>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/fc/signup">Ikke
                        registeret endnu?</a>
                </div>
                <c:if test="${isNotRegisterPage}">
                    <a type="button" class="btn btn-sm  btn-outline-secondary"
                       href="${pageContext.request.contextPath}/fc/signup">Opret bruger</a>
                </c:if>
            </c:if>
            </div>
        </c:if>
    </div>

</header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
    <hr>
    <br>
    <p align="center">Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</p>
    <jsp:invoke fragment="footer"/>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
</body>
</html>