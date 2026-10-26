<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Phân công công việc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            background: #f8fafc;
        }
        .main {
            padding: 25px;
        }
        .form-box {
            background: white;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 25px;
        }
        .table {
            background: white;
            vertical-align: middle;
        }
        .table th,
        .table td {
            padding: 10px;
        }
        .table thead th {
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid p-0">
        <div class="row g-0">
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="phancong" />
                </jsp:include>
            </div>
            <div class="col-md-10 main">
                <h2 class="mb-4 text-uppercase fw-bold text-primary">
                    <i class="fa-solid fa-users-gear me-2"></i>
                    PHÂN CÔNG CÔNG VIỆC
                </h2>
                <!-- Thông báo thành công -->
                <c:if test="${param.success == 'insert'}">
                    <div class="alert alert-success">
                        Phân công công việc thành công!
                    </div>
                </c:if>
                <c:if test="${param.success == 'update'}">
                    <div class="alert alert-success">
                        Cập nhật phân công thành công!
                    </div>
                </c:if>
                <c:if test="${param.success == 'delete'}">
                    <div class="alert alert-success">
                        Xóa phân công thành công!
                    </div>
                </c:if>
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger">
                        Có lỗi xảy ra! Vui lòng kiểm tra dữ liệu và Console.
                    </div>
                </c:if>
                <!-- Form thêm và sửa phân công -->
                <div class="form-box">
                    <h5 class="mb-3">
                        <c:choose>
                            <c:when test="${not empty editPC}">
                                <i class="fa-solid fa-pen text-warning"></i>
                                Sửa phân công
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-plus text-success"></i>
                                Thêm phân công
                            </c:otherwise>
                        </c:choose>
                    </h5>
                    <form action="${pageContext.request.contextPath}/PhanCongServlet"
                          method="post">
                        <input type="hidden" name="action"
                               value="${not empty editPC ? 'update' : 'insert'}">
                        <c:if test="${not empty editPC}">
                            <input type="hidden" name="id"
                                   value="${editPC.id}">
                        </c:if>
                        <div class="row mb-3">
                            <!-- Chọn công việc -->
                            <div class="col-md-4">
                                <label class="form-label">Công việc</label>
                                <select name="congViecId"
                                        class="form-select" required>
                                    <option value="">-- Chọn công việc --</option>
                                    <c:forEach items="${dsCongViec}" var="cv">
                                        <option value="${cv.id}"
                                            ${not empty editPC && editPC.congViecId == cv.id ? 'selected' : ''}>
                                            <c:out value="${cv.tenCongViec}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Chọn nhân viên -->
                            <div class="col-md-4">
                                <label class="form-label">Nhân viên</label>
                                <select name="nhanVienId"
                                        class="form-select" required>
                                    <option value="">-- Chọn nhân viên --</option>
                                    <c:forEach items="${dsNhanVien}" var="nv">
                                        <option value="${nv.id}"
                                            ${not empty editPC && editPC.nhanVienId == nv.id ? 'selected' : ''}>
                                            <c:out value="${nv.hoTen}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <!-- Ngày phân công -->
                            <div class="col-md-4">
                                <label class="form-label">Ngày phân công</label>

                                <input type="date"
                                       name="ngayPhanCong"
                                       class="form-control"
                                       value="${editPC.ngayPhanCong}"
                                       required>
                            </div>
                        </div>
                        <!-- Nút thêm hoặc cập nhật -->
                        <c:choose>
                            <c:when test="${not empty editPC}">
                                <button type="submit"
                                        class="btn btn-warning text-white">
                                    <i class="fa-solid fa-save"></i>
                                    Cập nhật
                                </button>
                                <a href="${pageContext.request.contextPath}/PhanCongServlet"
                                   class="btn btn-secondary">
                                    Hủy sửa
                                </a>
                            </c:when>
                            <c:otherwise>
                                <button type="submit"
                                        class="btn btn-success">
                                    <i class="fa-solid fa-plus"></i>
                                    Phân công
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
                <!-- Form tìm kiếm -->
                <form action="${pageContext.request.contextPath}/PhanCongServlet"
                      method="get"
                      class="d-flex mb-3"
                      style="gap:10px;">

                    <input type="text"
                           name="keyword"
                           class="form-control"
                           placeholder="Tìm công việc hoặc nhân viên..."
                           value="<c:out value='${keyword}'/>">

                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-search"></i>
                        Tìm kiếm
                    </button>
                    <a href="${pageContext.request.contextPath}/PhanCongServlet"
                       class="btn btn-secondary">
                        Làm mới
                    </a>
                </form>
                <!-- Bảng danh sách phân công -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th style="width: 100px;">ID</th>
                                <th>Công việc</th>
                                <th>Nhân viên</th>
                                <th>Ngày phân công</th>
                                <th style="width: 130px;">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty dsPhanCong}">
                                    <c:forEach items="${dsPhanCong}"
                                               var="pc"
                                               varStatus="st">
                                        <tr>
                                            <!-- ID hiển thị tuần tự từ 1 -->
                                            <td>${st.count}</td>
                                            <td>
                                                <c:out value="${pc.tenCongViec}"/>
                                            </td>
                                            <td>
                                                <c:out value="${pc.tenNhanVien}"/>
                                            </td>
                                            <td>
                                                <c:out value="${pc.ngayPhanCong}"/>
                                            </td>
                                            <td>
                                                <!-- Sửa theo ID thật trong database -->
                                                <a href="${pageContext.request.contextPath}/PhanCongServlet?action=edit&id=${pc.id}"
                                                   class="btn btn-warning btn-sm text-white"
                                                   title="Sửa">
                                                    <i class="fa-solid fa-pen"></i>
                                                </a>
                                                <!-- Xóa theo ID thật trong database -->
                                                <a href="${pageContext.request.contextPath}/PhanCongServlet?action=delete&id=${pc.id}"
                                                   class="btn btn-danger btn-sm"
                                                   title="Xóa"
                                                   onclick="return confirm('Bạn có chắc muốn xóa phân công này không?');">
                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5"
                                            class="text-center text-muted">
                                            Chưa có phân công công việc nào.
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```