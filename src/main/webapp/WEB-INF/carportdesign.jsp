<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Carport design
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <label for="width">Carport bredde</label>
            <select id="width" type="text">
            </select><br>

        <label for="længde">Carport længde</label>
            <select id="længde" type="text">
            </select><br>

        <label for="roof">Tag</label>
            <select id="roof" type="text">
            </select><br>

        <label for="shed">Redskabsrum<br>Redskabsrum:
            NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</label>
            <select id="shed" type="text">
            </select><br>
        <label for="shedWidth">Redskabsrum bredde</label>
            <select id="shedWidth" type="text">
            </select><br>
        <label for="shedLength">Redskabsrum længde</label>
            <select id="shedLength" type="text">
            </select><br>

        <label for="name">Navn</label>
        <input id="name" type="text"><br>
        <label for="adress">Adresse</label>
        <input id="adress" type="text"><br>
        <label for="zipAndCity">Postnummer og by</label>
        <input id="zipAndCity" type="text"><br>
        <label for="phoneNo">Telefon</label>
        <input id="phoneNo" type="text"><br>
        <label for="email">E-mail adresse</label>
        <input id="email" type="text"><br>
        <label for="etc">Evt. bemærkninger</label>
        <input id="etc" type="text"><br>
        <button type="submit">Send forspørgsel</button>
        <p>* Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle 210x330 cm.</p>

    </jsp:body>
</t:genericpage>