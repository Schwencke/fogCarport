<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Register as new User
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div style="margin-top: 5em;">
            <form name="login" action="${pageContext.request.contextPath}/fc/commandsignup" method="post">
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="name">Navn</label>
                    <div class="col-sm-4">
                        <input id="name" class="form-control" type="text" name="name" value="${param.name}" placeholder="Indtast navn">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="address">Adresse</label>
                    <div class="col-sm-4">
                        <input id="address" class="form-control" type="text" name="address" value="${param.address}" placeholder="Indtast adresse">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="postalcode">Postnummer</label>
                    <div class="col-sm-4">
                        <input id="postalcode" class="form-control" type="text" name="postalcode" value="${param.postalcode}" placeholder="Indtast postnummer">
                    </div>
                </div>
                <!--
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="city">By</label>
                    <div class="col-sm-4">
                        <input id="city" class="form-control" type="text" name="city" value="${param.city}" placeholder="Angiv by">
                    </div>
                </div>
                -->
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="phoneno">Telefonnummer</label>
                    <div class="col-sm-4">
                        <input id="phoneno" class="form-control" type="text" name="phoneno" value="${param.phoneno}" placeholder="Indtast telefonnummer">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="email">Email</label>
                    <div class="col-sm-4">
                        <input id="email" class="form-control" type="text" name="email" value="${param.email}" placeholder="Indtast email">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="password1">Password</label>
                    <div class="col-sm-4">
                        <input id="password1" class="form-control" type="password" name="password1"  value="${param.password1}"  placeholder="Indtast password">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-1 col-form-label" for="password2">Password</label>
                    <div class="col-sm-4">
                        <input id="password2" class="form-control" type="password" name="password2" value="${param.password2}"  placeholder="Gentag password">
                    </div>
                </div>

                <input class="btn btn-primary" type="submit" value="Log ind">
            </form>

            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>


