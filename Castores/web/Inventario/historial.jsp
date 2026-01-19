<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/stylesInventario.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Salida de Productos</h1>
        <div class="formularios">
            <form class="tarj filtros" action="InventarioController?accion=filtrar" method="POST" autocomplete="off">
                <label>
                    <input type="radio" name="estatus" value="todo" ${param.estatus == null || param.estatus == 'todo' ? 'checked' : ''}>
                    Todo
                </label>
                
                <label>
                    <input type="radio" name="estatus" value="1" ${param.estatus == '1' ? 'checked' : ''}>
                    Activos
                </label>
                    
                <label>
                    <input type="radio" name="estatus" value="0" ${param.estatus == '0' ? 'checked' : ''}>
                    Inactivos
                </label>
                <button type="submit" id="filtrar" name="filtrar">Filtrar</button>
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
                        <th>Movimiento</th>
                        <th>Cantidad</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "historial" items="${lista}">
                        <tr>
                            <td><c:out value="${historial.id_producto}"/></td>
                            <td><c:out value="${historial.producto}"/></td>
                            <c:if test="${historial.movimiento == 0}">
                                <td>Salida</td>
                            </c:if>
                            <c:if test="${historial.movimiento == 1}">
                                <td>Entrada</td>
                            </c:if>
                            <td><c:out value="${historial.cantidad}"/></td>
                            <td><c:out value="${historial.usuario}"/></td>
                            <td><c:out value="${historial.fecha}"/></td>
                            <td><c:out value="${historial.hora}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
