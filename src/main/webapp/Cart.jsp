<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="static/css/bootstrap.min.css"/>

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="static/css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="static/css/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="static/css/nouislider.min.css"/>

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="static/css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="static/css/style.css"/>
    <style>
        /* Bảng sản phẩm giỏ hàng */
        .custom-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: -20px;
        }

        /* Header của bảng */
        .custom-table thead th {
            background-color: #f8f9fa;
            font-weight: bold;
            text-align: left;
            padding: 35px;
        }

        /* Dòng dữ liệu */
        .custom-table tbody td {
            padding: 20px;
            vertical-align: middle;
            border-bottom: 1px solid #dee2e6;
        }

        /* Định dạng container chứa hình ảnh và link */
        .custom-cell-content {
            display: flex;
            align-items: center;
        }

        /* Định dạng hình ảnh */
        .custom-img {
            width: 100px;
            height: 100px;
            margin-right: 20px; /* Khoảng cách giữa hình ảnh và link */
        }

        /* Định dạng custom-link */
        .custom-link {
            text-decoration: none;
            color: #333;
            transition: color 0.3s ease;
            flex: 1; /* Kéo link ra toàn bộ phần trống còn lại */
        }

        /* Nút thêm, bớt */
        .custom-btn-subtract,
        .custom-btn-add {
            border: none;
            background: none;
            cursor: pointer;
            font-weight: bold;
            font-size: 18px;
            vertical-align: middle;
        }

        /* Nút xóa */
        .custom-btn-danger {
            border: none;
            background: none;
            color: red;
            cursor: pointer;
            font-size: 18px;
        }

        /* Số lượng */
        .custom-amount {
            margin: 0 10px;
            font-weight: bold;
            font-size: 18px;
        }

        /* Hover cho nút xóa */
        .custom-btn-danger:hover {
            text-decoration: underline;
        }

        /* Hover cho link */
        .custom-link {
            /* Các thuộc tính chung */
            color: #333; /* Màu chữ mặc định */
            text-decoration: none; /* Loại bỏ gạch chân mặc định cho liên kết */
            transition: all 0.3s ease; /* Hiệu ứng chuyển đổi mượt mà */

            /* Hiệu ứng hover */

            &:hover {
                color: red; /* Thay đổi màu khi di chuột qua */
                transform: scale(1.1); /* Phóng to khi di chuột qua */
                /* hoặc các hiệu ứng khác như đổi màu gradient, thay đổi đổ bóng, vv. */
            }
        }


        /* Định dạng số lượng và các nút */
        .custom-cell .custom-cell-controls {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .custom-cell .custom-cell-controls .custom-btn-subtract,
        .custom-cell .custom-cell-controls .custom-btn-add {
            border: none;
            background: none;
            cursor: pointer;
            font-weight: bold;
            font-size: 18px;
            width: 30px;
            height: 30px;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background-color 0.3s ease;
        }

        .custom-cell .custom-cell-controls .custom-btn-subtract {
            color: red;
        }

        .custom-cell .custom-cell-controls .custom-btn-add {
            color: green;
        }

        .custom-cell .custom-cell-controls .custom-btn-subtract:hover,
        .custom-cell .custom-cell-controls .custom-btn-add:hover {
            background-color: rgba(0, 0, 0, 0.1);
        }


        .custom-cell .custom-cell-controls .custom-amount {
            font-weight: bold;
            font-size: 18px;
            margin: 10000px 0; /* Điều chỉnh khoảng cách xích xuống của số lượng */
        }

        .custom-alert {
            padding: 10px;
            border-radius: 5px;
            font-weight: bold;
            /* Thêm các thuộc tính chung cho thông báo */
        }

        .custom-alert-success {
            background-color: #dff0d8; /* Màu nền cho thông báo thành công */
            color: #3c763d; /* Màu chữ cho thông báo thành công */
            border: 1px solid #d6e9c6; /* Viền cho thông báo thành công */
        }

        /* Hiệu ứng hover */
        .custom-alert-success:hover {
            opacity: 0.9; /* Giảm độ mờ khi di chuột qua */
            /* Các hiệu ứng khác nếu bạn muốn */
        }


    </style>
</head>

<body onload="loadTotalMoney()">
<jsp:include page="Menu.jsp"></jsp:include>
<div class="shopping-cart">
    <div class="px-4 px-lg-0">

        <div class="pb-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                        <!-- Shopping cart table -->
                        <div class="custom-table-responsive">
                            <table class="custom-table shopping-cart-table">
                                <thead>
                                <c:if test="${error!=null }">
                                    <div class="custom-alert custom-alert-danger" role="alert">
                                            ${error}
                                    </div>
                                </c:if>
                                <c:if test="${mess!=null }">
                                    <div class="custom-alert custom-alert-success" role="alert">
                                            ${mess}
                                    </div>
                                </c:if>
                                <tr>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Sản phẩm</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Đơn giá</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Màu sắc</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Delivery</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Kích thước</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell">Số lượng</div>
                                    </th>
                                    <th scope="col" class="custom-border-0 custom-bg-light">
                                        <div class="custom-header-cell"></div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listCart}" var="o">
                                    <c:forEach items="${listProduct}" var="p">
                                        <c:if test="${o.productID == p.id}">
                                            <tr>
                                                <td class="custom-cell">
                                                    <div class="custom-cell-content">
                                                        <img src="${p.image}" alt="" width="70" class="custom-img">
                                                        <div class="custom-text">
                                                            <h5 class="custom-heading"><a href="#"
                                                                                          class="custom-link">${p.name}</a>
                                                            </h5>
                                                            <span class="custom-info"></span>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="custom-cell">${p.price}$</td>
                                                <td class="custom-cell">${p.color}</td>
                                                <td class="custom-cell">${p.delivery}</td>
                                                <td class="custom-cell">${o.size}</td>
                                                <td class="custom-cell">
                                                    <a href="subAmountCart?productID=${o.productID}&amount=${o.amount}"
                                                       class="custom-link">
                                                        <button class="custom-btn-subtract">-</button>
                                                    </a>
                                                    <span class="custom-amount">${o.amount}</span>
                                                    <a href="addAmountCart?productID=${o.productID}&amount=${o.amount}"
                                                       class="custom-link">
                                                        <button class="custom-btn-add">+</button>
                                                    </a>
                                                </td>
                                                <td class="custom-cell">
                                                    <a href="deleteCart?productID=${o.productID}"
                                                       class="custom-text-dark">
                                                        <button type="button" class="custom-btn custom-btn-danger">
                                                            Delete
                                                        </button>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- End -->

                    </div>
                </div>

                <div class="row py-5 p-4 bg-white rounded shadow-sm">

                    <div class="col-lg-6" style="margin-left: 550px;">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>

                        <div class="p-4">
                            <ul class="list-unstyled mb-4" id="contentTotalMoney"></ul>
                            <a href="order" class="btn btn-success rounded-pill py-2 btn-block text-white"> Đặt hàng</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function loadTotalMoney() {
        $.ajax({
            url: "/shoestoreweb/totalMoneyCart",
            type: "get",
            data: {},
            success: function (responseData) {
                document.getElementById("contentTotalMoney").innerHTML = responseData;
            }
        });
    }

    window.addEventListener("load", function loadAmountCart() {
        $.ajax({
            url: "/shoestoreweb/loadAllAmountCart",
            type: "get",
            data: {},
            success: function (responseData) {
                document.getElementById("amountCart").innerHTML = responseData;
            }
        });
    }, false);
</script>
</body>

</html>
</html>
