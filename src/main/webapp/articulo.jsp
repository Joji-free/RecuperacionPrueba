<%--
  Created by IntelliJ IDEA.
  User: ander
  Date: 30/5/2025
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Optional,org.anderson.proyectocompraventa.controllers.models.Articulo" %>
<%@ page import="java.util.List" %>
<%
    List<Articulo> articulos = (List<Articulo>) request.getAttribute("articulos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/privados/cabecero.jsp"/>
<!--Contenido-->
<div class="content-wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header with-border">
                        <h1 class="box-title">Artículos
                            <button class="btn btn-success" id="btnagregar" onclick="mostrarform(true)">
                                <i class="fa fa-plus-circle"></i> Agregar
                            </button>
                        </h1>
                        <div class="box-tools pull-right"></div>
                    </div>

                    <!-- Listado de registros -->
                    <div class="panel-body table-responsive" id="listadoregistros">
                        <table id="tbllistado" class="table table-striped table-bordered table-hover">
                            <thead>
                            <th>ID</th>
                            <th>Categoria</th>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Stock</th>
                            <th>Descripción</th>
                            <th>Imagen</th>
                            <th>Estado</th>
                            <th>Opciones</th>
                            </thead>
                            <tbody>
                            <% for (Articulo a : articulos) { %>
                            <tr>
                                <td><%= a.getIdArticulo() %></td>
                                <td><%= a.getIdCategoria() %></td>
                                <td><%= a.getCodigo() %></td>
                                <td><%= a.getNombre() %></td>
                                <td><%= a.getStock() %></td>
                                <td><%= a.getDescripcion() %></td>
                                <td><img src="img/articulos/<%= a.getImagen() %>" height="50px" width="50px"></td>
                                <td><%= a.getCondicion() == 1 ? "Activo" : "Inactivo" %></td>
                                <td>
                                    <!-- Botones para editar/eliminar si lo deseas -->
                                </td>
                            </tr>
                            <% } %>
                            </tbody>
                            <tfoot>
                            <th>ID</th>
                            <th>Categoria</th>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Stock</th>
                            <th>Descripción</th>
                            <th>Imagen</th>
                            <th>Estado</th>
                            <th>Opciones</th>
                            </tfoot>
                        </table>
                    </div>

                    <!-- Formulario de ingreso -->
                    <div class="panel-body" style="height: 400px;" id="formularioregistros">
                        <form name="formulario" id="formulario" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="idarticulo" id="idarticulo">
                            <div class="form-group col-lg-6">
                                <label>Categoría:</label>
                                <input type="text" class="form-control" name="idcategoria" id="idcategoria" required>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Código:</label>
                                <input type="text" class="form-control" name="codigo" id="codigo" required>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Nombre:</label>
                                <input type="text" class="form-control" name="nombre" id="nombre" required>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Stock:</label>
                                <input type="number" class="form-control" name="stock" id="stock" required>
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Descripción:</label>
                                <input type="text" class="form-control" name="descripcion" id="descripcion">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Imagen:</label>
                                <input type="file" class="form-control" name="imagen" id="imagen">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Condición:</label>
                                <select class="form-control" name="condicion" id="condicion">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                            <div class="form-group col-lg-12">
                                <button class="btn btn-primary" type="submit" id="btnGuardar">
                                    <i class="fa fa-save"></i> Guardar
                                </button>
                                <button class="btn btn-danger" onclick="cancelarform()" type="button">
                                    <i class="fa fa-arrow-circle-left"></i> Cancelar
                                </button>
                            </div>
                        </form>
                    </div>
                    <!--Fin del formulario-->
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="WEB-INF/privados/footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/articulo.js"></script>
