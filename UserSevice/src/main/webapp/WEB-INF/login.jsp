Hello my JSP Guyssssss
<div>
<form action="/login" method="post">

    <c:if test="${not empty error}">
    <h5>${error}</h5>
    </c:if>
    <div><input type="text" name="username" value="" placeholder="Username"></div>
    <div><input type="password" name="password" value="" placeholder="Password"></div>
    <div><input type="submit" name="login" value="Login"></div>
    <div><a href="/register">Register</a></div>
    <div></div>
</form>



</div>