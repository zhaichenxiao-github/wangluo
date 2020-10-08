package com.example.wangluo.utils;

import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.GreenDaoBean;
import com.example.wangluo.dao.DaoMaster;
import com.example.wangluo.dao.DaoSession;
import com.example.wangluo.dao.GreenDaoBeanDao;

import java.util.List;

public class DbHelper {
    private static volatile DbHelper instance;
    private final GreenDaoBeanDao greenDaoBeanDao;

    public static DbHelper getInstance(){
        if (instance==null){
            synchronized (DbHelper.class){
                if (instance==null){
                    instance=new DbHelper();
                }
            }
        }
        return instance;
    }
    public DbHelper(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(ShopApplication.getApp(), "111.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        greenDaoBeanDao = daoSession.getGreenDaoBeanDao();
    }
    private boolean isHashed(GreenDaoBean greenDaoBean){
        List<GreenDaoBean> list = greenDaoBeanDao.queryBuilder().where(GreenDaoBeanDao.Properties.GoodsDesc.eq(greenDaoBean.getGoodsDesc()) ).list();
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
    public long insert(GreenDaoBean greenDaoBean){
        if(!isHashed(greenDaoBean)){
            long l = greenDaoBeanDao.insertOrReplace(greenDaoBean);
            return l;
        }
        return -1;
    }
    public boolean delete(GreenDaoBean greenDaoBean){
        if(isHashed(greenDaoBean)){
            greenDaoBeanDao.delete(greenDaoBean);
            return true;
        }
        return false;
    }
    public List<GreenDaoBean> queryAll(){
        List<GreenDaoBean> collectBeans = greenDaoBeanDao.loadAll();
        return collectBeans;
    }
    public boolean update(GreenDaoBean greenDaoBean){
        if(isHashed(greenDaoBean)){
            greenDaoBeanDao.update(greenDaoBean);
            return true;
        }
        return false;
    }
}
