<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex flex-column flex-shrink-0 p-3 bg-light border-end min-vh-100" style="width: 240px;">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="NhanVienServlet" class="nav-link text-dark">
                <i class="bi bi-people-fill me-2 text-success fs-5"></i>
                Quản lý Nhân viên
            </a>
        </li>
        <li class="mt-2">
            <a href="DuAnServlet" class="nav-link active">
                <i class="bi bi-folder-symlink-fill me-2 fs-5"></i>
                Quản lý Dự án
            </a>
        </li>
        <li class="mt-2">
            <a href="CongViecServlet" class="nav-link text-dark">
                <i class="bi bi-list-task me-2 text-info fs-5"></i>
                Quản lý Công việc
            </a>
        </li>
        <li class="mt-2">
            <a href="PhanCongServlet" class="nav-link text-dark">
                <i class="bi bi-person-plus-fill me-2 text-danger fs-5"></i>
                Phân công công việc
            </a>
        </li>
        <li class="mt-2">
            <a href="ThongKeServlet" class="nav-link text-dark">
                <i class="bi bi-bar-chart-line-fill me-2 text-secondary fs-5"></i>
                Thống kê tiến độ
            </a>
        </li>
    </ul>
</div>