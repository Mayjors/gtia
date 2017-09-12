package com.eagle.ssm.dao;

import com.eagle.ssm.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
//定义在该命名空间内允许使用内置缓存,最大值为512个对象引用,读写默认是开启的,缓存内省刷新时间为默认的3600000毫秒,写策略是拷贝整个对象镜像到
//全新堆(如同CopyOnWriteList)因此线程安全
@CacheNamespace(size = 512)
public interface UserMapper {

    /**
     * get test bean by UID
     *
     * @param id
     * @return
     */
    //提供查询的SQL语句,如果你不用这个注解,你也可以直接使用@Select("select * fron ..")，把查询SQL抽取到一个类里面,方便管理,同时复杂的SQL也容易操作
    //type=TestSqlProvider.class就是存放SQL语句的类, method 表示get接口方法需要到TestSqlProvider类的getSql方法中获取SQL语句.
    @SelectProvider(type = TestSqlProvider.class, method = "getSql")
    //一些查询的选项开关,userCache=true 表示本次查询结果被缓存以提高下次查询速度, flushCache表示下次查询时不刷新缓存,timeout表示查询结果缓存10000秒
    @Options(useCache = true, flushCache = false, timeout = 10000)
    /**
     * 表示sql查询返回的结果集，@Results是以@Result为元素的数组，@Result表示单条属性-字段的映射关系，
     * 如：@Result(id = true, property = "id", column = "test_id", javaType = String.class, jdbcType = JdbcType.VARCHAR)
     * 可以简写为：@Result(id = true, property = "id", column = "test_id")，id = true表示这个test_id字段是个PK，查询时mybatis会给予必要的优化，
     * 应该说数组中所有的@Result组成了单个记录的映射关系，而@Results则单个记录的集合。另外还有一个非常重要的注解@ResultMap也和@Results差不多，到时会讲到。
     */
    @Results(value = {
            @Result(id = true, property = "id", column = "test_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "testText", column = "test_text", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    //@Param("id") ：全局限定别名，定义查询参数在sql语句中的位置不再是顺序下标0,1,2,3....的形式，而是对应名称，该名称就在这里定义。
    public User get(@Param("id") String id);

    /**
     * get list by xxx
     *
     * @param name
     * @return
     */
    @SelectProvider(type = TestSqlProvider.class, method = "getListSql")
    @Options(useCache = true, flushCache = false, timeout = 10000)
    /**
     * 重要的注解,可以解决浮渣的映射关系,包括resultMap嵌套,鉴别器discriminator..注意一旦你启用该注解,你将不得不在你的映射文件中配置你的resultMap
     * 而value="getList"即为映射文件中的resultMap ID(注意此处的value="getList",必须是在映射文件中指定命名空间路径.)
     * @ResultMap 在某些简单场合可以用@Result代替,但是复杂查询,比如联合、嵌套查询@ResultMap就会显得解耦方便更容易管理
     */
    @ResultMap(value = "getListSql")
//    @ResultMap(value = "getList")
    public List<User> getList(@Param("name") String name);


    /**
     * insert a user into database
     *
     * @param user
     */
    @InsertProvider(type = TestSqlProvider.class, method = "insertSql")
    //Options(flushCache=true, timeout=20000)对于需要更新数据库的操作,需要重新刷新缓存flushCache=true是缓存同步.
    @Options(flushCache = true, timeout = 20000)
    public void insert(@Param("User") User user);

    /**
     * update a user with database
     *
     * @param user
     */
    @UpdateProvider(type = TestSqlProvider.class, method = "updateSql")
    @Options(flushCache = true, timeout = 20000)
    public void update(@Param("User") User user);

    /**
     * delete a user by id
     *
     * @param id
     */
    @DeleteProvider(type = TestSqlProvider.class, method = "deleteSql")
    @Options(flushCache = true, timeout = 20000)
    public void delete(@Param("id") String id);
}
