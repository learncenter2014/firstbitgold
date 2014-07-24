/**
 * 
 */
package actions;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.SubNetBean;
import bl.mysqlbus.SubNetBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class SubNetAction extends BaseTableAction<SubNetBusiness> {
    private static Logger log = LoggerFactory.getLogger(SubNetAction.class);
    private SubNetBean subNet;

    public SubNetBean getSubNet() {
        return subNet;
    }

    public void setSubNet(SubNetBean subNet) {
        this.subNet = subNet;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Sub Type Name").enableSearch().setbSortable(true));
        return init;
    }

    @Override
    public String save() throws Exception {
        if (subNet.getId() == 0) {
            getBusiness().createLeaf(subNet);
        } else {
            SubNetBean origBean = (SubNetBean) getBusiness().getLeaf(String.valueOf(subNet.getId())).getResponseData();
            SubNetBean newBean = (SubNetBean) origBean.clone();
            BeanUtils.copyProperties(newBean, subNet);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        subNet = (SubNetBean) getBusiness().getLeaf(getId()).getResponseData();
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
        return getRequest().getContextPath() + "/subnet";
    }
}
