package com.eagle.ssm.dao;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Created by Administrator on 2017/4/21.
 */
public class TestSqlProvider {
    private static final String TABLE_NAME = "test";

    public String getSql(Map<String, Object> parameters) {
        String uid = (String) parameters.get("id");
        BEGIN();
        SELECT("user_id, user_name");
        FROM(TABLE_NAME);
        if (uid != null) {
            WHERE("test_id = #{id, javaType=string,jdbcType=VARCHAR}");
        }
        return SQL();
    }

    /**
     * get all users sql script
     * @return
     */
    public String getAllSql() {
        BEGIN();
        SELECT("user_id, user_name");
        FROM(TABLE_NAME);
        return SQL();
    }

    /**
     *get list by name sql script
     * @param parameters
     * @return
     */
    public String getListSql(Map<String, Object> parameters) {
        String tTest = (String) parameters.get("user");
        BEGIN();
        SELECT("user_id, user_name");
        FROM(TABLE_NAME);
        if (tTest != null) {
            WHERE("name like #{name, javaType=string,jdbcType=VARCHAR}");
        }
        return SQL();
    }

    /**
     * insert a test sql script.
     *
     * @return
     */
    public String insertSql() {
        BEGIN();
        INSERT_INTO(TABLE_NAME);
        VALUES("test_id", "#{testBean.id,javaType=string,jdbcType=VARCHAR}");
        VALUES("test_text", "#{testBean.testText,javaType=string,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * update a test sql script.
     *
     * @return
     */
    public String updateSql() {
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("test_text = #{testBean.testText,javaType=string,jdbcType=VARCHAR}");
        WHERE("test_id = #{testBean.id,javaType=string,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * delete a test sql script.
     *
     * @return
     */
    public String deleteSql() {
        BEGIN();
        DELETE_FROM(TABLE_NAME);
        WHERE("test_id = #{id,javaType=string,jdbcType=VARCHAR}");
        return SQL();
    }
}
