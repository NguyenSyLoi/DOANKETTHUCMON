<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .sidebar-wrapper {
        background-color: #1e293b;
        min-height: calc(100vh - 56px);
        width: 100%;
    }
    .sidebar-nav .nav-link {
        color: #94a3b8 !important;
        font-weight: 500;
        padding: 12px 20px;
        border-radius: 0;
        display: flex;
        align-items: center;
        gap: 12px;
        transition: all 0.2s ease;
    }
    .sidebar-nav .nav-link:hover {
        background-color: #334155 !important;
        color: #ffffff !important;
    }
    .sidebar-nav .nav-link.active {
        background-color: #0284c7 !important;
        color: #ffffff !important;
        font-weight: 600;
    }
</style>

<div class="sidebar-wrapper py-3">
    <ul class="nav nav-pills flex-column sidebar-nav mb-auto">
        <li class="nav-item">
            <a href="HOME.jsp" class="nav-link ${param.activePage == 'home' ? 'active' : ''}">
                <i class="bi bi-house-door-fill fs-5"></i>
                <span>Trang chủ</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="NhanVienServlet" class="nav-link ${param.activePage == 'nhanvien' ? 'active' : ''}">
                <i class="bi bi-people-fill fs-5"></i>
                <span>Quản lý Nhân viên</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="du-an" class="nav-link ${param.activePage == 'duan' ? 'active' : ''}">
                <i class="bi bi-folder-fill fs-5"></i>
                <span>Quản lý Dự án</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="CongViecServlet" class="nav-link ${param.activePage == 'congviec' ? 'active' : ''}">
                <i class="bi bi-list-task fs-5"></i>
                <span>Quản lý Công việc</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="PhancongServlet" class="nav-link ${param.activePage == 'phancong' ? 'active' : ''}">
                <i class="bi bi-person-plus-fill fs-5"></i>
                <span>Phân công công việc</span>
            </a>
        </li>
    </ul>
</div>
