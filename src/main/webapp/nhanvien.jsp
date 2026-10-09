<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Nhân Viên</title>
    <!-- Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
 <jsp:include page="header.jsp" />

    <div class="container-fluid flex-grow-1 p-0">
        <div class="row g-0 h-100">
            
            <!-- 2. Kết nối Sidebar -->
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="nhanvien" />
                </jsp:include>
            </div>
            <div class="col-md-10">
               <h2 class="mb-4 text-uppercase fw-bold text-primary"><i class="fa-solid fa-users"></i> Quản lý nhân viên</h2>

                <!-- Thanh Tìm kiếm và Nút Thêm -->
                <div class="card p-3 mb-4 shadow-sm">
                    <form action="nhanvien" method="get" class="row g-2 align-items-center">
                        <input type="hidden" name="action" value="search">
                        <div class="col-md-8">
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa-solid fa-magnifying-glass"></i></span>
                                <input type="text" name="keyword" class="form-control" placeholder="Nhập tên, email hoặc số điện thoại..." value="${keyword}">
                                <button type="submit" class="btn btn-primary"><i class="fa-solid fa-search"></i> Tìm kiếm</button>
                            </div>
                        </div>
                        <div class="col-md-4 text-end">
                            <button type="button" class="btn btn-success" onclick="openAddModal()">
                                <i class="fa-solid fa-plus"></i> + Thêm nhân viên
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Bảng Danh Sách Nhân Viên -->
                <div class="card shadow-sm">
                    <div class="card-body p-0">
                        <table class="table table-hover table-striped mb-0 align-middle text-center">
                            <thead class="table-light">
                                <tr>
                                    <th>STT</th>
                                    <th>ID</th>
                                    <th>Họ tên</th>
                                    <th>Chức vụ</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="nv" items="${listNhanVien}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td><strong>NV${nv.id}</strong></td>
                                        <td class="text-start">${nv.hoTen}</td>
                                        <td>${nv.chucVu}</td>
                                        <td>${nv.email}</td>
                                        <td>${nv.soDienThoai}</td>
                                        <td>
                                            <button class="btn btn-sm btn-primary me-1" 
                                                    onclick="openEditModal('${nv.id}', '${nv.hoTen}', '${nv.chucVu}', '${nv.email}', '${nv.soDienThoai}')">
                                                <i class="fa-solid fa-pen-to-square"></i> Sửa
                                            </button>
                                            <a href="nhanvien?action=delete&id=${nv.id}" 
                                               class="btn btn-sm btn-danger" 
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?')">
                                                <i class="fa-solid fa-trash"></i> Xóa
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty listNhanVien}">
                                    <tr>
                                        <td colspan="7" class="text-center py-4 text-muted">Không tìm thấy dữ liệu nhân viên!</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- POPUP MODAL (FORM THÊM / SỬA NHÂN VIÊN) -->
    <div class="modal fade" id="employeeModal" tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title fw-bold" id="modalTitle"><i class="fa-solid fa-user-plus"></i> Thêm nhân viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="employeeForm" action="nhanvien" method="post">
                    <div class="modal-body">
                        <!-- Input ẩn chứa ID khi Sửa -->
                        <input type="hidden" id="empId" name="id" value="">

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Họ tên <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="empHoTen" name="hoTen" placeholder="Nhập họ tên..." required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Chức vụ <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="empChucVu" name="chucVu" placeholder="Nhập chức vụ..." required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Email <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" id="empEmail" name="email" placeholder="Nhập địa chỉ email..." required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Số điện thoại <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="empSoDienThoai" name="soDienThoai" placeholder="Nhập số điện thoại..." required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary"><i class="fa-solid fa-floppy-disk"></i> Lưu thông tin</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Script điều khiển Popup Modal -->
    <script>
        var empModal = new bootstrap.Modal(document.getElementById('employeeModal'));

        // Hàm mở Modal Thêm mới
        function openAddModal() {
            document.getElementById('modalTitle').innerHTML = '<i class="fa-solid fa-user-plus"></i> Thêm nhân viên';
            document.getElementById('employeeForm').action = 'nhanvien?action=insert';
            document.getElementById('empId').value = '';
            document.getElementById('empHoTen').value = '';
            document.getElementById('empChucVu').value = '';
            document.getElementById('empEmail').value = '';
            document.getElementById('empSoDienThoai').value = '';
            empModal.show();
        }

        // Hàm mở Modal Chỉnh sửa & Điền dữ liệu cũ vào Form
        function openEditModal(id, hoTen, chucVu, email, soDienThoai) {
            document.getElementById('modalTitle').innerHTML = '<i class="fa-solid fa-user-pen"></i> Cập nhật thông tin nhân viên';
            document.getElementById('employeeForm').action = 'nhanvien?action=update';
            document.getElementById('empId').value = id;
            document.getElementById('empHoTen').value = hoTen;
            document.getElementById('empChucVu').value = chucVu;
            document.getElementById('empEmail').value = email;
            document.getElementById('empSoDienThoai').value = soDienThoai;
            empModal.show();
        }
    </script>
</body>
</html>