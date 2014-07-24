/**
 *
 */
package bl.mysqlbus;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.UserBean;
import bl.beans.UserGroupBean;
import bl.common.BeanContext;
import dao.MysqlHibernateDao;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class UserGroupBusiness extends MysqlBusiness<BeanContext, UserGroupBean> {
    private static Logger log = LoggerFactory.getLogger(UserGroupBusiness.class);

    public UserGroupBusiness() {
        this.cls = UserGroupBean.class;
    }
}
