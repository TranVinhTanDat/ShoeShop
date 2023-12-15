<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Statistic</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ------>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="css/managerfivefirst.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
    <!-- Material Design Bootstrap -->
    <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
    <!-- Material Design Bootstrap Ecommerce -->
    <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">
    <!-- Your custom styles (optional) -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ------>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/manager.css" rel="stylesheet" type="text/css"/>

    <!--           <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"> -->
    <!--         <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> -->
    <!--       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
    <!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Roboto, sans-serif;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap">
    <link rel="stylesheet" type="text/css"
          href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css">
    <style>body {
        background-color: #fbfbfb;
    }

    @media (min-width: 991.98px) {
        main {
            padding-left: 240px;
        }
    }

    /* Sidebar */
    .sidebar {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        padding: 58px 0 0; /* Height of navbar */
        box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
        width: 240px;
        z-index: 600;
    }

    @media (max-width: 991.98px) {
        .sidebar {
            width: 100%;
        }
    }

    .sidebar .active {
        border-radius: 5px;
        box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
    }

    .sidebar-sticky {
        position: relative;
        top: 0;
        height: calc(100vh - 48px);
        padding-top: 0.5rem;
        overflow-x: hidden;
        overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    }</style>
</head>
<body style="position: relative">
<c:if test="${addSu != null }">
    <div style="position: fixed;width: 100%; height: 100%; z-index: 1000; display: flex;
           background-color: rgba(144, 189, 166, 0.9); justify-content: center">

        <div class="card-header" style="background: aliceblue; display: flex; position: absolute;top: 3%;width: 50%;">
            <div>
                <a style="position: absolute ;top: 0;
    right: 0;
    padding: 6px 14px;
    font-size: 20px;
    color: red; text-decoration: none" href="managerSupplier">X</a>

            </div>
            <form action="addSupplierYour" method="post">
                <input type="text" style="visibility: hidden" name="id" value="${accEdit.id}">
                <div class="card-body">
                    <h1 class="card-title" style="text-align: center">Thêm nhà cung cấp</h1>
                    <div class="row">
                        <div class="col-md-12" style="font-size: 18px">
                            <div style="" class="form-group form-group-default">
                                <label style="line-height: 36px;">Tên NCC</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="name" value=""
                                       required="required"><br/>
                                <label style="line-height: 36px;">SĐT</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="sdt" value=""
                                       required="required"><br/>
                                <label style="line-height: 36px;">Email</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="email" value=""
                                       required="required"><br/>
                                <label style="line-height: 36px;">Địa chỉ</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="address"
                                       value="" required="required"><br/>
                                <label style="line-height: 36px;">Hãng PP</label>
                                <select name="add-row_length" aria-controls="add-row"
                                        class="form-control form-control-sm">
                                    <option value="1">GIAY ADIDAS</option>
                                    <option value="2">GIAY NIKE</option>
                                    <option value="3">GIAY BITIS</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input type="submit" value="Thêm NCC">
                </div>

            </form>
        </div>

    </div>
</c:if>
<!--Main Navigation-->
<header>
    <jsp:include page="LeftAdmin.jsp"></jsp:include>


</header>
<!--Main Navigation-->

<!--Main layout-->
<main>
    <div class="container pt-4">
        <!--Section: Quan Ly tai Khoan-->
        <section class="mb-4">
            <div class="card">
                <div class="content-slider_bar" style="width: 1110px">
                    <div class="input-group">
                        <div class="input-group-prepend rounded" style="height: 100%">
                            <button type="submit" class="btn btn-search pr-1" style="padding: 4px">
                                <i class="rounded bi bi-search"></i>
                            </button>
                        </div>
                        <form action="searchSupplier" method="get">
                            <input type="search" name="search" placeholder="Search ..." class="form-control"
                                   style="height: 100%">
                        </form>

                    </div>
                    <div class="author-logout" style="position: relative;">
                        <i class="bi bi-person"></i>
                        <div class="log-out"><a href="/Orchestra/user/logout.php">Log Out</a>
                            <span></span>
                        </div>
                    </div>
                </div>
                <div class="page-header">
                    <h4 class="page-title m-1">Dữ liệu</h4>
                    <ul class="breadcrumbs">
                        <li class="nav-home">
                            <a href="#">
                                <i class="bi bi-house"></i>
                            </a>
                        </li>
                        <li class="separator">
                            <i class="bi bi-arrow-right"></i>
                        </li>
                        <li class="nav-item">
                            <a href="#">Nhà cung cấp</a>
                        </li>
                    </ul>
                </div>


                <c:if test="${error!=null }">
                    <div class="alert alert-danger" role="alert">
                            ${error}
                    </div>
                </c:if>
                <c:if test="${mess!=null }">
                    <div class="alert alert-success" role="alert">
                            ${mess}
                    </div>
                </c:if>
                <div class="row m-2">
                    <div class="col-sm-12 col-md-6">
                        <div class="dataTables_length" id="add-row_length">
                            <label>Search:
                                <form action="searchSupplier" method="get">
                                    <input type="search" name="search" value="" class="form-control form-control-sm"
                                           placeholder="" aria-controls="add-row">
                                </form>
                            </label>

                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div id="add-row_filter" style="display: flex" class="dataTables_filter">

                            <form action="addSu" method="get">
                                <input type="submit" value="Thêm NCC" class="btn btn-success">
                            </form>
                            <form action="xuatExcelSupplierControl" method="get">
                                <button type="submit" style="height: 38px;min-height: 0;width: 150px;min-width: 0;background-color: #1e517c !important;
" class="mb-0 text-center btn btn-primary"> Xuất Excel
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="add-row" class="display table table-striped table-hover dataTable" role="grid"
                               aria-describedby="add-row_info">
                            <thead>
                            <tr role="row">
                                <th class="sorting_asc" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-sort="ascending" aria-label="Mã: activate to sort column descending"
                                    style="width: 74.05px;">ID
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Tên
                                    NCC
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">SĐT
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Email
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Địa
                                    chỉ
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Hãng
                                    PP
                                </th>
                                <th style="width: 100px;" class="sorting" tabindex="0" aria-controls="add-row"
                                    rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending">
                                    Action
                                </th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">ID</th>
                                <th rowspan="1" colspan="1">Tên NCC</th>
                                <th rowspan="1" colspan="1">SĐT</th>
                                <th rowspan="1" colspan="1">Email</th>
                                <th rowspan="1" colspan="1">Địa chỉ</th>
                                <th rowspan="1" colspan="1">Hãng PP</th>
                                <th rowspan="1" colspan="1">Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${listAllSupplier}" var="o">

                                <tr>
                                    <td>${o.idSupplier}</td>
                                    <td>${o.nameSupplier}</td>
                                    <td>${o.phoneSupplier}</td>
                                    <td>${o.emailSupplier}</td>
                                    <td>${o.addressSupplier}</td>
                                    <c:forEach items="${listAllCategory}" var="t">
                                        <c:if test="${o.cateID==t.cid }">
                                            <td>${t.cname }</td>
                                        </c:if>
                                    </c:forEach>
                                    <td>
                                        <button style="width: 90px;min-width: 0;height:36px;min-height: 0" type="submit"
                                                data-toggle="tooltip" title="" class="btn btn-link btn-danger"
                                                data-original-title="Edit">
                                            <a style="color: #000; text-align: center"
                                               href="deleteSupplier?id=${o.idSupplier}">Delete</a>

                                        </button>

                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </section>
        <!--Section: Quan Ly tai Khoan-->
    </div>
</main>


<!-- Edit Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="addSupplier" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm nhà cung cấp</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Tên nhà cung cấp</label>
                        <input name="nameSupplier" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input name="phoneSupplier" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input name="emailSupplier" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ</label>
                        <input name="addressSupplier" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Cung cấp cho</label>
                        <select name="cateID" class="form-select" aria-label="Default select example">
                            <c:forEach items="${listAllCategory}" var="t">
                                <option value="${t.cid}">${t.cname}</option>
                            </c:forEach>
                        </select>
                    </div>


                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="js/manager.js" type="text/javascript"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--Main layout-->
<!-- SCRIPTS -->
<!-- JQuery -->
<script src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
<!-- MDB Ecommerce JavaScript -->
<script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
<!-- MDB -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>
<script src="https://mdbootstrap.com/api/snippets/static/download/MDB5-Free_3.8.1/js/mdb.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


<script type="text/javascript"
        src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/plugins/mdb-plugins-gathered.min.js"></script>
<!-- MDB -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>
</body>
</html>