package by.training.news.domain;

public class Request {
    private String commandName;
    private String params;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (commandName != null ? !commandName.equals(request.commandName) : request.commandName != null) return false;
        return params != null ? params.equals(request.params) : request.params == null;

    }

    @Override
    public int hashCode() {
        int result = commandName != null ? commandName.hashCode() : 0;
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
