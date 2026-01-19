<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/stylesInventario.css">
        <title>Salida de Productos</title>
    </head>
    <body>
        <h1>Salida de Productos</h1>
            <div class="formularios">
                <c:if test="${not empty error}">
                    <div id="error">
                        ⚠️ ${error}
                    </div>
                </c:if>
                <form class="tarj" action="InventarioController?accion=saveS" method="POST" autocomplete="off">
                    <h2>Entrada de Producto</h2>
                    <p>
                        <label for="id">ID: </label>
                        <input type="text" id="id" name="id" value="<c:out value="${producto.id}"/>" readonly/>
                    </p>
                    <p>
                        <label for="nombre">Nombre: </label>
                        <input type="text" id="nombre" name="nombre" value="<c:out value="${producto.nombre}"/>" readonly/>
                    </p>
                    <p>
                        <label for="cantidad">Cantidad: </label>
                        <input type="text" id="cantidad" name="cantidad" value="<c:out value="${producto.cantidad}"/>"/>
                    </p>
                    <button type="submit" id="saveS" name="saveS">Salida</button>
                </form>  
                
                <a class="btn btn-editar" href="InventarioController?accion=inventario">Regresar Inventario</a>
            </div>
        
        <br>
        
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Estatus</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "inventario" items="${lista}">
                        <tr>
                            <td><c:out value="${inventario.id}"/></td>
                            <td><c:out value="${inventario.nombre}"/></td>
                            <td><c:out value="${inventario.cantidad}"/></td>
                            <td><c:out value="${inventario.estatus}"/></td>
                            <td><a class="btn btn-editar" href="InventarioController?accion=editarS&id=<c:out value="${inventario.id}"/>">Editar</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
