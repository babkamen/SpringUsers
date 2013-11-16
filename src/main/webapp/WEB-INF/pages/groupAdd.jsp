<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: babkamen
  Date: 14.11.13
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
<div class="page-header">
    <h2 class="text-center">${title}</h2>
</div>
<form:form methodParam="post" commandName="userGroup" cssClass="form-horizontal">
    <form:hidden path="id"  />
    <div class="control-group">
       <form:label path="title" for="title" cssClass="control-label">Title</form:label>
        <div class="controls">
            <form:input path="title" id="title" />
            <form:errors path="title" cssClass="text-error" />
        </div>

    </div>
    <div class="control-group">
        <form:label for="about"  path="about" cssClass="control-label">About</form:label>
        <div class="controls">
        <form:textarea path="about"></form:textarea>
            <form:errors path="about" cssClass="text-error" />
        </div>
    </div>
    <div class="control-group">
        <label for="userIds" class="control-label">Users</label>
        <div class="controls">
            <c:choose>
                <c:when test="${userIds!=null&&userIds.size()>0}">
            <select name="userIds" id="userIds" multiple>
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
                </c:when>
                <c:otherwise>
                    <p>No available users</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>



    <div class="controls">
        <button type="submit" class="btn btn-primary  btn-large">${submitButton}</button>
    </div>

</form:form>
</body>
</html>