<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="menu" />
<tiles:insertAttribute name="body" />
<hr>
<div class="footer text-center">
<tiles:insertAttribute name="footer" />
</div>
</body>
</html>