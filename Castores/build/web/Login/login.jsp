<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/stylesLogin.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="formulario">
            <h2>Sistema de Inventario</h2>
            <c:if test="${not empty error}">
                <div id="error">
                    ⚠️ ${error}
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/LoginController" method="post">
                <p>
                    <label>Usuario:</label>
                    <input type="text" id="user" name="user"/>
                </p>
                <p>
                    <label>Contraseña:</label>
                    <input type="password" id="pass" name="pass"/>
                </p>
                
                <button type="submit" id="acceder" name="acceder">Acceder</button>
            </form>
        </div>
    </body>
</html>
