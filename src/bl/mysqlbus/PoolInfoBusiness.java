/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.PoolInfoBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class PoolInfoBusiness extends MysqlBusiness<BeanContext, PoolInfoBean> {
    private static Logger log = LoggerFactory.getLogger(PoolInfoBusiness.class);

    public PoolInfoBusiness() {
        this.cls = PoolInfoBean.class;
    }
}
