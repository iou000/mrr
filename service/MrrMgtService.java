package egovframework.admin.mrr.service;

import java.util.List;

import egovframework.framework.common.object.DataMap;

/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : MrrMgtService.java
 * 3. Package  : egovframework.admin.mrr.service
 * 4. Comment  : 회의실 예약 관리
 * 5. 작성자   : SEOP
 * 6. 작성일   : 2021. 12. 16. 오후 4:43:46
 * </PRE>
 */
public interface MrrMgtService {

   /**
    * <PRE>
    * 1. MethodName : selectTotCntMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 총개수 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:44:13
    * </PRE>
    *   @return int
    *   @param param
    *   @return
    *   @throws Exception
    */
   int selectTotCntMrrMgt(DataMap param) throws Exception;

   /**
    * <PRE>
    * 1. MethodName : selectPageListMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 페이지 리스트 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:44:29
    * </PRE>
    *   @return List
    *   @param param
    *   @return
    *   @throws Exception
    */
   List selectPageListMrrMgt(DataMap param) throws Exception;

   /**
    * <PRE>
    * 1. MethodName : selectMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 상세 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:44:49
    * </PRE>
    *   @return DataMap
    *   @param param
    *   @return
    *   @throws Exception
    */
   DataMap selectMrrMgt(DataMap param) throws Exception;

   /**
    * <PRE>
    * 1. MethodName : deleteMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 삭제
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:45:09
    * </PRE>
    *   @return void
    *   @param param
    *   @throws Exception
    */
   void deleteMrrMgt(DataMap param)throws Exception;

   /**
    * <PRE>
    * 1. MethodName : insertMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 등록
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:45:12
    * </PRE>
    *   @return void
    *   @param param
    *   @throws Exception
    */
   void insertMrrMgt(DataMap param)throws Exception;

   /**
    * <PRE>
    * 1. MethodName : checkRsdveTmMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 시간 중복 체크
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:45:39
    * </PRE>
    *   @return int
    *   @param param
    *   @return
    *   @throws Exception
    */
   int checkRsdveTmMrrMgt(DataMap param) throws Exception;

   /**
    * <PRE>
    * 1. MethodName : updateMrrMgt
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 수정
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:46:28
    * </PRE>
    *   @return void
    *   @param param
    *   @throws Exception
    */
   void updateMrrMgt(DataMap param)throws Exception;

   /**
    * <PRE>
    * 1. MethodName : selectListMrrCldr
    * 2. ClassName  : MrrMgtService
    * 3. Comment   : 회의실 예약 조회(달력)
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:46:40
    * </PRE>
    *   @return List
    *   @param param
    *   @return
    *   @throws Exception
    */
   List selectListMrrCldr(DataMap param) throws Exception;
}