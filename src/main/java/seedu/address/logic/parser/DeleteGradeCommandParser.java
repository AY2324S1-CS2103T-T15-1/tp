package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteGradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.assignment.AssignmentName;

/**
 * Parses input arguments and creates a new DeleteGradeCommand object
 */
public class DeleteGradeCommandParser implements Parser<DeleteGradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGradeCommand
     * and returns an DeleteGradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGradeCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteGradeCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ASSIGNMENT);
        AssignmentName assignmentName = ParserUtil.parseAssignmentName(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        return new DeleteGradeCommand(index, assignmentName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
