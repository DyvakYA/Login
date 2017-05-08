<%--suppress XmlPathReference --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/WEB-INF/error/errorPage.jsp" %>
<jsp:include page="elem/head.jsp"/>
<body>
<jsp:include page="elem/header.jsp"/>
<div class="container">
    <c:if test="${!empty result}">
        <div class="alert alert-warning">
            <c:out value="${result}"/>
        </div>
    </c:if>
<h3>Рады приветствовать Вас в универсальном интернет-магазине </h3>
</div>
<jsp:include page="elem/footer.jsp"/>
</body>
