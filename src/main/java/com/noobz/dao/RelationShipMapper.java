package com.noobz.dao;

import com.noobz.domain.RelationShipExample;
import com.noobz.domain.RelationShip;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationShipMapper {
    long countByExample(RelationShipExample example);

    int deleteByExample(RelationShipExample example);

    int deleteByPrimaryKey(RelationShip key);

    int insert(RelationShip record);

    int insertSelective(RelationShip record);

    List<RelationShip> selectByExample(RelationShipExample example);

    int updateByExampleSelective(@Param("record") RelationShip record, @Param("example") RelationShipExample example);

    int updateByExample(@Param("record") RelationShip record, @Param("example") RelationShipExample example);
}