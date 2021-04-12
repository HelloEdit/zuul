package zuul.pkg_command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure pkg_game.
 * <p>
 * This class holds an enumeration table of all pkg_command words known to the pkg_game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Corentin POUPRY
 * @version 06.04.21
 */
public class CommandWords {
    /**
     * A mapping between a pkg_command word and the CommandWord associated to it.
     */
    public static final Map<String, Command> VALID_COMMANDS;

    static {
        Map<String, Command> vMap = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command.isHidden()) continue;
            vMap.put(command.toString(), command.getCommand());
        }

        VALID_COMMANDS = Collections.unmodifiableMap(vMap);
    }

    /**
     * Finds the pkg_command object associated with a pkg_command name.
     *
     * @param pCommandWord The pkg_command name to look up.
     * @return The pkg_command object associated with the comme name or the unknown pkg_command
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
