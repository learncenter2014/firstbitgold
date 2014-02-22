package bl.beans;

import java.sql.Timestamp;

import bl.common.BeanContext;

public class Bean implements BeanContext, Cloneable {
    long id;

    Timestamp createTime;
    Timestamp modifyTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * Clone (create a deep copy of) this instance.
     * 
     * @return A clone of this instance.
     */
    public Object clone() {
        // It's necessary to provide a default clone() method in this base class in
        // order to allow public access to it, because Object.clone() is protected.
        try {
            return super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw new RuntimeException("Error cloning in Bean", cnse);
        }
    }
}
