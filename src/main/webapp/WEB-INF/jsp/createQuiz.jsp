<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Quiz</title>
</head>
<body>
<h1>Create a New Quiz</h1>

<form action="${pageContext.request.contextPath}/admin/createQuiz" method="post">
    <label for="quizTitle">Quiz Title:</label>
    <input type="text" id="quizTitle" name="quizTitle" required><br><br>

    <h3>Select Questions:</h3>
    <c:forEach items="${allQuestions}" var="question">
        <input type="checkbox" name="questionIds" value="${question.id}"> ${question.title}<br>
    </c:forEach>

    <button type="submit">Create Quiz</button>
</form>

<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
</body>
</html>
