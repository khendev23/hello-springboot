<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="관리자"/>
</jsp:include>
	<div class="text-center my-5">
		관리자만 볼수 있는 페이지입니다.
	</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
