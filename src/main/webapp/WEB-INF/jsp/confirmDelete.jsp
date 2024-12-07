<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Account</title>
</head>
<body>
<h1>Are you sure you want to delete your account?</h1>
<form action="${pageContext.request.contextPath}/deleteAccount" method="post">
    <button type="submit">Yes, Delete My Account</button>
</form>
<a href="${pageContext.request.contextPath}/profile">No, Go Back</a>
</body>
</html>
