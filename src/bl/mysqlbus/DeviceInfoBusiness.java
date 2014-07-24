/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.DeviceInfoBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceInfoBusiness extends MysqlBusiness<BeanContext, DeviceInfoBean> {
    private static Logger log = LoggerFactory.getLogger(DeviceInfoBusiness.class);

    public DeviceInfoBusiness() {
        this.cls = DeviceInfoBean.class;
    }
}
