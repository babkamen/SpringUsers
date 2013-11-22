<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-inverse">
    <div class="	">
        <div class="navbar-inner">
            <ul class="nav offset2">
                <li >
                    <a href="<c:url value="/users"/>">Users</a>
                </li>
                <li class="divider-vertical"></li>
                <li >
                    <a href="<c:url value="/groups"/>">Groups</a>
                </li>
                <li class="divider-vertical"></li>
                <li >
                    <a href="<c:url value="/j_spring_security_logout"/>">Log out</a>
                </li>
                <li class="divider-vertical"/>
            </ul>
        </div>
    </div>
</div>