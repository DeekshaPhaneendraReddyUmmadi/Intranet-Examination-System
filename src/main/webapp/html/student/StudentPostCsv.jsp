<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CSV File Upload</title>
</head>
<body>
    <h1>Upload CSV Data</h1>
    <form action="<%= request.getContextPath()%>/insertData" method="get" id="csvForm" enctype="multipart/form-data">
        <input type="file" name="csvFile" id="csvFile" accept=".csv">
        <input type="submit" value="Upload">
    </form>

</body>
</html>