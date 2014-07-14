package webapps;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import util.NullAwareBeanUtilsBean;
import util.ServerContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class WebInitListener implements ServletContextListener {
    protected static Logger LOG = LoggerFactory.getLogger(WebInitListener.class);

    static {
        //注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new MyDateConvert(), Date.class);
        //non-null will be copied otherwise ignore null value.
        BeanUtilsBean.setInstance(new NullAwareBeanUtilsBean());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("init firstbitgold war");

        LOG.info("init server.properties file");
        ServerContext.init(WebInitListener.class.getResourceAsStream("/server.properties"));
        sce.getServletContext().setAttribute("rootPath", sce.getServletContext().getContextPath());

        LOG.info("init /etc/db.properties file");
        try {
            ServerContext.init(new FileInputStream("/etc/db.properties"));
        } catch (Exception e) {
            LOG.error("Reading file has some exception #0", e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("destroy firstbitgold war");
    }

    static class MyDateConvert implements Converter {

        public Date convert(Class arg0, Object arg1) {
            String p = (String) arg1;
            if (p == null || p.trim().length() == 0) {
                return null;
            }
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.parse(p.trim());
            } catch (Exception e) {
                return null;
            }
        }
    }

}
