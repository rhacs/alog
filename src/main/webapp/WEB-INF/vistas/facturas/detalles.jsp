<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <li class="breadcrumb-item"><a href="<core:url value="/" />">Home</a></li>
                    <li class="breadcrumb-item active">Detalles</li>
                </ol>
            </nav>
            <!-- /Nagevación -->

            <!-- Contenido -->

            <div class="row border-bottom my-4">
                <div class="col-12">
                    <h1>Detalles de la Factura</h1>
                </div>
            </div>

            <core:choose>
                <core:when test="${ not empty error }">
                    <div class="alert alert-warning">${ error }</div>
                </core:when>
                <core:otherwise>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <th scope="row" class="text-nowrap">Número</th>
                                <td class="text-nowrap">${factura.getFacturaId()}</td>
                            </tr>
                            <tr>
                                <th scope="row" class="text-nowrap">Fecha</th>
                                <td class="text-nowrap">${factura.getFecha()}</td>
                            </tr>
                        </tbody>
                    </table>

                    <table class="table table-striped table-hover mt-2">
                        <thead>
                            <tr>
                                <th scope="col" class="text-nowrap">Cant.</th>
                                <th scope="col" class="text-nowrap">Descripción</th>
                                <th scope="col" class="text-nowrap">P. Unitario</th>
                                <th scope="col" class="text-nowrap">SubTotal</th>
                            </tr>
                        </thead>

                        <tbody>
                            <core:choose>
                                <core:when test="${ items != null && items.size() > 0 }">
                                    <core:forEach items="${items}" var="item">
                                        <tr>
                                            <th scope="row" class="text-nowrap text-right">${item.getCantidad()}</th>
                                            <td class="text-nowrap">${item.getDescripcion()}</td>
                                            <td class="text-nowrap"><format:formatNumber value="${item.getPrecioUnitario()}" type="currency" currencyCode="CLP" /></td>
                                            <td class="text-nowrap"><format:formatNumber value="${item.calcularTotal()}" type="currency" currencyCode="CLP" /></td>
                                        </tr>
                                    </core:forEach>
                                    <tr>
                                        <th scope="row" colspan="3" class="text-right">SubTotal</th>
                                        <td><format:formatNumber value="${factura.calcularSubtotal()}" currencyCode="CLP" type="currency" /></td>
                                    </tr>
                                    <tr>
                                        <th scope="row" colspan="3" class="text-right">IVA</th>
                                        <td><format:formatNumber value="${factura.calcularImpuesto()}" currencyCode="CLP" type="currency" /></td>
                                    </tr>
                                    <tr>
                                        <th scope="row" colspan="3" class="text-right">Total</th>
                                        <td><format:formatNumber value="${factura.calcularTotal()}" currencyCode="CLP" type="currency" /></td>
                                    </tr>
                                </core:when>
                                <core:otherwise>
                                    <tr>
                                        <th scope="row" colspan="4" class="text-center">No hay registros</th>
                                    </tr>
                                </core:otherwise>
                            </core:choose>
                        </tbody>
                    </table>
                </core:otherwise>
            </core:choose>

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
    </body>
</html>