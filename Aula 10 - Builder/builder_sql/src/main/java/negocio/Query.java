package negocio;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private List<String> columns;
    private String table;
    private List<String> tablesJoin;
    private String where;
    private String orderBy;

    private Query() {
        this.columns = new ArrayList<String>();
        this.tablesJoin = new ArrayList<String>();

    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    public List<String> getTablesJoin() {
        return tablesJoin;
    }

    public void setTablesJoin(List<String> tablesJoin) {
        this.tablesJoin = tablesJoin;
    }

    @Override
    public String toString() {
        String query = "SELECT ";
        if (this.columns.size() > 0) {
            for (String column : columns) {
                query += column + ",";
            }
            query += " FROM ";
            query = query.replace(", FROM ", " FROM ");
        } else {
            query += " *  FROM ";
        }
        if (this.table != null && !this.table.isEmpty()) {
            query += this.table;
        } else {
            throw new IllegalArgumentException("Without TABLE!");
        }
        if (this.tablesJoin.size() > 0) {
            for (int i = 0; i < this.tablesJoin.size(); i++) {
                if (i == 0) {
                    query += " JOIN " + this.tablesJoin.get(i) + " ON (" + this.table + ".id = " + this.tablesJoin.get(i) + "."
                            + table + "_id)";
                } else {
                    query += " JOIN " + this.tablesJoin.get(i) + " ON (" + this.tablesJoin.get(i-1) + ".id = "
                            + this.tablesJoin.get(i) + "." + this.tablesJoin.get(i-1) + "_id)";

                }

            }
        } else {
            
        }
        if (this.where != null && !this.where.isEmpty()) {
            query += " where " + this.where;
        }

        if (this.orderBy != null && !this.orderBy.isEmpty()) {
            query += " order by " + this.orderBy;
        }
        return query;

    }

    

    public static class QueryBuilder {

        private List<String> columns;
        private String table;
        private String where;
        private String orderBy;
        private List<String> tablesJoin;

        public QueryBuilder(String table) {
            this.table = table;
            this.columns = new ArrayList<String>();
            this.tablesJoin =  new ArrayList<String>();
        }

        public QueryBuilder columns(List<String> columns) {
            this.columns = columns;
            return this;
        }

         public QueryBuilder tablesJoin(List<String> tablesJoin) {
            this.tablesJoin = tablesJoin;
            return this;
        }

        public QueryBuilder where(String where) {
            this.where = where;
            return this;
        }

        public QueryBuilder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Query build() {
            Query query = new Query();
            query.setTable(this.table);
            query.setTablesJoin(this.tablesJoin);
            query.setColumns(this.columns);
            query.setWhere(this.where);
            query.setOrderBy(orderBy);
            return query;
        }
    }




}
