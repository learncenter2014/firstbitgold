/**
 * 
 */
package actions;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.DeviceTypeBean;
import bl.mysqlbus.DeviceTypeBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceTypeAction extends BaseTableAction<DeviceTypeBusiness> {
    private static Logger log = LoggerFactory.getLogger(DeviceTypeAction.class);
    private DeviceTypeBean deviceType;

    public DeviceTypeBean getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeBean deviceType) {
        this.deviceType = deviceType;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Type Name").enableSearch().setbSortable(true));
        return init;
    }

    @Override
    public String save() throws Exception {
        if (deviceType.getId() == 0) {
            getBusiness().createLeaf(deviceType);
        } else {
            DeviceTypeBean origBean = (DeviceTypeBean) getBusiness().getLeaf(String.valueOf(deviceType.getId())).getResponseData();
            DeviceTypeBean newBean = (DeviceTypeBean) origBean.clone();
            BeanUtils.copyProperties(newBean, deviceType);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        deviceType = (DeviceTypeBean) getBusiness().getLeaf(getId()).getResponseData();
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
        return getRequest().getContextPath() + "/devicetype";
    }
}
