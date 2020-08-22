<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	
	<div>
		<img src="E:/BookstoreProject/BookStoreWebsite/WebContent/images/BookstoreLogo.png">
	</div>
	
	<div>
		<input type="text" name="keyword" size="50"/>
		<input type="button" value="Search"/>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="login">Sign In</a> |
		<a href="register">Register</a> |
		<a href="view_cart">Cart</a>		
	</div>
	<div>&nbsp;</div>
	<div>
		<c:forEach var="category" items="${listCategory}">
			<a href="view_category?id=${category.categoryId}">
				<font color="red" face="Comic Sans MS, cursive, sans-serif" 
				><b><c:out value="${category.name}"/></b></font>
			</a>
			&nbsp;&nbsp;
			&nbsp;
			<c:if test="${not status.last}">
			&nbsp;| &nbsp;
			</c:if>
		
		</c:forEach>
	</div>
	
</div>