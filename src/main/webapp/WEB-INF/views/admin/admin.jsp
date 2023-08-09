<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="관리자"/>
</jsp:include>
	<div class="w-75 mx-auto">
        <button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#adminNoticeModal">
        	공지
        </button>
    </div>
    
    <!-- 관리자용 공지 modal -->
    <div class="modal fade" id="adminNoticeModal" tabindex="-1" role="dialog" aria-labelledby="#adminNoticeModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="adminNoticeModalLabel">Notice</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form name="adminNoticeFrm">
              <div class="form-group">
                <label for="to" class="col-form-label">받는이 :</label>
                <input type="text" class="form-control" id="to">
              </div>
              <div class="form-group">
                <label for="content" class="col-form-label">메세지 :</label>
                <textarea class="form-control" id="content"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="adminNoticeSendBtn">전송</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
	<script>
		document.querySelector("#adminNoticeSendBtn").onclick = (e) => {
			const payload = {
					type : "NOTICE",
					from : memberId,
					to : document.querySelector("#to").value,
					content : document.querySelector("form #content").value,
					createdAt : Date.now()
			};
			console.log(payload);
			
			const url = payload.to ? `/app/notice/\${payload.to}` : `/app/notice`;
			
			stompClient.send(url, null, JSON.stringify(payload));
			
			$("#adminNoticeModal").modal('hide');
			document.adminNoticeFrm.reset();
		};
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
