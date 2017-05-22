<%--
  Created by IntelliJ IDEA.
  User: MInol
  Date: 20.05.2017
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Autentificare</title>
    <link href="loginstyle/style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/LoginCatalog" method ="post">
    Introduceti numele de utilizator: <input type="text" name = "username"><br>
    Introduceti parola: <input type="password" name = "pass"><br>
    <input type = "submit" value = "Submit">
  </form>
  </body>
</html>
