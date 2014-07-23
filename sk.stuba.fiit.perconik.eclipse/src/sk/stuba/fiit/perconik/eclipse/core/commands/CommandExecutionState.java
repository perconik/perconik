package sk.stuba.fiit.perconik.eclipse.core.commands;

public enum CommandExecutionState {
  UNDEFINED,

  DISABLED,

  UNHANDLED,

  WAITING,

  EXECUTING,

  SUCCEEDED,

  FAILED;
}
