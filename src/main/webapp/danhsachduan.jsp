<%-- 
    Document   : danhsachduan
    Created on : Sep 16, 2026, 2:01:49 PM
    Author     : PC-31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Dự Án</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2>DANH SÁCH DỰ ÁN</h2>
    
    <!-- Form Thêm Dự Án -->
    <form action="du-an" method="post" class="card p-3 mb-4 bg-light">
        <h5 class="card-title">Thêm Dự Án Mới</h5>
        <div class="row">
            <div class="col-md-4 mb-2">
                <label>Tên Dự Án:</label>
                <input type="text" name="tenDuAn" class="form-control" required>
            </div>
            <div class="col-md-4 mb-2">
                <label>Ngày Bắt Đầu:</label>
                <input type="date" name="ngayBatDau" class="form-control">
            </div>
            <div class="col-md-4 mb-2">
                <label>Ngày Kết Thúc:</label>
                <input type="date" name="ngayKetThuc" class="form-control">
            </div>
            <div class="col-md-12 mb-2">
                <label>Mô Tả:</label>
                <textarea name="moTa" class="form-control" rows="2"></textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-primary w-25 mt-2">Lưu Dự Án</button>
    </form>

    <!-- Bảng Danh Sách -->
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên Dự Án</th>
                <th>Ngày Bắt Đầu</th>
                <th>Ngày Kết Thúc</th>
                <th>Mô Tả</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listDuAn}" var="d">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.tenDuAn}</td>
                    <td>${d.ngayBatDau}</td>
                    <td>${d.ngayKetThuc}</td>
                    <td>${d.moTa}</td>
                    <td>
                        <a href="du-an?action=delete&id=${d.id}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>