package com.example.scheduler.repository;

import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateSchedulerRepository implements SchedulerRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchedulerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SchedulerResponseDto saveScheduler(Scheduler scheduler) {

        //INSERT Query 직접 작성하지 않아도 됨
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("password", scheduler.getPassword());
        parameters.put("name", scheduler.getName());
        parameters.put("title", scheduler.getTitle());
        parameters.put("contents", scheduler.getContents());
        parameters.put("time", scheduler.getTime());

        //저장 후 생성된 key값을 Number 타입으로 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SchedulerResponseDto(key.longValue(), scheduler.getPassword(), scheduler.getName(), scheduler.getTitle(), scheduler.getContents(), scheduler.getTime());
    }

    @Override
    public List<SchedulerResponseDto> findAllScheduler() {
        return jdbcTemplate.query("select * from schedule", schedulerRowMapper());
    }

    @Override
    public Optional<Scheduler> findSchedulerById(Long id) {
        List<Scheduler> result = jdbcTemplate.query("select * from schedule where id = ?", schedulerRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public Scheduler findSchedulerByIdOrElseThrow(Long id) {
        List<Scheduler> result = jdbcTemplate.query("select * from schedule where id = ?", schedulerRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다"));
    }

    @Override
    public int updateScheduler(Long id, String title, String contents) {

        return jdbcTemplate.update("update Schedule set title = ?, contents = ? where id = ?", title, contents, id);
    }

    @Override
    public int deleteScheduler(Long id) {
        return jdbcTemplate.update("delete from Schedule where id = ?", id);

    }

    private RowMapper<SchedulerResponseDto> schedulerRowMapper(){

        return new RowMapper<SchedulerResponseDto>() {
            @Override
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulerResponseDto(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("time").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Scheduler> schedulerRowMapperV2(){
        return new RowMapper<Scheduler>() {
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("time").toLocalDateTime()
                );
            }
        };
    }

}
