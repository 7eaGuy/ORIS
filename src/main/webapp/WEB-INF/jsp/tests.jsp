<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Quizzes</title>
</head>
<body>
<h1>Manage Quizzes</h1>
<form method="post" action="${pageContext.request.contextPath}/admin/quizzes">
    <label for="title">Quiz Title:</label>
    <input type="text" id="title" name="title" required>
    <br>
    <h2>Select Questions:</h2>
    <ul>
        <c:forEach items="${questions}" var="question">
            <li>
                <input type="checkbox" name="questionIds" value="${question.id}">
                    ${question.title} - ${question.questionText}
            </li>
        </c:forEach>
    </ul>
    <button type="submit">Create Quiz</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<h2>Existing Quizzes</h2>
<ul>
    <c:forEach items="${quizzes}" var="quiz">
        <li>${quiz.title}</li>
    </c:forEach>
</ul>
</body>
</html>
