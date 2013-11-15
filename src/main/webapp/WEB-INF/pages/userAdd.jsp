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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <c:if test="${user.userGroupId!=null}">
    <script type="text/javascript">
        onload = function() {
            document.getElementById('select').value=<c:out value="${user.userGroupId}"/>;
        }
    </script>
    </c:if>
</head>
<body>
<div class="page-header">

    <h2 class="text-center">${title}</h2>
</div>
<form:form  commandName="user" method="post" class="form-horizontal">
    <form:hidden path="id"></form:hidden>
    <div class="control-group">
        <form:label path="name" for="name" cssClass="control-label">Username</form:label>
        <div class="controls">
        <form:input path="name" id="name"></form:input>
            <form:errors path="name" cssClass="text-error" />

        </div>
    </div>
    <div class="control-group">
      <form:label path="password" for="password" cssClass="control-label">Password</form:label>
        <div class="controls">
        <form:password path="password" id="password"></form:password>
            <form:errors path="password" cssClass="text-error" />

        </div>
    </div>
    <div class="control-group">
        <form:label path="email" for="email" cssClass="control-label">Email</form:label>
        <div class="controls">
            <form:input type="email" path="email" ></form:input>
            <form:errors path="email" cssClass="text-error" />

        </div>
    </div>
    <div class="control-group">
        <form:label path="about" cssClass="control-label">About</form:label>
        <div class="controls">
            <form:textarea path="about"></form:textarea>
            <form:errors path="about" cssClass="text-error" />
        </div>
    </div>

    <div class="control-group">
        <form:label path="about" cssClass="control-label">About</form:label>
        <div class="controls">
            <form:textarea path="about"></form:textarea>
            <form:errors path="about" cssClass="text-error" />
        </div>
    </div>
    <div class="control-group">
    <div class="controls">
    <select name="groupId" id="select">
        <option value="-1">No Group</option>
         <c:forEach items="${groups}" var="group">
            <option value="${group.id}">${group.title}</option>
         </c:forEach>

    </select>
    </div>
    </div>
    <div class="controls">
        <button type="submit" class="btn btn-primary  btn-large">${submitButton}</button>
    </div>

</form:form>
</body>
</html>