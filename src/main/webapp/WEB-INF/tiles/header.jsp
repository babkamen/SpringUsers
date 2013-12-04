<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-inverse">
    <div class="	">
        <div class="navbar-inner">
            <ul class="nav offset2">
                <li>
                    <a href="<c:url value="/"/>">Home</a>
                </li>
                <li class="divider-vertical"></li>
                <li>
                    <a href="<c:url value="/users"/>">Users</a>
                </li>
                <li class="divider-vertical"></li>
                <li>
                    <a href="<c:url value="/groups"/>">Groups</a>
                </li>
                <li class="divider-vertical"></li>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="<c:url value="/j_spring_security_logout"/>">Log out</a>
                    </li>
                    <li class="divider-vertical"/>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li>
                        <a href="<c:url value="/login"/>">Sign in</a>
                    </li>
                    <li class="divider-vertical"/>
                </sec:authorize>

            </ul>
        </div>
    </div>
</div>