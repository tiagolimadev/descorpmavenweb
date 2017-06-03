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
        <form action="ConsultaUsuarioServlet" method="POST">
            <input type="text" placeholder="CPF" />
            <button type="submit">Pesquisar</button>
        </form>
        
        <c:if test="${requestScope['usuario'] != null}">
            <div>
                Nome: ${requestScope['usuario'].nome} </br>
                CPF: ${requestScope['usuario'].cpf} </br>
                <a href="EditarUsuarioServlet?cpf=${requestScope['usuario'].cpf}">Editar</a></br>
                <a href="ConsultaUsuarioServlet?excluir=${requestScope['usuario'].cpf}">Excluir</a></br>
            </div>
        </c:if>
    </body>
</html>
