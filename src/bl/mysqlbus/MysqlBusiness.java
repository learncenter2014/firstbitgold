package bl.mysqlbus;

import bl.common.*;
import vo.table.TableDataVo;
import vo.table.TableQueryVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by pli on 14-7-14.
 */
public class MysqlBusiness<F, L> implements BusinessInterface,
        TableBusinessInterface {

    @Override
    public BeanContext constructLeafBean() {
        return null;
    }

    @Override
    public BusinessResult createLeaf(BeanContext genLeafBean) {
        return null;
    }

    @Override
    public BusinessResult getLeaf(String objectId) {
        return null;
    }

    @Override
    public BusinessResult getLeafByName(String name) {
        return null;
    }

    @Override
    public BusinessResult deleteLeaf(String objectId) {
        return null;
    }

    @Override
    public BusinessResult updateLeaf(BeanContext origBean, BeanContext newBean) {
        return null;
    }

    @Override
    public BusinessResult getAllLeaves() {
        return null;
    }

    @Override
    public void deleteByCondition(Map filter) {

    }

    @Override
    public List queryDataByCondition(Map filter, Set sorted, SpecPaginationContext spc) {
        return null;
    }

    @Override
    public List queryDataByCondition(Map filter, Set sorted) {
        return null;
    }

    @Override
    public TableDataVo query(TableQueryVo queryParam) {
        return null;
    }

    @Override
    public long getCount(TableQueryVo queryParam) {
        return 0;
    }
}
