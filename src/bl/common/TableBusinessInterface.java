/**
 * @author pli
 * @since $Date:2014-07-16$ Date: Mar 1, 2014
 */
package bl.common;

import vo.table.TableDataVo;
import vo.table.TableQueryVo;

/**
 * @author pli
 *
 */
public interface TableBusinessInterface {
  
  /**
   * 
   * @param start
   * @param limt
   * @return
   */
  public TableDataVo query(TableQueryVo queryParam);

  public long getCount(TableQueryVo queryParam);
}
