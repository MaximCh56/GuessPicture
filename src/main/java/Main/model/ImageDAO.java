package Main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maxim on 17.10.2016.
 */
public class ImageDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public void insert(ImageDataSet imageDataSet) {
        String sql = "insert into IMAGES (NAME, URL) VALUES (:NAME, :URL)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("NAME", imageDataSet.getName());
        params.addValue("URL", imageDataSet.getURL());
        jdbcTemplate.update(sql, params);
    }
    public void delete(int id) {
        String sql = "delete from IMAGES where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
    public ImageDataSet getImageDataSetByID(int id) {
        String sql = "select * from IMAGES where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new IMAGERowMapper());
    }
    public List<ImageDataSet> getAllImageDataSet() {
        String sql = "select * from IMAGES";
        return jdbcTemplate.query(sql, new IMAGERowMapper());
    }
    private static final class IMAGERowMapper implements RowMapper<ImageDataSet> {

        @Override
        public ImageDataSet mapRow(ResultSet rs, int rowNum) throws SQLException {
            ImageDataSet imageDataSet = new ImageDataSet();
            imageDataSet.setId(rs.getInt("ID"));
            imageDataSet.setName(rs.getString("NAME"));
            imageDataSet.setURL(rs.getString("URL"));
            return imageDataSet;
        }

    }
}
