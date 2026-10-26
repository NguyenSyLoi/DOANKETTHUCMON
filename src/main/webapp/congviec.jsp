<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý công việc</title>
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
            background: #f8fafc;
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

        .badge-doing {
            background: #ffc107;
            color: black;
        }

        .badge-done {
            background: #198754;
        }

        .badge-not {
            background: #6c757d;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid p-0">
        <div class="row g-0">
            <!-- SIDEBAR -->
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="congviec" />
                </jsp:include>
            </div>
            <!-- NỘI DUNG CHÍNH -->
            <div class="col-md-10 main">
                <h2 class="mb-4 text-uppercase fw-bold text-primary">
                    <i class="fa-solid fa-list-check me-2"></i>
                    QUẢN LÝ CÔNG VIỆC
                </h2>
                <!-- THÔNG BÁO -->
                <c:if test="${param.success == 'insert'}">
                    <div class="alert alert-success">
                        <i class="fa-solid fa-circle-check"></i>
                        Thêm công việc thành công!
                    </div>
                </c:if>
                <c:if test="${param.success == 'update'}">
                    <div class="alert alert-success">
                        <i class="fa-solid fa-circle-check"></i>
                        Cập nhật công việc thành công!
                    </div>
                </c:if>
                <c:if test="${param.success == 'delete'}">
                    <div class="alert alert-success">
                        <i class="fa-solid fa-circle-check"></i>
                        Xóa công việc thành công!
                    </div>
                </c:if>
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger">
                        Có lỗi xảy ra! Vui lòng kiểm tra dữ liệu.
                    </div>
                </c:if>
                <!-- FORM THÊM / SỬA -->
                <div class="form-box">
                    <h5 class="mb-3">
                        <c:choose>
                            <c:when test="${not empty editId}">
                                <i class="fa-solid fa-pen text-warning"></i>
                                Sửa công việc
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-plus text-success"></i>
                                Thêm công việc
                            </c:otherwise>
                        </c:choose>
                    </h5>
                    <form action="${pageContext.request.contextPath}/CongViecServlet"
                          method="post">
                        <input type="hidden"
                               name="action"
                               value="${not empty editId ? 'update' : 'insert'}">
                        <c:if test="${not empty editId}">
                            <input type="hidden"
                                   name="id"
                                   value="${editId}">
                        </c:if>
                        <!-- TÊN CÔNG VIỆC VÀ DỰ ÁN -->
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Tên công việc</label>
                                <input type="text"
                                       name="tenCongViec"
                                       class="form-control"
                                       value="<c:out value='${editTen}'/>"
                                       placeholder="Nhập tên công việc"
                                       required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Dự án</label>
                                <select name="duAnId"
                                        class="form-select"
                                        required>
                                    <option value="">-- Chọn dự án --</option>
                                    <c:forEach items="${dsDuAn}" var="da">
                                        <option value="${da.id}"
                                            ${da.id == editDuAnId ? 'selected' : ''}>
                                            <c:out value="${da.tenDuAn}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- MÔ TẢ -->
                        <div class="mb-3">
                            <label class="form-label">Mô tả</label>
                            <textarea name="moTa"
                                      class="form-control"
                                      rows="3"
                                      placeholder="Nhập mô tả công việc"><c:out value="${editMoTa}"/></textarea>
                        </div>
                        <!-- HẠN, TRẠNG THÁI, ƯU TIÊN -->
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label">Hạn hoàn thành</label>
                                <input type="date"
                                       name="hanHoanThanh"
                                       class="form-control"
                                       value="${editHan}">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">Trạng thái</label>
                                <select name="trangThai"
                                        class="form-select">
                                    <option value="Chưa bắt đầu"
                                        ${editTrangThai == 'Chưa bắt đầu' ? 'selected' : ''}>
                                        Chưa bắt đầu
                                    </option>
                                    <option value="Đang thực hiện"
                                        ${editTrangThai == 'Đang thực hiện' ? 'selected' : ''}>
                                        Đang thực hiện
                                    </option>
                                    <option value="Hoàn thành"
                                        ${editTrangThai == 'Hoàn thành' ? 'selected' : ''}>
                                        Hoàn thành
                                    </option>

                                </select>
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">Mức độ ưu tiên</label>

                                <select name="mucDoUuTien"
                                        class="form-select">
                                    <option value="Thấp"
                                        ${editUuTien == 'Thấp' ? 'selected' : ''}>
                                        Thấp
                                    </option>
                                    <option value="Trung bình"
                                        ${editUuTien == 'Trung bình' ? 'selected' : ''}>
                                        Trung bình
                                    </option>
                                    <option value="Cao"
                                        ${editUuTien == 'Cao' ? 'selected' : ''}>
                                        Cao
                                    </option>
                                </select>
                            </div>
                        </div>
                        <!-- NÚT THÊM / CẬP NHẬT -->
                        <c:choose>
                            <c:when test="${not empty editId}">
                                <button type="submit"
                                        class="btn btn-warning text-white">
                                    <i class="fa-solid fa-save"></i>
                                    Cập nhật
                                </button>
                                <a href="${pageContext.request.contextPath}/CongViecServlet"
                                   class="btn btn-secondary">
                                    Hủy sửa
                                </a>
                            </c:when>
                            <c:otherwise>
                                <button type="submit"
                                        class="btn btn-success">
                                    <i class="fa-solid fa-plus"></i>
                                    Thêm công việc
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
                <!-- TÌM KIẾM -->
                <form action="${pageContext.request.contextPath}/CongViecServlet"
                      method="get"
                      class="d-flex mb-3"
                      style="gap:10px;">
                    <input type="text"
                           name="keyword"
                           class="form-control"
                           placeholder="Tìm theo tên công việc..."
                           value="<c:out value='${param.keyword}'/>">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-search"></i>
                        Tìm kiếm
                    </button>
                    <a href="${pageContext.request.contextPath}/CongViecServlet"
                       class="btn btn-secondary">
                        Làm mới
                    </a>
                </form>
                <!-- BẢNG DANH SÁCH CÔNG VIỆC -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th style="width: 70px;">ID</th>
                                <th>Tên công việc</th>
                                <th>Mô tả</th>
                                <th>Hạn</th>
                                <th>Trạng thái</th>
                                <th>Ưu tiên</th>
                                <th>Dự án</th>
                                <th style="width: 120px;">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty dsCongViec}">

                                    <c:forEach items="${dsCongViec}"
                                               var="cv"
                                               varStatus="st">
                                        <tr>
                                            <!-- ID HIỂN THỊ THEO THỨ TỰ -->
                                            <td>${st.count}</td>
                                            <td>
                                                <strong>
                                                    <c:out value="${cv.tenCongViec}"/>
                                                </strong>
                                            </td>

                                            <td>
                                                <c:out value="${cv.moTa}"/>
                                            </td>

                                            <td>
                                                <c:out value="${cv.hanHoanThanh}"/>
                                            </td>

                                            <!-- TRẠNG THÁI -->
                                            <td>
                                                <c:choose>
                                                    <c:when test="${cv.trangThai == 'Hoàn thành'}">
                                                        <span class="badge badge-done">
                                                            Hoàn thành
                                                        </span>
                                                    </c:when>
                                                    <c:when test="${cv.trangThai == 'Đang thực hiện'}">
                                                        <span class="badge badge-doing">
                                                            Đang thực hiện
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge badge-not">
                                                            Chưa bắt đầu
                                                        </span>
                                                    </c:otherwise>

                                                </c:choose>
                                            </td>
                                            <!-- ƯU TIÊN -->
                                            <td>
                                                <c:out value="${cv.mucDoUuTien}"/>
                                            </td>
                                            <!-- DỰ ÁN -->
                                            <td>
                                                <c:out value="${cv.duAnId}"/>
                                            </td>
                                            <!-- THAO TÁC -->
                                            <td>
                                                <a href="${pageContext.request.contextPath}/CongViecServlet?action=edit&id=${cv.id}"
                                                   class="btn btn-warning btn-sm text-white"
                                                   title="Sửa">
                                                    <i class="fa-solid fa-pen"></i>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/CongViecServlet?action=delete&id=${cv.id}"
                                                   class="btn btn-danger btn-sm"
                                                   title="Xóa"
                                                   onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?');">

                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="8"
                                            class="text-center text-muted">
                                            Không có công việc nào.
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