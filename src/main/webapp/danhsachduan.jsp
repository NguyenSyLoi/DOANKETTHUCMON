<%-- 
    Document   : danhsachduan
    Created on : Sep 16, 2026, 2:01:49 PM
    Author     : PC-31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Dự Án</title>
    <!-- Bootstrap 5 CSS & Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

    <!-- 1. Kết nối Header -->
    <jsp:include page="header.jsp" />

    <div class="container-fluid flex-grow-1 p-0">
        <div class="row g-0 h-100">
            <!-- 2. Kết nối Sidebar -->
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="duan" />
                </jsp:include>
            </div>

            <!-- 3. Nội dung chính Quản lý Dự án -->
            <div class="col-md-10 p-4">
                
                <h2 class="mb-4 text-uppercase fw-bold text-primary">
                    <i class="bi bi-folder-fill me-2"></i>DANH SÁCH DỰ ÁN
                </h2>

                <!-- Form Thêm Dự Án Mới -->
                <form action="du-an" method="post" class="card p-3 mb-4 bg-light shadow-sm border-0">
                    <input type="hidden" name="action" value="insert">
                    <h5 class="card-title fw-bold text-dark mb-3">Thêm Dự Án Mới</h5>
                    <div class="row">
                        <div class="col-md-4 mb-2">
                            <label class="form-label font-weight-bold">Tên Dự Án:</label>
                            <input type="text" name="tenDuAn" class="form-control" required>
                        </div>
                        <div class="col-md-4 mb-2">
                            <label class="form-label">Ngày Bắt Đầu:</label>
                            <input type="date" name="ngayBatDau" class="form-control">
                        </div>
                        <div class="col-md-4 mb-2">
                            <label class="form-label">Ngày Kết Thúc:</label>
                            <input type="date" name="ngayKetThuc" class="form-control">
                        </div>
                        <div class="col-md-12 mb-2">
                            <label class="form-label">Mô Tả:</label>
                            <textarea name="moTa" class="form-control" rows="2"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary w-25 mt-2">Lưu Dự Án</button>
                </form>
                <!-- Bảng Danh Sách -->
                <div class="card shadow-sm border-0">
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover align-middle mb-0">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên Dự Án</th>
                                        <th>Ngày Bắt Đầu</th>
                                        <th>Ngày Kết Thúc</th>
                                        <th>Mô Tả</th>
                                        <th class="text-center">Hành Động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Thêm varStatus="loop" để lấy số thứ tự tự động 1, 2, 3... -->
                                    <c:forEach items="${listDuAn}" var="d" varStatus="loop">
                                        <tr>
                                            <!-- loop.count sẽ tự đếm từ 1, 2, 3,... thay vì lấy d.id bị nhảy số -->
                                            <td class="fw-bold text-center">${loop.count}</td>
                                            <td class="fw-bold">${d.tenDuAn}</td>
                                            <td>${d.ngayBatDau}</td>
                                            <td>${d.ngayKetThuc}</td>
                                            <td>${d.moTa}</td>
                                            <td class="text-center">
                                                <!-- Nút Sửa vẫn giữ nguyên data-id="${d.id}" để gửi ID chuẩn về database -->
                                                <button type="button" 
                                                        class="btn btn-success btn-sm me-1" 
                                                        data-bs-toggle="modal" 
                                                        data-bs-target="#modalSuaDuAn"
                                                        data-id="${d.id}"
                                                        data-tenduan="${d.tenDuAn}"
                                                        data-ngaybatdau="${d.ngayBatDau}"
                                                        data-ngayketthuc="${d.ngayKetThuc}"
                                                        data-mota="${d.moTa}">
                                                    Sửa
                                                </button>
                                                <a href="du-an?action=delete&id=${d.id}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listDuAn}">
                                        <tr>
                                            <td colspan="6" class="text-center text-muted py-3">Chưa có dữ liệu dự án.</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- MODAL POPUP CẬP NHẬT DỰ ÁN -->
    <div class="modal fade" id="modalSuaDuAn" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title fw-bold text-primary">Cập Nhật Dự Án</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="du-an" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" id="edit-id">

                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label fw-medium">Tên Dự Án <span class="text-danger">*</span></label>
                            <input type="text" name="tenDuAn" id="edit-tenDuAn" class="form-control" required>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-medium">Ngày Bắt Đầu</label>
                                <input type="date" name="ngayBatDau" id="edit-ngayBatDau" class="form-control">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-medium">Ngày Kết Thúc</label>
                                <input type="date" name="ngayKetThuc" id="edit-ngayKetThuc" class="form-control">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label fw-medium">Mô Tả</label>
                            <textarea name="moTa" id="edit-moTa" class="form-control" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary px-4" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary px-4"><i class="bi bi-floppy me-1"></i>Lưu Chỉnh Sửa</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 4. Kết nối Footer -->
    <jsp:include page="footer.jsp" />

    <!-- JS Bootstrap và Script đổ dữ liệu vào Modal -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        var modalSuaDuAn = document.getElementById('modalSuaDuAn');
        if (modalSuaDuAn) {
            modalSuaDuAn.addEventListener('show.bs.modal', function (event) {
                var button = event.relatedTarget;
                
                var id = button.getAttribute('data-id');
                var tenDuAn = button.getAttribute('data-tenduan');
                var ngayBatDau = button.getAttribute('data-ngaybatdau');
                var ngayKetThuc = button.getAttribute('data-ngayketthuc');
                var moTa = button.getAttribute('data-mota');

                document.getElementById('edit-id').value = id;
                document.getElementById('edit-tenDuAn').value = tenDuAn;
                document.getElementById('edit-ngayBatDau').value = ngayBatDau;
                document.getElementById('edit-ngayKetThuc').value = ngayKetThuc;
                document.getElementById('edit-moTa').value = moTa;
            });
        }
    </script>
</body>
</html>