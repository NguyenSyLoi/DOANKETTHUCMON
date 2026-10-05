<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #e4efe9 0%, #c1dfc4 50%, #b0c2de 100%);
        }

        .login-container {
            position: relative;
            width: 380px;
            background: #ffffff;
            border-radius: 20px;
            padding: 70px 35px 35px 35px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            margin: 20px;
        }

        /* Avatar Icon Circle */
        .avatar-container {
            position: absolute;
            top: -50px;
            left: 50%;
            transform: translateX(-50%);
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.6);
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 6px;
        }

        .avatar-inner {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #002244;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #ffffff;
            font-size: 42px;
        }

        /* Input Group Style */
        .input-group {
            display: flex;
            align-items: center;
            background-color: #d9d9d9;
            border-radius: 4px;
            margin-bottom: 18px;
            overflow: hidden;
        }

        .input-group .icon {
            width: 50px;
            height: 45px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #555555;
            font-size: 18px;
        }

        .input-group input {
            width: 100%;
            height: 45px;
            border: none;
            outline: none;
            background: transparent;
            font-size: 14px;
            color: #333333;
            padding-right: 15px;
        }

        .input-group input::placeholder {
            color: #888888;
        }

        /* Options: Remember me & Forgot Password */
        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 12px;
            color: #a0a0a0;
            margin-bottom: 25px;
            padding: 0 2px;
        }

        .remember-me {
            display: flex;
            align-items: center;
            gap: 6px;
            cursor: pointer;
        }

        .remember-me input[type="checkbox"] {
            accent-color: #666666;
            cursor: pointer;
        }

        .forgot-password {
            color: #a0a0a0;
            text-decoration: none;
            font-style: italic;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }

        /* Button Login */
        .btn-login {
            width: 100%;
            height: 45px;
            background-color: #002244;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            font-weight: 600;
            letter-spacing: 1.5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-login:hover {
            background-color: #00152b;
        }

        /* Dòng thông báo lỗi màu đỏ */
        .error-message {
            color: #dc3545;
            font-size: 13px;
            text-align: center;
            margin-top: 15px;
            font-weight: 500;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <!-- Avatar Icon -->
        <div class="avatar-container">
            <div class="avatar-inner">
                <i class="fa-regular fa-user"></i>
            </div>
        </div>

        <!-- Form Đăng nhập -->
        <form action="LoginServlet" method="POST">
            <!-- Username Input (Bắt đầu trống, giữ lại username cũ nếu báo lỗi) -->
            <div class="input-group">
                <div class="icon">
                    <i class="fa-solid fa-user"></i>
                </div>
                <input type="text" name="username" value="${enteredUser != null ? enteredUser : ''}" placeholder="Tài Khoản" required>
            </div>

            <!-- Password Input (Luôn trống để người dùng tự nhập) -->
            <div class="input-group">
                <div class="icon">
                    <i class="fa-solid fa-lock"></i>
                </div>
                <input type="password" name="password" value="" placeholder="Mật Khẩu" required>
            </div>
            <!-- Login Button -->
            <button type="submit" class="btn-login">Đăng Nhập</button>

            <!-- Dòng chữ màu đỏ hiển thị khi đăng nhập sai -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                    <i class="fa-solid fa-circle-exclamation me-1"></i> ${errorMessage}
                </div>
            </c:if>
            <c:if test="${param.error != null}">
                <div class="error-message">
                    <i class="fa-solid fa-circle-exclamation me-1"></i> Tài khoản và mật khẩu của bạn nhập không đúng
                </div>
            </c:if>
        </form>
    </div>

</body>
</html>