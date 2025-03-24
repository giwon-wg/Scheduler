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
import java.time.LocalDate;
import java.util.*;

@Repository
public class JdbcTemplateSchedulerRepository implements SchedulerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchedulerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SchedulerResponseDto saveScheduler(Scheduler scheduler) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedules") // ✅ 테이블명 수정
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", scheduler.getTitle());
        parameters.put("contents", scheduler.getContents());
        parameters.put("start_time", scheduler.getStartTime());
        parameters.put("end_time", scheduler.getEndTime());
        parameters.put("created_at", scheduler.getCreatedAt());
        parameters.put("password", scheduler.getPassword());
        parameters.put("name", scheduler.getName());
        parameters.put("user_id", 1); // 테스트용 하드 코딩

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SchedulerResponseDto(
                key.longValue(),
                scheduler.getTitle(),
                scheduler.getContents(),
                scheduler.getStartTime(),
                scheduler.getEndTime(),
                scheduler.getCreatedAt(),
                scheduler.getName()
        );
    }

    @Override
    public List<SchedulerResponseDto> findAllScheduler() {
        String sql = "SELECT * FROM schedules ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToResponseDto(rs));
    }

    @Override
    public Optional<Scheduler> findSchedulerById(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        List<Scheduler> result = jdbcTemplate.query(sql, (rs, rowNum) -> mapToScheduler(rs), id);
        return result.stream().findAny();
    }

    @Override
    public Scheduler findSchedulerByIdOrElseThrow(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        List<Scheduler> result = jdbcTemplate.query(sql, (rs, rowNum) -> mapToScheduler(rs), id);
        return result.stream().findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다"));
    }

    @Override
    public int updateScheduler(Long id, String title, String contents) {
        String sql = "UPDATE schedules SET title = ?, contents = ?, created_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql, title, contents, new Date(), id);
    }

    @Override
    public int deleteScheduler(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulerWithFilter(String name, LocalDate date) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedules WHERE 1 = 1");
        List<Object> params = new ArrayList<>();

        if(name != null && !name.isEmpty()){
            sql.append("AND name = ?");
            params.add(name);
        }

        if(date != null){
            sql.append("AND DATE(created_at) = ? ");
            params.add(java.sql.Date.valueOf(date));
        }

        sql.append("ORDER BY created_at DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> mapToResponseDto(rs));
    }

    // Response DTO 매핑
    private SchedulerResponseDto mapToResponseDto(ResultSet rs) throws SQLException {
        return new SchedulerResponseDto(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("start_time").toLocalDateTime(),
                rs.getTimestamp("end_time").toLocalDateTime(),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("name")
        );
    }

    // Entity 매핑
    private Scheduler mapToScheduler(ResultSet rs) throws SQLException {
        return new Scheduler(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("start_time").toLocalDateTime(),
                rs.getTimestamp("end_time").toLocalDateTime(),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("password"),
                rs.getString("name")
        );
    }
}
