/**
 * 
 */
package actions;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.DeviceInfoBean;
import bl.beans.DeviceModelBean;
import bl.beans.PoolInfoBean;
import bl.beans.ProjectBean;
import bl.beans.UserBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mysqlbus.DeviceInfoBusiness;
import bl.mysqlbus.DeviceModelBusiness;
import bl.mysqlbus.PoolInfoBusiness;
import bl.mysqlbus.ProjectBusiness;
import bl.mysqlbus.UserBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceInfoAction extends BaseTableAction<DeviceInfoBusiness> {
    private static Logger log = LoggerFactory.getLogger(DeviceInfoAction.class);
    private static final DeviceModelBusiness dmb = (DeviceModelBusiness) SingleBusinessPoolManager
            .getBusObj(BusTieConstant.BUS_CPATH_DEVICEMODEL_BUSINESS);
    private static final PoolInfoBusiness pib = (PoolInfoBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_POOLINFO_BUSINESS);
    private static final UserBusiness urb = (UserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USER_BUSINESS);
    private static final ProjectBusiness prb = (ProjectBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_PROJECT_BUSINESS);

    private DeviceInfoBean deviceInfo;

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Device Name").enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("devtypeinfo", "Device Type").enableSearch().setbSortable(true));
        List<DeviceModelBean> deviceModelList = (List<DeviceModelBean>) dmb.getAllLeaves().getResponseData();
        String[][] dms = new String[2][deviceModelList.size()];
        if (deviceModelList.size() > 0) {
            for (int i = 0; i < deviceModelList.size(); i++) {
                dms[0][i] = String.valueOf(deviceModelList.get(i).getId());
                dms[1][i] = deviceModelList.get(i).getName();
            }
        } else {
            dms = null;
        }
        init.getAoColumns().add(new TableHeaderVo("devmodel", "Device Model").addSearchOptions(dms).enableSearch().setbSortable(true));

        List<PoolInfoBean> poolInfoList = (List<PoolInfoBean>) pib.getAllLeaves().getResponseData();
        String[][] pls = new String[2][poolInfoList.size()];
        if (poolInfoList.size() > 0) {
            for (int i = 0; i < poolInfoList.size(); i++) {
                pls[0][i] = String.valueOf(poolInfoList.get(i).getId());
                pls[1][i] = poolInfoList.get(i).getName();
            }
        } else {
            pls = null;
        }
        init.getAoColumns().add(new TableHeaderVo("poolid", "Pool Name").addSearchOptions(pls).enableSearch());
        init.getAoColumns().add(new TableHeaderVo("rack", "Rack").enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("slot", "Slot").enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("ipInfoDisplay", "IP Info").enableSearch());
        List<UserBean> userList = (List<UserBean>) urb.getAllLeaves().getResponseData();
        String[][] urs = new String[2][userList.size()];
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                urs[0][i] = String.valueOf(userList.get(i).getId());
                urs[1][i] = userList.get(i).getName();
            }
        } else {
            urs = null;
        }
        init.getAoColumns().add(new TableHeaderVo("userid", "User Name").addSearchOptions(urs).enableSearch().setbSortable(true));

        List<ProjectBean> projectList = (List<ProjectBean>) prb.getAllLeaves().getResponseData();
        String[][] prs = new String[2][projectList.size()];
        if (projectList.size() > 0) {
            for (int i = 0; i < projectList.size(); i++) {
                prs[0][i] = String.valueOf(projectList.get(i).getId());
                prs[1][i] = projectList.get(i).getName();
            }
        } else {
            prs = null;
        }
        init.getAoColumns().add(new TableHeaderVo("project", "Project").addSearchOptions(prs).enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("comments", "Comments").enableSearch());
        return init;
    }

    @Override
    public String save() throws Exception {
        if (deviceInfo.getId() == 0) {
            getBusiness().createLeaf(deviceInfo);
        } else {
            DeviceInfoBean origBean = (DeviceInfoBean) getBusiness().getLeaf(String.valueOf(deviceInfo.getId())).getResponseData();
            DeviceInfoBean newBean = (DeviceInfoBean) origBean.clone();
            BeanUtils.copyProperties(newBean, deviceInfo);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        deviceInfo = (DeviceInfoBean) getBusiness().getLeaf(getId()).getResponseData();
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

    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/deviceinfo";
    }

    @Override
    public String getCustomJsp() {
        return "/pages/deviceinfo/deviceinfopost.jsp";
    };

}
