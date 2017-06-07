<%-- 
    Document   : cadastroUsuario
    Created on : May 27, 2017, 9:40:34 AM
    Author     : tiagolima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="UsuarioServlet" method="post">
            <label> Nome:
                <input type="text" name="nome" value="" />
            </label>
            </br>
            
            <label> Email:
                <input type="email" name="email" value="" />
            </label>
            </br>
            
            <label> CPF:
                <input type="text" name="cpf" value="" />
            </label>
            </br>
            
            <label> Data Nasc.:
                <input type="date" name="dataNasc" value="" />
            </label>
            </br>
            
            <label> Login:
                <input type="text" name="login" value="" />
            </label>
            </br>
            
            <label> Senha:
                <input type="password" name="senha" value="" />
            </label>
            </br>
            
            <button type="submit">Submit</button>   
        </form>
        
    </body>
</html>
