<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Trang chủ</title>
        <!-- Thêm Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <style>
            /* Áp dụng phông chữ Google Fonts */
            body {
                font-family: 'Roboto', sans-serif;
            }

            /* Thêm màu nền cho các phần header và footer */
            header, footer {
                background-color: #f8f9fa;
                padding: 10px 0;
            }

            /* Tạo viền cho card sản phẩm và hiệu ứng khi di chuột vào */
            .card {
                border: 1px solid #dee2e6;
                border-radius: 5px;
                transition: box-shadow 0.3s ease;
            }

            .card:hover {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* Định dạng các tiêu đề của section */
            .section-title {
                color: #333;
                font-size: 1.5rem;
                font-weight: bold;
            }

            /* Định dạng các nút Xem tất cả */
            .btn-secondary {
                background-color: #6c757d;
                color: #fff;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
            }

            /* Định dạng giá sản phẩm */
            .price {
                color: #dc3545;
                font-size: 1.25rem;
            }

            /* Định dạng badge */
            .badge {
                font-size: 0.875rem;
                padding: 4px 8px;
                border-radius: 4px;
            }

            /* Định dạng ảnh sản phẩm */
            .img-fluid {
                max-width: 100%;
                height: auto;
            }

            /* Định dạng các ô danh mục sách */
            .book-category-card {
                border: 1px solid #dee2e6;
                border-radius: 5px;
                padding: 20px;
                text-align: center;
                transition: box-shadow 0.3s ease;
            }

            .book-category-card:hover {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .book-category-title {
                font-size: 1.25rem;
                font-weight: bold;
                color: #333;
            }

            /* Đảm bảo tất cả các sản phẩm có cùng kích thước */
            .item-grid .col-xl-3, .item-grid .col-lg-4, .item-grid .col-md-6 {
                display: flex;
            }

            .item-grid .card {
                width: 100%;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .item-grid .img-wrap {
                flex-grow: 1;
            }

            .item-grid .info-wrap {
                text-align: center;
                width: 100%;
            }

            .item-grid .title {
                font-size: 1rem;
                font-weight: 500;
                color: #333;
            }

            .original-price {
                font-size: 1rem;
                font-weight: 700;
            }
            .category-title {
                color: #000; /* Đặt màu chữ thành màu đen */
            }
            /* Căn giữa banner và tăng kích thước */
            .banner-container {
                width: 67%; /* Set a specific width for the banner container, adjust as needed */
                margin: 0 auto; /* Center the banner container horizontally */
                position: relative;
                overflow: hidden;
                height: auto;
            }


            .banner-container img {
    width: 100%; /* Set the width to 100% to fill the entire width of the container */
    height: auto; /* Set a fixed height for the images, adjust as needed */
    object-fit: cover; /* Ensure the images cover the entire container while maintaining aspect ratio */
    display: block; /* Ensure the images are displayed as block elements */
}

            /* CSS cho nút "Next" và "Previous" */
            .control-btn {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
                font-size: 24px;
                color: white;
                background-color: rgba(0, 0, 0, 0.5);
                padding: 10px;
                border: none;
                outline: none;
                z-index: 1;
            }
            .next-btn {
                right: 0;
            }
            .prev-btn {
                left: 0;
            }

        </style>
    </head>

    <body>
        <jsp:include page="_header1.jsp"/>
        <jsp:include page="navbar.jsp"/>

        <section class="section-content mb-2">
            <div class="banner-container">
                <button class="control-btn next-btn">&#10095;</button>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner.jpg" alt="Banner 1">
                </a>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner_1.jpg" alt="Banner 2">
                </a>
                <a class="text-body d-flex align-items-center" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/banner_2.jpg" alt="Banner 3">
                </a>
                <button class="control-btn prev-btn">&#10094;</button>
            </div>
        </section>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var banners = document.querySelectorAll(".banner-container img"); // Lấy tất cả các ảnh trong banner-container
                var currentBannerIndex = 0; // chỉ số của banner hiện tại
                var interval = 10000; // thời gian (ms) giữa các lần chuyển banner

                // Ẩn tất cả các ảnh ngoại trừ ảnh hiện tại
                function hideAllExceptCurrent() {
                    banners.forEach(function (banner, index) {
                        if (index !== currentBannerIndex) {
                            banner.style.display = "none";
                        }
                    });
                }

                // Hiển thị banner hiện tại và ẩn các banner khác
                function showCurrentBanner() {
                    banners[currentBannerIndex].style.display = "block";
                    hideAllExceptCurrent();
                }

                // Chuyển đổi banner sang banner tiếp theo
                function nextBanner() {
                    // Ẩn banner hiện tại
                    banners[currentBannerIndex].style.display = "none";
                    // Tăng chỉ số của banner hiện tại, nếu đã đến banner cuối cùng thì quay lại banner đầu tiên
                    currentBannerIndex = (currentBannerIndex + 1) % banners.length;
                    // Hiển thị banner mới
                    showCurrentBanner();
                }

                // Chuyển đổi banner sang banner trước đó
                function prevBanner() {
                    // Ẩn banner hiện tại
                    banners[currentBannerIndex].style.display = "none";
                    // Giảm chỉ số của banner hiện tại, nếu đã đến banner đầu tiên thì quay lại banner cuối cùng
                    currentBannerIndex = (currentBannerIndex - 1 + banners.length) % banners.length;
                    // Hiển thị banner mới
                    showCurrentBanner();
                }

                // Hiển thị banner hiện tại
                showCurrentBanner();

                // Tự động chuyển đổi banner sau mỗi interval
                var bannerInterval = setInterval(nextBanner, interval);

                // Xử lý sự kiện khi người dùng click vào nút "Next"
                document.querySelector(".next-btn").addEventListener("click", function () {
                    clearInterval(bannerInterval); // Dừng tự động chuyển đổi
                    nextBanner(); // Chuyển đổi sang banner tiếp theo
                    bannerInterval = setInterval(nextBanner, interval); // Bắt đầu lại tự động chuyển đổi
                });

                // Xử lý sự kiện khi người dùng click vào nút "Previous"
                document.querySelector(".prev-btn").addEventListener("click", function () {
                    clearInterval(bannerInterval); // Dừng tự động chuyển đổi
                    prevBanner(); // Chuyển đổi sang banner trước đó
                    bannerInterval = setInterval(nextBanner, interval); // Bắt đầu lại tự động chuyển đổi
                });
            });
        </script>

        <div class="container">
            <header class="section-heading py-4 d-flex justify-content-between">
                <h3 class="section-title">Danh mục sản phẩm</h3>
                <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
            </header> <!-- section-heading.// -->
        </div> <!-- container.// -->
    </section> <!-- section-content.// -->

    <!-- Danh mục sách -->
    <div class="container"> <!-- Bọc cả phần "Danh mục sản phẩm" và "Sản phẩm mới nhất" trong container -->
        <div class="row item-grid mb-5"> <!-- Thêm lớp mb-5 để tạo khoảng cách dưới -->
            <c:forEach var="category" items="${requestScope.category}">
                <div class="col-lg-3 col-md-6">
                    <div class="card mb-4">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/category?id=${category.getCategoryID()}" class="stretched-link">
                                <div class="d-flex align-items-center">
                                    <span class="category-title ms-3">${category.getCategoryName()}</span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div> <!-- col.// -->
            </c:forEach>
        </div> <!-- row.// -->

        <section class="section-content">
            <header class="section-heading py-4 d-flex justify-content-between">
                <h3 class="section-title">Sản phẩm mới nhất</h3>
                <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
            </header> <!-- section-heading.// -->
            <div class="row item-grid">
                <c:forEach var="product" items="${requestScope.listbook}">
                    <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                        <div class="card p-3 h-100">
                            <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="img-wrap text-center">
                                <img width="200" height="200" class="img-fluid" src="${product.getCoverImage()}" alt="${product.getTitle()}">
                            </a>
                            <figcaption class="info-wrap mt-2">
                                <a href="${pageContext.request.contextPath}/detailbook?id=${product.getBookID()}" class="title">${product.getTitle()}</a>
                                <div>
                                    <span class="original-price mt-1 fw-bold">
                                        <fmt:formatNumber pattern="#,##0" value="${product.getPrice()}"/>₫
                                    </span>
                                </div>
                            </figcaption>
                        </div>
                    </div> <!-- col.// -->
                </c:forEach>
            </div> <!-- row.// -->
        </section> <!-- section-content.// -->
    </div> <!-- container.// -->

    <jsp:include page="_footer.jsp"/>
</body>


</html>
