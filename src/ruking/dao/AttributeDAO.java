package ruking.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ruking.db.DataSourceFactory;
import ruking.db.DbUtil;
import ruking.db.MDTMySQLRowMapper;
import ruking.db.QueryRunner;
import ruking.db.TransRunner;
import ruking.dto.AttributeDTO;
import ruking.utils.Util;

public class AttributeDAO {
	public String hostName;
	public String dbName;// = "zkm0m1_db";
	public String password;// = "pjsong";
	public String dbUser;

	public AttributeDAO(String hostName, String dbName, String dbUser,
			String password) {
		super();
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.password = password;
	}

	public List<Map> getAllAttributes() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(
				hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM attributes";
		return runner.query(sql);
	}

	public List<AttributeDTO> getAttributesByProductId(String id)
			throws SQLException {
		List<AttributeDTO> attr = new ArrayList<AttributeDTO>();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(
				hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM attributes WHERE productID = "+ DbUtil.escSql(id);
		List<Map> m = runner.query(sql);
		if (m == null || m.size() == 0)
			return null;
		return attr;
	}

	public AttributeDTO getAttributeByID(String id) throws SQLException {
		AttributeDTO u = new AttributeDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM attributes WHERE ID = " + DbUtil.escSql(id);
		Map m = runner.queryForMap(sql);
		if (m == null)
			return null;
		else {
			u.setId(((Integer) m.get("ID")).toString());
			u.setProductId((String) m.get("ProductID"));
			u.setAttrName((String) m.get("AttrName"));
			u.setAttrValue((String) m.get("AttrValue"));
		}
		return u;
	}

	public AttributeDTO insertProduct(AttributeDTO p) throws SQLException {
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(
				hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "insert into attributes(ProductID,AttrName,AttrValue";
		sql = sql + ") values (" + DbUtil.escSql(p.getProductId().trim()) + ","
				+ DbUtil.escSql(p.getAttrName().trim()) + ","
				+ DbUtil.escSql(p.getAttrValue());
		sql = sql + ");";
		runner.update(sql);
		Map m = runner.queryForMap("select ID from attributes where ProductID="
				+ DbUtil.escSql(p.getProductId()));
		p.setId(((Integer) m.get("ID")).toString());
		return p;
	}

	public void updateAttribute(AttributeDTO p) throws SQLException {
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "update attributes set ProductID="
				+ DbUtil.escSql(p.getProductId()) + ",AttrName="
				+ DbUtil.escSql(p.getAttrName());
		sql += ",AttrValue=" + DbUtil.escSql(p.getAttrValue())+ " where ID="+DbUtil.escSql(p.getId());
		runner.update(sql);
	}

	public void deleteProduct(String id) throws SQLException {
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(
				hostName, dbName, dbUser, password), new MDTMySQLRowMapper());
		String sql = "delete FROM attributes WHERE ID = " + DbUtil.escSql(id);
		runner.update(sql);
	}
}
