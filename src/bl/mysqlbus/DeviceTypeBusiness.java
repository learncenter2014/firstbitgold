/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.DeviceTypeBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceTypeBusiness extends MysqlBusiness<BeanContext, DeviceTypeBean> {
    private static Logger log = LoggerFactory.getLogger(DeviceTypeBusiness.class);

    public DeviceTypeBusiness() {
        this.cls = DeviceTypeBean.class;
    }
}
