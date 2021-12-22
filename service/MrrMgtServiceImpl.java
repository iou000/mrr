package egovframework.admin.mrr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.framework.common.dao.CommonMybatisDao;
import egovframework.framework.common.object.DataMap;
import egovframework.framework.common.util.StringUtil;
import egovframework.framework.common.util.SysUtil;
import egovframework.framework.common.util.file.NtsysFileMngUtil;
import egovframework.framework.common.util.file.service.NtsysFileMngService;
import egovframework.framework.common.util.file.vo.NtsysFileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : MrrMgtServiceImpl.java
 * 3. Package  : egovframework.admin.mrr.service
 * 4. Comment  : 회의실 예약 관리
 * 5. 작성자   : SEOP
 * 6. 작성일   : 2021. 12. 16. 오후 4:49:50
 * </PRE>
 */
@Service("mrrMgtService")
public class MrrMgtServiceImpl extends EgovAbstractServiceImpl implements MrrMgtService {

   /** commonDao */
   @Resource(name="commonMybatisDao")
    private CommonMybatisDao commonMybatisDao;


   /**
    * <PRE>
    * 1. MethodName : selectTotCntMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 총개수 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:50:50
    * </PRE>
    *   @param param
    *   @return
    *   @throws Exception
    */
   public int selectTotCntMrrMgt(DataMap param) throws Exception {
      return (Integer) commonMybatisDao.selectOne("admin.mrr.selectTotCntMrrMgt", param);
   }


   /**
    * <PRE>
    * 1. MethodName : selectPageListMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 페이지 리스트 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:51:06
    * </PRE>
    *   @param param
    *   @return
    *   @throws Exception
    */
   public List selectPageListMrrMgt(DataMap param) throws Exception {
      return (List) commonMybatisDao.selectList("admin.mrr.selectPageListMrrMgt", param);
   }


   /**
    * <PRE>
    * 1. MethodName : selectMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 상세 조회
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:51:13
    * </PRE>
    *   @param param
    *   @return
    *   @throws Exception
    */
   public DataMap selectMrrMgt(DataMap param) throws Exception {
      return (DataMap) commonMybatisDao.selectOne("admin.mrr.selectMrrMgt", param);
   }

   /**
    * <PRE>
    * 1. MethodName : deleteMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 삭제
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:51:38
    * </PRE>
    *   @param param
    *   @throws Exception
    */
   public void deleteMrrMgt(DataMap param) throws Exception {
       commonMybatisDao.delete("admin.mrr.deleteMrrMgt", param);
   }


   /**
    * <PRE>
    * 1. MethodName : insertMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 등록
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:51:44
    * </PRE>
    *   @param param
    *   @throws Exception
    */
   public void insertMrrMgt(DataMap param) throws Exception {
      commonMybatisDao.insert("admin.mrr.insertMrrMgt", param);
   }

   /**
    * <PRE>
    * 1. MethodName : checkRsdveTmMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 시간 중복 체크
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:51:53
    * </PRE>
    *   @param param
    *   @return
    *   @throws Exception
    */
   public int checkRsdveTmMrrMgt(DataMap param) throws Exception {
      return (Integer) commonMybatisDao.selectOne("admin.mrr.checkRsdveTmMrrMgt", param);
   }

   /**
    * <PRE>
    * 1. MethodName : updateMrrMgt
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 수정
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:52:00
    * </PRE>
    *   @param param
    *   @throws Exception
    */
   public void updateMrrMgt(DataMap param) throws Exception {
      commonMybatisDao.update("admin.mrr.updateMrrMgt", param);
   }

    /**
    * <PRE>
    * 1. MethodName : selectListMrrCldr
    * 2. ClassName  : MrrMgtServiceImpl
    * 3. Comment   : 회의실 예약 조회(달력)
    * 4. 작성자    : SEOP
    * 5. 작성일    : 2021. 12. 16. 오후 4:52:14
    * </PRE>
    *   @param param
    *   @return
    *   @throws Exception
    */
   public List selectListMrrCldr(DataMap param) throws Exception {
          return (List) commonMybatisDao.selectList("admin.mrr.selectListMrrCldr", param);
       }

}