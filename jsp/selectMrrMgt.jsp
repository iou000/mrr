<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="Util" uri="/WEB-INF/tlds/Util.tld"%>

<%@ include file="/WEB-INF/jsp/egovframework/mordern/config/common.jsp" %>
<script type="text/javascript">
//<![CDATA[

   function fnList(){
      $("#aform").attr({action:"/admin/mrr/"+fnSysMappingCode()+"selectPageListMrrMgt.do", method:'post'}).submit();
   }

   function fnDelete(qna_seq){
      if(confirm("삭제하시겠습니까?")){
         $("#aform").attr({action:"/admin/mrr/"+fnSysMappingCode()+"deleteMrrMgt.do", method:'post'}).submit();
      }
   }

   function fnUpdateForm(){
      $("#aform").attr({action:"/admin/mrr/"+fnSysMappingCode()+"updateFormMrrMgt.do", method:'post'}).submit();
   }
//]]>
</script>
<div class="content">
   <div class="container-fluid">
      <div class="row">
         <div class="col-lg-12">
            <form role="form" id="aform" method="post" action="/admin/mrr/updateFormMrrMgt.do" enctype="multipart/form-data">
               <input type="hidden" name="mrr_seq" value="${requestScope.param.mrr_seq}"/>
               <input type="hidden" name="sch_start_rsdve_dt" value="${requestScope.param.sch_start_rsdve_dt}" />
               <input type="hidden" name="sch_end_rsdve_dt" value="${requestScope.param.sch_end_rsdve_dt}" />
               <input type="hidden" name="sch_text" value="${requestScope.param.sch_text}" />
               <input type="hidden" name="currentPage" value="${requestScope.param.currentPage}"/>
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
                                 <th>회의 제목</th>
                                 <td colspan="3">
                                    ${resultMap.SJ}
                                 </td>
                              </tr>
                              <tr>
                                 <th>등록자 부서</th>
                                 <td>
                                    ${resultMap.DEPT}
                                 </td>
                                 <th>등록자</th>
                                 <td>
                                    ${resultMap.RGST_NO}
                                 </td>
                              </tr>
                              <tr>
                                 <th>예약일</th>
                                 <td>
                                    ${resultMap.RSDVE_DT}
                                 </td>
                                 <th>예약 시간</th>
                                 <td>
                                    ${resultMap.START_T} ~ ${resultMap.END_T}
                                 </td>
                              </tr>
                              <tr>
                                 <th>참여 인원</th>
                                 <td>
                                    ${resultMap.PARTCPTN_CNT}
                                 </td>
                                 <th></th>
                                 <td>
                                 </td>
                              </tr>
                              <tr>
                                 <th>회의 내용</th>
                                 <td colspan="3">
                                    ${resultMap.CN}
                                 </td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <div class="card-footer">
                     <div class="form-row justify-content-end">
                        <div class="form-inline">
                           <button type="button" class="btn bg-gradient-secondary mr-2" onclick="fnList(); return false;">목록</button>
                           <button type="button" class="btn bg-gradient-success mr-2" onclick="fnUpdateForm(); return false;">수정</button>
                           <button type="button" class="btn bg-gradient-danger" onclick="fnDelete(); return false;">삭제</button>
                        </div>
                     </div>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>