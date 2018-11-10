package com.noobz.dao;

import com.noobz.domain.RelationShipExample;
import com.noobz.domain.RelationShipKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationShipMapper {
    long countByExample(RelationShipExample example);

    int deleteByExample(RelationShipExample example);

    int deleteByPrimaryKey(RelationShipKey key);

    int insert(RelationShipKey record);

    int insertSelective(RelationShipKey record);

    List<RelationShipKey> selectByExample(RelationShipExample example);

    int updateByExampleSelective(@Param("record") RelationShipKey record, @Param("example") RelationShipExample example);

    int updateByExample(@Param("record") RelationShipKey record, @Param("example") RelationShipExample example);
}