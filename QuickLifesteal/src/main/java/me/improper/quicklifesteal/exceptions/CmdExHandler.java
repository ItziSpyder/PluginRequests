package me.improper.quicklifesteal.exceptions;

import me.improper.quicklifesteal.QuickLifesteal;
import org.bukkit.command.Command;

/**
 * Handles exceptions that maybe have been thrown while executing commands.
 */
public class CmdExHandler {

    private Exception exception;
    private Command command;

    /**
     * Handles an exception if one is thrown while running a bukkit command.
     * @param exception exception to catch and handle
     * @param command command that caused the error
     */
    public CmdExHandler(Exception exception, Command command) {
        this.exception = exception;
        this.command = command;
    }

    /**
     * Returns the full error message of what went wrong while attempting to run a command.
     * @return the error message
     */
    public String getErrorMsg() {
        String msg = QuickLifesteal.starter + "§cCommand error: ";
        if (exception instanceof NullPointerException) msg += "Command contains a null value!";
        else if (exception instanceof IndexOutOfBoundsException) msg += "Incomplete command! Please provide more information!";
        else msg += exception.getMessage();
        return msg += "\nCorrect usage: §7" + command.getUsage();
    }

    public Command getCommand() {
        return command;
    }

    public Exception getException() {
        return exception;
    }
}
