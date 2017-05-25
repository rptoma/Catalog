<%--
  Created by IntelliJ IDEA.
  User: MInol
  Date: 21.05.2017
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import ="servlets.*"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Afisare</title>
    <link href="afisarestyle.css" rel="stylesheet" type="text/css">
</head>
<body>

<table>
    <tr>
        <th>NumePrenume</th>
        <th>Nota</th>
        <th>Materie</th>
    </tr>
<%
    ArrayList<StudentNotaMaterie> list = (ArrayList<StudentNotaMaterie>) request.getAttribute("listaStudenti");
    for(StudentNotaMaterie st:list){
%>
        <tr>
            <td> <%=st.getStudent().getNume()%> <%=st.getStudent().getPrenume()%> </td>
            <td> <%=st.getNota().getNota()%> </td>
            <td> <%=st.getMaterie().getId_materie()%> </td>
        </tr>
<%
    }
%>

</table>
</body>
</html>
