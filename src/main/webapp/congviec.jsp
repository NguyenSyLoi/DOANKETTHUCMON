<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý công việc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        .main { flex: 1; padding: 25px; background: #f8fafc; }
        .page-title { font-size: 24px; font-weight: bold; margin-bottom: 20px; color: #0f172a; }
        .form-box { background: white; border: 1px solid #e2e8f0; border-radius: 8px; padding: 20px; margin-bottom: 25px; box-shadow: 0 1px 3px rgba(0,0,0,0.05); }
        .table { vertical-align: middle; background: white; }
        .badge-doing { background: #ffc107; color: black; }
        .badge-done { background: #198754; }
        .badge-not { background: #6c757d; }
    </style>
</head>
<body>

    <!-- 1. Header & Sidebar -->
    <jsp:include page="header.jsp" />

    <div class="container-fluid flex-grow-1 p-0">
        <div class="row g-0 h-100">
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="congviec" />
                </jsp:include>
            </div>

            <!-- MAIN CONTENT -->
            <div class="col-md-10 p-4">
                <h2 class="mb-4 text-uppercase fw-bold text-primary">
                    <i class="bi bi-list-task me-2"></i>QUẢN LÝ CÔNG VIỆC
                </h2>

                <!-- THÔNG BÁO -->
                <c:if test="${param.success == 'insert'}">
                    <div class="alert alert-success"><i class="fa-solid fa-circle-check"></i> Thêm công việc thành công!</div>
                </c:if>
                <c:if test="${param.success == 'update'}">
                    <div class="alert alert-success"><i class="fa-solid fa-circle-check"></i> Cập nhật công việc thành công!</div>
                </c:if>
                <c:if test="${param.success == 'delete'}">
                    <div class="alert alert-success"><i class="fa-solid fa-circle-check"></i> Xóa công việc thành công!</div>
                </c:if>
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger">Có lỗi xảy ra!</div>
                </c:if>

                <!-- FORM THÊM / SỬA -->
                <div class="form-box">
                    <h5 class="mb-3">
                        <c:choose>
                            <c:when test="${not empty editId}">
                                <i class="fa-solid fa-pen text-warning"></i> Sửa công việc
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-plus text-success"></i> Thêm công việc
                            </c:otherwise>
                        </c:choose>
                    </h5>

                    <form action="CongViecServlet" method="post">
                        <input type="hidden" name="action" value="${not empty editId ? 'update' : 'insert'}">
                        <c:if test="${not empty editId}">
                            <input type="hidden" name="id" value="${editId}">
                        </c:if>

                        <div class="row mb-3">
                            <!-- TÊN CÔNG VIỆC -->
                            <div class="col-md-6">
                                <label class="form-label">Tên công việc</label>
                                <input type="text" name="tenCongViec" class="form-control" value="${editTen}" required>
                            </div>

                            <!-- DỰ ÁN -->
                            <div class="col-md-6">
                                <label class="form-label">Dự án</label>
                                <select name="duAnId" class="form-select" required>
                                    <option value="">-- Chọn dự án --</option>
                                    <c:forEach items="${dsDuAn}" var="da">
                                        <option value="${da.id}" ${da.id == editDuAnId ? 'selected' : ''}>${da.tenDuAn}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- MÔ TẢ -->
                        <div class="mb-3">
                            <label class="form-label">Mô tả</label>
                            <textarea name="moTa" class="form-control" rows="3">${editMoTa}</textarea>
                        </div>

                        <div class="row mb-3">
                            <!-- HẠN HOÀN THÀNH -->
                            <div class="col-md-4">
                                <label class="form-label">Hạn hoàn thành</label>
                                <input type="date" name="hanHoanThanh" class="form-control" value="${editHan}">
                            </div>

                            <!-- TRẠNG THÁI -->
                            <div class="col-md-4">
                                <label class="form-label">Trạng thái</label>
                                <select name="trangThai" class="form-select">
                                    <option value="Chưa bắt đầu" ${editTrangThai == 'Chưa bắt đầu' ? 'selected' : ''}>Chưa bắt đầu</option>
                                    <option value="Đang thực hiện" ${editTrangThai == 'Đang thực hiện' ? 'selected' : ''}>Đang thực hiện</option>
                                    <option value="Hoàn thành" ${editTrangThai == 'Hoàn thành' ? 'selected' : ''}>Hoàn thành</option>
                                </select>
                            </div>

                            <!-- ƯU TIÊN -->
                            <div class="col-md-4">
                                <label class="form-label">Mức độ ưu tiên</label>
                                <select name="mucDoUuTien" class="form-select">
                                    <option value="Thấp" ${editUuTien == 'Thấp' ? 'selected' : ''}>Thấp</option>
                                    <option value="Trung bình" ${editUuTien == 'Trung bình' ? 'selected' : ''}>Trung bình</option>
                                    <option value="Cao" ${editUuTien == 'Cao' ? 'selected' : ''}>Cao</option>
                                </select>
                            </div>
                        </div>

                        <!-- BUTTON -->
                        <c:choose>
                            <c:when test="${not empty editId}">
                                <button type="submit" class="btn btn-warning text-white"><i class="fa-solid fa-save"></i> Cập nhật</button>
                                <a href="CongViecServlet" class="btn btn-secondary">Hủy sửa</a>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus"></i> Thêm công việc</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>

                <!-- TÌM KIẾM -->
                <form action="CongViecServlet" method="get" class="d-flex mb-3" style="gap:10px;">
                    <input type="text" name="keyword" class="form-control" placeholder="Tìm theo tên công việc..." value="${param.keyword}">
                    <button type="submit" class="btn btn-primary"><i class="fa-solid fa-search"></i> Tìm kiếm</button>
                    <a href="CongViecServlet" class="btn btn-secondary">Làm mới</a>
                </form>

                <!-- DANH SÁCH CÔNG VIỆC -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Tên công việc</th>
                                <th>Mô tả</th>
                                <th>Hạn</th>
                                <th>Trạng thái</th>
                                <th>Ưu tiên</th>
                                <th>Dự án</th>
                                <th width="130">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty dsCongViec}">
                                    <c:forEach items="${dsCongViec}" var="cv">
                                        <tr>
                                            <td>${cv.id}</td>
                                            <td><strong>${cv.tenCongViec}</strong></td>
                                            <td>${cv.moTa}</td>
                                            <td>${cv.hanHoanThanh}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${cv.trangThai == 'Hoàn thành'}">
                                                        <span class="badge badge-done">Hoàn thành</span>
                                                    </c:when>
                                                    <c:when test="${cv.trangThai == 'Đang thực hiện'}">
                                                        <span class="badge badge-doing">Đang thực hiện</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge badge-not">Chưa bắt đầu</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${cv.mucDoUuTien}</td>
                                            <td>${cv.duAnId}</td>
                                            <td>
                                                <a href="CongViecServlet?action=edit&id=${cv.id}" class="btn btn-warning btn-sm text-white" title="Sửa">
                                                    <i class="fa-solid fa-pen"></i>
                                                </a>
                                                <a href="CongViecServlet?action=delete&id=${cv.id}" class="btn btn-danger btn-sm" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?');">
                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="8" class="text-center text-muted">Không có công việc nào.</td>
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
</body>
</html>
