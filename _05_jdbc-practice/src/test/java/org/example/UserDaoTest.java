package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    // @Test 코드 실행하기 앞서 수행해야 할 작업을 작성하는 부분
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("wizard","password","name","email"));

        User user = userDao.findByUserId("wizard");
        assertThat(user).isEqualTo(new User("wizard","password","name","email"));
    }
}
