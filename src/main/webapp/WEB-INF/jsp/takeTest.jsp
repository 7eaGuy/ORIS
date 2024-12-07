<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${quiz.title}</title>
</head>
<body>
<h1>${quiz.title}</h1>

<form action="${pageContext.request.contextPath}/takeQuiz" method="post">
    <input type="hidden" name="quizId" value="${quiz.id}">

    <c:forEach items="${questions}" var="question" varStatus="status">
        <h3>${question.title}</h3>
        <p>${question.questionText}</p>

        <c:forEach items="${question.answers}" var="answer">
            <input type="radio" name="answer${status.index}" value="${answer.id}"> ${answer.answerText}<br>
        </c:forEach>
    </c:forEach>

    <button type="submit">Submit Answers</button>
</form>
</body>
</html>
