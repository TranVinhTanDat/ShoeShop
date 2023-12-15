<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
            </ul>
            <ul class="header-links pull-right">
                <% if (session.getAttribute("acc") == null) { %>
                <!-- Nếu chưa đăng nhập -->
                <li><a href="login"><i class="fa fa-sign-in"></i> Login</a></li>
                <% } else { %>
                <!-- Nếu đã đăng nhập -->
                <li><a href="#"><i class=""></i> ${sessionScope.acc.user}</a></li>
                <li><a href="logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                <li><a href="EditProfile.jsp"><i class="fa fa-pencil"></i> Edit Profile</a></li>
                <% } %>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="home" class="logo">
                            <img src="static/img/logo.png" alt="">
                        </a>
                    </div>
                </div>

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

                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <form action="search" method="post" class="form-inline my-2 my-lg-0">
                            <a class="btn btn-sm ml-3" href="managerCart">
                                <i class="fa fa-shopping-cart" style="color: #e3e3e3; font-size: 25px"></i>
                                <span style="font-size: 14px; color: #e3e3e3"></span>
                                <b><span id="amountCart" class="badge badge-light"
                                         style="color:black; font-size: 12px;"></span></b>
                            </a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->
