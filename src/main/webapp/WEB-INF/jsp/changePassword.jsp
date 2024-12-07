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
    <title>Change Password</title>
</head>
<body>
<h1>Change Password</h1>

<form action="${pageContext.request.contextPath}/changePassword" method="post">
    <label for="oldPassword">Old Password:</label>
    <input type="password" name="oldPassword" id="oldPassword" required><br>

    <label for="newPassword">New Password:</label>
    <input type="password" name="newPassword" id="newPassword" required><br>

    <button type="submit">Change Password</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
