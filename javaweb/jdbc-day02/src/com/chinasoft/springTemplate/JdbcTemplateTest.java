package com.chinasoft.springTemplate;

import com.chinasoft.pojo.Emp;
import com.chinasoft.utils.JDBCUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 使用jdbcTemplate对Dept表做 CURD 操作
 *
 *   将查询结果 分别封装为map集合进行打印输出
 *   将查询出来的所有结果  封装到list集合打印输出
 *
 *   求出部门编号为3的部门全部信息并打印输出
 *
 *   求出emo表中的年工资最大值  封装后打印输出
 */
public class JdbcTemplateTest {
    JdbcTemplate jdbcTemplate = null;

    @Before
    public void init(){
        jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    }

    /* 增加一条信息*/
    @Test
    public void f1(){
        String sql = "insert into dept values(?,?,?)";
        int insert = jdbcTemplate.update(sql,5,"混子部门","荆州");
        System.out.println(insert);
    }
    /*修改一条信息*/
    @Test
    public void f2(){
        String sql = "update dept set dname = ? where id = ?";
        int update = jdbcTemplate.update(sql,  "武装部",5);
        System.out.println(update);
    }
    /*删除一条信息*/
    @Test
    public void f3(){
        String sql = "delete from dept where id = ?";
        int delete = jdbcTemplate.update(sql, 5);
        System.out.println(delete);
    }
    /*求出部门编号为3的部门全部信息并打印输出*/
    @Test
    public void f4(){
        String sql = "select * from dept where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 3);
        System.out.println(map);
    }
    /*求出emo表中的年工资最大值  封装后打印输出*/
    @Test
    public void f5(){
        String sql = "select max((ifnull(salary,0)+ifnull(bonus,0))*12) from emp";
        Double aDouble = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println("最大年工资为： "+aDouble);
    }
    /*查询dept表id>1 的所有信息，封装到list集合并打印输出*/
    @Test
    public void f6(){
        String sql = "select * from dept where id > ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, 1);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }
    /*查询demp表id>1010 的所有信息，封装Emp对象并添加到list集合后打印输出*/
//    @Test
//    public void f7(){
//        String sql = "select * from emp where id > ?";
//        List<Emp> query = jdbcTemplate.query(sql,1010, new BeanPropertyRowMapper<Emp>(Emp.class));
//        for (Emp emp : query) {
//            System.out.println(emp);
//        }
//    }

}
