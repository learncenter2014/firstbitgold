package bl.mysqlbus;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.table.TableDataVo;
import vo.table.TableQueryVo;
import bl.common.BeanContext;
import bl.common.BusinessInterface;
import bl.common.BusinessResult;
import bl.common.SpecPaginationContext;
import bl.common.TableBusinessInterface;
import dao.MysqlHibernateDao;

/**
 * Created by pli on 14-7-14. <b>F: should be FolderBean L: should be
 * LeafBean</b><br>
 * In this business, keep dao tier in here, in the further, please extract this
 * dao to a separate tier.
 */
public class MysqlBusiness<F, L> implements BusinessInterface<BeanContext, BeanContext>, TableBusinessInterface {
    protected Class<?> cls;

    @Override
    public BeanContext constructLeafBean() {
        return null;
    }

    @Override
    public BusinessResult createLeaf(BeanContext genLeafBean) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(genLeafBean);
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        // TODO deal with return value.
        return null;
    }

    @Override
    public BusinessResult getLeaf(String objectId) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        BusinessResult br = new BusinessResult();
        try {
            String hql = "from " + this.cls.getSimpleName() + " as p where p.Id=?";
            Query query = session.createQuery(hql);
            query.setLong(0, Long.valueOf(objectId));
            br.setResponseData(query.uniqueResult());
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        return br;
    }

    @Override
    public BusinessResult getLeafByName(String name) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        BusinessResult br = new BusinessResult();
        try {
            String hql = "from " + this.cls.getSimpleName() + " as p where p.Name=?";
            Query query = session.createQuery(hql);
            query.setString(0, name);
            br.setResponseData(query.uniqueResult());
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        // TODO deal with return value.
        return br;
    }

    @Override
    public BusinessResult deleteLeaf(String objectId) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        try {
            String hql = "delete " + this.cls.getSimpleName() + " as p where p.Id=?";
            Query query = session.createQuery(hql);
            query.setLong(0, Long.valueOf(objectId));
            query.executeUpdate();
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        // TODO deal with return value.
        return null;
    }

    @Override
    public BusinessResult updateLeaf(BeanContext origBean, BeanContext newBean) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(newBean);
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        // TODO deal with return value.
        return null;
    }

    @Override
    public BusinessResult getAllLeaves() {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        BusinessResult br = new BusinessResult();
        try {
            String hql = "from " + this.cls.getSimpleName() + " as p";
            Query query = session.createQuery(hql);
            br.setResponseData(query.list());
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        return br;
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
        TableDataVo dataTable = new TableDataVo();
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        try {
            dataTable.setsEcho(queryParam.getSEcho());
            Query query = session.createQuery("from " + this.cls.getSimpleName());
            query.setFirstResult(queryParam.getIDisplayStart());
            query.setMaxResults(queryParam.getIDisplayLength());
            dataTable.setAaData(query.list());
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        return dataTable;
    }

    @Override
    public long getCount(TableQueryVo queryParam) {
        Session session = MysqlHibernateDao.currentSession();
        Transaction tx = session.beginTransaction();
        long result = 0;
        try {
            Query query = session.createQuery("select count(*) from " + this.cls.getSimpleName());
            result = ((Long) query.uniqueResult()).longValue();
            tx.commit();
        } catch (Exception he) {
            tx.rollback();
        }
        return result;
    }
}
