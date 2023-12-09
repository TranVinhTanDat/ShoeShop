<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link
        href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet" id="bootstrap-css">
<script
        src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
        src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
          crossorigin="anonymous">
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <title>Forgot Password Form</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

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

    <style>
        /* Body Styling */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #348F50, #56B4D3); /* Gradient màu n?n */
            color: #fff; /* Màu ch? chung */
        }

        /* Form Styling */
        #logreg-forms {
            width: 90%;
            max-width: 400px;
            margin: 10vh auto;
            background-color: rgba(255, 255, 255, 0.1); /* Màu n?n form */
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
            background-color: rgba(255, 255, 255, 0.7); /* Màu n?n input */
            color: #333; /* Màu ch? input */
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
            background-color: #FF6347; /* Màu thông báo l?i */
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
    <form class="form-signin" action="forgotPassword" method="post">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Forgot Password</h1>
        <p class="text-success">${mess}</p>
        <p class="text-danger">${error}</p>
        <label for="username">Username</label>
        <input name="username" type="text" id="username" class="form-control" placeholder="Username" required=""
               autofocus="">
        <label for="email">Email</label>
        <input name="email" type="text" id="email" class="form-control" placeholder="Email" required="" autofocus="">
        <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Retrieve</button>
    </form>

</div>


<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
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