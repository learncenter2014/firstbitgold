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
import bl.beans.DeviceModelBean;
import bl.beans.DeviceTypeBean;
import bl.beans.UserGroupBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mysqlbus.DeviceModelBusiness;
import bl.mysqlbus.DeviceTypeBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceModelAction extends BaseTableAction<DeviceModelBusiness> {
    private static Logger log = LoggerFactory.getLogger(DeviceModelAction.class);
    private static final DeviceTypeBusiness dtb = (DeviceTypeBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_DEVICETYPE_BUSINESS);
    private DeviceModelBean deviceModel;
    private List<DeviceTypeBean> deviceTypes;

    public List<DeviceTypeBean> getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(List<DeviceTypeBean> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public DeviceModelBean getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(DeviceModelBean deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        List<DeviceTypeBean> deviceTypeBeans = (List<DeviceTypeBean>) dtb.getAllLeaves().getResponseData();
        this.setDeviceTypes(deviceTypeBeans);
        String[][] devices = new String[2][deviceTypeBeans.size()];
        if (deviceTypeBeans.size() > 0) {
            for (int i = 0; i < deviceTypeBeans.size(); i++) {
                devices[0][i] = String.valueOf(deviceTypeBeans.get(i).getId());
                devices[1][i] = deviceTypeBeans.get(i).getName();
            }
        } else {
            devices = null;
        }

        init.getAoColumns().add(new TableHeaderVo("devtype", "Device Type").addSearchOptions(devices).enableSearch().setbSortable(true));
        init.getAoColumns().add(new TableHeaderVo("name", "Model Name").enableSearch().setbSortable(true));
        return init;
    }

    @Override
    public String save() throws Exception {
        if (deviceModel.getId() == 0) {
            getBusiness().createLeaf(deviceModel);
        } else {
            DeviceModelBean origBean = (DeviceModelBean) getBusiness().getLeaf(String.valueOf(deviceModel.getId())).getResponseData();
            UserGroupBean newBean = (UserGroupBean) origBean.clone();
            BeanUtils.copyProperties(newBean, deviceModel);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }
    /**
    *
    * @return
    * @throws Exception
    */
   public String add() throws Exception {
       List<DeviceTypeBean> deviceTypeBeans = (List<DeviceTypeBean>) dtb.getAllLeaves().getResponseData();
       this.setDeviceTypes(deviceTypeBeans);
       return SUCCESS;
   }
   
    @Override
    public String edit() throws Exception {
        deviceModel = (DeviceModelBean) getBusiness().getLeaf(getId()).getResponseData();
        List<DeviceTypeBean> deviceTypeBeans = (List<DeviceTypeBean>) dtb.getAllLeaves().getResponseData();
        this.setDeviceTypes(deviceTypeBeans);
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
        return getRequest().getContextPath() + "/devicemodel";
    }
}
