module com.example.tetrislike {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.tetrislike to javafx.fxml;
    exports com.example.tetrislike.logic;
    exports com.example.tetrislike.usercontroller;
    exports com.example.tetrislike;
}