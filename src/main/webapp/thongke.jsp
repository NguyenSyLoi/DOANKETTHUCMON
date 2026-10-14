<%-- 
    Document   : thongke
    Created on : Oct 9, 2026, 3:54:56 PM
    Author     : PC_29
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thống kê Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp" />

    <div class="container-fluid flex-grow-1 p-0">
        <div class="row g-0 h-100">
            
            <!-- 2. Kết nối Sidebar -->
            <div class="col-md-2">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="thongke" />
                </jsp:include>
            </div>
            <div class="col-md-9">
                <h3 class="mb-4">Bảng Thống Kê Công Việc</h3>

                <!-- TỔNG QUAN -->
                <div class="row mb-4">
                    <div class="col-md-4">
                        <div class="card text-white bg-success">
                            <div class="card-body">
                                <h6>Đã hoàn thành</h6>
                                <h2>${statsTongQuan.DaHoanThanh}</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-white bg-primary">
                            <div class="card-body">
                                <h6>Đang làm</h6>
                                <h2>${statsTongQuan.DangLam}</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-white bg-danger">
                            <div class="card-body">
                                <h6>Quá hạn</h6>
                                <h2>${statsTongQuan.QuaHan}</h2>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- THEO NHÂN VIÊN -->
                <h5 class="mt-4">Thống kê theo Nhân viên</h5>
                <table class="table table-bordered align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã NV</th>
                            <th>Tên Nhân Viên</th>
                            <th>Đã hoàn thành</th>
                            <th>Đang làm</th>
                            <th>Quá hạn</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="nv" items="${listTheoNhanVien}">
                            <tr>
                                <td>${nv.objectId}</td>
                                <td>${nv.objectName}</td>
                                <td class="text-success fw-bold">${nv.daHoanThanh}</td>
                                <td class="text-primary">${nv.dangLam}</td>
                                <td class="text-danger fw-bold">${nv.quaHan}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- THEO DỰ ÁN -->
                <h5 class="mt-5">Thống kê theo Dự án</h5>
                <table class="table table-bordered align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã Dự Án</th>
                            <th>Tên Dự Án</th>
                            <th>Đã hoàn thành</th>
                            <th>Đang làm</th>
                            <th>Quá hạn</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="da" items="${listTheoDuAn}">
                            <tr>
                                <td>${da.objectId}</td>
                                <td>${da.objectName}</td>
                                <td class="text-success fw-bold">${da.daHoanThanh}</td>
                                <td class="text-primary">${da.dangLam}</td>
                                <td class="text-danger fw-bold">${da.quaHan}</td>
                            </tr>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>