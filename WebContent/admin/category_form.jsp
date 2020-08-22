<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category</title>
<link rel="stylesheet" href="../css/style.css" >
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>


<!-- Javascript formdaki girisi dogrulamak icin kullanilir -->

<script type="text/javascript">
	$(document).ready(function(){
		$("#catForm").validate({
			rules:{
				name:"required"
			},
			messages:{
				name:"Please enter category name"
			}
		});
		
		$("#cancelButton").click(function(){
			history.go(-1);
		});
	})
</script>


</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
		<c:if test="${category != null }">
			<h2>Edit Category</h2>
		</c:if>
		<c:if test="${category==null }">
			<h2>Create New Category</h2>
		</c:if>
	</h2>
	</div>

	<div align="center">

		<c:if test="${category != null }">
			<form action="update_category" method="post" id="catForm">
			<input type="hidden" name="categoryId"value="${category.categoryId}">
		</c:if>

		<c:if test="${category == null }">
			<form action="create_category" method="post" id="catForm">
		</c:if>



		<table>

			<tr>
				<td>Name:</td>
				<td><input type="text" id="name" name="name" size="20"
					value="${category.name}"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save </button>&nbsp;&nbsp;&nbsp;
				<button type="button" id="cancelButton">Cancel</button></td>
			</tr>

		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp" />


</body>

</html>