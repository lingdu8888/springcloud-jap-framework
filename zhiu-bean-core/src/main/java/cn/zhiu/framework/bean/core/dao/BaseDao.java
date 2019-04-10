package cn.zhiu.framework.bean.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    /**
     * 锁行读写的方式通过ID获取对象
     *
     * @param id 对象ID
     *
     * @return 对应的对象
     */
    T findOneForUpdate(ID id);

}
