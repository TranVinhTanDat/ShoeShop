<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design Bootstrap</title>
    <!-- Roboto Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap">
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


    <%--Teamplace--%>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
    <link href="static/css/test.css" rel="stylesheet" type="text/css"/>
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
    <%--End Teamplace--%>

    <style>
        .product-img img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 0 auto;
        }

        .col-md-5 pagination {
            width: 20%;
        }

        .product-container {
            display: flex;
            flex-wrap: wrap;
        }

        .product {
            /* Thiết lập chiều rộng tối đa cho card */
            max-width: 100%;
            /* Đảm bảo chiều rộng bằng nhau cho các card */
            flex: 1 1 30%; /* Điều chỉnh con số này để thay đổi kích thước card */
            margin: 0 1rem 1rem 0; /* Điều chỉnh khoảng cách giữa các card */
        }

        /*Navigation*/
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }


        /* Navigation styles */
        #navigation {
            background-color: #333;
        }

        .container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 15px;
        }

        #responsive-nav {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 50px;
        }

        .nav-barTab {
            list-style: none;
            display: flex;
        }

        .nav-barTab li {
            margin-right: 10px;
            padding: 15px; /* Expand clickable area */
            transition: background-color 0.3s ease;
        }

        .nav-barTab li:hover {
            background-color: #c11818;
            border-radius: 5px; /* Smoother transition */
        }

        .nav-barTab li a {
            color: #fff;
            font-weight: bold;
            text-decoration: none;
            border-radius: 5px;
        }

        /*End Navigation*/
    </style>
</head>

<body class="skin-light" onload="loadAmountCart()">

<!--Main Navigation-->
<header>

    <jsp:include page="Menu.jsp"></jsp:include>

</header>
<!--Main Navigation-->
<%--Navigation--%>
<nav id="navigation">
    <div class="container">
        <div id="responsive-nav">
            <ul class="nav-barTab">
                <li><a href="home">HOME</a></li>
                <li><a href="shop">PRODUCT</a></li>
                <c:forEach items="${listCC}" var="o">
                    <li><a onclick="load(${o.cid})" class="text-white">${o.cname}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>

<%--End Navigation--%>
<!-- Main layout -->
<main>
    <div class="container" style="margin-top:100px">

        <!-- Row for Sidebar and Products -->
        <div class="row mt-5">

            <!-- Sidebar Column -->
            <div class="col-md-12 mb-4">

                <!-- Section: Sidebar -->
                <section class="d-flex flex-wrap justify-content-between">

                    <!-- Section: Categories -->
                    <section class="mb-4">
                        <h5 style="font-weight: bold">Categories</h5>
                        <div class="text-muted small text-uppercase d-flex flex-column flex-wrap">
                            <c:forEach items="${listCC}" var="o">
                                <p class="mb-3"><a onclick="load(${o.cid})" class="card-link-secondary">${o.cname}</a>
                                </p>
                            </c:forEach>
                        </div>
                    </section>
                    <!-- Section: Categories -->


                    <!-- Section: Price -->
                    <section class="mb-4">

                        <h6 class="font-weight-bold mb-3">Price</h6>

                        <div class="form-check pl-0 mb-3">
                            <input onchange="searchByPriceUnder100()" type="radio" class="form-check-input"
                                   id="under100" name="materialExampleRadios">
                            <label class="form-check-label small text-uppercase card-link-secondary" for="under100">Under
                                $100</label>
                        </div>
                        <div class="form-check pl-0 mb-3">
                            <input onchange="searchByPrice100To200()" type="radio" class="form-check-input"
                                   id="100200" name="materialExampleRadios">
                            <label class="form-check-label small text-uppercase card-link-secondary" for="100200">$100
                                to
                                $200</label>
                        </div>
                        <div class="form-check pl-0 mb-3">
                            <input onchange="searchByPriceAbove200()" type="radio" class="form-check-input"
                                   id="200above" name="materialExampleRadios">
                            <label class="form-check-label small text-uppercase card-link-secondary" for="200above">$200
                                &
                                Above</label>
                        </div>
                        <form>
                            <div class="d-flex align-items-center mt-4 pb-1">
                                <div class="md-form md-outline my-0">
                                    <input oninput="searchByPriceMinToMax()" id="priceMin" type="text"
                                           class="form-control mb-0">
                                    <label for="priceMin">$ Min</label>
                                </div>
                                <p class="px-2 mb-0 text-muted"> - </p>
                                <div class="md-form md-outline my-0">
                                    <input oninput="searchByPriceMinToMax()" id="priceMax" type="text"
                                           class="form-control mb-0">
                                    <label for="priceMax">$ Max</label>
                                </div>
                            </div>
                        </form>

                    </section>
                    <!-- Section: Price -->

                    <!-- Section: Color -->
                    <section class="mb-4">
                        <h5 class="mb-3">Color</h5>
                        <!-- Color Checkboxes -->
                        <div class="btn-group btn-group-toggle btn-color-group d-flex flex-wrap">
                            <label style="width: 20px;height: 20px" class="btn rounded-circle white border-inset-grey p-3 m-2">
                                <input onchange="searchByColorWhite()" type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle grey p-3 m-2">
                                <input onchange="searchByColorGray()" type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle black p-3 m-2">
                                <input onchange="searchByColorBlack()" type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle green p-3 m-2">
                                <input type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle blue p-3 m-2">
                                <input type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle purple p-3 m-2">
                                <input type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle yellow p-3 m-2">
                                <input onchange="searchByColorYellow()" type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle indigo p-3 m-2">
                                <input type="checkbox" checked autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px" class="btn rounded-circle red p-3 m-2">
                                <input type="checkbox" autocomplete="off">
                            </label>
                            <label style="width: 20px;height: 20px"  class="btn rounded-circle orange p-3 m-2">
                                <input type="checkbox" autocomplete="off">
                            </label>
                        </div>
                    </section>
                    <!-- Section: Color -->

                </section>
                <!-- Section: Sidebar -->

                <!-- Section: Filters -->
                <section class="col-md-4 mb-4" style="margin-left: 781px; margin-top: -80px">
                    <h5 class="mb-3">Filters</h5>

                    <!-- Search Input -->
                    <section class="mb-4">
                        <div class="md-form md-outline mt-0 d-flex justify-content-between align-items-center">
                            <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text"
                                   class="form-control mb-0" placeholder="Search...">
                            <a href="#!" class="btn btn-flat btn-md px-3 waves-effect"><i
                                    class="fas fa-search fa-lg"></i></a>
                        </div>
                    </section>
                </section>
                <!-- Section: Filters -->

            </div>
            <!-- Sidebar Column -->


            <!-- Section: Block Content -->
            <section class="mb-3">
                <div class="row d-flex justify-content-between align-items-center">
                    <%--<div class="col-12 col-md-3 text-center text-md-left d-flex justify-content-around">
                        <a href="#!" class="text-reset"><i class="fas fa-th-list fa-lg mr-1"></i></a>
                        <a href="#!" class="text-reset"><i class="fas fa-th-large fa-lg"></i></a>
                    </div--%>
                    <div class="container">
                        <div class="row">
                            <div class="col-12 col-md-5 d-flex justify-content-center align-items-center">
                                <div class="select-outline position-relative w-100">
                                    <select class="mdb-select md-outline md-form" searchable="Search here..">
                                        <option value="" disabled selected>Choose category</option>
                                        <option value="1">Category 1</option>
                                        <option value="2">Category 2</option>
                                        <option value="3">Category 3</option>
                                        <option value="4">Category 4</option>
                                        <option value="5">Category 5</option>
                                    </select>
                                    <label class="text-center" style="">Tất cả sản phẩm</label>
                                    <button class="btn-save btn btn-primary btn-sm mt-2">Save</button>
                                </div>
                            </div>
                            <div class="col-12 col-md-5 d-flex justify-content-center align-items-center pagination">
                                <nav aria-label="Page navigation example" style="margin-left: 150px; width: 600px;">
                                    <ul class="pagination pagination-circle justify-content-center mb-0">
                                        <c:if test="${tag != 1}">
                                            <li class="page-item"><a href="shop?index=${tag-1 }" class="page-link"><i
                                                    class="fas fa-chevron-left"></i></a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endPage }" var="i">
                                            <li class="${tag==i?'page-item active':'page-item'}"><a
                                                    href="shop?index=${i }"
                                                    class="page-link">${i }</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${tag != endPage}">
                                            <li class="page-item"><a href="shop?index=${tag+1 }" class="page-link"><i
                                                    class="fas fa-chevron-right"></i></a></li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>

                </div>
            </section>

            <!-- Section: Block Content -->
            <!-- Products Column -->
            <div class="col-md-14 mb-5" style="">


                <!-- Section: Block Content -->
                <section>
                    <div id="store" class="width-product" style="">

                        <!-- Grid row for Products -->
                        <div class="row">
                            <!-- Product -->
                            <c:forEach items="${listP}" var="o">
                                <div class="col-md-4 col-xs-6">
                                    <div class="product" style="height: 490px;">
                                        <div class="product-img">
                                            <a href="detail?pid=${o.id}" title="View Product">
                                                <img src="${o.image}" alt="${o.name}">
                                                <div class="product-label">
                                                    <span class="new">NEW</span>
                                                </div>
                                            </a> <!-- Thêm thẻ đóng cho thẻ <a> ở đây -->
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">Category</p>
                                            <h3 class="product-name"><a href="detail?pid=${o.id}">${o.name}</a></h3>
                                            <h4 class="product-price">$${o.price}
                                                <del class="product-old-price">$990.00</del>
                                            </h4>
                                            <div class="product-rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
<%--                                            <div class="product-btns">--%>
<%--                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span--%>
<%--                                                        class="tooltipp">add to wishlist</span></button>--%>
<%--                                                <button class="add-to-compare"><i class="fa fa-exchange"></i><span--%>
<%--                                                        class="tooltipp">add to compare</span></button>--%>
<%--                                                <button class="quick-view"><i class="fa fa-eye"></i><span--%>
<%--                                                        class="tooltipp">quick view</span></button>--%>
<%--                                            </div>--%>
                                        </div>
                                            <%--     <div class="add-to-cart">
                                                     <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                 </div>--%>
                                    </div>
                                </div>
                            </c:forEach>
                            <!-- /Product -->
                        </div>
                        <!-- Grid row for Products -->

                    </div>

                </section>
                <!-- Section: Block Content -->

            </div>
            <!-- Products Column -->

        </div>
        <!-- Row for Sidebar and Products -->

    </div>
</main>
<!-- Main layout -->


<!-- Footer -->


<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Footer -->


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
<script>
    $('#multi').mdbRange({
        single: {
            active: true,
            multi: {
                active: true,
                rangeLength: 1
            },
        }
    });

    $(document).ready(function () {
        $('.mdb-select').materialSelect();
        $('.select-wrapper.md-form.md-outline input.select-dropdown').bind('focus blur', function () {
            $(this).closest('.select-outline').find('label').toggleClass('active');
            $(this).closest('.select-outline').find('.caret').toggleClass('active');
        });
    });

    function load(cateid) {
        $.ajax({
            url: "/shoestoreweb/categoryShop",
            type: "get", //send it through get method
            data: {
                cid: cateid
            },
            success: function (responseData) {
                document.getElementById("store").innerHTML = responseData;
            }
        });
    }

    function searchByName(param) {
        var txtSearch = param.value;
        $.ajax({
            url: "/shoestoreweb/searchAjaxShop",
            type: "get", //send it through get method
            data: {
                txt: txtSearch
            },
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByPriceMinToMax() {
        var numMin = document.getElementById("priceMin").value;
        var numMax = document.getElementById("priceMax").value;
        $.ajax({
            url: "/shoestoreweb/searchAjaxPriceMinToMax",
            type: "get", //send it through get method
            data: {
                priceMin: numMin,
                priceMax: numMax
            },
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByPriceUnder100() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxPriceUnder100Shop",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByPrice100To200() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxPrice100To200Shop",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByPriceAbove200() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxPriceAbove200Shop",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByColorWhite() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxColorWhite",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByColorGray() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxColorGray",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByColorBlack() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxColorBlack",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function searchByColorYellow() {
        $.ajax({
            url: "/shoestoreweb/searchAjaxColorYellow",
            type: "get", //send it through get method
            data: {},
            success: function (data) {
                var row = document.getElementById("store");
                row.innerHTML = data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function loadAmountCart() {
        $.ajax({
            url: "/shoestoreweb/loadAllAmountCart",
            type: "get", //send it through get method
            data: {},
            success: function (responseData) {
                document.getElementById("amountCart").innerHTML = responseData;
            }
        });
    }
</script>
<!-- MDB -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>
<!-- jQuery Plugins -->
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/slick.min.js"></script>
<script src="static/js/nouislider.min.js"></script>
<script src="static/js/jquery.zoom.min.js"></script>
<script src="static/js/main.js"></script>
</body>

</html>