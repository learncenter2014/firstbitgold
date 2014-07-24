package actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.SimpleProcessRunner;
import bl.beans.IpTagBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mysqlbus.IpTagBusiness;

public class OvmIpAction extends BaseAction {
    private static final IpTagBusiness itb = (IpTagBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_IPTAG_BUSINESS);
    private static Logger logger = LoggerFactory.getLogger(OvmIpAction.class);
    private static String QPIPMGMT = "/opt/ipmgmt/bin/qpipmgmt.py";
    private JSONArray ipscopes;
    private String ovmips;
    private List<IpTagBean> ipTagBeans;
    private boolean test;

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public JSONArray getIpscopes() {
        return ipscopes;
    }

    public void setIpscopes(JSONArray ipscopes) {
        this.ipscopes = ipscopes;
    }

    public String getOvmips() {
        return ovmips;
    }

    public void setOvmips(String ovmips) {
        this.ovmips = ovmips;
    }

    public List<IpTagBean> getIpTagBeans() {
        return ipTagBeans;
    }

    public void setIpTagBeans(List<IpTagBean> ipTagBeans) {
        this.ipTagBeans = ipTagBeans;
    }

    public String init() {
        if (isTest()) {
            ipTagBeans = (List<IpTagBean>) itb.getAllLeaves().getResponseData();
            byte[] ipscopeJsonByte = null;
            try {
                ipscopeJsonByte = Files.readAllBytes(Paths.get(OvmIpAction.class.getResource("ipscopelist.js").toURI()));
            } catch (Exception e) {
                logger.error("Reading this file ipscopelist.js, some error occured:{}", e.getMessage());
            }
            ipscopes = JSONArray.fromObject(new String(ipscopeJsonByte));
            return INPUT;
        } else {
            // Reading IP tag Information.
            ipTagBeans = (List<IpTagBean>) itb.getAllLeaves().getResponseData();

            // Reading IP scope Information.
            SimpleProcessRunner spr = new SimpleProcessRunner(QPIPMGMT + " --list-ip-scope", 5);
            spr.run();
            if (spr.getRc() != 0) {
                logger.error("Problem getting pid. Error = {}", spr.getStdErr());
                super.addActionError("qpipmgmt.py cannot return right information.");
                return INPUT;
            } else {
                ipscopes = JSONArray.fromObject(spr.getStdOut());
                return INPUT;
            }

        }

    }

    public static void main(String[] args) throws Exception {
        JsonConfig config = new JsonConfig();
        byte[] ipscopeJsonByte = Files.readAllBytes(Paths.get(OvmIpAction.class.getResource("ipscopelist.js").toURI()));
        String ipscopeJson = new String(ipscopeJsonByte);
        JSONArray jsa = JSONArray.fromObject(ipscopeJson);
        Iterator it = jsa.getJSONObject(0).keys();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
