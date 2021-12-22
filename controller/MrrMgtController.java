package egovframework.admin.mrr.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.admin.author.web.AuthorMgtController;
import egovframework.admin.common.vo.UserInfoVo;
import egovframework.admin.mrr.service.MrrMgtService;
import egovframework.framework.common.constant.Const;
import egovframework.framework.common.constant.Globals;
import egovframework.framework.common.object.DataMap;
import egovframework.framework.common.page.util.pageNavigationUtil;
import egovframework.framework.common.util.EgovMessageSource;
import egovframework.framework.common.util.EgovPropertiesUtil;
import egovframework.framework.common.util.MessageUtil;
import egovframework.framework.common.util.RequestUtil;
import egovframework.framework.common.util.SessionUtil;
import egovframework.framework.common.util.TransReturnUtil;
import egovframework.framework.common.util.file.NtsysFileMngUtil;
import egovframework.framework.common.util.file.service.NtsysFileMngService;
import egovframework.framework.common.util.file.vo.NtsysFileVO;

@Controller
public class MrrMgtController {

	private static Log log = LogFactory.getLog(AuthorMgtController.class);

	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "mrrMgtService")
    private MrrMgtService mrrMgtService;

	/**
	 * <PRE>
	 * 1. MethodName : selectPageListMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 관리 목록
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:46:39
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/selectPageListMrrMgt.do", "/admin/mrr/{sys}/selectPageListMrrMgt.do" })
	public String selectPageListMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		DataMap param = RequestUtil.getDataMap(request);

		if (sys != null) {
			param.put("sys_mapping_code", sys);
			param.put("sch_sys_code", sys);
		}

		param.put("group_id", Const.UPCODE_SYS_CODE); // 시스템 코드
		param.put("sch_start_rsdve_dt_tmp", param.getString("sch_start_rsdve_dt").replaceAll("\\.", ""));
		param.put("sch_end_rsdve_dt_tmp", param.getString("sch_end_rsdve_dt").replaceAll("\\.", ""));

		/* ### Pasing 시작 ### */
		int totCnt = mrrMgtService.selectTotCntMrrMgt(param);
		param.put("totalCount", totCnt);
		param = pageNavigationUtil.createNavigationInfo(model, param);
		List resultList = mrrMgtService.selectPageListMrrMgt(param);
		/* ### Pasing 끝 ### */

        model.addAttribute("resultList", resultList);
        model.addAttribute("param", param);

		return "admin/mrr/selectPageListMrrMgt";
	}

	/**
	 * <PRE>
	 * 1. MethodName : selectMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 관리 상세 조회
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:46:25
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/selectMrrMgt.do", "/admin/mrr/{sys}/selectMrrMgt.do" })
	public String selectMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		DataMap param = RequestUtil.getDataMap(request);

		if (sys != null) {
			param.put("sys_mapping_code", sys);
		}

		DataMap resultMap = mrrMgtService.selectMrrMgt(param);

		model.addAttribute("resultMap", resultMap);
		model.addAttribute("param", param);

		return "admin/mrr/selectMrrMgt";
	}

	/**
	 * <PRE>
	 * 1. MethodName : deleteMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 관리 삭제
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:46:11
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/deleteMrrMgt.do", "/admin/mrr/{sys}/deleteMrrMgt.do" })
	public String deleteMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// 시스템 별 returnUrl 설정
		String returnUrl = "";
		if (sys != null) {
			returnUrl = "/admin/mrr/" + sys;
		} else {
			returnUrl = "/admin/mrr";
		}

		UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);
		DataMap param = RequestUtil.getDataMap(request);

		param.put("ss_user_no", userInfoVo.getUserNo());
		model.addAttribute("param", param);

		mrrMgtService.deleteMrrMgt(param);

		MessageUtil.setMessage(request, egovMessageSource.getMessage("succ.data.delete"));

		return TransReturnUtil.returnPage(Globals.METHOD_GET, returnUrl + "/selectPageListMrrMgt.do", null, model);
	}


	/**
	 * <PRE>
	 * 1. MethodName : insertFormMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 등록 폼
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:43:54
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/insertFormMrrMgt.do", "/admin/mrr/{sys}/insertFormMrrMgt.do" })
	public String insertFormMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		DataMap param = RequestUtil.getDataMap(request);

		if (sys != null) {
			param.put("sys_mapping_code", sys);
			param.put("sys_code", sys);
		}

		UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);
		// 로그인한 사용자 이름
		model.addAttribute("ss_user_nm", userInfoVo.getUserNm());

		model.addAttribute("param", param);

		return  "admin/mrr/insertFormMrrMgt";
	}

	/**
	 * <PRE>
	 * 1. MethodName : checkRsdveTmMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 시간 중복 여부 확인
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:42:10
	 * </PRE>
	 *   @return char
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/checkRsdveTmMrrMgt.do", "/admin/mrr/{sys}/checkRsdveTmMrrMgt.do" })
	public @ResponseBody char checkRsdveTmMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		DataMap param = RequestUtil.getDataMap(request);

		param.put("rsdve_dt", param.getString("rsdve_dt").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("start_t", param.getString("start_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("end_t", param.getString("end_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));

		int rsdve_cnt = mrrMgtService.checkRsdveTmMrrMgt(param);

		char existYn;
		//중복시간 없을때
		if (rsdve_cnt == 0){
			existYn = 'N';
		}
		//중복시간 있을때
		else {
			existYn = 'Y';
		}
		return existYn;
	}

	/**
	 * <PRE>
	 * 1. MethodName : insertMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   :  회의실 예약 관리 등록
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:44:29
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/insertMrrMgt.do", "/admin/mrr/{sys}/insertMrrMgt.do" })
	public String insertMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// 시스템 별 returnUrl 설정
		String returnUrl = "";
		if (sys != null) {
			returnUrl = "/admin/mrr/" + sys;
		} else {
			returnUrl = "/admin/mrr";
		}

		UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);
		DataMap param = RequestUtil.getDataMap(request);
		param.put("ss_user_no", userInfoVo.getUserNo());

		param.put("rsdve_dt", param.getString("rsdve_dt").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("start_t", param.getString("start_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("end_t", param.getString("end_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));


		mrrMgtService.insertMrrMgt(param);

		MessageUtil.setMessage(request, egovMessageSource.getMessage("succ.data.insert"));

		return TransReturnUtil.returnPage(Globals.METHOD_GET, returnUrl + "/selectPageListMrrMgt.do", null, model);
	}

	/**
	 * <PRE>
	 * 1. MethodName : updateFormMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 관리 업데이트(수정) 폼
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:44:52
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/updateFormMrrMgt.do", "/admin/mrr/{sys}/updateFormMrrMgt.do" })
	public String updateFormMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		DataMap param = RequestUtil.getDataMap(request);

		if (sys != null) {
			param.put("sys_mapping_code", sys);
		}

		UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);
		model.addAttribute("ss_user_nm", userInfoVo.getUserNm());

		DataMap resultMap = mrrMgtService.selectMrrMgt(param);

		model.addAttribute("resultMap", resultMap);
		model.addAttribute("param", param);

		return "admin/mrr/updateFormMrrMgt";

	}

	/**
	 * <PRE>
	 * 1. MethodName : updateMrrMgt
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 관리 업데이트(수정)
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:45:06
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/updateMrrMgt.do", "/admin/mrr/{sys}/updateMrrMgt.do" })
	public String updateMrrMgt(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// 시스템 별 returnUrl 설정
		String returnUrl = "";
		if (sys != null) {
			returnUrl = "/admin/mrr/" + sys;
		} else {
			returnUrl = "/admin/mrr";
		}

		UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);

		DataMap param = RequestUtil.getDataMap(request);
		param.put("ss_user_no", userInfoVo.getUserNo());

		param.put("rsdve_dt", param.getString("rsdve_dt").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("start_t", param.getString("start_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));
		param.put("end_t", param.getString("end_t").replaceAll("\\.", "").replaceAll(" ", "").replaceAll("\\:", ""));

		mrrMgtService.updateMrrMgt(param);

		MessageUtil.setMessage(request, egovMessageSource.getMessage("succ.data.update"));

		DataMap resultParam = new DataMap();
		resultParam.put("mrr_seq", param.getString("mrr_seq"));
		return TransReturnUtil.returnPage(Globals.METHOD_GET, returnUrl + "/selectMrrMgt.do", resultParam, model);
	}

	/**
	 * <PRE>
	 * 1. MethodName : mrrCldr
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 달력
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:45:22
	 * </PRE>
	 *   @return String
	 *   @param sys
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = { "/admin/mrr/mrrCldr.do", "/admin/mrr/{sys}/mrrCldr.do" })
	public String mrrCldr(@PathVariable(name = "sys", required = false) String sys, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		return "admin/mrr/mrrCldr";
	}

	/**
	 * <PRE>
	 * 1. MethodName : selectListMrrCldrAjax
	 * 2. ClassName  : MrrMgtController
	 * 3. Comment   : 회의실 예약 조회(달력)
	 * 4. 작성자    : DDU
	 * 5. 작성일    : 2021. 12. 16. 오후 4:47:45
	 * </PRE>
	 *   @return DataMap
	 *   @param request
	 *   @param response
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value="/admin/mrr/selectListMrrCldrAjax.do")
	public @ResponseBody DataMap selectListMrrCldrAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

			DataMap param = RequestUtil.getDataMap(request);

			UserInfoVo userInfoVo = SessionUtil.getSessionUserInfoVo(request);
			param.put("ss_user_id", userInfoVo.getId());

			List mrrCldr = mrrMgtService.selectListMrrCldr(param);

			DataMap resultJSON = new DataMap();
			DataMap resultMsg = new DataMap();
			resultMsg.put("mrrCldr", mrrCldr);

			//return 상태
			DataMap resultStats = new DataMap();
			resultStats.put("resultCode", "success");
			resultStats.put("resultMsg", resultMsg);
			resultJSON.put("resultStats", resultStats);

			return resultJSON;
		}
}
