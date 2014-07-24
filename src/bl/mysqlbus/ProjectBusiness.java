/**
 *
 */
package bl.mysqlbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bl.beans.ProjectBean;
import bl.common.BeanContext;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class ProjectBusiness extends MysqlBusiness<BeanContext, ProjectBean> {
    private static Logger log = LoggerFactory.getLogger(ProjectBusiness.class);

    public ProjectBusiness() {
        this.cls = ProjectBean.class;
    }
}
