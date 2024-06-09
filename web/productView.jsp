<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

    <head>
        <jsp:include page="_meta.jsp" />
        <title>${requestScope.product.getTitle()}</title>

        <!-- Custom Scripts -->
        <script src="${pageContext.request.contextPath}/js/toast.js" type="module"></script>
        <script src="${pageContext.request.contextPath}/js/product.js" type="module"></script>
    </head>

    <body>
        <jsp:include page="_header.jsp" />

        <section class="section-pagetop-2 bg-light">
            <div class="container">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a
                                href="${pageContext.request.contextPath}/category?id=${requestScope.category.getCategoryID()}">${requestScope.category.getCategoryName()}</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            ${requestScope.product.getTitle()}</li>
                    </ol>
                </nav>
            </div>
        </section>

        <section class="section-content padding-y">
            <div class="container">
                <div class="row">
                    <aside class="col-md-5 mb-md-0 mb-4 d-flex justify-content-center align-items-center">
                        <c:choose>
                            <c:when test="${empty requestScope.product.getCoverImage()}">
                                <img width="280" height="280" class="img-fluid"
                                     src="${pageContext.request.contextPath}/img/280px.png" alt="280px.png">
                            </c:when>
                            <c:otherwise>
                                <img width="280" height="280" class="img-fluid"
                                     src="${requestScope.product.getCoverImage()}"
                                     alt="${requestScope.product.getCoverImage()}">
                            </c:otherwise>
                        </c:choose>
                    </aside>

                    <main class="col-md-7">
                        <h2 class="title">${requestScope.product.getTitle()}</h2>

                        <div class="rating-wrap my-3">
                            <small class="label-rating text-muted me-2">${requestScope.countReview} đánh
                                giá</small>
                            <small class="label-rating text-success">
                                <i class="bi bi-bag-check-fill"></i> ${requestScope.product.getSoldQuantity()}
                                đã mua
                            </small>
                        </div>

                        <div class="mb-4">
                            <span class="price h4">
                                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.getPrice()}" />₫
                            </span>
                            <span class="ms-2 text-muted text-decoration-line-through">
                                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.getPrice()}" />₫
                            </span>
                            <span class="ms-2 badge bg-info">
                                -
                                <fmt:formatNumber pattern="#,##0" value="${requestScope.product.getPrice()}" />%
                            </span>
                        </div>

                        <dl class="row mb-4">
                            <dt class="col-xl-4 col-sm-5 col-6">Tác giả</dt>
                            <c:forEach var="author" items="${requestScope.author}">
                                <dd class="col-xl-8 col-sm-7 col-6">${author.getName()}</dd>
                            </c:forEach>


                            <dt class="col-xl-4 col-sm-5 col-6">ISBN</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getISBN()}</dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Nhà xuất bản</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getPublisher()}</dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Năm xuất bản</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">${requestScope.product.getPublicationDate()}
                            </dd>

                            <dt class="col-xl-4 col-sm-5 col-6">Số lượng</dt>
                            <dd class="col-xl-8 col-sm-7 col-6">
                                <input type="number" id="cart-item-quantity" class="form-control w-50" value="1"
                                       min="1" max="${requestScope.product.stock}" step="1" />
                            </dd>
                        </dl>

                        <div>
                            <button type="button" class="btn btn-danger" id="add-wishlist-item"
                                    title="Thêm vào danh sách yêu thích" ${requestScope.isWishlistItem==1
                                                                           ? 'disabled' : '' }>
                                <i class="bi bi-heart"></i>
                            </button>
                            <button type="button" class="btn btn-primary ms-2" id="buy-now">Mua ngay</button>
                            <button type="button" class="btn btn-light ms-2" id="add-cart-item">Thêm vào giỏ
                                hàng</button>
                        </div>
                    </main>
                </div>
            </div>
        </section>

        <section class="section-content mb-4">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h3 class="pb-2">Mô tả sản phẩm</h3>
                        <div>${requestScope.product.getDescription()}</div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section-content mb-5">
            <div class="container">
                <c:if test="${requestScope.countReview != 0}">
                    <h3 id="review" class="pb-2">${requestScope.countReview} đánh giá</h3>

                    <c:if test="${not empty sessionScope.successMessage}">
                        <div class="alert alert-success">${sessionScope.successMessage}</div>
                    </c:if>

                    <c:if test="${not empty sessionScope.errorDeleteReviewMessage}">
                        <div class="alert alert-danger">${sessionScope.errorDeleteReviewMessage}</div>
                    </c:if>

                    <c:forEach var="review" items="${requestScope.review}">
                        <div class="border p-3 rounded mb-3">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <strong class="d-inline-block me-2">${review.getUserID()}</strong>

                                    </span>
                                </div>
                                <small class="text-muted">${review.getReviewDate()}</small>
                            </div>
                            <p class="mt-2 mb-1">${review.getComment()}</p>
                            <c:if test="${review.user.id == sessionScope.currentUser.id}">
                                <form class="d-inline-block"
                                      action="${pageContext.request.contextPath}/deleteProductReview"
                                      method="post">
                                    <input type="hidden" name="userId" value="${review.user.id}">
                                    <input type="hidden" name="productId" value="${review.product.id}">
                                    <button type="submit" class="btn btn-link btn-sm p-0">Xóa</button>
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:if>

                <h3 id="review-form" class="pb-2">Thêm đánh giá</h3>

                <c:if test="${not empty sessionScope.errorAddReviewMessage}">
                    <div class="alert alert-danger">${sessionScope.errorAddReviewMessage}</div>
                </c:if>

                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <div class="ratting-form-wrapper">
                            <div class="ratting-form">
                                <form action="${pageContext.request.contextPath}/addProductReview"
                                      method="post">
                                    <div class="row">
                                        <div class="col-md-3 mb-3">
                                            <select
                                                class="form-select ${not empty sessionScope.violations.ratingScoreViolations ? 'is-invalid' : (not empty sessionScope.values.ratingScore ? 'is-valid' : '')}"
                                                name="ratingScore">
                                                <option disabled ${not empty sessionScope.values.ratingScore
                                                                   ? '' : 'selected' }>Cho sao</option>
                                                <c:forEach var="i" begin="1" end="5">
                                                    <option value="${i}" ${sessionScope.values.ratingScore==i
                                                                     ? 'selected' : '' }>${i}</option>
                                                </c:forEach>
                                            </select>
                                            <c:if
                                                test="${not empty sessionScope.violations.ratingScoreViolations}">
                                                <div class="invalid-feedback">
                                                    <ul class="list-unstyled mb-0">
                                                        <c:forEach var="violation"
                                                                   items="${sessionScope.violations.ratingScoreViolations}">
                                                            <li>${violation}</li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col">
                                            <textarea
                                                class="form-control ${not empty sessionScope.violations.contentViolations ? 'is-invalid' : (not empty sessionScope.values.content ? 'is-valid' : '')}"
                                                name="content" placeholder="Nội dung đánh giá"
                                                rows="3">${sessionScope.values.content}</textarea>
                                            <c:if test="${not empty sessionScope.violations.contentViolations}">
                                                <div class="invalid-feedback">
                                                    <ul class="list-unstyled mb-0">
                                                        <c:forEach var="violation"
                                                                   items="${sessionScope.violations.contentViolations}">
                                                            <li>${violation}</li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <input type="hidden" name="userId" value="${sessionScope.currentUser.id}">
                                    <input type="hidden" name="productId" value="${requestScope.product.id}">
                                    <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Vui lòng <a href="${pageContext.request.contextPath}/login">đăng nhập</a> để đánh giá
                            sản phẩm.</p>
                        </c:otherwise>
                    </c:choose>
                    <c:remove var="values" scope="session" />
                    <c:remove var="violations" scope="session" />
                    <c:remove var="successMessage" scope="session" />
                    <c:remove var="errorAddReviewMessage" scope="session" />
                    <c:remove var="errorDeleteReviewMessage" scope="session" />
            </div>
        </section>



        <section class="section-content mb-5">
            <div class="container">
                <h3 class="pb-2">Sản phẩm liên quan</h3>
                <div class="related-products-container position-relative">
                    <button class="scroll-button left" onclick="scrollLeft()">&#10094;</button>
                    <div class="related-products-wrapper d-flex overflow-hidden">
                        <div class="row item-grid">

                            <c:forEach var="relatedProduct" items="${requestScope.bookgetbyid}">
                                <c:choose>
                                    <c:when test="${requestScope.product.getBookID() != relatedProduct.getBookID()}">
                                        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 related-product-item">
                                            <div class="card p-3 mb-4">
                                                <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                   class="img-wrap text-center">
                                                    <c:choose>
                                                        <c:when test="${empty relatedProduct.getCoverImage()}">
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${pageContext.request.contextPath}/img/280px.png"
                                                                 alt="280px.png">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img width="200" height="200" class="img-fluid"
                                                                 src="${relatedProduct.getCoverImage()}"
                                                                 alt="${relatedProduct.getTitle()}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                                <figcaption class="info-wrap mt-2">
                                                    <a href="${pageContext.request.contextPath}/detailbook?id=${relatedProduct.getBookID()}"
                                                       class="title">${relatedProduct.getTitle()}</a>
                                                    <div>
                                                        <span class="price mt-1 fw-bold">
                                                            <fmt:formatNumber pattern="#,##0"
                                                                              value="${relatedProduct.getPrice()}" />₫
                                                        </span>
                                                        <span class="ms-2 text-muted text-decoration-line-through">
                                                            <fmt:formatNumber pattern="#,##0"
                                                                              value="${relatedProduct.getPrice()}" />₫
                                                        </span>
                                                        <span class="ms-2 badge bg-info">
                                                            -<fmt:formatNumber pattern="#,##0"
                                                                              value="${relatedProduct.getPrice()}" />%
                                                        </span>
                                                    </div>
                                                </figcaption>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <%-- Nếu ID của sản phẩm hiển thị ở phía trên trùng với ID của sản phẩm liên quan, không làm gì --%>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <button class="scroll-button right" onclick="scrollRight()">&#10095;</button>
                </div>
            </div>
        </section>


        <!-- JavaScript đặt sau nội dung HTML -->
        <script>
            // Define scrollLeft function globally
            function scrollLeft() {
                const container = document.querySelector('.related-products-wrapper');
                const width = container.clientWidth;
                container.scrollBy({left: -width, behavior: 'smooth'});
            }

            // Define scrollRight function globally
            function scrollRight() {
                const container = document.querySelector('.related-products-wrapper');
                const width = container.clientWidth;
                container.scrollBy({left: width, behavior: 'smooth'});
            }
        </script>

        <style>
            .related-products-container {
                display: flex;
                align-items: center;
                position: relative;
            }

            .related-products-wrapper {
                overflow-x: auto;
                display: flex;
                width: 100%;
                scroll-behavior: smooth;
            }

            .related-products-wrapper .item-grid {
                display: flex;
                flex-wrap: nowrap;
            }

            .related-product-item {
                flex: 0 0 auto;
                width: 25%; /* 100% / 4 items per view */
            }

            .scroll-button {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                background-color: rgba(0, 0, 0, 0.5);
                color: white;
                border: none;
                padding: 10px;
                cursor: pointer;
                z-index: 1000;
            }

            .scroll-button.left {
                left: 0;
            }

            .scroll-button.right {
                right: 0;
            }
            .item-grid {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }

            .related-product-item {
                flex: 0 0 calc(25% - 15px); /* Chia đều 4 cột, với margin giữa các sản phẩm */
                margin-bottom: 30px;
                max-width: calc(25% - 15px); /* Đảm bảo rằng các sản phẩm không tràn ra khỏi khung */
            }

            .card {
                height: 100%;
            }



        </style>


        <jsp:include page="_footer.jsp" />
    </body>
    <div class="toast-container position-fixed bottom-0 start-0 p-3"></div>

</html>