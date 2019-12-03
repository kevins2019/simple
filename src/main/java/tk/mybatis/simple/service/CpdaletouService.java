package tk.mybatis.simple.service;

import tk.mybatis.simple.model.CpdaleTou;

import java.io.Serializable;
import java.util.List;

public interface CpdaletouService {

    public <T extends Serializable> List<CpdaleTou> getAll(Class<T> clazz);
}
