/**
 * 
 */
package actions;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.IpTagBean;
import bl.mysqlbus.IpTagBusiness;

/**
 * @author pli
 * @since $Date:2014-07-16$
 */
public class IpTagAction extends BaseTableAction<IpTagBusiness> {
    private static Logger log = LoggerFactory.getLogger(IpTagAction.class);
    private IpTagBean ipTag;

    public IpTagBean getIpTag() {
        return ipTag;
    }

    public void setIpTag(IpTagBean ipTag) {
        this.ipTag = ipTag;
    }

    /**
   * 
   */
    private static final long serialVersionUID = -5222876000116738224L;

    @Override
    public TableInitVo getTableInit() {
        TableInitVo init = new TableInitVo();
        init.getAoColumns().add(new TableHeaderVo("name", "Tag Name").enableSearch().setbSortable(true));
        return init;
    }

    @Override
    public String save() throws Exception {
        if (ipTag.getId() == 0) {
            getBusiness().createLeaf(ipTag);
        } else {
            IpTagBean origBean = (IpTagBean) getBusiness().getLeaf(String.valueOf(ipTag.getId())).getResponseData();
            IpTagBean newBean = (IpTagBean) origBean.clone();
            BeanUtils.copyProperties(newBean, ipTag);
            getBusiness().updateLeaf(origBean, newBean);
        }
        return SUCCESS;
    }

    @Override
    public String edit() throws Exception {
        ipTag = (IpTagBean) getBusiness().getLeaf(getId()).getResponseData();
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
        return getRequest().getContextPath() + "/iptag";
    }
}
