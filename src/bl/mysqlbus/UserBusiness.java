/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.UserBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class UserBusiness extends MysqlBusiness<BeanContext, UserBean> {
    private static Logger log = LoggerFactory.getLogger(UserBusiness.class);

    public UserBusiness() {
        this.cls = UserBean.class;
    }
}
