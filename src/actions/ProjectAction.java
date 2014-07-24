/**
 * 
 */
package actions;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.ProjectBean;
import bl.mysqlbus.ProjectBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class ProjectAction extends BaseTableAction<ProjectBusiness> {
    private static Logger log = LoggerFactory.getLogger(ProjectAction.class);
    private ProjectBean project;

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Project Name").enableSearch().setbSortable(true));
        return init;
    }

    @Override
    public String save() throws Exception {
        if (project.getId() == 0) {
            getBusiness().createLeaf(project);
        } else {
            ProjectBean origBean = (ProjectBean) getBusiness().getLeaf(String.valueOf(project.getId())).getResponseData();
            ProjectBean newBean = (ProjectBean) origBean.clone();
            BeanUtils.copyProperties(newBean, project);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        project = (ProjectBean) getBusiness().getLeaf(getId()).getResponseData();
        return SUCCESS;
    }

    @Override
    public String delete() throws Exception {
        if (getIds() != null) {
            for (String id : getIds()) {
                getBusiness().deleteLeaf(id);
            }
        }
        return SUCCESS;
    }

    @Override
    public String getActionPrex() {
        return getRequest().getContextPath() + "/project";
    }
}
