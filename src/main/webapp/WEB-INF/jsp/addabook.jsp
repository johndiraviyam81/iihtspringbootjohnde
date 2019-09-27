<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title> Add a Book </title>
<style>
#formrecrd{width:420px;}
.divtitle{float:left; width:200px}
</style>
</head>

<body>
	<h2>All Books in System</h2>
	 <%@ include file="/WEB-INF/jsp/navigation.jsp" %> 
	 <div style="clear:both" modelAttribute="message"><p>&nbsp; <br><span style="color:red">${message}</span><br></p></div>
 <br>
 <div id="formrecrd">
	<form:form name="addabook" id="addabook" action="addaBookSave" method="POST" modelAttribute="bookrecord">
	<div class="divtitle">	Bookid: </div><div class="divtitle"><form:input type="text" path="bookId" name="bookId" /></div>
	<div class="divtitle">	Title:</div><div class="divtitle"><form:input type="text" path="title" name="title" /></div>
	<div class="divtitle">	Price:</div><div class="divtitle"><form:input type="text" path="price" name="price" /></div>
	<div class="divtitle">	Volume:</div><div class="divtitle"><form:input type="text" path="volume" name="volume" /></div>
	<div class="divtitle">	PublishDate:</div><div class="divtitle"><form:input type="date" path="publishDate" name="publishDate" /></div>
		
<div class="divtitle"> <input type="submit" value="submit" /></div>
	</form:form>
</div>
</body>
</html>