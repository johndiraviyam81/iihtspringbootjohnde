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
	 <div style="clear:both"><p>&nbsp;</p></div>
	 
	  <br>
	<form name="searchabook" id="searchabook" action="searchabook" method="POST" modelAttribute="bookrecord">
	
		Book ID:<input type="text" name="bookId" /><br>
	
 <input type="submit" value="submit" />
	</form>
	<div style="clear:both"><p>&nbsp;</p></div>

	<table border="1">
		<tr>
			<th>Book Id</th>
			<th>Title</th>
			<th>Price</th>
			<th>Volume</th>
			<th>Published Date</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.bookId}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.volume}</td>
				<td>${book.publishDate}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>