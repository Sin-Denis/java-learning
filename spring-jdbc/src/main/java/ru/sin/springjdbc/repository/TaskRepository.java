package ru.sin.springjdbc.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sin.springjdbc.model.Task;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Task> getListTasks() {
        final String sql = "select * from task";

        return jdbcTemplate.query(sql, (rs, rn) -> new Task()
                    .setId(rs.getInt(1))
                    .setName(rs.getString(2))
                    .setDescription(rs.getString(3))
                    .setDifficult(rs.getInt(4)));
    }

    /**
     * Список задач с пагинацией через JdbcTemplate
     *
     * @param page номер страницы
     * @param pageSize размер страницы
     * @return список задач
     */
    public List<Task> getListTasks(int page, int pageSize) {
        int firstRowNum = page * pageSize + 1;
        int lastRowNum = (page + 1) * pageSize;

        final String sql = "select * from " +
                "(select t.*, rownum r " +
                "from (select * from task) t " +
                "where rownum <= ?) " +
                "where r >= ?;";

        PreparedStatementSetter parameterSetter = ps -> {
            ps.setInt(1, lastRowNum);
            ps.setInt(2, firstRowNum);
        };

        RowMapper<Task> rowMapper = (rs, rn) -> new Task()
                .setId(rs.getInt(1))
                .setName(rs.getString(2))
                .setDescription(rs.getString(3))
                .setDifficult(rs.getInt(4));

        return jdbcTemplate.query(sql, parameterSetter, rowMapper);
    }

    public List<Task> getPaginationListTasks(int page, int pageSize) {
        log.info("invoke getPaginationListTasks");
        int firstRowNum = page * pageSize + 1;
        int lastRowNum = (page + 1) * pageSize;

        final String sql = "select * from " +
                "(select t.*, rownum r " +
                "from (select * from task) t " +
                "where rownum <= :lastRowNum) " +
                "where r >= :firstRowNum;";

        var queryParams = Map.of("lastRowNum", lastRowNum, "firstRowNum", firstRowNum);

        RowMapper<Task> rowMapper = (rs, rn) -> new Task()
                .setId(rs.getInt(1))
                .setName(rs.getString(2))
                .setDescription(rs.getString(3))
                .setDifficult(rs.getInt(4));

        return namedParameterJdbcTemplate.query(sql, queryParams, rowMapper);
    }
}
