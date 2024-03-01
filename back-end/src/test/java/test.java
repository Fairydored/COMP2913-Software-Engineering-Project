

import java.io.InputStream;
import com.example.helloworld.model.User;
import com.example.helloworld.Mapper.userMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        String resource = "Mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            userMapper mapper = sqlSession.getMapper(userMapper.class);

            // 创建一个新的 User 对象
            User newUser = new User();
            newUser.setUsername("tingwoshuo");
            newUser.setPassword("xiexieni");

            // 插入新用户到数据库
            mapper.insertUser(newUser);
            sqlSession.commit(); // 确保提交事务以保存更改

            // 检索并打印所有用户信息以验证插入操作
            List<User> selectall = mapper.selectall();
            System.out.println(selectall);
        } finally {
            sqlSession.close();
        }
    }
}
