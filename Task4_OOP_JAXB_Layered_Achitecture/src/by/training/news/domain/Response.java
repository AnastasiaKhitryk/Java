package by.training.news.domain;

public class Response {
    private String commandName;
    private boolean status;
    private String message;
    private News news;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String answer) {
        this.message = answer;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (status != response.status) return false;
        if (commandName != null ? !commandName.equals(response.commandName) : response.commandName != null)
            return false;
        if (message != null ? !message.equals(response.message) : response.message != null) return false;
        return news != null ? news.equals(response.news) : response.news == null;

    }

    @Override
    public int hashCode() {
        int result = commandName != null ? commandName.hashCode() : 0;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "commandName='" + commandName + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", news=" + news +
                '}';
    }
}
