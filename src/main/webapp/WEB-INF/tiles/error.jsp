<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="text-center text-error">Something went wrong!</p>


<c:if test="${not empty exception}">
    <h4>${exception}</h4>
</c:if>