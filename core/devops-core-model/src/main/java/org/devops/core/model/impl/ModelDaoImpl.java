package org.devops.core.model.impl;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import org.devops.core.model.interfaces.BaseModelDao;

import java.sql.*;
import java.util.*;

@Slf4j
public class ModelDaoImpl implements BaseModelDao{

	private DataSource dataSource;
	
	public ModelDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, Object>> select(String sql) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			Set<String> labels = new HashSet<String>();
			for(int i=1;i<=count;i++) {
				labels.add(md.getColumnLabel(i));
			}
			while(rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for(String label : labels) {
					row.put(label, rs.getObject(label));
				}
				result.add(row);
			}
			ps.close();
		} catch (SQLException e) {
			log.error("[ModelDaoImpl.select.exception]",e);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public long insert(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		long result = 0;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			log.error("[ModelDaoImpl.insert.exception]",e);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public long update(String sql) {
		Connection conn = null;
		long result = 0;
		Boolean autoCommit = null;
		Statement statement = null;
		try{
			conn = dataSource.getConnection();
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			String ss[] = sql.replace(";UPDATE", ";UPDATEUPDATE").split(";UPDATE");
			for(String s : ss) {
				statement.addBatch(s);
			}
			int affectRows[] = statement.executeBatch();
			for(int r : affectRows) {
				result+=r;
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				if(conn != null) {
					conn.rollback();
					if(autoCommit != null) {
						conn.setAutoCommit(autoCommit);
					}
				}
			} catch (SQLException e1) {
			}
			
			log.error("[ModelDaoImpl.update.exception]",e);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public long delete(String sql) {
		long result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			log.error("[ModelDaoImpl.delete.exception]",e);
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}
}
