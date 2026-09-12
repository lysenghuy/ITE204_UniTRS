<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor Dashboard - UniTRS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/professor/dashboard">
                <i class="bi bi-person-workspace me-2"></i>UniTRS Professor
            </a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">
                    <i class="bi bi-person-circle me-1"></i>${sessionScope.user.fullName}
                </span>
                <c:if test="${sessionScope.user.deanSchoolId != null}">
                    <a href="${pageContext.request.contextPath}/dean/dashboard" class="btn btn-outline-info btn-sm me-2"><i class="bi bi-mortarboard"></i> Switch to Dean Dashboard</a>
                </c:if>
                <a class="btn btn-outline-light btn-sm" href="${pageContext.request.contextPath}/auth/logout">
                    <i class="bi bi-box-arrow-right me-1"></i>Logout
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h2>Professor Dashboard</h2>
        <div class="alert alert-info">
            Welcome to the Professor Dashboard. This section is currently under construction.
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
