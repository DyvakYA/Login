<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/WEB-INF/error/errorPage.jsp" %>
<jsp:include page="../elem/head.jsp"/>
<body>
<jsp:include page="../elem/admin-header.jsp"/>
<fmt:bundle basename="labels">
    <div class="container">
        <c:if test="${!empty result}">
            <div class="alert alert-warning">
                <c:out value="${result}"/>
            </div>
        </c:if>
        <div class="row masonry" data-columns>
            <c:forEach var="orders" items="${orderMap}">
                <div class="item">
                    <form action="/shop/admin/adminDeleteOrder" method="GET">
                        <INPUT TYPE=hidden NAME=order_id VALUE="${orders.key.orderId}">
                        <button class="close" type="submit">
                            <i class="fa fa-close"></i>
                        </button>
                    </form>
                    <div class="thumbnail">
                        <div class="caption">
                            <h4><b>Номер заказа: ${orders.key.orderId}</b></h4>
                            <h4><b>Дата заказа: ${orders.key.date}</b></h4>
                            <form action="/shop/admin/adminUpdateOrder" method="GET">
                                <div class="form-group">
                                    <input type="text" name="order_status" size="auto" class="form-control"
                                           value="${orders.key.orderStatus}">
                                    <INPUT TYPE=hidden NAME=order_id VALUE="${orders.key.orderId}">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success btn-default">
                                        <i class="fa"><fmt:message key="ChangeStatus"/></i>
                                    </button>
                                </div>
                            </form>
                            <c:forEach var="products" items="${orders.value}">
                                <div id="accordion" class="panel-group">
                                    <div class="panel panel-success">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <form action="/shop/admin/adminDeleteProduct" method="GET">
                                                    <INPUT TYPE=hidden NAME=order_id VALUE="${orders.key.orderId}">
                                                    <INPUT TYPE=hidden NAME=product_id VALUE="${products.id}">
                                                    <button class="close" type="submit">
                                                        <i class="fa fa-close"></i>
                                                    </button>
                                                </form>
                                                <a href="#collapse-${orders.key.orderId}-${products.id}-${orders.key.date}"
                                                   data-parent="#accordion"
                                                   data-toggle="collapse">${products.name}<br> Price: <b>${products.price} uah</b></a>
                                            </h4>
                                        </div>
                                        <div class="panel-collapse collapse"
                                             id="collapse-${orders.key.orderId}-${products.id}-${orders.key.date}">
                                            <div class="panel-body">
                                                <p>${products.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <jsp:include page="../elem/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>