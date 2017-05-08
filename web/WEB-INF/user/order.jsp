<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page errorPage="/WEB-INF/error/errorPage.jsp" %>--%>
<fmt:bundle basename="labels">
<jsp:include page="../elem/head.jsp"/>
<body>
<jsp:include page="../elem/user-header.jsp"/>
<div class="container">
    <c:if test="${!empty result}">
        <div class="alert alert-warning">
            <c:out value="${result}"/>
        </div>
    </c:if>
    <div class="row masonry" data-columns>
        <c:forEach var="orders" items="${orderMap}">
            <div class="item">
                <form action="/shop/user/userDeleteOrder" method="GET" >
                    <INPUT TYPE=hidden NAME=order_id VALUE="${orders.key.orderId}">
                    <button class="close" type="submit">
                        <i class="fa fa-close"></i>
                    </button>
                </form>
                <div class="thumbnail">
                    <div class="caption">
                        <h4><b>Номер заказа: ${orders.key.orderId}</b></h4>
                        <h4><b>Дата заказа: ${orders.key.date}</b></h4>
                        <p>${orders.key.orderStatus}</p>
                    </div>

                    <c:forEach var="products" items="${orders.value}">
                    <div id="accordion" class="panel-group">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a href="#collapse-${orders.key.orderId}-${products.id}" data-parent="#accordion"
                                       data-toggle="collapse">${products.name} <br> Price: <b>${products.price} uah</b></a>
                                </h4>
                            </div>
                            <div class="panel-collapse collapse" id="collapse-${orders.key.orderId}-${products.id}">
                                <div class="panel-body">
                                    <p>${products.description}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                    <button type="button" class="btn btn-success btn-default" data-toggle="modal"
                            data-target="#client-pay-modal">
                        <i class="fa"><fmt:message key="Pay"/></i>
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../user/payment.jsp"/>
<jsp:include page="../elem/footer.jsp"/>
</body>
</html>
</fmt:bundle>