<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Questions</title>
</head>
<body>
<h1>Manage Questions</h1>
<form method="post" action="${pageContext.request.contextPath}/admin/questions">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>
    <br>
    <label for="text">Question Text:</label>
    <textarea id="text" name="text" required></textarea>
    <br>
    <button type="submit">Add Question</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<h2>Existing Questions</h2>
<ul>
    <c:forEach items="${questions}" var="question">
        <li>${question.title} - ${question.questionText}</li>
    </c:forEach>
</ul>
</body>
</html>
