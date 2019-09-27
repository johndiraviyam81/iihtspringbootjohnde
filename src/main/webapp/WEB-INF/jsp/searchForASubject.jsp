<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Books web application</title>
</head>

<body>
	<h2>All Subjects in System</h2>
	 <%@ include file="/WEB-INF/jsp/navigation.jsp" %> 
	 <div style="clear:both"><p>&nbsp;</p></div>
	 
	  <br>
	<form name="searchasubject" id="searchasubject" action="searchasubject" method="POST" modelAttribute="subjectrecord">
	
		title:<input type="text" name="title" /><br>
	
 <input type="submit" value="submit" />
	</form>
	<div style="clear:both"><p>&nbsp;</p></div>

	<table border="1">
		<tr>
			<th>Subject Id</th>
			<th>Sub Title</th>
			<th>Duration Hours</th>
			<th>Book Id</th>
		
		
		</tr>
		<c:forEach items="${subjects}" var="subject">
			<tr>
				<td>${subject.subjectId}</td>
				<td>${subject.subtitle}</td>
				<td>${subject.durationInHours}</td>
				<td>${subject.bookId}</td>
			
			</tr>
		</c:forEach>
	</table>

</body>
</html>