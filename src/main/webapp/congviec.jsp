<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="control.CongViecServlet.CongViec"%>
<%@page import="control.CongViecServlet.DuAn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý công việc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        /* MAIN CONTENT AREA */
        .main {
            flex: 1;
            padding: 25px;
            background: #f8fafc;
        }
        .page-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #0f172a;
        }
        .form-box {
            background: white;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 25px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }
        .table {
            vertical-align: middle;
            background: white;
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

    <!-- 1. Kết nối Header -->
    <jsp:include page="header.jsp" />

    <div class="container-fluid flex-grow-1 p-0">
        <div class="row g-0 h-100">
            
            <!-- 2. Kết nối Sidebar (Đồng thời highlight mục 'duan') -->
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="congviec" />
                </jsp:include>
            </div>

    <!-- ================= MAIN ================= -->
            <div class="col-md-10 p-4">
                
                <h2 class="mb-4 text-uppercase fw-bold text-primary">
                    <i class="bi bi-list-task me-2"></i>QUẢN LÝ CÔNG VIỆC
                </h2>
        <!-- ================= THÔNG BÁO ================= -->
        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");

            if ("insert".equals(success)) {
        %>
        <div class="alert alert-success">
            <i class="fa-solid fa-circle-check"></i> Thêm công việc thành công!
        </div>
        <%
            }
            if ("update".equals(success)) {
        %>
        <div class="alert alert-success">
            <i class="fa-solid fa-circle-check"></i> Cập nhật công việc thành công!
        </div>
        <%
            }
            if ("delete".equals(success)) {
        %>
        <div class="alert alert-success">
            <i class="fa-solid fa-circle-check"></i> Xóa công việc thành công!
        </div>
        <%
            }
            if (error != null) {
        %>
        <div class="alert alert-danger">Có lỗi xảy ra!</div>
        <%
            }
        %>

        <!-- ================= FORM THÊM / SỬA ================= -->
        <%
            Object editId = request.getAttribute("editId");
            boolean isEdit = editId != null;
        %>

        <div class="form-box">
            <h5 class="mb-3">
                <% if (isEdit) { %>
                <i class="fa-solid fa-pen text-warning"></i> Sửa công việc
                <% } else { %>
                <i class="fa-solid fa-plus text-success"></i> Thêm công việc
                <% } %>
            </h5>

            <form action="CongViecServlet" method="post">
                <!-- ACTION -->
                <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">

                <!-- ID khi sửa -->
                <% if (isEdit) { %>
                <input type="hidden" name="id" value="<%= request.getAttribute("editId") %>">
                <% } %>

                <div class="row">
                    <!-- TÊN -->
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Tên công việc</label>
                        <input type="text" name="tenCongViec" class="form-control" value="<%= isEdit ? request.getAttribute("editTen") : "" %>" required>
                    </div>

                    <!-- DỰ ÁN -->
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Dự án</label>
                        <select name="duAnId" class="form-select" required>
                            <option value="">-- Chọn dự án --</option>
                            <%
                                List<DuAn> dsDuAn = (List<DuAn>) request.getAttribute("dsDuAn");
                                if (dsDuAn != null) {
                                    for (DuAn da : dsDuAn) {
                                        boolean selected = false;
                                        if (isEdit) {
                                            Object oldDuAn = request.getAttribute("editDuAnId");
                                            if (oldDuAn != null && da.getId() == (Integer) oldDuAn) {
                                                selected = true;
                                            }
                                        }
                            %>
                            <option value="<%= da.getId() %>" <%= selected ? "selected" : "" %>>
                                <%= da.getTenDuAn() %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>

                <!-- MÔ TẢ -->
                <div class="mb-3">
                    <label class="form-label">Mô tả</label>
                    <textarea name="moTa" class="form-control" rows="3"><%= isEdit && request.getAttribute("editMoTa") != null ? request.getAttribute("editMoTa") : "" %></textarea>
                </div>

                <div class="row">
                    <!-- HẠN -->
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Hạn hoàn thành</label>
                        <input type="date" name="hanHoanThanh" class="form-control" value="<%= isEdit ? request.getAttribute("editHan") : "" %>" required>
                    </div>

                    <!-- TRẠNG THÁI -->
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Trạng thái</label>
                        <select name="trangThai" class="form-select">
                            <%
                                String oldStatus = isEdit ? (String) request.getAttribute("editTrangThai") : "";
                            %>
                            <option value="Chưa bắt đầu" <%= "Chưa bắt đầu".equals(oldStatus) ? "selected" : "" %>>Chưa bắt đầu</option>
                            <option value="Đang thực hiện" <%= "Đang thực hiện".equals(oldStatus) ? "selected" : "" %>>Đang thực hiện</option>
                            <option value="Hoàn thành" <%= "Hoàn thành".equals(oldStatus) ? "selected" : "" %>>Hoàn thành</option>
                        </select>
                    </div>

                    <!-- ƯU TIÊN -->
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Mức độ ưu tiên</label>
                        <%
                            String oldPriority = isEdit ? (String) request.getAttribute("editUuTien") : "";
                        %>
                        <select name="mucDoUuTien" class="form-select">
                            <option value="Thấp" <%= "Thấp".equals(oldPriority) ? "selected" : "" %>>Thấp</option>
                            <option value="Trung bình" <%= "Trung bình".equals(oldPriority) ? "selected" : "" %>>Trung bình</option>
                            <option value="Cao" <%= "Cao".equals(oldPriority) ? "selected" : "" %>>Cao</option>
                        </select>
                    </div>
                </div>

                <!-- BUTTON -->
                <% if (isEdit) { %>
                <button type="submit" class="btn btn-warning text-white">
                    <i class="fa-solid fa-save"></i> Cập nhật
                </button>
                <a href="CongViecServlet" class="btn btn-secondary">Hủy sửa</a>
                <% } else { %>
                <button type="submit" class="btn btn-success">
                    <i class="fa-solid fa-plus"></i> Thêm công việc
                </button>
                <% } %>
            </form>
        </div>

        <!-- ================= TÌM KIẾM ================= -->
        <form action="CongViecServlet" method="get" class="d-flex mb-3" style="gap:10px;">
            <input type="text" name="keyword" class="form-control" placeholder="Tìm theo tên công việc..." value="<%= request.getParameter("keyword") == null ? "" : request.getParameter("keyword") %>">
            <button type="submit" class="btn btn-primary">
                <i class="fa-solid fa-search"></i> Tìm kiếm
            </button>
            <a href="CongViecServlet" class="btn btn-secondary">Làm mới</a>
        </form>

        <!-- ================= DANH SÁCH ================= -->
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
                <%
                    List<CongViec> dsCongViec = (List<CongViec>) request.getAttribute("dsCongViec");
                    if (dsCongViec != null && !dsCongViec.isEmpty()) {
                        for (CongViec cv : dsCongViec) {
                %>
                    <tr>
                        <td><%= cv.getId() %></td>
                        <td><strong><%= cv.getTenCongViec() %></strong></td>
                        <td><%= cv.getMoTa() == null ? "" : cv.getMoTa() %></td>
                        <td><%= cv.getHanHoanThanh() %></td>
                        <td>
                            <% if ("Hoàn thành".equals(cv.getTrangThai())) { %>
                            <span class="badge badge-done">Hoàn thành</span>
                            <% } else if ("Đang thực hiện".equals(cv.getTrangThai())) { %>
                            <span class="badge badge-doing">Đang thực hiện</span>
                            <% } else { %>
                            <span class="badge badge-not">Chưa bắt đầu</span>
                            <% } %>
                        </td>
                        <td><%= cv.getMucDoUuTien() %></td>
                        <td><%= cv.getDuAnId() %></td>
                        <td>
                            <!-- SỬA -->
                            <a href="CongViecServlet?action=edit&id=<%= cv.getId() %>" class="btn btn-warning btn-sm text-white" title="Sửa">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <!-- XÓA -->
                            <a href="CongViecServlet?action=delete&id=<%= cv.getId() %>" class="btn btn-danger btn-sm" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?');">
                                <i class="fa-solid fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="8" class="text-center text-muted">Không có công việc nào.</td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
    <jsp:include page="footer.jsp" />
</body>
</html>