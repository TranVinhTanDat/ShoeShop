<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i>  1734 Stonecoal Road</a></li>
            </ul>
            <ul class="header-links pull-right">
                <c:if test="${sessionScope.acc != null}">
                    <li><a href="#"> ${sessionScope.acc.user}</a></li>
                    <li><a href="logout"><i class="fa fa-user-o"></i> Logout</a></li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li><a href="login"><i class="fa fa-sign-in"></i> Login</a></li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li><a href="forgotPassword"><i class="fa fa-unlock"></i> Forgot PassWord</a></li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <li><a href="EditProfile.jsp"><i class="fa fa-dollar"></i> Edit  Profile</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="home" class="logo">
                            <img src="static/img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form>
                            <select class="input-select">
                                <option value="0">All Categories</option>
                                <option value="1">Category 01</option>
                                <option value="1">Category 02</option>
                            </select>
                            <input class="input" placeholder="Search here">
                            <button class="search-btn">Search</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <%--<div>
                            <a href="#">
                                <i class="fa fa-heart-o"></i>
                                <span>Your Wishlist</span>
                                <div class="qty">2</div>
                            </a>
                        </div>--%>
                        <!-- /Wishlist -->

                        <!-- Cart -->


                        <form action="search" method="post" class="form-inline my-2 my-lg-0">

                            <a class="btn btn-sm ml-3" href="managerCart">
                                <i class="fa fa-shopping-cart" style="color: #e3e3e3; font-size: 25px"></i> <span style="font-size: 14px; color: #e3e3e3"></span>
                                <b><span id="amountCart" class="badge badge-light" style="color:black; font-size: 12px;"></span></b>

                            </a>
                        </form>
                        <!-- /Cart -->


                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->