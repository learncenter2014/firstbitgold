/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.IpTagBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class IpTagBusiness extends MysqlBusiness<BeanContext, IpTagBean> {
    private static Logger log = LoggerFactory.getLogger(IpTagBusiness.class);

    public IpTagBusiness() {
        this.cls = IpTagBean.class;
    }
}
