package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.session.Session;

import java.util.Comparator;

/**
 * Represents a UI component that displays information about a session in the user interface.
 */
public class SessionCard extends UiPart<Region> {
    private static final String FXML = "SessionListCard.fxml";

    /** The session associated with this card. */
    public final Session session;

    @FXML
    private HBox cardPane;
    @FXML
    private Label sessionNumberLabel;
    @FXML
    private FlowPane students;

    /**
     * Creates a `SessionCard` object to display information about a session.
     *
     * @param session The session to display information about.
     */
    public SessionCard(Session session) {
        super(FXML);
        this.session = session;
        sessionNumberLabel.setText(session.getSessionNumber() + ". ");
        session.getStudents().stream()
                .sorted(Comparator.comparing(student -> student.getName().fullName))
                .forEach(student -> students.getChildren().add(new Label(student.getName().fullName)));
    }
}
