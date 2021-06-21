<%--
  Created by IntelliJ IDEA.
  User: khoad
  Date: 5/17/2021
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="calculator.styles.css"/>
    <title>Title</title>
    <div class="container">
        <form action="/login" method="post" onsubmit="return handleLogin()">
            <h1 class="title">Login</h1>
            <div class="mb-3">
                <label class="form-label" for="username">Username: </label>
                <input class="form-control" id="username" type="text" placeholder="Please enter your username" name="username"/>
                <small id="username-help" class="form-text"></small>
            </div>
            <div class="mb-3">
                <label class="form-label" for="password">Password: </label>
                <input class="form-control" id="password" type="text" placeholder="Please enter your password" name="password"/>
                <small id="password-help" class="form-text"></small>
            </div>
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
    </div>
    <script lang="javascript">
        function handleLogin() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            console.log(username);
            if (username == "") {
                document.getElementById("username-help").innerText = "Please input your username !";
                return false;
            }
            if (password == "") {
                document.getElementById("password-help").innerText = "Please input your password !";
                return false;
            }
            return true
        }
    </script>
</body>
</html>
