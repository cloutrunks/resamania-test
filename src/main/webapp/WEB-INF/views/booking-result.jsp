<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<title>Résultats de la recherche</title>
</head>
<body>
	<f:form method="post" commandName="bookingResult">
		<table class="table1">
				<tr>
					<th></th><th>Date</th><th>Durée</th><th>Salle</th><th>Activité</th><th>Places disponibles</th><th>Coach</th>
				</tr>

<!-- 					information message when no booking matches the search -->
				<c:choose>
  					<c:when test="${empty bookingsList}">
  						<tr>
  						<td colspan="5">Aucune réservation n'est disponible à cette date</td>
  						</tr>
	  				</c:when>
					<c:otherwise>
						<c:forEach items="${bookingsList}" var="b">
							<tr>
								<td><f:radiobutton  value="${b.id}" path="id" /></td>
								<td>${b.beginDate}</td>
								<td>${b.activity.duration}</td>
								<td>${b.room.name}</td>
								<td>${b.activity.name}</td>
								<td>${b.nbPlace - b.nbPlaceInUsed}</td>
								<td>${b.coach}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
<!-- 		submit not implemented !!! -->
		<input type="submit" value="confirmer la réservation"/>
	</f:form>
</body>
</html>