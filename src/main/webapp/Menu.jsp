<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> 028 38382 664</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> j2eeprojectshoe@email.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> 273 An Duong Vuong</a></li>
            </ul>
            <ul class="header-links pull-right">
                <% if (session.getAttribute("acc") == null) { %>
                <!-- Nếu chưa đăng nhập -->
                <li><a href="login"><i class="fa fa-sign-in"></i> Login</a></li>
                <% } else { %>
                <!-- Nếu đã đăng nhập -->
                <li><a href="#"><i class="fa fa-user"></i> ${sessionScope.acc.user}</a></li>
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

                <div class="col-md-8">
                    <div class="header-search">
                        <form action="searchForm" method="get" >
                            <input id="searchInput" name="search" class="input" style="border-radius: 40px 0px 0px 40px;" placeholder="Search here">
                            <button type="submit" class="search-btn">Search</button>
                        </form>
                    </div>
                </div>

                <div class="col-md-1 clearfix">
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
