package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import bl.beans.DashBoardBean;
import bl.beans.DeviceInfoBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mysqlbus.DeviceInfoBusiness;
import bl.mysqlbus.ProjectBusiness;
import bl.mysqlbus.UserBusiness;

import com.opensymphony.xwork2.ActionSupport;

public class DashBoardAction extends ActionSupport {
    private DashBoardBean dashBoardBean;
    private static final long serialVersionUID = 1L;
    private static final UserBusiness urb = (UserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USER_BUSINESS);
    private static final ProjectBusiness prb = (ProjectBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_PROJECT_BUSINESS);
    private static final DeviceInfoBusiness dib = (DeviceInfoBusiness) SingleBusinessPoolManager
            .getBusObj(BusTieConstant.BUS_CPATH_DEVICEINFO_BUSINESS);
    private TreeMap<Integer, List<DeviceInfoBean>> mapRackDeviceInfo = new TreeMap<Integer, List<DeviceInfoBean>>();

    public TreeMap<Integer, List<DeviceInfoBean>> getMapRackDeviceInfo() {
        return mapRackDeviceInfo;
    }

    public void setMapRackDeviceInfo(TreeMap<Integer, List<DeviceInfoBean>> mapRackDeviceInfo) {
        this.mapRackDeviceInfo = mapRackDeviceInfo;
    }

    public DashBoardBean getDashBoardBean() {
        return dashBoardBean;
    }

    public void setDashBoardBean(DashBoardBean dashBoardBean) {
        this.dashBoardBean = dashBoardBean;
    }

    public String getAllData() {
        dashBoardBean = new DashBoardBean();
        dashBoardBean.setUserCounter(urb.getCount(null));
        dashBoardBean.setProjectCounter(prb.getCount(null));
        dashBoardBean.setDeviceCounter(dib.getCount(null));
        List<DeviceInfoBean> deviceInfos = (List<DeviceInfoBean>) dib.getAllLeaves().getResponseData();
        // sorted by Rack value.
        if (deviceInfos != null) {
            for (int i = 0; i < deviceInfos.size(); i++) {
                DeviceInfoBean db = deviceInfos.get(i);
                if (!mapRackDeviceInfo.containsKey(db.getRack())) {
                    List<DeviceInfoBean> arrayDevice = new ArrayList<DeviceInfoBean>();
                    arrayDevice.add(db);
                    mapRackDeviceInfo.put(db.getRack(), arrayDevice);
                } else {
                    List<DeviceInfoBean> arrayDevice = mapRackDeviceInfo.get(db.getRack());
                    arrayDevice.add(db);
                }
            }
        }
        return SUCCESS;
    }
}
