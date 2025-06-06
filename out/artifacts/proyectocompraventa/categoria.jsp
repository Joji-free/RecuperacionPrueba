<%@ page import="java.util.Optional,org.anderson.proyectocompraventa.controllers.models.Categoria" %>
<%@ page import="java.util.List" %>
<%
    List<Categoria> categorias=(List<Categoria>) request.getAttribute("categorias");
    Optional<String> username=(Optional<String>)request.getAttribute("username");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/privados/cabecero.jsp"/>
<!--Contenido-->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-header with-border">
                        <h1 class="box-title">Categoría
                            <button class="btn btn-success" id="btnagregar" onclick="mostrarform(true)"><i
                                    class="fa fa-plus-circle"></i> Agregar
                            </button>
                        </h1>
                        <div class="box-tools pull-right">
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <!-- centro -->
                    <div class="panel-body table-responsive" id="listadoregistros">
                        <table id="tbllistado"
                               class="table table-striped table-bordered table-responsive table-condensed table-hover">
                            <thead>
                                <th>Id Categoria</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Estado</th>
                                <th>Opciones</th>
                            </thead>
                            <% for (Categoria c : categorias) {%>
                            <tbody>

                            </tbody>
                            <%}%>
                            <tfoot>
                                <th>Id Categoria</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Estado</th>
                                <th>Opciones</th>
                            </tfoot>
                        </table>
                    </div>
                    <div class="panel-body" style="height: 400px;" id="formularioregistros">
                        <form name="formulario" id="formulario" method="POST">
                            <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <label>Nombre:</label>
                                <input type="hidden" name="idcategoria" id="idcategoria">
                                <input type="text" class="form-control" name="nombre" id="nombre" maxlength="50"
                                       placeholder="Nombre" required>
                            </div>
                            <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <label>Descripción:</label>
                                <input type="text" class="form-control" name="descripcion" id="descripcion"
                                       maxlength="256" placeholder="Descripción">
                            </div>
                            <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <button class="btn btn-primary" type="submit" id="btnGuardar"><i
                                        class="fa fa-save"></i> Guardar
                                </button>

                                <button class="btn btn-danger" onclick="cancelarform()" type="button"><i
                                        class="fa fa-arrow-circle-left"></i> Cancelar
                                </button>
                            </div>
                        </form>
                    </div>
                    <!--Fin centro -->
                </div><!-- /.box -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </section><!-- /.content -->

</div>
<!-- /.content-wrapper -->
<!--Fin-Contenido-->

<jsp:include page="WEB-INF/privados/footer.jsp"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/categoria.js"></script>