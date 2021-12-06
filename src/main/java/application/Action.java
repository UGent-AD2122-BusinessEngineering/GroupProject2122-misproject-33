package application;

import java.util.Objects;

public class Action {
    private String name;
    private String actionId;

    public Action(String name, String actionId) {
        this.name = name;
        this.actionId = actionId;
    }

    public Action() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(name, action.name) && Objects.equals(actionId, action.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, actionId);
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", actionId='" + actionId + '\'' +
                '}';
    }
}
