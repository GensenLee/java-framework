package org.devops.iweb.auth.service;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.CookieUtil;
import org.devops.core.utils.util.IPUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.constant.AuthCookieConstant;
import org.devops.iweb.auth.helper.IWebAuthRsaHelper;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthCompany;
import org.devops.iweb.auth.model.AuthLoginLog;
import org.devops.iweb.auth.model.AuthRole;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.redis.IWebAuthRedisDao;
import org.devops.iweb.auth.redis.IWebAuthVertifyCodeRedisDao;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthCompanyRepository;
import org.devops.iweb.auth.repository.AuthLoginLogRepository;
import org.devops.iweb.auth.repository.AuthRoleRepository;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.iweb.auth.util.PasswdUtil;
import org.devops.iweb.auth.vo.inVO.AuthLoginInVO;
import org.devops.iweb.auth.vo.inVO.AuthUserInstanceVO;
import org.devops.iweb.auth.vo.inVO.AuthUserRepasswdInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.outVO.AuthUserListOutVO;
import org.devops.iweb.auth.vo.searchVO.AuthUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthAppUserInstanceRepository authAppUserInstanceRepository;

    @Autowired
    private AuthAppRepository authAppRepository;

    @Autowired
    private AuthAppInstanceRepository authAppInstanceRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthCompanyRepository authCompanyRepository;

    @Autowired
    private IWebAuthRedisDao iWebAuthRedisDao;

    @Autowired
    private AuthLoginLogRepository authLoginLogRepository;

    @Autowired
    private IWebAuthVertifyCodeRedisDao iWebAuthVertifyCodeRedisDao;

    @Autowired
    private IWebAuthRsaHelper iWebAuthRsaHelper;

    @Value("${iweb.auth.verifyCode: false}")
    private boolean bVerifyCode;


    private static String targetIp;

    static {
        targetIp = IPUtil.getLocalIP();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object list(AuthUserSearchVO svo, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        ModelWhere mw = new ModelWhere();

        if (StringUtil.isNotEmpty(svo.getQuickSearch())) {
            mw.add(AuthUser.NAME, svo.getQuickSearch(), ModelOperator.LIKE);
        }

        if (LongUtil.isZero(svo.getCompanyId())) {
            mw.add(AuthUser.COMPANY_ID, userInfoInnerVO.getCompanyId());
        } else if (LongUtil.toLong(svo.getCompanyId()) != AuthConstant.Common.ALL) {
            mw.add(AuthUser.COMPANY_ID, svo.getCompanyId());
        }

        List<AuthUser> liAuthUser = authUserRepository.where(mw)
                .orderBy(AuthUser.ID, "DESC")
                .limit(svo.getStart(), svo.getSize()).list();

        List<Map<String, Object>> listMap = MapUtil.toMapListResultExcludeFields(liAuthUser, AuthUser.PASSWD);
        PageResult pr = PageResult.get();
        pr.setList(listMap);
        pr.setCount(authUserRepository.where(mw).count());

        return pr;
    }


    /**
     * 用户列表，包括角色信息
     * @param svo
     * @param userInfoInnerVO
     * @return
     * @throws CommonException
     */
    public PageResult<AuthUserListOutVO> listV2(AuthUserSearchVO svo, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        ModelWhere mw = new ModelWhere();

        if (StringUtil.isNotEmpty(svo.getQuickSearch())) {
            mw.add(AuthUser.NAME, svo.getQuickSearch(), ModelOperator.LIKE);
        }
        Long searchCompanyId = null;
        if (LongUtil.isZero(svo.getCompanyId())) {
            searchCompanyId = userInfoInnerVO.getCompanyId();
        } else if (LongUtil.toLong(svo.getCompanyId()) != AuthConstant.Common.ALL) {
            searchCompanyId = svo.getCompanyId();
        }
        mw.add(AuthUser.COMPANY_ID, searchCompanyId);

        List<AuthUserListOutVO> liAuthUser = authUserRepository.where(mw)
                .exclude(AuthUser.PASSWD)
                .orderBy(AuthUser.ID, "DESC")
                .limit(svo.getStart(), svo.getSize())
                .list(AuthUserListOutVO.class);

        acceptUserInstances(liAuthUser, searchCompanyId);
        PageResult<AuthUserListOutVO> pr = PageResult.get();
        pr.setList(liAuthUser);
        pr.setCount(authUserRepository.where(mw).count());
        return pr;
    }

    /**
     * @param list
     */
    private void acceptUserInstances(List<AuthUserListOutVO> list, Long companyId){
        if (ListUtil.isNull(list)) {
            return;
        }
        List<Long> userIdList = ListUtil.toLongList(list, AuthUser.ID);
        List<AuthUserInstanceVO> userInstanceList = authAppUserInstanceRepository
                .exclude(AuthConstant.Common.EXCLUDE_FIELDS)
                .where(AuthAppUserInstance.COMPANY_ID, companyId)
                .where(AuthAppUserInstance.USER_ID, userIdList, ModelOperator.IN)
                .list(AuthUserInstanceVO.class);
        if (ListUtil.isNull(userInstanceList)) {
            return;
        }
        userInstanceList = authAppInstanceRepository
                .join(userInstanceList, StringUtil.toLHCase(AuthAppUserInstance.APP_INSTANCE_ID), AuthAppInstance.ID,
                        AuthAppInstance.NAME, "appInstanceName")
                .list(AuthUserInstanceVO.class);
        userInstanceList = authRoleRepository
                .join(userInstanceList, StringUtil.toLHCase(AuthAppUserInstance.ROLE_ID), AuthRole.ID,
                        AuthRole.NAME, "roleName")
                .list(AuthUserInstanceVO.class);
        Map<Long, List<AuthUserInstanceVO>> userInstanceCollect = userInstanceList.stream()
                .collect(Collectors.groupingBy(AuthUserInstanceVO::getUserId));
        list.forEach(u -> u.setInstanceList(userInstanceCollect.get(u.getId())));
    }


    public Object logout(UserInfoInnerVO userInfoInnerVO, HttpServletRequest req, HttpServletResponse resp) {
        iWebAuthRedisDao.del(userInfoInnerVO.getSessionKey());
        return userInfoInnerVO.getSessionKey();
    }

    /**
     * @param authLoginInVO
     * @param req
     * @param resp
     * @return
     * @throws CommonException
     */
    @Transactional(rollbackFor = Exception.class)
    public Object login(AuthLoginInVO authLoginInVO, HttpServletRequest req, HttpServletResponse resp) throws CommonException {

        //验证
        VerifyUtil.verify(authLoginInVO);

        verifyCode(authLoginInVO, req, resp);

        AuthUser authUser = authUserRepository
                .where(AuthUser.ACCOUNT, authLoginInVO.getAccount())
                .or(AuthUser.EMAIL, authLoginInVO.getAccount())
                .get();

        if (authUser == null) {
            throw new CommonException("账号错误");
        }

        if (authUser.getIsActive() == AuthConstant.Common.NO) {
            throw new CommonException("账号已经被冻结，请联系管理员激活账号");
        }

        if (authUser.getTryCount() >= 6) {
            throw new CommonException("密码输入错误次数过多，账号已经被封停，请联系系统管理人员解封");
        }

        //登录成功,插入登录日志
        AuthLoginLog authLoginLog = new AuthLoginLog();
        authLoginLog.setCompanyId(authUser.getCompanyId());
        authLoginLog.setCreateTime(new Date());
        authLoginLog.setStatus(AuthConstant.Common.NO);
        authLoginLog.setTargetIp(targetIp);
        authLoginLog.setUserId(authUser.getId());
        authLoginLog.setIp(IPUtil.getIpAddr(req));
        authLoginLog.setAccount(authLoginInVO.getAccount());
        authLoginLog.setAddress(IPUtil.getIpRegion(authLoginLog.getIp()).toLocalString());

        verifyPasswd(authLoginInVO, authUser, authLoginLog);

        authUser.setTryCount(0);
        authUserRepository.include(AuthUser.TRY_COUNT).update(authUser);

        String sessionKey = UUID.randomUUID().toString().replaceAll("-", "");

        UserInfoInnerVO userInfoInnerVO = buildUserInfoInnerVO(authUser, sessionKey);

        Map<String, Object> mapResult = new HashMap<>();

        mapResult = checkoutUserInstances(authUser, userInfoInnerVO, mapResult);

        //登陆
        iWebAuthRedisDao.set(userInfoInnerVO);
        mapResult.put("userId", authUser.getId());
        mapResult.put("userName", authUser.getName());
        mapResult.put("sessionKey", sessionKey);

        authLoginLog.setStatus(AuthConstant.Common.YES);
        authLoginLogRepository.insert(authLoginLog);

        return mapResult;
    }

    /**
     * 检查用户实例
     * @param authUser
     * @param userInfoInnerVO
     * @param mapResult
     * @return
     */
    private Map<String, Object> checkoutUserInstances(AuthUser authUser, UserInfoInnerVO userInfoInnerVO, Map<String, Object> mapResult) {
        //判断用户是否只有一个实例，如果只有一个实例，直接跳转
        List<AuthAppUserInstance> liAuthAppUserInstance = authAppUserInstanceRepository
                .where(AuthAppUserInstance.DEL_FLAG, AuthConstant.Common.NO)
                .where(AuthAppUserInstance.USER_ID, authUser.getId())
                .list();
        if (liAuthAppUserInstance.size() == 1) {
            mapResult = authAppRepository.join(liAuthAppUserInstance.get(0),
                    AuthAppUserInstance.APP_ID, AuthAppInstance.ID,
                    AuthApp.CN_NAME, "appName",
                    AuthApp.URL, "url")
                    .get(Map.class);
            userInfoInnerVO.setUserInstanceId(liAuthAppUserInstance.get(0).getId());
        }
        mapResult.put("toggle", liAuthAppUserInstance.size() != 1);
        return mapResult;
    }

    /**
     * 构建用户信息
     * @param authUser
     * @param sessionKey
     * @return
     */
    private UserInfoInnerVO buildUserInfoInnerVO(AuthUser authUser, String sessionKey) {
        UserInfoInnerVO userInfoInnerVO = new UserInfoInnerVO();
        userInfoInnerVO.setSessionKey(sessionKey);
        userInfoInnerVO.setUserId(authUser.getId());
        userInfoInnerVO.setCompanyId(authUser.getCompanyId());
        userInfoInnerVO.setUserName(authUser.getName());

        UserInfoInnerVO.UserAccount userAccount = new UserInfoInnerVO.UserAccount();
        userAccount.setAccount(authUser.getAccount());
        userAccount.setOrgAccount(authUser.getOrgAccount());
        userAccount.setEmail(authUser.getEmail());
        userAccount.setCreateTime(authUser.getCreateTime());
        userAccount.setIsAdmin(authUser.getIsAdmin());
        userInfoInnerVO.setAccountInfo(userAccount);
        return userInfoInnerVO;
    }

    /**
     * 校验密码
     * @param authLoginInVO
     * @param authUser
     * @param authLoginLog
     * @throws CommonException
     */
    private void verifyPasswd(AuthLoginInVO authLoginInVO, AuthUser authUser, AuthLoginLog authLoginLog) throws CommonException {
        authLoginInVO.setPasswd(iWebAuthRsaHelper.decrypt(authLoginInVO.getPasswd()));
        if (!PasswdUtil.getPasswd(authLoginInVO.getPasswd(), authUser.getPasswdSuffix()).equals(authUser.getPasswd())) {
            authUser.setTryCount(authUser.getTryCount() + 1);
            if (authUser.getTryCount() >= 6) {
                authUser.setIsActive(AuthConstant.Common.NO);
            }
            authUserRepository.include(AuthUser.TRY_COUNT, AuthUser.IS_ACTIVE).update(authUser);
            String message = "";
            if (authUser.getTryCount() > 2) {
                message = String.format("输入错误 %d 次,你还有 %d 次机会", authUser.getTryCount(), 6 - authUser.getTryCount());
            }
            // authLoginLog.setReason("密码错误:"+authLoginInVO.getPasswd());
            authLoginLog.setReason("密码错误");
            authLoginLogRepository.insert(authLoginLog);
            throw new CommonException("密码错误 " + message);
        }
    }

    /**
     * 校验验证码
     * @param authLoginInVO
     * @param req
     * @param resp
     * @throws CommonException
     */
    private void verifyCode(AuthLoginInVO authLoginInVO, HttpServletRequest req, HttpServletResponse resp) throws CommonException {
        if (bVerifyCode && StringUtil.isEmpty(authLoginInVO.getVerifyCode())) {
            throw new CommonRuntimeException(ApiResultCode.PARAMETER_MISSING, "验证码不能为空");
        }

        if (bVerifyCode) {
            CookieUtil cookieUtil = new CookieUtil(req, resp);
            String authVerifySessionKey = cookieUtil.getString(AuthCookieConstant.AUTH_VERIFY_SESSION_KEY);
            String code = iWebAuthVertifyCodeRedisDao.get(authVerifySessionKey);
            iWebAuthVertifyCodeRedisDao.delete(authVerifySessionKey);
            if (code == null || !code.equalsIgnoreCase(authLoginInVO.getVerifyCode())) {
                throw new CommonException(ApiResultCode.VERIFY_CODE_ERROR);
            }
        }
    }

    public Object save(AuthUser authUser, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        VerifyUtil.verify(authUser);
        authUser.setCompanyId(userInfoInnerVO.getCompanyId());
        authUser.setIsAdmin(AuthConstant.Common.NO);
        if (LongUtil.isNotZero(authUser.getId())) {
            AuthUser orgAuthUser = authUserRepository
                    .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                    .where(AuthUser.ID, authUser.getId()).get();
            if (orgAuthUser == null || orgAuthUser.getIsAdmin() == AuthConstant.Common.YES) {
                throw new CommonException("无此用户权限");
            }
            authUser.setPasswd(null);
            if (authUserRepository
                    .where(AuthUser.ORG_ACCOUNT, authUser.getOrgAccount())
                    .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                    .where(AuthUser.ID, authUser.getId(), ModelOperator.NEQ).isExists()) {
                throw new CommonException("账号已经被占用");
            }
            if (authUserRepository
                    .where(AuthUser.EMAIL, authUser.getEmail())
                    .where(AuthUser.ID, authUser.getId(), ModelOperator.NEQ).isExists()) {
                throw new CommonException("邮箱已经被占用");
            }
            if (authUserRepository
                    .where(AuthUser.NAME, authUser.getName())
                    .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                    .where(AuthUser.ID, authUser.getId(), ModelOperator.NEQ).isExists()) {
                throw new CommonException("名字已经被占用");
            }
            if (StringUtil.isNotEmpty(authUser.getPhone())) {
                if (authUserRepository
                        .where(AuthUser.PHONE, authUser.getPhone())
                        .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                        .where(AuthUser.ID, authUser.getId(), ModelOperator.NEQ).isExists()) {
                    throw new CommonException("手机号已被占用");
                }
            }
            BeanUtil.initModify(authUser, userInfoInnerVO.getUserName());
        } else {
            if (authUserRepository
                    .where(AuthUser.ORG_ACCOUNT, authUser.getOrgAccount())
                    .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                    .isExists()) {
                throw new CommonException("账号已经被占用");
            }
            if (authUserRepository
                    .where(AuthUser.EMAIL, authUser.getEmail())
                    .isExists()) {
                throw new CommonException("邮箱已经被占用");
            }
            if (authUserRepository
                    .where(AuthUser.NAME, authUser.getName())
                    .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                    .isExists()) {
                throw new CommonException("名字已经被占用");
            }
            if (StringUtil.isNotEmpty(authUser.getPhone())) {
                if (authUserRepository
                        .where(AuthUser.PHONE, authUser.getPhone())
                        .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                        .isExists()) {
                    throw new CommonException("手机号已被占用");
                }
            }
            BeanUtil.initCreate(authUser, userInfoInnerVO.getUserName());
        }

        if (StringUtil.isNotEmpty(authUser.getPasswd())) {
            authUser.setPasswdSuffix(UUID.randomUUID().toString().replaceAll("-", ""));
            authUser.setPasswd(PasswdUtil.getPasswd(authUser.getPasswd(), authUser.getPasswdSuffix()));
        }

        if (authUser.getCompanyId() == -1) {
            AuthUser admin = authUserRepository.where(AuthUser.COMPANY_ID, -1).where(AuthUser.IS_ADMIN, AuthConstant.Common.YES).get();
            String account = "admin";
            if (admin != null) {
                account = admin.getOrgAccount();
            }
            authUser.setAccount(authUser.getOrgAccount() + "@" + account);
        } else {
            AuthCompany authCompany = authCompanyRepository.where(AuthCompany.ID, authUser.getCompanyId()).get();
            if (authCompany != null && StringUtil.isNotEmpty(authCompany.getAccount())) {
                authUser.setAccount(authUser.getOrgAccount() + "@" + authCompany.getAccount());
            } else {
                authUser.setAccount(authUser.getOrgAccount() + "@" + authUser.getCompanyId());
            }
        }

        authUser.setIsAdmin(AuthConstant.Common.NO);
        authUserRepository.save(authUser);

        return authUser;
    }

    public Object rePasswd(AuthUserRepasswdInVO authUserRepasswdInVO, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        //验证
        VerifyUtil.verify(authUserRepasswdInVO);

        AuthUser authUser = authUserRepository.get(userInfoInnerVO.getUserId());

        if (authUser.getTryCount() >= 6) {
            throw new CommonException("密码输入错误次数过多，账号已经被封停，请联系系统管理人员解封");
        }

        if (authUser.getIsActive() == AuthConstant.Common.NO) {
            throw new CommonException("账号已经被冻结，请联系管理员激活账号");
        }

        if (!authUser.getPasswd().equals(PasswdUtil.getPasswd(authUserRepasswdInVO.getPasswd(), authUser.getPasswdSuffix()))) {
            authUser.setTryCount(authUser.getTryCount() + 1);
            authUserRepository.include(AuthUser.TRY_COUNT).update(authUser);
            String message = "";
            if (authUser.getTryCount() > 2) {
                message = String.format("输入错误 %d 次,你还有 %d 次机会", authUser.getTryCount(), 6 - authUser.getTryCount());
            }
            throw new CommonException("原始密码错误 " + message);
        }

        authUser.setPasswdSuffix(UUID.randomUUID().toString().replaceAll("-", ""));
        authUser.setPasswd(PasswdUtil.getPasswd(authUserRepasswdInVO.getNewPasswd(), authUser.getPasswdSuffix()));
        authUser.setTryCount(0);
        authUserRepository.include(AuthUser.TRY_COUNT, AuthUser.PASSWD, AuthUser.PASSWD_SUFFIX).update(authUser);
        return null;
    }


    public Object active(AuthUser authUser, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        VerifyUtil.verify(authUser, AuthUser.ID, AuthUser.IS_ACTIVE);
        AuthUser orgAuthUser = authUserRepository
                .where(AuthUser.COMPANY_ID, authUser.getCompanyId())
                .where(AuthUser.ID, authUser.getId()).get();
        if (orgAuthUser == null || (orgAuthUser.getIsAdmin() == AuthConstant.Common.YES && orgAuthUser.getIsActive() != AuthConstant.Common.NO)) {
            throw new CommonException("无此用户权限");
        }
        authUser.setTryCount(0);
        authUserRepository
                .include(AuthUser.IS_ACTIVE, AuthUser.TRY_COUNT)
                .save(authUser);
        return authUser;
    }
}
