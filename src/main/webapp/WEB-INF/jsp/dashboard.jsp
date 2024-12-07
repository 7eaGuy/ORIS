<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
<h1>Welcome, ${user.username}!</h1>
<h2>Your Test Results</h2>
<table border="1">
    <thead>
    <tr>
        <th>Test Title</th>
        <th>Score</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userQuizzes}" var="result">
        <tr>
            <td>${result.quizTitle}</td>
            <td>${result.score}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
