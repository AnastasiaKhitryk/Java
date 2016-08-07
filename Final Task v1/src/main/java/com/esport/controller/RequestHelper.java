package main.java.com.esport.controller;

import java.util.HashMap;
import java.util.Map;

import main.java.com.esport.command.Command;
import main.java.com.esport.command.impl.HelloCommand;
import main.java.com.esport.command.impl.LoginCommand;
import main.java.com.esport.command.impl.LogoutCommand;
import main.java.com.esport.command.impl.RegistrationCommand;
import main.java.com.esport.command.impl.WelcomeCommand;
import main.java.com.esport.controller.exception.CommandNotFoundException;

// ??????? Should i do private static final exception message ??????????? 

public class RequestHelper {

    private static final CommandName DEFAULT_COMMAND_NAME = CommandName.WELCOME;
	
    private static final RequestHelper instance = new RequestHelper();

	private Map<CommandName, Command> commands = new HashMap<>();

	private RequestHelper() {
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.WELCOME, new WelcomeCommand());
		commands.put(CommandName.HELLO, new HelloCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
	}

	public Command getCommand(String name) throws CommandNotFoundException {
		if(name == null){
            return commands.get(DEFAULT_COMMAND_NAME);
        }
        name = name.replace('-', '_');
        try {
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            return commands.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CommandNotFoundException("Wrong or empty command name", e);
        }
	}

	public static RequestHelper getInstance() {
		return instance;
	}
}
