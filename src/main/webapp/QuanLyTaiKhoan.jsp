<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%

    var a = request.getSession().getAttribute("acc");
    if (a == null) {
        request.getSession().setAttribute("page", "managerAccount");
        String redirectURL = "Login.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản Lý Tài Khoản</title>
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


    <link href="css/managerfivefirst.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <!--           <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"> -->
    <!--         <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> -->
    <!--       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
    <!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<c:if test="${accEdit != null }">
    <div style="position: fixed;width: 100%; height: 100%; z-index: 1000; display: flex;
           background-color: rgba(144, 189, 166, 0.9); justify-content: center">

        <div class="card-header" style="background: aliceblue; display: flex; position: absolute;top: 30%">
            <div>
                <a style="position: absolute ;top: 0;
    right: 0;
    padding: 6px 14px;
    font-size: 20px;
    color: red; text-decoration: none" href="managerAccount">X</a>

            </div>
            <form action="editAccount" method="post">
                <input type="text" style="visibility: hidden" name="id" value="${accEdit.id}">
                <div class="card-body">
                    <h1 class="card-title" style="text-align: center">Sửa đổi thông tin tài khoản</h1>
                    <div class="row">
                        <div class="col-md-12" style="font-size: 18px">
                            <div style="display: flex;" class="form-group form-group-default">
                                <label style="line-height: 36px;">Tên người dùng</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="name"
                                       value="${accEdit.user}" required="required"><br/>
                                <label style="line-height: 36px;">Mật khẩu</label>
                                <input style="margin-right: 10px" type="text" class="form-control" name="pass"
                                       value="${accEdit.pass}" required="required"><br/>
                                <label style="line-height: 36px;">Vai trò</label>
                                <select name="add-row_length" aria-controls="add-row"
                                        class="form-control form-control-sm">
                                    <option value="1">Người bán hàng</option>
                                    <option value="0">Người quản lý</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input type="submit" value="Sửa tài khoản">
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
                        <form action="searchAccount" method="get">
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
                            <a href="#">Tài khoản</a>
                        </li>
                    </ul>
                </div>
                <!--                <div class="card-header py-3 row">
                                    <div class="col-sm-3">
                                        <h5 class="mb-0 text-left" id="">
                                            <strong>Quản lý tài khoản</strong>
                                        </h5>
                                    </div>

                                    <div class="col-sm-9 text-right">
                                        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i
                                                class="material-icons">&#xE147;</i></a>

                                        <form action="xuatExcelAccountControl" method="get">
                                            <button type="submit" class="mb-0 text-center btn btn-primary">Xuất file Excel</button>
                                        </form>
                                    </div>
                                </div>-->

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
                            <label>Show
                                <form action="filterAccount" method="get">
                                    <select name="add-row_length" aria-controls="add-row"
                                            class="form-control form-control-sm">
                                        <option value="10">10</option>
                                        <option value="25">25</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                        <input style="width: 50px" type="submit" value="lọc"
                                               class="form-control form-control-sm" placeholder=""
                                               aria-controls="add-row">
                                    </select>

                                </form>
                                entries</label></div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div id="add-row_filter" class="dataTables_filter">
                            <label>Search:
                                <form action="searchAccount" method="get">
                                    <input type="search" name="search" value="" class="form-control form-control-sm"
                                           placeholder="" aria-controls="add-row">
                                </form>
                            </label>
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
                                    style="width: 74.05px;">Tài khoản
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Mật
                                    khẩu
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Chức
                                    vụ
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="add-row" rowspan="1" colspan="1"
                                    aria-label="Hình: activate to sort column ascending" style="width: 77.775px;">Email
                                </th>

                                <th style="width: 100px;" class="sorting" tabindex="0" aria-controls="add-row"
                                    rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending">
                                    Action
                                </th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">Tài khoản</th>
                                <th rowspan="1" colspan="1">Mật khẩu</th>
                                <th rowspan="1" colspan="1">Chức vụ</th>
                                <th rowspan="1" colspan="1">Email</th>
                                <th rowspan="1" colspan="1">Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${listAllAccount}" var="o">
                                <tr role="row" class="odd">
                                    <td class="sorting_1">${o.user}</td>
                                    <td>${o.pass}</td>
                                    <c:if test="${o.isSell == 1 }">
                                        <td>Người bán hàng</td>
                                    </c:if>
                                    <c:if test="${o.isSell == 0 }">
                                        <td>Người quản lý</td>
                                    </c:if>

                                    <td>${o.email}</td>
                                    <td>
                                        <div class="form-button-action">

                                            <button style="width: 80px;min-width: 0 ;height:36px;min-height: 0 ;background-color: aqua !important;"
                                                    type="submit" data-toggle="tooltip" title=""
                                                    class="btn btn-link btn-danger"
                                                    data-original-title="Edit">
                                                <a href="editAcc?id=${o.id}">edit</a>

                                            </button>

                                        </div>

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
            <form action="addAccount" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm tài khoản</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Username</label>
                        <input name="user" type="text" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input name="pass" type="password" class="form-control" required>
                    </div>
                    <div class="form-group form-check">
                        <input name="isSell" value="1" type="checkbox" class="form-check-input" id="isSell">
                        <label class="form-check-label" for="isSell">Là người bán</label>
                    </div>

                    <div class="form-group form-check">
                        <input name="isAdmin" value="1" type="checkbox" class="form-check-input" id="isAdmin">
                        <label class="form-check-label" for="isAdmin">Là Admin</label>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input name="email" type="text" class="form-control" required>
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