/**
 *
 */
package bl.mysqlbus;

import bl.beans.UserBean;
import bl.common.BeanContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pli
 * @since $Date:2014-02-20$
 */
public class UserBusiness extends MysqlBusiness<BeanContext, UserBean> {
    private static Logger log = LoggerFactory.getLogger(UserBusiness.class);

    public UserBusiness() {
    }

    @Override
    public TableDataVo query(TableQueryVo queryParam) {
        List<UserBean> list = new ArrayList<UserBean>();
        UserBean ub1 = new UserBean();
        ub1.setName("peterli");
        UserBean ub2 = new UserBean();
        ub2.setName("china");
        list.add(ub1);
        list.add(ub2);
        TableDataVo dataTable = new TableDataVo();
        dataTable.setsEcho(queryParam.getSEcho());
        dataTable.setAaData(list);
        return dataTable;
    }

    @Override
    public long getCount(TableQueryVo queryParam) {
        return 2;
    }
}
