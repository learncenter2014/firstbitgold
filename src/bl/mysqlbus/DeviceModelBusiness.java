/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.DeviceModelBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class DeviceModelBusiness extends MysqlBusiness<BeanContext, DeviceModelBean> {
    private static Logger log = LoggerFactory.getLogger(DeviceModelBusiness.class);

    public DeviceModelBusiness() {
        this.cls = DeviceModelBean.class;
    }
}
