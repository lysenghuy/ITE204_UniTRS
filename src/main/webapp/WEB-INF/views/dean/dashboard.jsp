<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dean Dashboard - Curriculum Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .dashboard-header { background-color: #343a40; color: white; padding: 2rem 0; margin-bottom: 2rem; }
        .nav-tabs .nav-link { color: #495057; font-weight: 500; }
        .nav-tabs .nav-link.active { font-weight: 700; color: #0d6efd; }
        .card-custom { border: none; box-shadow: 0 4px 6px rgba(0,0,0,0.05); border-radius: 12px; }
        .table th { background-color: #f1f3f5; font-weight: 600; }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="dashboard-header shadow-sm">
        <div class="container d-flex justify-content-between align-items-center">
            <div>
                <h2 class="mb-1"><i class="bi bi-mortarboard me-2"></i>Dean Dashboard</h2>
                <p class="mb-0 text-white-50">Curriculum & Academic Management</p>
            </div>
            <div>
                <span class="me-3"><i class="bi bi-person-circle me-1"></i> ${user.fullName}</span>
                <a href="${pageContext.request.contextPath}/auth/logout" class="btn btn-outline-light btn-sm"><i class="bi bi-box-arrow-right"></i> Logout</a>
            </div>
        </div>
    </header>

    <div class="container mb-5">

        <!-- Alerts -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <!-- Navigation Tabs -->
        <ul class="nav nav-tabs mb-4" id="deanTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link ${activeTab == 'courses' ? 'active' : ''}" id="courses-tab" data-bs-toggle="tab" data-bs-target="#courses" type="button" role="tab"><i class="bi bi-book me-2"></i>Master Courses</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link ${activeTab == 'terms' ? 'active' : ''}" id="terms-tab" data-bs-toggle="tab" data-bs-target="#terms" type="button" role="tab"><i class="bi bi-calendar3 me-2"></i>Academic Terms</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link ${activeTab == 'bundles' ? 'active' : ''}" id="bundles-tab" data-bs-toggle="tab" data-bs-target="#bundles" type="button" role="tab"><i class="bi bi-collection me-2"></i>Curriculum Bundling</button>
            </li>
        </ul>

        <!-- Tab Content -->
        <div class="tab-content" id="deanTabsContent">
            
            <!-- 1. Master Courses Tab -->
            <div class="tab-pane fade ${activeTab == 'courses' ? 'show active' : ''}" id="courses" role="tabpanel">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0">Course Catalog</h4>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCourseModal"><i class="bi bi-plus-lg me-1"></i> Add Course</button>
                </div>
                
                <div class="card card-custom">
                    <div class="card-body p-0">
                        <table class="table table-hover mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Course Code</th>
                                    <th>Course Title</th>
                                    <th>Credits</th>
                                    <th class="text-end">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="course" items="${courses}">
                                    <tr>
                                        <td>${course.id}</td>
                                        <td><span class="badge bg-secondary">${course.courseCode}</span></td>
                                        <td>${course.courseTitle}</td>
                                        <td>${course.credits}</td>
                                        <td class="text-end">
                                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCourseModal${course.id}"><i class="bi bi-pencil"></i> Edit</button>
                                        </td>
                                    </tr>

                                    <!-- Edit Course Modal -->
                                    <div class="modal fade" id="editCourseModal${course.id}" tabindex="-1">
                                        <div class="modal-dialog">
                                            <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="modal-content">
                                                <input type="hidden" name="action" value="updateCourse">
                                                <input type="hidden" name="courseId" value="${course.id}">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Edit Course</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <label class="form-label">Course Code</label>
                                                        <input type="text" class="form-control" name="courseCode" value="${course.courseCode}" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Course Title</label>
                                                        <input type="text" class="form-control" name="courseTitle" value="${course.courseTitle}" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Credits</label>
                                                        <input type="number" class="form-control" name="credits" value="${course.credits}" required min="1" max="10">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty courses}">
                                    <tr><td colspan="5" class="text-center text-muted py-4">No courses available.</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- 2. Academic Terms Tab -->
            <div class="tab-pane fade ${activeTab == 'terms' ? 'show active' : ''}" id="terms" role="tabpanel">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0">Academic Terms</h4>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTermModal"><i class="bi bi-plus-lg me-1"></i> Add Term</button>
                </div>

                <div class="card card-custom">
                    <div class="card-body p-0">
                        <table class="table table-hover mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Term Number</th>
                                    <th>Term Name</th>
                                    <th class="text-end">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="term" items="${terms}">
                                    <tr>
                                        <td>${term.id}</td>
                                        <td><span class="badge bg-info text-dark">Term ${term.termNumber}</span></td>
                                        <td>${term.termName}</td>
                                        <td class="text-end">
                                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editTermModal${term.id}"><i class="bi bi-pencil"></i> Edit</button>
                                        </td>
                                    </tr>

                                    <!-- Edit Term Modal -->
                                    <div class="modal fade" id="editTermModal${term.id}" tabindex="-1">
                                        <div class="modal-dialog">
                                            <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="modal-content">
                                                <input type="hidden" name="action" value="updateTerm">
                                                <input type="hidden" name="termId" value="${term.id}">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Edit Term</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <label class="form-label">Term Number (e.g. 1, 2, 3)</label>
                                                        <input type="number" class="form-control" name="termNumber" value="${term.termNumber}" required min="1">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Term Name</label>
                                                        <input type="text" class="form-control" name="termName" value="${term.termName}" required>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:if test="${empty terms}">
                                    <tr><td colspan="4" class="text-center text-muted py-4">No terms available.</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- 3. Curriculum Bundling Tab -->
            <div class="tab-pane fade ${activeTab == 'bundles' ? 'show active' : ''}" id="bundles" role="tabpanel">
                <div class="row mb-3 align-items-center">
                    <div class="col">
                        <h4 class="mb-0">Term Curriculum Bundles</h4>
                        <p class="text-muted mb-0 small">Assign courses that will be offered in each term.</p>
                    </div>
                </div>

                <div class="row g-4">
                    <c:forEach var="entry" items="${curriculumMap}">
                        <c:set var="term" value="${entry.key}" />
                        <c:set var="termCourses" value="${entry.value}" />
                        
                        <div class="col-md-6 col-lg-4">
                            <div class="card card-custom h-100">
                                <div class="card-header bg-white d-flex justify-content-between align-items-center py-3">
                                    <h5 class="mb-0 text-primary fw-bold">${term.termName}</h5>
                                    <button class="btn btn-sm btn-success" data-bs-toggle="modal" data-bs-target="#bundleModal${term.id}">
                                        <i class="bi bi-plus"></i> Add Course
                                    </button>
                                </div>
                                <div class="card-body p-0">
                                    <ul class="list-group list-group-flush">
                                        <c:forEach var="course" items="${termCourses}">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <div>
                                                    <strong>${course.courseCode}</strong><br>
                                                    <small class="text-muted">${course.courseTitle}</small>
                                                </div>
                                                <!-- Unbundle form -->
                                                <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="m-0 p-0">
                                                    <input type="hidden" name="action" value="unbundleCourse">
                                                    <input type="hidden" name="termId" value="${term.id}">
                                                    <input type="hidden" name="courseId" value="${course.id}">
                                                    <button type="submit" class="btn btn-sm btn-outline-danger border-0" title="Remove from term" onclick="return confirm('Remove ${course.courseCode} from ${term.termName}?')">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${empty termCourses}">
                                            <li class="list-group-item text-center text-muted py-3">No courses assigned.</li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Bundle Course Modal for this Term -->
                        <div class="modal fade" id="bundleModal${term.id}" tabindex="-1">
                            <div class="modal-dialog">
                                <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="modal-content">
                                    <input type="hidden" name="action" value="bundleCourse">
                                    <input type="hidden" name="termId" value="${term.id}">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Assign Course to ${term.termName}</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label class="form-label">Select Course</label>
                                            <select name="courseId" class="form-select" required>
                                                <option value="">-- Choose a Course --</option>
                                                <c:forEach var="c" items="${courses}">
                                                    <!-- Check if course is already in this term to disable it -->
                                                    <c:set var="isAssigned" value="false" />
                                                    <c:forEach var="tc" items="${termCourses}">
                                                        <c:if test="${tc.id == c.id}">
                                                            <c:set var="isAssigned" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <option value="${c.id}" ${isAssigned ? 'disabled' : ''}>
                                                        ${c.courseCode} - ${c.courseTitle} ${isAssigned ? '(Already Assigned)' : ''}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary">Assign Course</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty curriculumMap}">
                        <div class="col-12 text-center text-muted py-4">No terms created yet. Please create a term first.</div>
                    </c:if>
                </div>
            </div>

        </div> <!-- End Tab Content -->
    </div>

    <!-- Modals for Creating New Items -->

    <!-- Add Course Modal -->
    <div class="modal fade" id="addCourseModal" tabindex="-1">
        <div class="modal-dialog">
            <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="modal-content">
                <input type="hidden" name="action" value="addCourse">
                <div class="modal-header">
                    <h5 class="modal-title">Create New Course</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Course Code (e.g. ITE 301)</label>
                        <input type="text" class="form-control" name="courseCode" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Course Title</label>
                        <input type="text" class="form-control" name="courseTitle" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Credits</label>
                        <input type="number" class="form-control" name="credits" value="3" required min="1" max="10">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Create Course</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Add Term Modal -->
    <div class="modal fade" id="addTermModal" tabindex="-1">
        <div class="modal-dialog">
            <form action="${pageContext.request.contextPath}/dean/dashboard" method="post" class="modal-content">
                <input type="hidden" name="action" value="addTerm">
                <div class="modal-header">
                    <h5 class="modal-title">Create New Term</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Term Number (e.g. 9)</label>
                        <input type="number" class="form-control" name="termNumber" required min="1">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Term Name</label>
                        <input type="text" class="form-control" name="termName" placeholder="e.g. Term 9" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Create Term</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Update URL hash without jumping to keep tab state clean on refresh
        const tabElements = document.querySelectorAll('button[data-bs-toggle="tab"]');
        tabElements.forEach(tab => {
            tab.addEventListener('shown.bs.tab', event => {
                const target = event.target.getAttribute('data-bs-target').substring(1);
                // We use history replaceState to not create a mess of back-buttons
                window.history.replaceState(null, null, '?tab=' + target);
            });
        });
    </script>
</body>
</html>
