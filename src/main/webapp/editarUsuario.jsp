<%-- 
    Document   : consultarAdministrador
    Created on : 01/06/2017, 22:33:55
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta</title>
    </head>
    <body>
         <form action="EditarServlet" method="post">
            <label> Nome:
                <input type="text" name="nome" value="${requestScope['usuario'].nome}" />
            </label>
            </br>
            
            <label> Email:
                <input type="email" name="email" value="${requestScope['usuario'].email}" />
            </label>
            </br>
            
            <label> CPF:
                <span>${requestScope['usuario'].cpf}</span>
            </label>
            </br>
            
            <label> Data Nasc.:
                <input type="date" name="dataNasc" value="${requestScope['usuario'].dataNascimento}" />
            </label>
            </br>
            
            <label> Login:
                <input type="text" name="login" value="${requestScope['usuario'].login}" />
            </label>
            </br>
            
            <button type="submit">Submit</button>   
        </form>
        
    </body>
</html>
