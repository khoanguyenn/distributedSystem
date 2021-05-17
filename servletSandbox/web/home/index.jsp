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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="calculator.styles.css"/>
    <title>Title</title>
    <div class="container">
        <form action="/login" method="post">
            <h1 class="title">Login</h1>
            <div class="mb-3">
                <label class="form-label" for="username">Username: </label>
                <input class="form-control" id="username" type="text" placeholder="Please enter your username" name="username"/>
            </div>
            <div class="mb-3">
                <label class="form-label" for="password">Password: </label>
                <input class="form-control" id="password" type="text" placeholder="Please enter your password" name="password"/>
            </div>
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
    </div>

</head>
<body>

</body>
</html>
