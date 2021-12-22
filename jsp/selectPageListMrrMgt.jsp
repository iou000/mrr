<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="CommboUtil" uri="/WEB-INF/tlds/CommboUtil.tld"%>
<%@ taglib prefix="CacheCommboUtil" uri="/WEB-INF/tlds/CacheCommboUtil.tld"%>

<%@ include file="/WEB-INF/jsp/egovframework/mordern/config/common.jsp"%>
<script type="text/javascript">
   //<![CDATA[
   $(function() {
      //활동을 기본으로 검색
      $('[name=sch_user_se_code], [name=sch_user_sttus_code]').change(function(e) {
         fnSearch();
      });

      // 목록 수 셀렉트 박스 변경시 조회
      $("#sch_row_per_page").change(function() {
         fnSearch();
      });
   });

   $(function() {
      $('[name=sch_start_rsdve_dt]').datetimepicker({
         locale : 'ko', // 화면에 출력될 언어를 한국어로 설정한다.
         format : 'YYYY.MM.DD',
         useCurrent : false, //Important! See issue #1075
         sideBySide : true,
         widgetPositioning : {
            horizontal : 'left',
            vertical : 'bottom'
         },
      });

      $('[name=sch_end_rsdve_dt]').datetimepicker({
         locale : 'ko', // 화면에 출력될 언어를 한국어로 설정한다.
         format : 'YYYY.MM.DD',
         useCurrent : false, //Important! See issue #1075
         sideBySide : true,
         widgetPositioning : {
            horizontal : 'right',
            vertical : 'bottom'
         },
      });

      // 목록 수 셀렉트 박스 변경시 조회
      $("#sch_row_per_page").change(function() {
         fnSearch();
      });

      // 검색 이벤트
      $('[name=sch_text]').on({
         'keyup' : function(e) {
            if (e.which == 13) {
               fnSearch();
            }
         },
         'keydown' : function(e) {
            if (e.which == 13) {
               e.preventDefault();
            }
         },
      });

      // 기관, 팝업구분 변경시
      $('[name=sch_sys_code], [name=sch_mrr_se_code]').change(function(e) {
         fnSearch();
      });

      if ("${sysMappingCode}" == "") {
         $('[name=sch_sys_code]').html("${CacheCommboUtil:getComboStr(UPCODE_SYS_CODE, 'CODE', 'CODE_NM', requestScope.param.sch_sys_code, '시스템')}");
      } else {
         $('[name=sch_sys_code]').html("${CacheCommboUtil:getEqulesComboStr(UPCODE_SYS_CODE, requestScope.param.sys_mapping_code, 'CODE', 'CODE_NM', requestScope.param.sch_sys_code, '')}");
      }
   });

   //페이지이동
   function fnGoPage(currentPage) {
      $("#currentPage").val(currentPage);
      $("#aform").attr({
         action : "/admin/mrr/"+fnSysMappingCode()+"selectPageListMrrMgt.do",
         method : 'post'
      }).submit();
   }

   //팝업상세
   function fnDetail(mrrSeq) {
      $("[name=mrr_seq]").val(mrrSeq);
      $("#aform").attr({
         action : "/admin/mrr/"+fnSysMappingCode()+"selectMrrMgt.do",
         method : 'post'
      }).submit();
   }

   //팝업검색
   function fnSearch() {
      $("#currentPage").val("1");
      $("#aform").attr({
         action : "/admin/mrr/"+fnSysMappingCode()+"selectPageListMrrMgt.do",
         method : 'post'
      }).submit();
   }

   //팝업등록
   function fnInsertForm() {
      document.location.href = "/admin/mrr/"+fnSysMappingCode()+"insertFormMrrMgt.do";
   }
   //]]>
</script>
<div class="content">
   <div class="container-fluid">
      <div class="row">
         <div class="col-lg-12">
            <form role="form" id="aform" method="post" action="/admin/mrr/selectPageListMrrMgt.do">
               <input type="hidden" name="mrr_seq" />

               <div class="card card-info card-outline">
                  <div class="card-header">
                     <div class="form-row justify-content-between">
                        <div class="form-inline">
                           <div class="form-group input-group-sm mb-1">
                              <select id="sch_row_per_page" name="sch_row_per_page"class="form-control input-sm">
                                 ${CacheCommboUtil:getComboStr(UPCODE_ROW_PER_PAGE, 'CODE', 'CODE_NM', requestScope.param.sch_row_per_page, '')}
                              </select>
                           </div>
                        </div>
                        <div class="form-inline">
                           <div class="input-group input-group-sm mr-1 mb-1">
                              <input type="text" class="form-control form-control-sm" name="sch_start_rsdve_dt" value="${requestScope.param.sch_start_rsdve_dt}" placeholder="예약 시작일" autocomplete='off'/>
                              <div class="input-group-prepend center">
                                 <div class="input-group-text">~</div>
                              </div>
                              <input type="text" class="form-control form-control-sm" name="sch_end_rsdve_dt" value="${requestScope.param.sch_end_rsdve_dt}" placeholder="예약 종료일" autocomplete='off'/>
                           </div>
                           <div class="input-group input-group-sm mr-1 mb-1">
                              <input type="text" class="form-control form-control-sm" name="sch_text" title="검색어를 입력하세요." placeholder="검색어를 입력하세요." value="${requestScope.param.sch_text}" />
                              <div class="input-group-append">
                                 <button type="button" class="btn btn-default btn-outline-dark" onclick="fnSearch(); return false;">
                                    <i class="fa fa-search"></i>
                                 </button>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="card-body">
                     <div class="table-responsive">
                        <table class="table table-hover text-nowrap table-grid">
                           <colgroup>
                              <col width="60px" />
                              <col width="*" />
                              <col width="15%" />
                              <col width="12%" />
                              <col width="5%" />
                              <col width="12%" />
                              <col width="10%" />
                              <col width="8%" />
                           </colgroup>
                           <thead>
                              <tr>
                                 <th class="text-center">No</th>
                                 <th class="text-center">제목</th>
                                 <th class="text-center">예약일</th>
                                 <th class="text-center">예약시간</th>
                                 <th class="text-center">상태</th>
                                 <th class="text-center">등록자</th>
                                 <th class="text-center">부서</th>
                                 <th class="text-center">등록일</th>
                              </tr>
                           </thead>
                           <tbody>
                              <c:forEach var="item" items="${resultList}" varStatus="status">
                                 <tr style="cursor: pointer; cursor: hand;" onclick="fnDetail('${item.MRR_SEQ}')">
                                    <td class="text-center">${pageNavigationVo.totalCount - (pageNavigationVo.currentPage-1) * pageNavigationVo.rowPerPage - status.index}</td>
                                    <td class="title text-center">${item.SJ}</td>
                                    <td class="title text-center">${item.RSDVE_DT}</td>
                                    <td class="text-center">${item.START_T} ~ ${item.END_T}</td>
                                    <td class="text-center">${item.STTUS}</td>
                                    <td class="text-center">${item.RGST_NO}</td>
                                    <td class="text-center">${item.DEPT}</td>
                                    <td class="text-center">${item.RGST_DT}</td>
                                 </tr>
                              </c:forEach>
                              <c:if test="${fn:length(resultList) == 0}">
                                 <tr>
                                    <td class="text-center" colspan="9"><spring:message   code="msg.data.empty" /></td>
                                 </tr>
                              </c:if>
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <div class="page_box">${navigationBar}</div>
                  <div class="card-footer">
                     <div class="form-row justify-content-end">
                        <div class="form-inline">
                           <button type="button" class="btn bg-gradient-success" onclick="fnInsertForm(); return false;">등록</button>
                        </div>
                     </div>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>