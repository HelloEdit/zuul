package zuul.pkg_command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class CommandWords {
    /**
     * A mapping between a command word and the CommandWord associated to it.
     */
    public static final Map<String, Command> VALID_COMMANDS;

    /**
     * An array of strings containing the available commands.
     */
    public static final String COMMANDS_STRING;

    static {
        // this map will contain all the commands without exception
        Map<String, Command> vMap = new HashMap<>();

        // this object will join all available command that can be directly executed by the user
        StringJoiner joiner = new StringJoiner(", ");

        for (CommandWord command : CommandWord.values()) {
            if (command.isHidden()) continue;
            vMap.put(command.toString(), command.getCommand());

            // exclude the command that cannot be executed in normal mode
            if (!command.isExecutable(false)) continue;

            joiner.add(command.toString());
        }

        COMMANDS_STRING = joiner.toString();
        VALID_COMMANDS = Collections.unmodifiableMap(vMap);
    }

    /**
     * Finds the command object associated with a command name.
     *
     * @param pCommandWord The command name to look up.
     * @return The command object associated with the comme name or the unknown command
     * if the name is not found in the mapping.
     */
    public static Command getCommand(final String pCommandWord, final boolean pTestMode) {
        Command vCommand = VALID_COMMANDS.getOrDefault(
                pCommandWord,
                CommandWord.UNKNOWN.getCommand()
        );

        return vCommand.isExecutable(pTestMode)
                ? vCommand
                : CommandWord.UNKNOWN.getCommand();
    }
}