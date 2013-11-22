<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="page-header">
    <h2 class="text-center"><tiles:getAsString ignore="true" name="title"/></h2>
</div>
<form:form methodParam="post" commandName="userGroup" cssClass="form-horizontal" enctype="multipart/form-data" >
    <form:hidden path="id"  />
    <div class="control-group">
       <form:label path="title" for="title" cssClass="control-label">Title</form:label>
        <div class="controls">
            <form:input path="title" id="title" />
            <form:errors path="title" cssClass="text-error" />
        </div>

    </div>
    <div class="control-group">
        <label path="logo" for="logo" class="control-label">Title</label>
        <div class="controls">
            <input  type="file" accept="image/*" name="logo" id="logo" />
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
        <button type="submit" class="btn btn-primary  btn-large"><tiles:getAsString name="submit-button" ignore="true"/></button>
    </div>

</form:form>
