<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Codificación de caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración inicial de ancho y escala -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Título -->
        <title>alog</title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css"
            integrity="sha512-rO2SXEKBSICa/AfyhEK5ZqWFCOok1rcgPYfGOqtX35OyiraBg6Xa4NnBJwXgpIRoXeWjcAmcQniMhp22htDc6g=="
            crossorigin="anonymous" />
    </head>
    <body class="py-3">
        <div class="container">
            <!-- Navegación -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">Home</li>
                </ol>
            </nav>
            <!-- /Nagevación -->

            <!-- Contenido -->
            <div class="row border-bottom my-4">
                <div class="col-10">
                    <h1>Listado de Facturas</h1>
                </div>

                <div class="col-2 text-right">
                    <button type="button" class="btn btn-primary" data-action="nuevo">Nueva</button>
                </div>
            </div>

            <core:if test="${ not empty param.rem }">
                <div class="alert alert-success">La Factura con el identificador '${ param.rem }' ha sido eliminada correctamente</div>
            </core:if>

            <core:if test="${ not empty param.noid }">
                <div class="alert alert-warning">La Factura con el identificador '${ param.noid }' no existe en nuestros registros</div>
            </core:if>

            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th scope="col" class="text-nowrap">#</th>
                            <th scope="col" class="text-nowrap">Fecha</th>
                            <th scope="col" class="text-nowrap">Items</th>
                            <th scope="col" class="text-nowrap">SubTotal</th>
                            <th scope="col" class="text-nowrap">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <core:choose>
                            <core:when test="${facturas != null && facturas.size() > 0}">
                                <core:forEach items="${facturas}" var="factura">
                                    <tr data-action="detalles" data-id="${factura.getFacturaId()}" role="button">
                                        <th scope="row" class="text-nowrap">${factura.getFacturaId()}</th>
                                        <td class="text-nowrap">${factura.getFecha()}</td>
                                        <td class="text-nowrap">${factura.contarItems()}</td>
                                        <td class="text-nowrap"><format:formatNumber value="${factura.calcularSubtotal()}" type="currency" /></td>
                                        <td class="text-nowrap"><format:formatNumber value="${factura.calcularTotal()}" currencyCode="CLP" type="currency" /></td>
                                    </tr>
                                </core:forEach>
                            </core:when>
                            <core:otherwise>
                                <tr>
                                    <th scope="row" colspan="5" class="text-nowrap text-center">No hay registros</th>
                                </tr>
                            </core:otherwise>
                        </core:choose>
                    </tbody>
                </table>
            </div>
            <!-- /Contenido -->
        </div>

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg=="
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"
            integrity="sha512-ubuT8Z88WxezgSqf3RLuNi5lmjstiJcyezx34yIU2gAHonIi27Na7atqzUZCOoY4CExaoFumzOsFQ2Ch+I/HCw=="
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha512-I5TkutApDjnWuX+smLIPZNhw+LhTd8WrQhdCKsxCFRSvhFx2km8ZfEpNIhF9nq04msHhOkE8BMOBj5QE07yhMA=="
            crossorigin="anonymous"></script>
        <script type="text/javascript" src="<core:url value="/res/js/facturas.js" />"></script>
    </body>
</html>
