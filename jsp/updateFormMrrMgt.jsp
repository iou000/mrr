<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="CommboUtil" uri="/WEB-INF/tlds/CommboUtil.tld"%>
<%@ taglib prefix="CacheCommboUtil"	uri="/WEB-INF/tlds/CacheCommboUtil.tld"%>

<%@ include file="/WEB-INF/jsp/egovframework/mordern/config/common.jsp" %>
<script type="text/javascript" src="/common/ckeditor/ckeditor.js" charset="UTF-8" ></script>
<script type="text/javascript" src="/common/ckeditor/adapters/jquery.js" charset="UTF-8" ></script>

<script type="text/javascript">
	$(function(){
		$('[name=rsdve_dt]').datetimepicker({
			locale : 'ko', 	// 화면에 출력될 언어를 한국어로 설정
			format : 'YYYY.MM.DD',
			useCurrent: false, 	//Important! See issue #1075
			sideBySide : true,
			widgetPositioning : {
				horizontal : 'left',
				vertical : 'bottom'
		},
	});

	$('[name=start_t]').datetimepicker({
			locale : 'ko', 	// 화면에 출력될 언어를 한국어로 설정
			format : 'HH:mm',
			useCurrent: false, 	//Important! See issue #1075
			sideBySide : true,
			widgetPositioning : {
				horizontal : 'right',
				vertical : 'bottom'
		},
	}).on('dp.change',function(e){  //시작 시간 변경시
		 $("[name=exist_yn]").val('');
	});
		$('[name=end_t]').datetimepicker({
			locale : 'ko', 	// 화면에 출력될 언어를 한국어로 설정
			format : 'HH:mm',
			useCurrent: false, 	//Important! See issue #1075
			sideBySide : true,
			widgetPositioning : {
				horizontal : 'right',
				vertical : 'bottom'
		},
	}).on('dp.change',function(e){  //종료 시간 변경시
		 $("[name=exist_yn]").val('');
	});
});

	// 예약시간 중복 확인 버튼
	   function fnCheck(){
		   if($("[name=rsdve_dt]").val() == ""){
		         alert("예약일을 입력해 주세요.");
		         $("[name=rsdve_dt]").focus();
		         return;
		      }
	      if($("[name=start_t]").val() == "") {
	            alert("예약 시작시간을 입력해 주세요.");
	            $("[name=start_t").focus();
	            return;
	         }
	         if($("[name=end_t]").val() == ""){
	            alert("예약 종료시간을 입력해 주세요.");
	            $("[name=end_t]").focus();
	            return;
	         }
	     	if($("[name=start_t]").val() >= $("[name=end_t]").val() ){
				alert("예약시간을 확인해주세요.");
				$("[name=start_t]").focus();
				return;
			}

	      var checkData = {
	            rsdve_dt : $("[name=rsdve_dt]").val(),
	            start_t : $("[name=start_t]").val(),
	            end_t : $("[name=end_t]").val()

	      }

	      $.ajax({
	         url : "/admin/mrr/checkRsdveTmMrrMgt.do",
	         data : checkData,
	         type : 'POST',
	         dataType : 'json',
	         success : function(param) {
	            if (param == 'Y') {
	               $("[name=exist_yn]").val(param);
	               alert("이미 예약된 시간입니다. 다른 시간을 선택해주세요.");
	               return;
	            }
	            else if(param == 'N'){
	               $("[name=exist_yn]").val(param);
	               alert("예약 가능한 시간입니다.");
	               return;
	            }
	         }
	      });
	   }


	function fnDetail(){
		$("#aform").attr({action:"/admin/mrr/"+fnSysMappingCode()+"selectMrrMgt.do", method:'post'}).submit();
	}

	function fnUpdate(){
		if($("[name=sj]").val() == ""){
			alert("제목을 입력해 주세요.");
			$("[name=sj]").focus();
			return;
		}
		if($("[name=rsdve_dt]").val() == ""){
			alert("예약일을 입력해 주세요.");
			$("[name=rsdve_dt]").focus();
			return;
		}
		if($("[name=start_t]").val() == ""){
			alert("예약 시작시간을 입력해 주세요.");
			$("[name=start_t").focus();
			return;
		}
		if($("[name=end_t]").val() == ""){
			alert("예약 종료시간을 입력해 주세요.");
			$("[name=end_t]").focus();
			return;
		}
		if($("[name=dept]").val() == ""){
			alert("등록자 부서를 입력해 주세요.");
			$("[name=dept]").focus();
			return;
		}
		if($("[name=rgst_no]").val() == ""){
			alert("등록자를 입력해 주세요.");
			$("[name=rgst_no]").focus();
			return;
		}
		if($("[name=start_t]").val() >= $("[name=end_t]").val() ){
			alert("예약시간을 확인해주세요.");
			$("[name=start_t]").focus();
			return;
		}

	      if($("[name=exist_yn]").val() == ""){
	          alert("예약시간 중복확인을 해주세요.");
	          return;
	       }

		if(confirm("수정하시겠습니까?")){
			if($("[name=exist_yn]").val() == 'Y'){
	              alert("예약시간 중복확인을 해주세요.");
	              return;
	           }
			$("#aform").attr({action:"/admin/mrr/"+fnSysMappingCode()+"updateMrrMgt.do", method:'post'}).submit();
		}
	}
//]]>
</script>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<form role="form" id="aform" method="post" enctype="multipart/form-data">
				<input type="hidden" name="mrr_seq" value="${requestScope.param.mrr_seq}">
				<input type="hidden" name="exist_yn" />
					<div class="card card-info card-outline">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table text-nowrap inlineBlock">
									<colgroup>
										<col style="width:15%;">
										<col style="width:35%;">
										<col style="width:15%;">
										<col style="width:35%;">
									</colgroup>
									<tbody>
										<tr>
											<th class="required_field">회의 제목</th>
											<td colspan="3">
												<input type="text" class="form-control" name="sj" value = "${resultMap.SJ}" maxlength="100">
											</td>
										</tr>
										<tr>
											<th class="required_field">등록자 부서</th>
											<td>
												<input type="text" class="form-control" name="dept" value = "${resultMap.DEPT}" maxlength="100">
											</td>
											<th class="required_field">등록자</th>
											<td>
												<input type="text" class="form-control" name="rgst_no" value = "${resultMap.RGST_NO}" maxlength="100">
											</td>

										</tr>
										<tr>
											<th class="required_field">예약일</th>
											<td class="datetimepicker-td">
												<div>
													<input type="text" class="form-control" name="rsdve_dt" value = "${resultMap.RSDVE_DT}">
												</div>
											</td>
											<th class="required_field">예약 시간</th>
											<td class="datetimepicker-td">
                                    			<div class="d-flex">
                                       			<input type="text" class="form-control" name="start_t" value = "${resultMap.START_T}" autocomplete='off'>
                                      			<div class="text-center"> ~ </div>
                                       			<input type="text" class="form-control" name="end_t" value = "${resultMap.END_T}" autocomplete='off'>
                                       			<button type="button" class="btn btn-info btn-xs ml-2" onclick="fnCheck(); return false;">중복확인</button>
                                   				</div>
                                 			</td>
										</tr>
										<tr>
											<th>참여인원</th>
											<td colspan="3">
												<input type="text" class="form-control" name="partcptn_cnt" maxlength="100" value = "${resultMap.PARTCPTN_CNT}">
											</td>
										</tr>
										<tr>
											<th>회의 내용</th>
											<td colspan="3">
                                    		<textarea class="form-control" rows="10" id="cn" name="cn">${resultMap.CN}</textarea>
                                 			</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="card-footer">
							<div class="form-row justify-content-end">
								<div class="form-inline">
									<button type="button" class="btn bg-gradient-secondary mr-2" onclick="fnDetail(); return false;">목록</button>
									<button type="button" class="btn bg-gradient-success" onclick="fnUpdate(); return false;">등록</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

