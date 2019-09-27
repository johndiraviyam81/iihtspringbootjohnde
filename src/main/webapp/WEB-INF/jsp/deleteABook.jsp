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

	<table border="1">
		<tr>
			<th>Book Id</th>
			<th>Title</th>
			<th>Price</th>
			<th>Volume</th>
			<th>Published Date</th>
				<th>Delete</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.bookId}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.volume}</td>
				<td>${book.publishDate}</td>
						<td><a href = "/bookssubjectjpaboot/books/deleteABookDelete/${book.bookId}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>