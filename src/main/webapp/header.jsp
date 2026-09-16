<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow-sm px-3">
    <!-- Logo & Tên ứng dụng -->
    <a class="navbar-brand fw-bold me-4" href="BangDieuKhienServlet">
        <i class="bi bi-kanban-fill text-primary me-2"></i>QUẢN LÝ CÔNG VIỆC
    </a>

    <!-- Nút toggle cho màn hình nhỏ (Mobile) -->
    <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarContent">
        <!-- Ô tìm kiếm ở giữa -->
        <form class="d-flex mx-auto my-2 my-lg-0 w-50" action="CongViecServlet" method="GET">
            <div class="input-group">
                <span class="input-group-text bg-white border-end-0 rounded-start-pill">
                    <i class="bi bi-search text-muted"></i>
                </span>
                <input class="form-control border-start-0 rounded-end-pill ps-0" type="search" name="keyword" placeholder="Tìm kiếm công việc theo tên, dự án..." aria-label="Search">
            </div>
        </form>

        <!-- Thông tin người dùng góc phải -->
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-center">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white d-flex align-items-center" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-person-circle fs-5 me-2 text-primary"></i>
                    <span class="fw-semibold">Quản trị viên</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end shadow border-0 mt-2" aria-labelledby="userDropdown">
                    <li><a class="dropdown-item py-2" href="#"><i class="bi bi-person me-2 text-secondary"></i>Hồ sơ cá nhân</a></li>
                    <li><a class="dropdown-item py-2" href="#"><i class="bi bi-gear me-2 text-secondary"></i>Cài đặt</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item py-2 text-danger" href="LogoutServlet"><i class="bi bi-box-arrow-right me-2"></i>Đăng xuất</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>