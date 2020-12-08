<%--
    Document   : index
    Created on : Dec 3, 2020, 12:02:35 AM
    Author     : Ardian
--%>

<%@page import="utils.DBConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.isNew()) {
        response.sendRedirect("login.jsp");
    } else if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            DBConfig dBConfig = new DBConfig();
            System.out.print(dBConfig.getConnection());
        %>
    </body>
</html>
