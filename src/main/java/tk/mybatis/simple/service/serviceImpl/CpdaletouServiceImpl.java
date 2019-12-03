package tk.mybatis.simple.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.simple.dao.DaletouDao;
import tk.mybatis.simple.model.CpdaleTou;
import tk.mybatis.simple.service.CpdaletouService;

import java.io.Serializable;
import java.util.List;

@Service("daletou")
public class CpdaletouServiceImpl implements CpdaletouService {

    @Autowired
    private DaletouDao  daletouDao;
    @Override
    public <T extends Serializable> List<CpdaleTou> getAll(Class<T> clazz) {
        return daletouDao.getAll(clazz);
    }
}
