package com.tz.bms.ssxjl.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.dao.ISSXJLDao;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class SSXJLDaoImpl implements ISSXJLDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Province> selectAllProvinces() {
		String sql = "select id,code,name from province";
		List<Province> provinces = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Province province = new Province();
			province.setProvinceId(((BigDecimal) row.get("id")).intValue());
			province.setProvinceCode((String) row.get("code"));
			province.setProvinceName((String) row.get("name"));
			provinces.add(province);
		}
		return provinces;
	}

	@Override
	public List<City> selectCityByProvinceCode(String provinceCode) {
		String sql = "SELECT ID,CODE,NAME FROM CITY WHERE PROVINCEID = ?";
		List<City> cities= new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,provinceCode);
		for (Map<String, Object> row : rows) {
			City city = new City();
			city.setCityId(((BigDecimal) row.get("id")).intValue());
			city.setCityCode((String) row.get("code"));
			city.setCityName((String) row.get("name"));
			cities.add(city);
		}
		return cities;
	}

	@Override
	public List<Area> selectAreaByCityCode(final String cityCode) {
		 String sql = "SELECT ID,CODE,NAME FROM AREA WHERE CITYID = ?";
	        List<Area> areas = new ArrayList<>();
	        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, cityCode);
	        for (Map<String, Object> row : rows) {
	            Area area = new Area();
	            area.setAreaId(((BigDecimal) row.get("id")).intValue());
	            area.setAreaCode((String) row.get("code"));
	            area.setAreaName((String) row.get("name"));
	            areas.add(area);
	        }
	        return areas;
	}

	@Override
	public Province selectProvinceByCode(String provinceCode) {
		String sql = "SELECT ID,CODE,NAME FROM PROVINCE WHERE CODE = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{provinceCode},new RowMapper<Province>() {
			@Override
			public Province mapRow(ResultSet resultSet, int i) throws SQLException {
				Province province=new Province();
				province.setProvinceId(resultSet.getInt("id"));
				province.setProvinceCode(resultSet.getString("code"));
				province.setProvinceName(resultSet.getString("name"));
				return province;
			}
		});
	}

	@Override
	public City selectCityByCode(String cityCode) {
		String sql = "SELECT ID,CODE,NAME FROM CITY WHERE CODE = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{cityCode},new RowMapper<City>() {
			@Override
			public City mapRow(ResultSet resultSet, int i) throws SQLException {
				City city=new City();
				city.setCityId(resultSet.getInt("id"));
				city.setCityCode(resultSet.getString("code"));
				city.setCityName(resultSet.getString("name"));
				return city;
			}
		});
	}

	@Override
	public Area selectAreaByCode(String areaCode) {
		String sql = "SELECT ID,CODE,NAME FROM AREA WHERE CODE = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{areaCode},new RowMapper<Area>() {
			@Override
			public Area mapRow(ResultSet resultSet, int i) throws SQLException {
				Area area=new Area();
				area.setAreaId(resultSet.getInt("id"));
				area.setAreaCode(resultSet.getString("code"));
				area.setAreaName(resultSet.getString("name"));
				return area;
			}
		});
	}

}
