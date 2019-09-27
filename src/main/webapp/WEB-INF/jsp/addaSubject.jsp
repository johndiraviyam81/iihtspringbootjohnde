<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title> Add a Subject </title>
<style>
#formrecrd{width:420px;}
.divtitle{float:left; width:200px}
</style>
</head>

<body>
	<h2>All Subjects in System</h2>
	 <%@ include file="/WEB-INF/jsp/navigation.jsp" %> 
	 <div style="clear:both" modelAttribute="message"><p>&nbsp; <br><span style="color:red">${message}</span><br></p></div>
 <br>
 <div id="formrecrd">
	<form name="addasubject" id="addasubject" action="addaSubjectSave" method="POST" modelAttribute="subjectrecord">
	
	<div class="divtitle">	SubjectId: </div><div class="divtitle"><input type="text" name="subjectId" /></div>
	<div class="divtitle">	Sub Title:</div><div class="divtitle"><input type="text" name="subtitle" /></div>
	<div class="divtitle">	Duration In Hours:</div><div class="divtitle"><input type="text" name="durationInHours" /></div>
	<div class="divtitle">	Book Id:</div><div class="divtitle"><input type="text" name="bookId" /></div>
	
		
<div class="divtitle"> <input type="submit" value="submit" /></div>
	</form>
</div>
</body>
</html>