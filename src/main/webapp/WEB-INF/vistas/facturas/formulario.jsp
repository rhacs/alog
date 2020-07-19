<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker3.min.css"
            integrity="sha512-rxThY3LYIfYsVCWPCW9dB0k+e3RZB39f23ylUYTEuZMDrN/vRqLdaCBo/FbvVT6uC2r0ObfPzotsfKF9Qc5W5g=="
            crossorigin="anonymous" />
    </head>
    <body class="py-3">
        <div class="container">
            <!-- Navegación -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<core:url value="/" />">Home</a></li>
                    <li class="breadcrumb-item active">Nueva Factura</li>
                </ol>
            </nav>
            <!-- /Nagevación -->

            <!-- Contenido -->

            <div class="card">
                <div class="card-header">
                    Agregar nueva Factura
                </div>
                <div class="card-body">
                    <form id="nuevo" action="<core:url value="/facturas/nueva/" />" method="post">
                        <div class="form-group">
                            <label for="fecha">Fecha</label>
                            <input type="date" class="form-control" name="fecha" id="fecha" required>
                        </div>

                        <table id="elementos" class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-nowrap">Cant.</th>
                                    <th scope="col" class="text-nowrap">Descripción</th>
                                    <th scope="col" class="text-nowrap">P. Unitario</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row" class="text-nowrap"><input type="number" class="form-control" min="1" max="1000000" value="1" required></th>
                                    <td class="text-nowrap"><input type="text" class="form-control" name="descripcion" id="descripcion" maxlength="250" required></td>
                                    <td class="text-nowrap"><input type="number" class="form-control" name="precio" id="precio" min="1" value="1" required></td>
                                    <td class="text-nowrap"><button type="button" class="btn btn-danger" data-eliminar>X</button></td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="form-group text-right">
                            <button type="button" class="btn btn-success" data-agregar>Agregar Item</button>
                            <button type="submit" class="btn btn-primary">Crear factura</button>
                        </div>
                    </form>
                </div>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"
            integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg=="
            crossorigin="anonymous"></script>
        <script type="text/javascript" src="<core:url value="/res/js/facturas.js" />"></script>
    </body>
</html>
