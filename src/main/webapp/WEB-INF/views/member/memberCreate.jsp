<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원등록" name="title"/>
</jsp:include>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/member.css" />

<div id="enroll-container" class="mx-auto text-center">
	<form:form name="memberCreateFrm" action="" method="POST">
		<table class="mx-auto w-75">
			<tr>
				<th>아이디</th>
				<td>
					<div id="memberId-container">
						<input type="text" 
							   class="form-control" 
							   placeholder="4글자이상"
							   name="memberId" 
							   id="memberId"
							   value="honggd"
							   pattern="\w{4,}"
							   required>
						<span class="guide ok">이 아이디는 사용가능합니다.</span>
						<span class="guide error">이 아이디는 이미 사용중입니다.</span>
						<input type="hidden" id="idValid" value="0"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" class="form-control" name="password" id="password" value="1234" required>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" class="form-control" id="passwordConfirmation" value="1234" required>
				</td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
					<input type="text" class="form-control" name="name" id="name" value="홍길동" required>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
					<input type="date" class="form-control" name="birthday" id="birthday" value="1999-09-09"/>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" class="form-control" placeholder="abc@xyz.com" name="email" id="email" value="honggd@naver.com">
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form:form>
</div>
<script>
document.querySelector("#memberId").onkeyup = (e) => {
	const value = e.target.value;
	console.log(value);
	
	const guideOk = document.querySelector(".guide.ok");
	const guideError = document.querySelector(".guide.error");
	const idValid = document.querySelector("#idValid");
	
	if(value.length >= 4) {
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkIdDuplicate.do",
			data : {
				memberId : value
			},
			method : "GET",
			dataType : "json",
			success(responseData) {
				console.log(responseData);
				const {available} = responseData;
				if(available) {
					guideOk.style.display = "inline";
					guideError.style.display = "none";
					idValid.value = "1";
				}
				else {
					guideOk.style.display = "none";
					guideError.style.display = "inline";
					idValid.value = "0";
				}
				
			}
		});
	}
	else {
		guideOk.style.display = "none";
		guideError.style.display = "none";
		idValid.value = "0";
	}
};

document.memberCreateFrm.onsubmit = (e) => {
	const idValid = document.querySelector("#idValid");
	const password = document.querySelector("#password");
	const passwordConfirmation = document.querySelector("#passwordConfirmation");
	
	if(idValid === "0") {
		alert("사용가능한 아이디를 작성해주세요.");
		return false;
	}
	
	
	if(password.value !== passwordConfirmation.value) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
