package tk.mybatis.simple.dao;

import tk.mybatis.simple.model.CpdaleTou;

import java.io.Serializable;
import java.util.List;

public interface DaletouDao {

    /**
     * 查询所有数据
     *
     */
    public <T extends Serializable> List<CpdaleTou>  getAll(Class<T> clazz);
}
