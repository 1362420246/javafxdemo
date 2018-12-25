package com.qbk.mapper;


import com.qbk.entity.DbManage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbManageMapper {

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    int deleteByPrimaryKey(String key);

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    int insert(DbManage record);

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    int insertSelective(DbManage record);

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    DbManage selectByPrimaryKey(String key);

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    int updateByPrimaryKeySelective(DbManage record);

    /**
     *
     * @mbg.generated Thu Dec 13 17:39:25 CST 2018
     */
    int updateByPrimaryKey(DbManage record);

    List<DbManage> selectSourceList();


    int deleteDbByList(List<String> list);
}