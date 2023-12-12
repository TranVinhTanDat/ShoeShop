<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <%--Teamplace--%>
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
    <%--End Teamplace--%>
    <title>Order</title>
    <style>
        /* Body Styling */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #348F50, #56B4D3); /* Gradient màu nền */
            color: #fff; /* Màu chữ chung */
        }

        /* Form Styling */
        #logreg-forms {
            width: 90%;
            max-width: 400px;
            margin: 10vh auto;
            background-color: rgba(255, 255, 255, 0.1); /* Màu nền form */
            border-radius: 10px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3), 0 1px 2px rgba(0, 0, 0, 0.24);
            padding: 20px;
        }

        /* Input Styling */
        #logreg-forms input[type="text"],
        #logreg-forms input[type="password"],
        #logreg-forms input[type="email"],
        #logreg-forms button {
            width: 100%;
            margin-bottom: 15px;
            padding: 15px;
            border-radius: 8px;
            border: none;
            background-color: rgba(255, 255, 255, 0.7); /* Màu nền input */
            color: #333; /* Màu chữ input */
        }

        /* Button Styling */
        #logreg-forms button {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        #logreg-forms button:hover {
            background-color: #45a049;
        }

        /* Alert Styling */
        .alert {
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 8px;
        }

        .alert-danger {
            background-color: #FF6347; /* Màu thông báo lỗi */
            color: white;
        }

        .alert-success {
            background-color: #32CD32; /* Màu thông báo thành công */
            color: white;
        }

        /* Links Styling */
        #logreg-forms a {
            color: #56B4D3;
            text-decoration: none;
            display: block;
            margin-top: 10px;
        }

        /* Media Queries for Responsive Design */
        @media screen and (max-width: 500px) {
            #logreg-forms {
                width: 80%;
            }
        }

    </style>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>
<div id="logreg-forms">
    <form class="form-signin" action="order" method="post">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Order</h1>
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
        <label for="name">Name</label>
        <input name="name" type="text" id="name" class="form-control" placeholder="Name" required="" autofocus="">
        <label for="phoneNumber">Phone number</label>
        <input name="phoneNumber" type="text" id="phoneNumber" class="form-control" placeholder="Phone number"
               required="" autofocus="">
        <label for="email">Email</label>
        <input name="email" type="text" id="email" class="form-control" placeholder="Email" required="" autofocus="">
        <label for="deliveryAddress">Delivery Address</label>
        <input name="deliveryAddress" type="text" id="deliveryAddress" class="form-control"
               placeholder="Delivery Address" required="" autofocus="">

        <button class="btn btn-success btn-block" type="submit"><i
                class="fas fa-american-sign-language-interpreting"></i> Order
        </button>

    </form>


    <br>

</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function toggleResetPswd(e) {
        e.preventDefault();
        $('#logreg-forms .form-signin').toggle() // display:block or none
        $('#logreg-forms .form-reset').toggle() // display:block or none
    }

    function toggleSignUp(e) {
        e.preventDefault();
        $('#logreg-forms .form-signin').toggle(); // display:block or none
        $('#logreg-forms .form-signup').toggle(); // display:block or none
    }

    $(() => {
        // Login Register Form
        $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
        $('#logreg-forms #cancel_reset').click(toggleResetPswd);
        $('#logreg-forms #btn-signup').click(toggleSignUp);
        $('#logreg-forms #cancel_signup').click(toggleSignUp);
    })

    window.addEventListener("load", function loadAmountCart() {
        $.ajax({
            url: "/shoestoreweb/loadAllAmountCart",
            type: "get", //send it through get method
            data: {},
            success: function (responseData) {
                document.getElementById("amountCart").innerHTML = responseData;
            }
        });
    }, false);
</script>
</body>
</html>