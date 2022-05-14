package org.devops.iweb.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthAppUserInstanceMenu;
import org.devops.iweb.auth.model.AuthMenu;
import org.devops.iweb.auth.model.AuthResources;
import org.devops.iweb.auth.model.AuthRole;
import org.devops.iweb.auth.model.AuthRoleMenu;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceMenuRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthMenuRepository;
import org.devops.iweb.auth.repository.AuthResourcesRepository;
import org.devops.iweb.auth.repository.AuthRoleMenuRepository;
import org.devops.iweb.auth.repository.AuthRoleRepository;
import org.devops.iweb.auth.vo.inVO.AuthMenuSaveInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.outVO.AuthMenuOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMenuService {

    @Autowired
    private AuthMenuRepository authMenuRepository;

    @Autowired
    private AuthAppInstanceRepository authAppInstanceRepository;

    @Autowired
    private AuthAppUserInstanceRepository authAppUserInstanceRepository;

    @Autowired
    private AuthAppUserInstanceMenuRepository authAppUserInstanceMenuRepository;

    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private AuthRoleMenuRepository authRoleMenuRepository;

    @Autowired
    private AuthResourcesRepository authResourcesRepository;


    public Object list(Long appId, Long appInstanceId) {
        if (LongUtil.isNotZero(appInstanceId)) {
            AuthAppInstance authAppInstance = authAppInstanceRepository.get(appInstanceId);
            if (authAppInstance == null) {
                appId = 0L;
            } else {
                appId = authAppInstance.getAppId();
            }
        }
        List<AuthMenuOutVO> liAuthMenu = authMenuRepository
                .exclude(AuthConstant.Common.EXCLUDE_FIELDS)
                .where(AuthMenu.APP_ID, appId)
                .where(AuthMenu.DEL_FLAG, AuthConstant.Common.NO)
                .orderBy(AuthMenu.SORT, "asc")
                .list(AuthMenuOutVO.class);

        Map<Long, AuthMenuOutVO> mapAuthMenu = MapUtil.toMap(liAuthMenu, AuthMenu.ID);
        List<AuthMenuOutVO> result = new ArrayList<AuthMenuOutVO>();
        for (AuthMenuOutVO authMenu : liAuthMenu) {
            if (LongUtil.isZero(authMenu.getParentId())) {
                result.add(authMenu);
                continue;
            }
            AuthMenuOutVO parentMenu = mapAuthMenu.get(authMenu.getParentId());
            if (parentMenu != null) {
                parentMenu.addChildren(authMenu);
            }
        }
        return MapUtil.toMapListResultExcludeFields(result, AuthConstant.Common.EXCLUDE_FIELDS);
    }

    public Object listUserMenu(Long userId, Long userInstanceId) {

        //AuthUser authUser = authUserRepository.exclude(AuthConstant.Common.EXCLUDE_FIELDS).get(userId);

        AuthAppUserInstance authAppUserInstance = authAppUserInstanceRepository.get(userInstanceId);
        List<AuthMenu> liAuthMenu = new ArrayList<AuthMenu>();

        Set<String> stResourceIds = new HashSet<String>();

        Integer isAdmin = authAppUserInstance.getIsAdmin();

        if (LongUtil.isNotZero(authAppUserInstance.getRoleId())) {
            //如果有角色，以角色为主
            AuthRole authRole = authRoleRepository.get(authAppUserInstance.getRoleId());
            isAdmin = (int) authRole.getIsAdmin();
        }

        if (isAdmin.equals(AuthConstant.Common.YES)) {
            //如果是管理员，获取所有的菜单
            liAuthMenu = authMenuRepository
                    .where(AuthMenu.APP_ID, authAppUserInstance.getAppId())
                    .where(AuthMenu.IS_ACTIVE, AuthConstant.Common.YES)
                    .where(AuthMenu.DEL_FLAG, AuthConstant.Common.NO)
                    .orderBy(AuthMenu.SORT, "asc")
                    .list();

            stResourceIds = new HashSet<String>(authResourcesRepository.include(AuthResources.ID)
                    .where(AuthResources.APP_ID, authAppUserInstance.getAppId())
                    .where(AuthResources.TYPE, AuthConstant.ResourcesType.BUTTON)
                    .listString());
        } else {
            List<Long> ids = new ArrayList<Long>();
            if (LongUtil.isNotZero(authAppUserInstance.getRoleId())) {
                List<AuthRoleMenu> liAuthRoleMenu = authRoleMenuRepository
                        .where(AuthRoleMenu.ROLE_ID, authAppUserInstance.getRoleId()).list();
                ids = ListUtil.toLongList(liAuthRoleMenu, AuthRoleMenu.MENU_ID);
                if (!ids.isEmpty()) {
                    for (AuthRoleMenu authRoleMenu : liAuthRoleMenu) {
                        if (StringUtil.isNotEmpty(authRoleMenu.getResources())) {
                            String resource = authRoleMenu.getResources().replaceAll("^,", "").replaceAll(",$", "");
                            stResourceIds.addAll(Arrays.asList(resource.split(",")));
                        }
                    }
                }

            } else {
                List<AuthAppUserInstanceMenu> liAuthAppUserInstanceMenu = authAppUserInstanceMenuRepository
                        .where(AuthAppUserInstanceMenu.APP_USER_INSTANCE_ID, userInstanceId).list();
                ids = ListUtil.toLongList(liAuthAppUserInstanceMenu, AuthAppUserInstanceMenu.MENU_ID);
                if (!ids.isEmpty()) {
                    for (AuthAppUserInstanceMenu authAppUserInstanceMenu : liAuthAppUserInstanceMenu) {
                        if (StringUtil.isNotEmpty(authAppUserInstanceMenu.getResources())) {
                            String resource = authAppUserInstanceMenu.getResources().replaceAll("^,", "").replaceAll(",$", "");
                            stResourceIds.addAll(Arrays.asList(resource.split(",")));
                        }
                    }
                }
            }
            if (!ids.isEmpty()) {
                liAuthMenu = authMenuRepository
                        .where(AuthMenu.APP_ID, authAppUserInstance.getAppId())
                        .where(AuthMenu.ID, ids, ModelOperator.IN)
                        .where(AuthMenu.IS_ACTIVE, AuthConstant.Common.YES)
                        .where(AuthMenu.DEL_FLAG, AuthConstant.Common.NO)
                        .orderBy(AuthMenu.SORT, "asc")
                        .list();
            }
        }

        List<AuthMenuOutVO> liAuthMenuOutVO = BeanUtil.copy(liAuthMenu, AuthMenuOutVO.class);
        Map<Long, AuthMenuOutVO> mapAuthMenu = MapUtil.toMap(liAuthMenuOutVO, AuthMenu.ID);
        List<AuthMenuOutVO> result = new ArrayList<AuthMenuOutVO>();


        for (AuthMenuOutVO authMenu : liAuthMenuOutVO) {
            if (LongUtil.isZero(authMenu.getParentId())) {
                result.add(authMenu);
                continue;
            }
            AuthMenuOutVO parentMenu = mapAuthMenu.get(authMenu.getParentId());
            if (parentMenu != null) {
                parentMenu.addChildren(authMenu);
            }

        }
        List<String> liResourceIds = new ArrayList<String>(stResourceIds);
        List<String> liCode = new ArrayList<String>();
        if (!liResourceIds.isEmpty()) {
            liCode = authResourcesRepository.include(AuthResources.CODE)
                    .where(AuthResources.ID, liResourceIds, ModelOperator.IN)
                    .where(AuthResources.TYPE, AuthConstant.ResourcesType.BUTTON)
                    .listString();
        }

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("menus", MapUtil.toMapListResultExcludeFields(result, AuthConstant.Common.EXCLUDE_FIELDS));
        mapResult.put("auth", liCode);
        return mapResult;

    }

    @SuppressWarnings("unused")
    private void getMenu(List<AuthMenuSaveInVO> out, AuthMenuSaveInVO authMenuSaveInVO) {
        out.add(authMenuSaveInVO);
        if (authMenuSaveInVO.getChildren() == null || authMenuSaveInVO.getChildren().isEmpty()) {
            return;
        }
        for (AuthMenuSaveInVO authMenu : authMenuSaveInVO.getChildren()) {
            getMenu(out, authMenu);
        }
    }

    private void save(List<AuthMenuSaveInVO> liAuthMenuSaveInVO, Long parentId, String path, UserInfoInnerVO userInfoInnerVO) {
        if (liAuthMenuSaveInVO == null || liAuthMenuSaveInVO.isEmpty()) {
            return;
        }
        List<AuthMenuSaveInVO> liUpdate = new ArrayList<AuthMenuSaveInVO>();
        List<AuthMenuSaveInVO> liInsert = new ArrayList<AuthMenuSaveInVO>();

        for (AuthMenuSaveInVO authMenuSaveInVO : liAuthMenuSaveInVO) {
            authMenuSaveInVO.setPath(path + parentId + ",");
            authMenuSaveInVO.setParentId(parentId);
            authMenuSaveInVO.setDelFlag(AuthConstant.Common.NO);
            if (LongUtil.isNotZero(authMenuSaveInVO.getId())) {
                BeanUtil.initModify(authMenuSaveInVO, userInfoInnerVO.getUserName());
                liUpdate.add(authMenuSaveInVO);
            } else {
                BeanUtil.initCreate(authMenuSaveInVO, userInfoInnerVO.getUserName());
                liInsert.add(authMenuSaveInVO);
            }
        }
        if (!liUpdate.isEmpty()) {
            authMenuRepository.updateByObject(liUpdate);
        }
        if (!liInsert.isEmpty()) {
            authMenuRepository.insertByObject(liInsert);
        }
        liInsert.addAll(liUpdate);
        liUpdate.clear();
        for (AuthMenuSaveInVO authMenuSaveInVO : liInsert) {
            save(authMenuSaveInVO.getChildren(), authMenuSaveInVO.getId(), authMenuSaveInVO.getPath(), userInfoInnerVO);
        }
    }

    public Object save(AuthMenuSaveInVO authMenuSaveInVO, UserInfoInnerVO userInfoInnerVO) throws CommonException {
        if (authMenuSaveInVO == null) {
            throw new CommonException("请传入正确的参数");
        }
        authMenuRepository
                .where(AuthMenu.APP_ID, authMenuSaveInVO.getAppId())
                .update(AuthMenu.DEL_FLAG, AuthConstant.Common.YES);

        if (authMenuSaveInVO.getChildren() == null || authMenuSaveInVO.getChildren().isEmpty()) {
            return null;
        }

        save(authMenuSaveInVO.getChildren(), 0L, ",", userInfoInnerVO);

        return null;
    }
}
