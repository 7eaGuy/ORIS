<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
</head>
<body>
<h1>Admin Panel</h1>

<h2>Quizzes</h2>
<ul>
    <c:forEach items="${tests}" var="test">
        <li>${test.title}</li>
    </c:forEach>
</ul>

<h2>Questions</h2>
<ul>
    <c:forEach items="${questions}" var="question">
        <li>${question.title}</li>
    </c:forEach>
</ul>
</body>
</html>
