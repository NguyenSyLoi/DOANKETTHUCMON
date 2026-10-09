<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Link Bootstrap Icons ken Bootstrap JS (no awan pay idiay main page) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

<header class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top shadow-sm px-3" style="background-color: #0066cc !important;">
    <!-- Logo & Tên ứng dụng -->
    <a class="navbar-brand fw-bold me-4 text-white d-flex align-items-center" href="DuAnServlet">
        <i class="bi bi-mortarboard-fill me-2 fs-5"></i>QUẢN LÝ GIÁO VỤ
    </a>

    <!-- Nút toggle cho màn hình nhỏ (Mobile) -->
    <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarContent">
        <!-- Ô tìm kiếm ở giữa -->
<form class="d-flex mx-auto my-2 my-lg-0 w-50" action="CongViecServlet" method="GET">
    <div class="input-group">
        <!-- Icon Kính lúp cố định ở đầu khung nhập -->
        <span class="input-group-text bg-white border-end-0 rounded-start-pill">
            <i class="bi bi-search text-muted"></i>
        </span>
        
        <!-- Ô nhập từ khóa -->
        <input class="form-control border-start-0 border-end-0 ps-0" 
               type="search" 
               name="keyword" 
               value="${keyword}" 
               placeholder="Tìm kiếm theo nhân viên, dự án, trạng thái..." 
               aria-label="Search">
               
        <!-- Nút Tìm kiếm (Bấm để gửi thông tin) -->
        <button class="btn btn-light bg-white border-start-0 rounded-end-pill px-3 text-primary fw-semibold" type="submit">
            Tìm
        </button>
    </div>
</form>
        <!-- Menu User Dropdown (Hồ sơ & Đăng xuất) -->
        <div class="dropdown ms-auto my-2 my-lg-0">
            <a class="nav-link dropdown-toggle text-white d-flex align-items-center fw-semibold" 
               href="#" 
               id="userDropdown" 
               role="button" 
               data-bs-toggle="dropdown" 
               aria-expanded="false">
                <i class="bi bi-person-circle fs-5 me-2"></i>
                <c:out value="${sessionScope.user}" default="Admin"/>
            </a>

            <!-- Menu xổ xuống -->
            <ul class="dropdown-menu dropdown-menu-end shadow border-0 mt-2" aria-labelledby="userDropdown">
                <li><hr class="dropdown-divider my-1"></li>
                <li>
                    <a class="dropdown-item py-2 text-danger" href="LoginServlet">
                        <i class="bi bi-box-arrow-right me-2"></i>Đăng xuất
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>