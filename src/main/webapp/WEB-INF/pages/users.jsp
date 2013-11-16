<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<div class="page-header">
<h2 class="text-center">Users</h2>
    </div>
<body>
       <div class="container">
               <input type="hidden" name="id" value="0"/>

           <table class="table table-striped table-bordered">
               <form method="get" action="${pageContext.request.contextPath}/users/edit">

               <thead>
		<tr>
            <th></th>
			<th>Username</th>
			<th>Email</th>
			<th>Password</th>
            <th>About</th>
            <th>User's group</th>


        </tr>
		</thead>
		<tbody>

        <tr>
			<c:forEach items="${users}" var="user">
                <td><input type="radio" name="id" value="${user.id}"/></td>
               <td>${user.name}</td>
					<td>${user.email}</td>
				<td>${user.password}</td>
                <td>${user.about}</td>
                <td>
            <c:choose>
                <c:when test="${user.userGroup!=null}">
                               ${user.userGroup.title}
                </c:when>
                    <c:otherwise>
                        No group
                    </c:otherwise>

            </c:choose>
                </td>


        </tr>
		</c:forEach>
</tbody>
	</table>
           <a href="${pageContext.request.contextPath}/users/add" class="btn btn-primary">Add</a>
           <button type="submit" class="btn btn-primary">Edit</button>
           <button type="submit" formaction="${pageContext.request.contextPath}/users/delete" class="btn btn-primary">Delete</button>
           </form>

       </div>
</body>
</html>
