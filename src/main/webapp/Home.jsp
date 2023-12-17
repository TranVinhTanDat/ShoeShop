<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
    <!-- Bootstrap core CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css">
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Your custom styles (optional) -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Additional custom stylesheets -->
    <link rel="stylesheet" href="./static/css/test.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="static/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="static/css/slick.css">
    <link type="text/css" rel="stylesheet" href="static/css/slick-theme.css">
    <link type="text/css" rel="stylesheet" href="static/css/nouislider.min.css">
    <link rel="stylesheet" href="static/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<style>
    /* Carousel styling */
    #introCarousel,
    .carousel-inner,
    .carousel-item,
    .carousel-item.active {
        height: 100vh;
    }

    .carousel-item:nth-child(1) {
        background-image: url('static/img/BIA.jpg');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        background-position: center center;
    }

    .carousel-item:nth-child(2) {
        background-image: url('static/img/slider-8.png');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        background-position: center center;
    }

    .carousel-item:nth-child(3) {
        background-image: url('static/img/slide4.jpg');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        background-position: center center;
    }

    /* Height for devices larger than 576px */
    @media (min-width: 992px) {
        #introCarousel {
            margin-top: -58.59px;
        }
    }

    /* .navbar .nav-link {
         color: #fff !important;
     }*/
    .product-img img {
        max-width: 100%;
        height: auto;
        display: block;
        margin: 0 auto;
    }
</style>

<style>
    /* Reset and general styles */
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

    /*.nav-barTab li a:hover {
        background-color: #c11818;
    }*/

</style>

</head>
<body class="skin-light" onload="loadAmountCart()">
<jsp:include page="Menu.jsp"></jsp:include>
<%--Navigation--%>
<nav id="navigation">
    <div class="container">
        <div id="responsive-nav">
            <ul class="nav-barTab">
                <li><a href="home">HOME</a></li>
                <li><a href="shop">PRODUCT</a></li>
                <c:forEach items="${listCC}" var="o">
                    <li><a href="shop" onclick="load(${o.cid})" class="text-white">${o.cname}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>

<%--End Navigation--%>

<!-- Carousel wrapper -->
<div id="introCarousel" class="carousel slide carousel-fade shadow-2-strong" data-mdb-ride="carousel"
     style="margin-top:-2px;">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-mdb-target="#introCarousel" data-mdb-slide-to="0" class="active"><i class="bi bi-chevron-right"></i></li>
        <li data-mdb-target="#introCarousel" data-mdb-slide-to="1"><i class="bi bi-chevron-right"></i></li>
        <li data-mdb-target="#introCarousel" data-mdb-slide-to="2"><i class="bi bi-chevron-right"></i></li>
    </ol>

    <!-- Inner -->
    <div class="carousel-inner">
        <!-- Single item -->
        <div class="carousel-item active">

        </div>

        <!-- Single item -->
        <div class="carousel-item">

        </div>

        <!-- Single item -->
        <div class="carousel-item">

        </div>
    </div>
    <!-- Inner -->

    <!-- Controls -->
    <a class="carousel-control-prev" href="#introCarousel" role="button" data-mdb-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#introCarousel" role="button" data-mdb-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!-- Carousel wrapper -->


<div class="section">
    <div class="container">

        <div class="row" style="margin-left:25px">
            <h1 style="text-align:center; width:100%" id="moiNhat">SẢN PHẨM MỚI NHẤT</h1>
            <div class="col-md-12">
                <div class="row" id="contentMoiNhat">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div class="tab-pane active">
                            <div class="products-slick" data-nav="#slick-nav-1">
                                <!-- product -->
                                <c:forEach items="${list8Last}" var="o">
                                    <div class="product" style="height: 500px;">
                                        <div class="product-img">
                                            <a href="detail?pid=${o.id}" title="View Product">
                                                <img src="${o.image}" alt="">
                                                <div class="product-label">
                                                    <span class="new">NEW</span>
                                                </div>
                                        </div>

                                        <div class="product-body">
                                            <p class="product-category">Category</p>
                                            <h3 class="product-name"><a href="detail?pid=${o.id}"
                                                                        title="View Product">${o.name}</a></h3>
                                            <h4 class="product-price">$980.00
                                                <del class="product-old-price">${o.price} $</del>
                                            </h4>
                                            <div class="product-rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
<%--                                            <div class="product-btns">--%>
<%--                                                <button class="add-to-wishlist"><i class="fa fa-shopping-cart"></i><span--%>
<%--                                                        class="tooltipp">add to wishlist</span></button>--%>
<%--                                                <button class="add-to-compare"><i--%>
<%--                                                        class="fa fa-shopping-basket"></i><span class="tooltipp">add to compare</span>--%>
<%--                                                </button>--%>
<%--                                                <button class="quick-view"><i class="fa fa-eye"></i><span--%>
<%--                                                        class="tooltipp">quick view</span></button>--%>
<%--                                            </div>--%>
                                        </div>
                                            <%--        <div class="add-to-cart">
                                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </div>--%>
                                    </div>
                                </c:forEach>
                                <!-- /product -->
                            </div>
                            <div class="products-slick-nav"></div>
                        </div>
                        <!-- /tab -->
                    </div>
                </div>
            </div>
        </div>


        <div class="row" style="margin-top:25px">
            <h1 style="text-align:center; width:100%" id="nike">GIÀY NIKE</h1>
            <div class="col-md-12 text-center">
                <div id="loadMoreContainer">
                    <button onclick="loadMoreNike()" class="btn btn-primary mt-4" style="margin-left: 1090px;">Load
                        more
                    </button>
                </div>
            </div>
            <div class="col-md-12">
                <div class="row" id="contentNike">
                    <div class="product-container" style="margin-left: 38px;">
                        <div class="products-tabs">
                            <!-- tab -->
                            <div class="tab-pane active">
                                <div class="products-slick" data-nav="#slick-nav-1">
                                    <c:forEach items="${list4NikeLast}" var="o">
                                        <div class="product productNike" style="height: 500px;">
                                            <div class="product-img">
                                                <a href="detail?pid=${o.id}" title="View Product">
                                                    <img src="${o.image}" alt="">
                                                    <div class="product-label">
                                                        <!-- <span class="sale">-30%</span> -->
                                                        <span class="new">NEW</span>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">Category</p>
                                                <h3 class="product-name"><a href="detail?pid=${o.id}"
                                                                            title="View Product">${o.name}</a></h3>
                                                <h4 class="product-price">$980.00
                                                    <del class="product-old-price">${o.price} $</del>
                                                </h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
<%--                                                <div class="product-btns">--%>
<%--                                                    <button class="add-to-wishlist"><i--%>
<%--                                                            class="fa fa-shopping-cart"></i><span class="tooltipp">add to wishlist</span>--%>
<%--                                                    </button>--%>
<%--                                                    <button class="add-to-compare"><i class="fa fa-shopping-basket"></i><span--%>
<%--                                                            class="tooltipp">add to compare</span></button>--%>
<%--                                                    <button class="quick-view"><i class="fa fa-eye"></i><span--%>
<%--                                                            class="tooltipp">quick view</span></button>--%>
<%--                                                </div>--%>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="products-slick-nav"></div>
                            </div>
                            <!-- /tab -->
                        </div>
                    </div>
                </div>
            </div>
        </div>


<div class="row" style="margin-top:25px">
<h1 style="text-align:center; width:100%" id="adidas">GIÀY ADIDAS</h1>
<div class="col-md-12 text-center">
    <div id="">
        <button onclick="loadMoreAdidas()" class="btn btn-primary mt-4" style="margin-left: 1090px;">Load more</button>
    </div>
</div>
<div class="col-md-12">
<div class="row" id="contentAdidas">
<div class="product-container" style="margin-left: 38px;">
<div class="products-tabs">
<!-- tab -->
<div class="tab-pane active">
<div class="products-slick" data-nav="#slick-nav-1">
<c:forEach items="${list4AdidasLast}" var="o">
    <div class="product productAdidas" style="height: 500px;">
    <div class="product-img">
    <a href="detail?pid=${o.id}" title="View Product">
    <img src="${o.image}" alt="">
    <div class="product-label">
    <!-- <span class="sale">-30%</span> -->
    <span class="new">NEW</span>
    </div>
    </a>
    </div>
    <div class="product-body">
    <p class="product-category">Category</p>
    <h3 class="product-name"><a href="detail?pid=${o.id}" title="View Product">${o.name}</a></h3>
    <h4 class="product-price">$980.00 <del class="product-old-price">${o.price} $</del></h4>
    <div class="product-rating">
    <i class="fa fa-star"></i>
    <i class="fa fa-star"></i>
    <i class="fa fa-star"></i>
    <i class="fa fa-star"></i>
    <i class="fa fa-star"></i>
    </div>
<%--    <div class="product-btns">--%>
<%--    <button class="add-to-wishlist"><i class="fa fa-shopping-cart"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--        <button class="add-to-compare"><i class="fa fa-shopping-basket"></i><span class="tooltipp">add to compare</span></button>--%>
<%--        <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--    </div>--%>
    </div>
    </div>
</c:forEach>
</div>
    <div class="products-slick-nav"></div>
</div>
    <!-- /tab -->
</div>
</div>
</div>
</div>
</div>

    </div>
</div>


<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    function loadMore() {
        var amount = document.getElementsByClassName("product").length;
        $.ajax({
            url: "/shoestoreweb/load",
            type: "get", //send it through get method
            data: {
                exits: amount
            },
            success: function (data) {
                var row = document.getElementById("content");
                row.innerHTML += data;
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function loadMoreNike() {
        var amountNike = document.getElementsByClassName("productNike").length;
        $.ajax({
            url: "/shoestoreweb/loadNike",
            type: "get", //send it through get method
            data: {
                exitsNike: amountNike
            },
            success: function (dataNike) {
                document.getElementById("contentNike").innerHTML += dataNike;

            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    function loadMoreAdidas() {
        var amountAdidas = document.getElementsByClassName("productAdidas").length;
        $.ajax({
            url: "/shoestoreweb/loadAdidas",
            type: "get", //send it through get method
            data: {
                exitsAdidas: amountAdidas
            },
            success: function (dataAdidas) {
                document.getElementById("contentAdidas").innerHTML += dataAdidas;

            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

    // function searchByName(param) {
    //     var txtSearch = param.value;
    //     $.ajax({
    //         url: "/shoestoreweb/searchAjax",
    //         type: "get", //send it through get method
    //         data: {
    //             txt: txtSearch
    //         },
    //         success: function (data) {
    //             var row = document.getElementById("content");
    //             row.innerHTML = data;
    //         },
    //         error: function (xhr) {
    //             //Do Something to handle error
    //         }
    //     });
    // }

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

    function redirectToPage() {
        var searchTerm = document.getElementById("searchInput").value;

        if (searchTerm.trim() !== "") {
            window.location.href = 'shop';
        }

        // Ngăn chặn form submit mặc định
        return false;
    }
</script>


<!-- MDB -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript" src="js/script.js"></script>

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

<!-- jQuery Plugins -->
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/slick.min.js"></script>
<script src="static/js/nouislider.min.js"></script>
<script src="static/js/jquery.zoom.min.js"></script>
<script src="static/js/main.js"></script>
</body>
</html>

