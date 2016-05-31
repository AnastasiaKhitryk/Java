package by.training.news.controller;

import by.training.news.command.ICommand;
import by.training.news.command.impl.FindNews;
import by.training.news.command.impl.SaveNewNews;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private Map<CommandName, ICommand> commands = new HashMap<>();

    public CommandHelper(){
        commands.put(CommandName.SAVE_NEW_NEWS, new SaveNewNews());
        commands.put(CommandName.FIND_NEWS, new FindNews());
    }

    public ICommand getCommand(String name){
        CommandName commadName = CommandName.valueOf(name);
        ICommand command = commands.get(commadName);

        return command;
    }
}
