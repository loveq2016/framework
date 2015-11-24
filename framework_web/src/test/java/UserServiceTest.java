

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.framework.domain.Pager;
import org.apache.framework.model.User;
import org.apache.framework.model.example.UserExample;
import org.apache.framework.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4  
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //指定Spring的配置文件 /为classpath下
public class UserServiceTest   {
	
	@Resource
	private UserService userService;
	
	/**
	 * 插入 测试，默认会把 create_time,create_user_id设置进去
	 */
	@Test
	public void testInsert() {
		User user = new User();
		user.setUserName("333333");
		user.setPassword("password");
		try {
			System.out.println(user.getId());
			userService.insert(user);
			System.out.println(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id更新，默认会把  update_time 设置进去
	 */
	@Test
	public void testUpdateById() {
		User user = new User();
		user.setId(1L);
		user.setFullName("wwww");
		try {
			userService.updateById(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id删除
	 */
	@Test
	public void testDeleteById() {
		try {
			userService.deleteById(63);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查询
	 */
	@Test
	public void testSelectById() {
		try {
			User user = userService.selectById(1);
			System.out.println(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复杂一点的查询， 支持分页，成第0页开始。 
	 */
	@Test
	public void testSelectByExample() {
		try {
			UserExample example = new UserExample();
			example.setColumn(" id, user_name "); //动态返回列
			example.setGroupByClause(" user_name "); //动态分组
			example.setOrderByClause(" id asc ");  //动态排序
			example.setDistinct(true); //去除重复行， 如果为true, column必须设置值
			
			UserExample.Criteria criteria = example.createCriteria(); //设置where条件
			criteria.andUserNameLike("%aa%"); //like查询
			criteria.andStatusEqualTo("10");
			Pager pager = userService.selectByExample(example, 2, 10);
			System.out.println(pager.getTotal());
			System.out.println(pager.getList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量插入，默认会把 create_time,create_user_id设置进去， 会自动把数据库id设置进去
	 */
	@Test
	public void testBatchInsert() {
		try {
			List<User> users = new ArrayList<>();
			User user = User.initDefaultValue();
			user.setUserName("223324323442fff2fffff");
			user.setPassword("password");
			users.add(user);
			
			user =  User.initDefaultValue();
			user.setUserName("23423423223fffff");
			user.setPassword("password");
			users.add(user);
			
			for (User user2 : users) {
				System.out.println(user2.getId());
			}
			int insertCount = userService.batchInsert(users);
			System.out.println(insertCount);
			
			for (User user2 : users) {
				System.out.println(user2.getId() + "=="+ user2.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量更新，默认会把  update_time 设置进去
	 */
	@Test
	public void testBatchUpdate() {
		User user = new User();
		user.setFullName("李李李"); //设置更新的值
		
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria(); //设置where条件
		List<Long> ids = new ArrayList<>();
		ids.add(76L);
		ids.add(77L);
		criteria.andIdIn(ids);
		userService.updateByExample(user, example);
	}
	
	/**
	 * 批量删除，默认会把  update_time 设置进去
	 */
	@Test
	public void testBatchDelete() {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria(); //设置where条件
		List<Long> ids = new ArrayList<>();
		ids.add(76L);
		ids.add(77L);
		criteria.andIdIn(ids);
		userService.deleteByExample(example);
	}
}