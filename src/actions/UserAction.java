/**
 * 
 */
package actions;

import java.util.List;

import bl.beans.UserBean;
import bl.beans.UserGroupBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mysqlbus.DeviceTypeBusiness;
import bl.mysqlbus.UserBusiness;
import bl.mysqlbus.UserGroupBusiness;

import com.opensymphony.xwork2.ActionContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pli
 * @since $Date:2014-07-16$ $Date:2014-02-10$
 */
public class UserAction extends BaseTableAction<UserBusiness> {
    private static Logger log = LoggerFactory.getLogger(UserAction.class);
    public final static String LOGIN_USER_SESSION_ID = "sessionUser";
    private UserBean user;
    private List<UserGroupBean> userGroup;
    private static final UserGroupBusiness ugb = (UserGroupBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERGROUP_BUSINESS);

    public List<UserGroupBean> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(List<UserGroupBean> userGroup) {
        this.userGroup = userGroup;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/user";
    }

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Owner").enableSearch().setbSortable(true));
        List<UserGroupBean> userGroupList = (List<UserGroupBean>) ugb.getAllLeaves().getResponseData();
        this.setUserGroup(userGroupList);
        String[][] userGroup = new String[2][userGroupList.size()];
        if (userGroupList.size() > 0) {
            for (int i = 0; i < userGroupList.size(); i++) {
                userGroup[0][i] = String.valueOf(userGroupList.get(i).getId());
                userGroup[1][i] = userGroupList.get(i).getName();
            }
        } else {
            userGroup = null;
        }

        init.getAoColumns().add(new TableHeaderVo("ugroup", "Group").addSearchOptions(userGroup).enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("email", "Email").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("location", "Location").enableSearch());
        init.getAoColumns().add(new TableHeaderVo("comments", "Comments").enableSearch());
        return init;
    }

    @Override
    public String save() throws Exception {
        if (user.getId() == 0) {
            getBusiness().createLeaf(user);
        } else {
            UserBean origUser = (UserBean) getBusiness().getLeaf(String.valueOf(user.getId())).getResponseData();
            UserBean newUser = (UserBean) origUser.clone();
            BeanUtils.copyProperties(newUser, user);
            getBusiness().updateLeaf(origUser, newUser);
        }
        return SUCCESS;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        List<UserGroupBean> userGroupList = (List<UserGroupBean>) ugb.getAllLeaves().getResponseData();
        this.setUserGroup(userGroupList);
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        List<UserGroupBean> userGroupList = (List<UserGroupBean>) ugb.getAllLeaves().getResponseData();
        this.setUserGroup(userGroupList);
        user = (UserBean) getBusiness().getLeaf(getId()).getResponseData();
        return SUCCESS;
    }

    @Override
    public String delete() throws Exception {
        if (getIds() != null) {
            for (String id : getIds()) {
                getBusiness().deleteLeaf(id);
            }
        }
        return SUCCESS;
    }

    /**
     * login
     * 
     * @return
     */
    public String login() {
        if (user != null) {
            UserBean userTmp = (UserBean) getBusiness().getLeafByName(user.getName()).getResponseData();
            //TODO checked non empty password and MD5 authentication. 
            if (userTmp != null && (userTmp.getPassword()==null || userTmp.getPassword()!=null && userTmp.getPassword().equals(user.getPassword()))) {
                getSession().setAttribute(LOGIN_USER_SESSION_ID, userTmp);
                return SUCCESS;
            }
        }
        return "login_failure";
    }
    
    /**
     * changePassword
     * 
     * @return
     * @throws Exception
     */
    public String changePassword() throws Exception {
        if (user != null) {
            UserBean originalUser = (UserBean) getSession().getAttribute(LOGIN_USER_SESSION_ID);
            if (originalUser != null && user.getPassword().equals(originalUser.getPassword())) {
                UserBean newPassword = (UserBean) originalUser.clone();
                newPassword.setPassword(getRequest().getParameter("newPassword"));
                getBusiness().updateLeaf(originalUser, newPassword);
                getSession().setAttribute(LOGIN_USER_SESSION_ID, newPassword);
                return SUCCESS;
            } else {
                addActionError("Original password doesn't match!");
            }
        }
        return FAILURE;
    }


    /**
     * login
     * 
     * @return
     */
    public String logout() {
        getSession().removeAttribute(LOGIN_USER_SESSION_ID);
        HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        eraseCookie(req, resp);
        return SUCCESS;
    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                resp.addCookie(cookies[i]);
            }
    }
}
