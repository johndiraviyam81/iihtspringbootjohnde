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
	<h2>All Books in System</h2>
	 <%@ include file="/WEB-INF/jsp/navigation.jsp" %> 
	 <div style="clear:both" modelAttribute="message"><p>&nbsp; <br><span style="color:red">${message}</span><br></p></div>

	  <br>

	<table border="1">
		<tr>
			<th>Subject Id</th>
			<th>Sub Title</th>
			<th>Duration Hours</th>
			<th>Book Id</th>
			<th>Delete</th>
		
		
		</tr>
		<c:forEach items="${subjects}" var="subject">
			<tr>
				<td>${subject.subjectId}</td>
				<td>${subject.subtitle}</td>
				<td>${subject.durationInHours}</td>
				<td>${subject.bookId}</td>
				<td><a href = "/bookssubjectjpaboot/subjects/deleteaSubjectDelete/${subject.subjectId}">Delete</a></td>
			
			</tr>
		</c:forEach>
	</table>

</body>
</html>