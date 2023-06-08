module com.example.tetrislike {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.tetrislike to javafx.fxml;
    exports com.example.tetrislike;
    exports com.example.tetrislike.gui;
    opens com.example.tetrislike.gui to javafx.fxml;
}