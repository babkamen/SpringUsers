<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="/resources/css/tablecloth.css" rel="stylesheet">
<link href="/resources/css/prettify.css" rel="stylesheet">

<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.metadata.js"></script>
<script type="text/javascript" src="/resources/js/jquery.tablecloth.js"></script>
<script type="text/javascript" src="/resources/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("table").tablecloth({
            theme: "dark",
            bordered: true,
            condensed: true,
            striped: true,
            sortable: true,
            clean: true,
            cleanElements: "thead,tbody,tr,th"

        });

    })
</script>
<div class="container">
    <input type="hidden" name="id" value="0"/>
    <c:if test="${idError!=null}">
        <div class="alert alert-error">

            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4 class="text-center">No element is chosen!</h4>
        </div>
    </c:if>
    <table>
        <form method="get" action="${pageContext.request.contextPath}/users/edit">

            <thead>
            <tr>
                <sec:authorize access='hasRole("admin")'>
                    <th></th>
                </sec:authorize>

                <th>Username</th>
                <th>Email</th>
                <th>Password</th>
                <th>About</th>
                <th>User's group</th>
                <th>User's roles</th>


            </tr>
            </thead>
            <tbody>

            <tr>
                <c:forEach items="${users}" var="user">
                <sec:authorize access='hasRole("admin")'>
                    <td><input type="radio" name="id" value="${user.id}"/></td>
                </sec:authorize>
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
                <td><c:forEach items="${user.roles}" var="role">
                    ${role.name}<br>
                </c:forEach>
                </td>

            </tr>
            </c:forEach>
            </tbody>
    </table>

    <sec:authorize access='hasRole("admin")'>
        <a href="${pageContext.request.contextPath}/users/add" class="btn btn-primary">Add</a>
        <button type="submit" class="btn btn-primary" onsubmit="checkFields()">Edit</button>
        <button type="submit" formaction="${pageContext.request.contextPath}/users/delete" class="btn btn-primary"
                onsubmit="checkFields()">Delete
        </button>
    </sec:authorize>

    </form>
</div>
