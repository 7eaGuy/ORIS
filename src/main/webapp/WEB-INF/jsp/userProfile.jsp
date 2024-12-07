<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>
<h1>Welcome, ${user.username}!</h1>

<h2>Test Results:</h2>
<table>
    <tr>
        <th>Quiz Title</th>
        <th>Score</th>
    </tr>
    <c:forEach items="${userQuizzes}" var="userQuiz">
        <tr>
            <td>${userQuiz.quizTitle}</td>
            <td>${userQuiz.score}</td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/changePassword">Change Password</a> |
<a href="${pageContext.request.contextPath}/deleteAccount">Delete Account</a>
</body>
</html>
