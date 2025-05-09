package com.cuzz.rookiecrates.mapper;

import com.cuzz.rookiecrates.model.DCrateLoot;
import com.cuzz.rookiecrates.model.DCrateLootExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DCrateLootMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int countByExample(DCrateLootExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int deleteByExample(DCrateLootExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int insert(DCrateLoot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int insertSelective(DCrateLoot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    List<DCrateLoot> selectByExample(DCrateLootExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    DCrateLoot selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int updateByExampleSelective(@Param("record") DCrateLoot record, @Param("example") DCrateLootExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int updateByExample(@Param("record") DCrateLoot record, @Param("example") DCrateLootExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int updateByPrimaryKeySelective(DCrateLoot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rookie_crate_loot
     *
     * @mbggenerated Wed May 07 18:21:10 CST 2025
     */
    int updateByPrimaryKey(DCrateLoot record);
}