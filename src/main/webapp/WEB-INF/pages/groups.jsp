<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<title>Hi ,admin</title>
</head>
<div class="page-header">
<h2 class="text-center">User Groups</h2>
</div>
<body>
<div class="container">
    <div class="search">
    <form class="form-search"  action="/groups/search" method="get">
        <div class="input-append">
            <input type="text" class="span2 search-query" name="q">
            <button type="submit" class="btn"><i class="icon-search icon-white"></i></button>
        </div>
        </form>
    </div>
    <table class="table table-striped table-bordered ">
        <form method="get" action="${pageContext.request.contextPath}/groups/edit" onsubmit="checkValues()">

            <thead>
		<tr>
            <th></th>
            <th>UserGroup</th>
			<th>Logo</th>
			<th>About</th>
            <th>Members</th>

        </tr>
		</thead>
		<tbody>
			<c:forEach items="${userGroups}" var="group">
                <tr>
                <td><input type="radio" name="id" value="${group.id}"/></td>
               <td>${group.title}</td>
                    <td>
                    <c:choose>
                        <c:when test="${group.logo!=null}">
                        ${group.logo}
                        </c:when>
                                     <c:otherwise>
                         <img src="/resources/img/no_logo.jpg"/>
                                     </c:otherwise>
                    </c:choose>
                    </td>
                <td>${group.about}</td>
<td>
    <c:choose>
                <c:when test="${group.users!=null}">
                <c:forEach items="${group.users}" var="user">
                              ${user.name}<br>
                </c:forEach>
                   Count:${group.users.size()}
                </c:when>
        <c:otherwise>
            No members
        </c:otherwise>
              </c:choose>
</td>
                </tr>
		</c:forEach>
</tbody>
	</table>
    <c:if test="${idError!=null}">
        <div class="alert alert-error">

            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>No element is chosen!</h4>
        </div>
    </c:if>
        <a href="${pageContext.request.contextPath}/groups/add" class="btn btn-primary">Add</a>
<button class="btn btn-primary" >Edit</button>
   <button formaction="${pageContext.request.contextPath}/groups/delete" class="btn btn-primary" >Delete</button>
    </form>
</div>
</body>
</html>
