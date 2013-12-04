<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <div class="well span4 offset4">
            <legend class="text-center">Login</legend>
            <c:if test="${param.auth!=null}">
                <div class="alert alert-error text-center">
                    Login Failed!!!
                </div>
            </c:if>
            <form action="<c:url value='j_spring_security_check'/>" method="POST">
                <input type="text" class="span4" placeholder="Email" name="j_username">
                <input type="password" class="span4" placeholder="Password" name="j_password">
                <label type="checkbox">
                    <input type="checkbox" name="_spring_security_remember_me"> Remember me
                </label>
                <button type="submit" name="submit" class="btn btn-block btn-success">Sign in</button>
            </form>
        </div>
    </div>
</div>
