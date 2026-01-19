<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/stylesInventario.css">
        <title>Inventario</title>
    </head>
    <body>
        <h1>Inventario</h1>
        
            <div class="formularios">
                <c:if test="${sessionScope.rol == 1}">
                    <form class="tarj" action="InventarioController?accion=add" method="POST" autocomplete="off">
                        <h2>Agregar Producto</h2>
                        <p>
                            <label for="nombre">Nombre: </label>
                            <input type="text" id="nombre" name="nombre"/>
                        </p>
                        <button type="submit" id="add" name="add">Guardar</button>
                    </form>
                    <br>
                    
                    <c:if test="${not empty error}">
                        <div id="error">
                            ⚠️ ${error}
                        </div>
                    </c:if>
                    <form class="tarj" action="InventarioController?accion=save" method="POST" autocomplete="off">
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
                        <button type="submit" id="save" name="save">Entrada</button>
                    </form>   
                </c:if>
                    
                <c:if test="${sessionScope.rol == 2}">
                    <a class="btn" href="InventarioController?accion=salida">Salida de Productos</a>
                </c:if>
                
                <c:if test="${sessionScope.rol == 1}">
                    <a class="btn" href="InventarioController?accion=historial">Historial de Productos</a>
                </c:if>
                 
                <a class="btn btn-editar" href="${pageContext.request.contextPath}/LogoutController">Cerrar Sesión</a>
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
                        <c:if test="${sessionScope.rol == 1}">
                            <th></th>
                            <th></th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "inventario" items="${lista}">
                        <tr>
                            <td><c:out value="${inventario.id}"/></td>
                            <td><c:out value="${inventario.nombre}"/></td>
                            <td><c:out value="${inventario.cantidad}"/></td>
                            <td><c:out value="${inventario.estatus}"/></td>
                            <c:if test="${sessionScope.rol == 1}">
                                <td><a class="btn btn-editar" href="InventarioController?accion=editar&id=<c:out value="${inventario.id}"/>">Editar</a></td>
                                <td>
                                    <c:if test="${inventario.estatus == 1}">
                                        <a class="btn btn-desactiva" href="InventarioController?accion=cambiaEst&id=<c:out value="${inventario.id}"/>">Desactivar</a>
                                    </c:if>
                                    <c:if test="${inventario.estatus == 0}">
                                        <a class="btn btn-activa" href="InventarioController?accion=cambiaEst&id=<c:out value="${inventario.id}"/>">Activar</a>
                                    </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
