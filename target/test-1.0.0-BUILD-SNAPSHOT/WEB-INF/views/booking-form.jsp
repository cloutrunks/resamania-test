<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Réserver un créneau</title>
<link rel="stylesheet" href="<c:url value="/resources/jquery/1.12.1/jquery-ui.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<script type="text/javascript" src="<c:url value="/resources/jquery/1.12.1/external/jquery/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/1.12.1/jquery-ui.js" />"></script>

<script>  
		$( function() {
		    $( "#datepicker" ).datepicker();
		} );
</script>
</head>
<body>
	<div id="bookingForm">
		<f:form method="post" action="validBook" commandName="book">
<!-- 			Amélioration:  1)ajout des activités et des salles a la volée apres avoir choisi un supplier -->
<!--   						2) service rest a consommer pr appels -->
			<table>
				<tr>
					<td>Date:</td>
					<td><f:input path="beginDate" id="datepicker"/></td>
				</tr>
				<tr>
					<td>Supplier:</td>
					<td>
						<f:select path="supplier" items="${suppliersList}"  />
					</td>
				</tr>
				<tr>
					<td>Activité:</td><!-- liste déroulante depuis resultats du service rest -->
					<td>
						<f:select path="activity" items="${activitiesList}" />
					</td>
				</tr>
				<tr>
					<td>Salle:</td> <!-- liste déroulante depuis resultats du service rest -->
					<td>
						<f:select path="room" items="${roomsList}" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="s'inscrire"/></td>
				<td>
			</table>
		</f:form>
	</div>
</body>
</html>