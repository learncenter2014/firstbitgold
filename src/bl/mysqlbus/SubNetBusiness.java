/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.SubNetBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class SubNetBusiness extends MysqlBusiness<BeanContext, SubNetBean> {
    private static Logger log = LoggerFactory.getLogger(SubNetBusiness.class);

    public SubNetBusiness() {
        this.cls = SubNetBean.class;
    }
}
