package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code TaskPriority} matches any of the keywords given.
 */
public class TaskPriorityContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskPriorityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getPriority().name(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskPriorityContainsKeywordsPredicate)) {
            return false;
        }

        TaskPriorityContainsKeywordsPredicate otherContainsKeywordsPredicate =
                (TaskPriorityContainsKeywordsPredicate) other;
        return keywords.equals(otherContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("priority keywords", keywords).toString();
    }
}
