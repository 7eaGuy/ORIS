<%--
  Created by IntelliJ IDEA.
  User: Badma
  Date: 07.12.2024
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Question</title>
</head>
<body>
<h1>Create a New Question</h1>

<form action="${pageContext.request.contextPath}/admin/createQuestion" method="post">
    <label for="questionTitle">Question Title:</label>
    <input type="text" id="questionTitle" name="questionTitle" required><br><br>

    <label for="questionText">Question Text:</label>
    <textarea id="questionText" name="questionText" required></textarea><br><br>

    <button type="submit">Create Question</button>
</form>

<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
</body>
</html>

