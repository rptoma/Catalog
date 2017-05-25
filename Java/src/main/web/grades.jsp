<%@ page import="servlets.Materie" %><%--
  Created by IntelliJ IDEA.
  User: MInol
  Date: 20.05.2017
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.ArrayList"%>
<html>
<head>
    <title>Catalog</title>
    <link href="${pageContext.request.contextPath}/notestyle.css" rel="stylesheet" type="text/css">
</head>
<body>


<form action = "${pageContext.request.contextPath}/Note" method="post">

    <input type="submit" name="buttonAfiseaza" value="Afiseaza Note" />


</form>
<iframe width="0" height="0" border="0" name="dummyframe" id="dummyframe"></iframe>

<form action = "${pageContext.request.contextPath}/Note" method="post" target="dummyframe">

    <select  id="materie" name = "materieStudent">
        <%
            ArrayList<Materie> list = (ArrayList<Materie>) request.getAttribute("listaMaterii");
            for(Materie mat:list){
        %>
    <option value=<%=mat.getId_materie()%>><%=mat.getId_materie()%></option>
        <%
            }
        %>

    </select>

<br>
    ID student:
    <input name="idStudent" type ="number"><br>
    Grupa student:
    <input name="grupaStudent" type ="number"><br>
    Nume student:
    <input name = "numeStudent" type="text"><br>
    Prenume student:
    <input name = "prenumeStudent" type="text"><br>
    Nota student:
    <input name="notaStudent" type ="number"><br>
    <input type="submit" name="buttonInsereaza" value="Adauga Nota" />

</form>




</body>
</html>
