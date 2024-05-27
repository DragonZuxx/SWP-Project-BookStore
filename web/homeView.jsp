<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp"/>
        <title>Trang chủ</title>
    </head>

    <body>
        <style>/* Thêm màu nền cho các phần header và footer */
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


        </style>
        <jsp:include page="_header1.jsp"/>
        <jsp:include page="navbar.jsp"/>

        <section class="section-content mb-2">
            <div class="container">
                <header class="section-heading py-4 d-flex justify-content-between">
                    <h3 class="section-title">Danh mục sản phẩm</h3>
                    <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
                </header> <!-- section-heading.// -->

            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <section class="section-content mb-5">
            <div class="container">
                <header class="section-heading py-4 d-flex justify-content-between">
                    <h3 class="section-title">Sản phẩm mới nhất</h3>
                    <a class="btn btn-secondary" href="#" role="button" style="height: fit-content;">Xem tất cả</a>
                </header> <!-- section-heading.// -->
                <div class="row item-grid">
                    <c:forEach var="product" items="${requestScope.book}">
                        <div class="col-xl-3 col-lg-4 col-md-6">
                            <div class="card p-3 mb-4">
                                <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="img-wrap text-center">
                                    <img width="200"
                                         height="200"
                                         class="img-fluid"
                                         src="${product.coverimage}"
                                         alt="${product.title}">
                                </a>
                                <figcaption class="info-wrap mt-2">
                                    <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="title">${product.title}</a>
                                    <div>
                                        <span class="original-price mt-1 fw-bold">
                                            <fmt:formatNumber pattern="#,##0" value="${product.price}"/>₫
                                        </span>
                                    </div>
                                </figcaption>
                            </div>
                        </div> <!-- col.// -->
                    </c:forEach>
                </div> <!-- row.// -->
            </div> <!-- container.// -->
        </section> <!-- section-content.// -->

        <jsp:include page="_footer.jsp"/>
    </body>

</html>
