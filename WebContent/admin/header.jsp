<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align="center">
	<img src="../images/BookstoreLogo.png">
	<div>
		Welcome, <c:out value="${sessionScope.userEmail}"/>|<a href="logout">Logout</a> <br></br>
	</div>
	<div id="headermenu" >
		<div >
			<a href="list_users"> <img alt="Users" src="../images/users.png"><br>Users
			</a>
		</div>

		<div >
			<a href="list_category"> <img alt="Category"
				src="../images/category.png"><br>Category
			</a>

		</div>

		<div >
			<a href="list_books"> <img alt="Books" src="../images/bookstack.png"><br>Books
			</a>

		</div>

		<div >
			<a href="customers"> <img alt="Customer"
				src="../images/customer.png"><br>Customer
			</a>
		</div>

		<div >
			<a href="reviews"> <img alt="Reviews" src="../images/review.png"><br>Reviews
			</a>
		</div>

		<div >
			<a href="orders"> <img alt="Orders" src="../images/order.png"><br>Order
			</a>
		</div>


	</div>
</div>

