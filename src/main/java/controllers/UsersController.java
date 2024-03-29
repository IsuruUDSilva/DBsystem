package controllers;

import entities.Employee;
import entities.EmployeeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.EmployeeService;
import services.EmployeeServiceI;
import services.db.DBTableColumnsService;
import services.db.DBTableColumnsServiceI;
import utility.Const;
import utility.DialogMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersController extends BaseController {

    private final EmployeeServiceI employeeServiceI = new EmployeeService();
    private DBTableColumnsServiceI dbTableColumnsServiceI = new DBTableColumnsService();
    private Integer selectedEmployee;
    @FXML
    private TableView<EmployeeView> userTable;
    @FXML
    private AnchorPane userAnchorPane;

    public UsersController() {
    }

    public Integer getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Integer selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserData();
        createTable();
    }

    public DBTableColumnsServiceI getDbTableColumnsServiceI() {
        return dbTableColumnsServiceI;
    }

    public void setDbTableColumnsServiceI(DBTableColumnsServiceI dbTableColumnsServiceI) {
        this.dbTableColumnsServiceI = dbTableColumnsServiceI;
    }

    public void getUserData() {
        Employee activeEmployee = getActiveUser();
        List<EmployeeView> employees = employeeServiceI.getAllEmployees();
        ObservableList<EmployeeView> data = FXCollections.observableList(employees);
        userTable.setItems(data);

    }

    private void createTable() {
        List<TableColumn> tableColumns = dbTableColumnsServiceI.getTableColumns(Const.EMPLOYEE_TABLE_REFERENCE);
        double tableWidth = 0;
        for (TableColumn column : tableColumns) {
            userTable.getColumns().add(column);
            tableWidth += column.getPrefWidth();
        }
        if (tableWidth > userAnchorPane.getPrefWidth()) {
            //TODO add a scroll bar
        } else {
            userTable.setPrefWidth(tableWidth);
        }
    }


    public void getSelectedRow(MouseEvent mouseEvent) {
        if (userTable.getSelectionModel().getSelectedItem() != null) {
            EmployeeView employeeView = userTable.getSelectionModel().getSelectedItem();
            setSelectedEmployee(employeeView.getM01Id());
        }
    }

    public void addUser(ActionEvent event) {
        openAddUpdateView(DialogMode.ADD, event);
    }

    public void updateEmployee(ActionEvent event) {
        openAddUpdateView(DialogMode.UPDATE,event);
    }

    public void openAddUpdateView(DialogMode mode, ActionEvent event) {

        if (mode == DialogMode.UPDATE && this.getSelectedEmployee() == null) {
            popup("Unable to Update","Please Select an Employee");
            return;
        }

        try {
            Stage currentStage = new Stage(); //use getCurrentStage() if you want to display the scene in currently opened stage instead of creating a new window
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/addUpdateUsers.fxml"));
            Pane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            AddUpdateUsersController controller = fxmlLoader.getController();
            controller.initData(mode, this.getSelectedEmployee());
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadExit(ActionEvent event) {
        try {
            hide(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
