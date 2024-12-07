<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
</head>
<body>
<h1>User Management</h1>
<table border="1">
    <thead>
    <tr>
        <th>Username</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.admin ? "Admin" : "User"}</td>
            <td>
                <form method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" name="action" value="delete">Delete</button>
                </form>
                <form method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" name="action" value="${user.admin ? 'revokeAdmin' : 'makeAdmin'}">
                            ${user.admin ? "Revoke Admin" : "Make Admin"}
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
