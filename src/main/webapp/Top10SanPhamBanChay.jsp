<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%

    var a = request.getSession().getAttribute("acc");
    if (a == null) {
        request.getSession().setAttribute("page", "top10");
        String redirectURL = "Login.jsp";
        response.sendRedirect(redirectURL);
    }
%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <style>
        img {
            width: 140px;
            height: 100px;
        }
    </style>
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
<body>

<!--Main Navigation-->
<header>
    <jsp:include page="LeftAdmin.jsp"></jsp:include>


</header>
<!--Main Navigation-->

<!--Main layout-->
<main>
    <div class="container pt-4" style="background-color: #f8f9fa; color: #343a40;">

        <!--Section: Top 5 Nhân Viên Bán Hàng Xuất Sắc-->
        <section class="mb-4">
            <div class="card" style="border: 1px solid #dee2e6; border-radius: 0.25rem;">

                <div class="page-header" style="margin: 0">
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
                            <a href="#">Top</a>
                        </li>
                    </ul>
                </div>
                <div style="">
                    <a href="top10"
                       style="color: black; display: block; padding: 10px; background-color: green; font-size: 16px; margin-bottom: 10px">
                        =>10 Sản phẩm bán chạy nhất tháng</a>
                    <a href="top5khachhang"
                       style="color: black; display: block; padding: 10px; background-color: green; font-size: 16px; margin-bottom: 10px">
                        =>5 Khách hàng tiềm năng</a>
                    <a href="top5nhanvien"
                       style="color: black; display: block; padding: 10px; background-color: green; font-size: 16px">=>5
                        Nhân viên tiềm năng</a>
                </div>


                <c:if test="${mess != null}">
                    <div class="alert alert-success" role="alert"
                         style="background-color: #d4edda; border-color: #c3e6cb; color: #155724;">
                            ${mess}
                    </div>
                </c:if>

                <div class="card-body">
                    <div class="table-responsive">

                        <c:if test="${listTop10Product != null}">
                            <h1 style="color: red; text-align: center">10 Sản phẩm bán chạy nhất</h1>
                            <div class="row py-3 justify-content-end">
                                <div class="col-sm-12 text-center">
                                    <form action="xuatExcelTop10ProductControl" method="get">
                                        <button type="submit" class="btn btn-primary float-right"
                                                style="background-color: #28a745; border-color: #28a745;">Xuất Excel
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <table id="add-row" class="display table table-striped table-hover dataTable" role="grid"
                                   aria-describedby="add-row_info">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-sort="ascending" aria-label="Mã: activate to sort column descending"
                                        style="width: 74.05px;">ID
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Hình ảnh
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Tên
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Giá
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">SL
                                        Bán
                                    </th>


                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th rowspan="1" colspan="1">ID</th>
                                    <th rowspan="1" colspan="1">Hình ảnh</th>
                                    <th rowspan="1" colspan="1">Tên</th>
                                    <th rowspan="1" colspan="1">Giá</th>
                                    <th rowspan="1" colspan="1">SL Bán</th>

                                </tfoot>
                                <tbody>
                                <c:forEach items="${listTop10Product}" var="t">
                                    <c:forEach items="${listAllProduct}" var="o">
                                        <c:if test="${t.productID==o.id }">
                                            <tr>
                                                <td>${o.id}</td>
                                                <td>
                                                    <img src="${o.image}">

                                                </td>
                                                <td>
                                                        ${o.name}

                                                </td>
                                                <td>${o.price} $</td>
                                                <td>${t.soLuongDaBan }</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>


                                </tbody>
                            </table>
                        </c:if>

                        <c:if test="${listTop5KhachHang != null}">
                            <h1 style="color: red; text-align: center">5 Khách hàng chi tiêu nhiều nhất</h1>
                            <div class="row py-3 justify-content-end">
                                <div class="col-sm-12 text-center">
                                    <form action="xuatExcelTop5CustomerControl" method="get">
                                        <button type="submit" class="btn btn-primary float-right"
                                                style="background-color: #28a745; border-color: #28a745;">Xuất Excel
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <table id="add-row" class="display table table-striped table-hover dataTable" role="grid"
                                   aria-describedby="add-row_info">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-sort="ascending" aria-label="Mã: activate to sort column descending"
                                        style="width: 74.05px;">Mã KH
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Tên KH
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Email
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Tổng chi tiêu
                                    </th>


                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th rowspan="1" colspan="1">Mã KH</th>
                                    <th rowspan="1" colspan="1">Tên KH</th>
                                    <th rowspan="1" colspan="1">Email</th>
                                    <th rowspan="1" colspan="1">Tổng chi tiêu</th>

                                </tfoot>
                                <tbody>
                                <c:forEach items="${listTop5KhachHang}" var="topCustomer">
                                    <c:forEach items="${listAllAccount}" var="account">
                                        <c:if test="${topCustomer.userID == account.id && topCustomer.tongChiTieu != 0.0}">
                                            <tr>
                                                <td>${account.id}</td>
                                                <td>${account.user}</td>
                                                <td>${account.email}</td>
                                                <td>${topCustomer.tongChiTieu}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>


                                </tbody>
                            </table>
                        </c:if>


                        <c:if test="${listTop5NhanVien != null}">
                            <h1 style="color: red; text-align: center">5 Nhân viên bán hàng xuất sắc</h1>
                            <div class="row py-3 justify-content-end">
                                <div class="col-sm-12 text-center">
                                    <form action="xuatExcelTop5EmployeeControl" method="get">
                                        <button type="submit" class="btn btn-primary float-right"
                                                style="background-color: #28a745; border-color: #28a745;">Xuất Excel
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <table id="add-row" class="display table table-striped table-hover dataTable" role="grid"
                                   aria-describedby="add-row_info">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-sort="ascending" aria-label="Mã: activate to sort column descending"
                                        style="width: 74.05px;">Mã NV
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Tên NV
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Email
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                        aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">
                                        Tổng bán hàng
                                    </th>


                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th rowspan="1" colspan="1">Mã NV</th>
                                    <th rowspan="1" colspan="1">Tên NV</th>
                                    <th rowspan="1" colspan="1">Email</th>
                                    <th rowspan="1" colspan="1">Tổng bán hàng</th>

                                </tfoot>
                                <tbody>
                                <c:forEach items="${listTop5NhanVien}" var="topEmployee">
                                    <c:forEach items="${listAllAccount}" var="account">
                                        <c:if test="${topEmployee.userID == account.id && topEmployee.tongBanHang != 0.0}">
                                            <tr>
                                                <td>${account.id}</td>
                                                <td>${account.user}</td>
                                                <td>${account.email}</td>
                                                <td>${topEmployee.tongBanHang}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>


                                </tbody>
                            </table>
                        </c:if>


                    </div>
                </div>
            </div>
        </section>
        <!--Section: Quan Ly tai Khoan-->
    </div>
</main>


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