package services.db;

import entities.db.DBTable;
import entities.db.DBTableColumn;
import javafx.scene.control.TableColumn;
import services.utils.BaseEntityService;
import utility.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBTableColumnsService extends BaseEntityService implements DBTableColumnsServiceI {
    @Override
    public List<TableColumn> getTableColumns(String referenceName) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("referenceName", referenceName);
        List<DBTable> z01Details = executeQuery(Const.DB_TABLE_SELECT_ALL_QUERY_WITH_WHERE + "z01ReferenceName:referenceName", queryParams);

        if (z01Details.size() > 0) {
            return createTableColumns(getDBTableColumns(z01Details.get(0).getZ01Id()));
        }
        return null;
    }

    private List<DBTableColumn> getDBTableColumns(int z01Id) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("z01Id", String.valueOf(z01Id));
        List<DBTableColumn> z02Details = executeQuery(Const.DB_TABLE_COLUMNS_SELECT_ALL_QUERY_WITH_WHERE + "z02IdZ01:z01Id", queryParams);

        if (z02Details.size() > 0) {
            return z02Details;
        }
        return null;

    }

    private List<TableColumn> createTableColumns(List<DBTableColumn> z02Columns) {
        List<TableColumn> tableColumns = new ArrayList<TableColumn>();
        for (DBTableColumn z02Column : z02Columns
        ) {
            TableColumn tableColumn = new TableColumn(z02Column.getZ02ColumnDisplayName());
            tableColumn.setMaxWidth(z02Column.getZ02Length());
            tableColumn.setMinWidth(z02Column.getZ02Length());
            tableColumn.setPrefWidth(z02Column.getZ02Length());
            tableColumns.add(tableColumn);
        }
        return tableColumns;
    }
}
