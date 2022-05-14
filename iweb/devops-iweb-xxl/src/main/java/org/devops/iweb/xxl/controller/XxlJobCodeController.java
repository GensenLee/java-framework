package org.devops.iweb.xxl.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.devops.iweb.xxl.model.XxlJobInfo;
import org.devops.iweb.xxl.model.XxlJobLogglue;
import org.devops.iweb.xxl.repository.XxlJobInfoRepository;
import org.devops.iweb.xxl.repository.XxlJobLogglueRepository;
import org.devops.iweb.xxl.util.I18nUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.glue.GlueTypeEnum;

/**
 * job code controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/xxl-job-admin/jobcode")
public class XxlJobCodeController {
	
	@Resource
	private XxlJobInfoRepository xxlJobInfoRepository;
	@Resource
	private XxlJobLogglueRepository xxlJobLogglueRepository;

	@RequestMapping
	public String index(HttpServletRequest request, Model model, int jobId) {
		XxlJobInfo jobInfo = xxlJobInfoRepository.get(jobId);
		List<XxlJobLogglue> jobLogGlues = xxlJobLogglueRepository.findByJobId(jobId);

		if (jobInfo == null) {
			throw new RuntimeException(I18nUtil.getString("jobinfo_glue_jobid_unvalid"));
		}
		if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType())) {
			throw new RuntimeException(I18nUtil.getString("jobinfo_glue_gluetype_unvalid"));
		}

		// valid permission
		XxlJobInfoController.validPermission(request, jobInfo.getJobGroup());

		// Glue类型-字典
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());

		model.addAttribute("jobInfo", jobInfo);
		model.addAttribute("jobLogGlues", jobLogGlues);
		return "jobcode/jobcode.index";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ReturnT<String> save(Model model, int id, String glueSource, String glueRemark) {
		// valid
		if (glueRemark==null) {
			return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_glue_remark")) );
		}
		if (glueRemark.length()<4 || glueRemark.length()>100) {
			return new ReturnT<String>(500, I18nUtil.getString("jobinfo_glue_remark_limit"));
		}
		XxlJobInfo exists_jobInfo = xxlJobInfoRepository.get(id);
		if (exists_jobInfo == null) {
			return new ReturnT<String>(500, I18nUtil.getString("jobinfo_glue_jobid_unvalid"));
		}
		
		// update new code
		exists_jobInfo.setGlueSource(glueSource);
		exists_jobInfo.setGlueRemark(glueRemark);
		exists_jobInfo.setGlueUpdatetime(new Date());
		xxlJobInfoRepository.update(exists_jobInfo);

		// log old code
		XxlJobLogglue xxlJobLogglue = new XxlJobLogglue();
		xxlJobLogglue.setJobId(exists_jobInfo.getId());
		xxlJobLogglue.setGlueType(exists_jobInfo.getGlueType());
		xxlJobLogglue.setGlueSource(glueSource);
		xxlJobLogglue.setGlueRemark(glueRemark);
		xxlJobLogglueRepository.save(xxlJobLogglue);

		// remove code backup more than 30
		xxlJobLogglueRepository.removeOld(exists_jobInfo.getId(), 30);

		return ReturnT.SUCCESS;
	}
	
}
