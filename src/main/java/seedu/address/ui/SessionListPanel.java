package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.Session;
import seedu.address.model.task.Task;

import java.util.logging.Logger;

public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @javafx.fxml.FXML
    private ListView<Session> sessionListView;

    /**
     * Creates a {@code SessionListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SessionCard(session).getRoot());
            }
        }
    }

}
