package com.github.marschall.punch.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TaskPath {
  
  private final List<Integer> elements;

  private TaskPath(List<Integer> elements) {
    this.elements = elements;
  }
  
  public TaskPath add(int i) {
    if (this.elements.isEmpty()) {
      return new TaskPath(Collections.singletonList(i));
    } else {
      List<Integer> newElements = new ArrayList<>(this.elements.size() + 1);
      newElements.addAll(this.elements);
      newElements.add(i);
      return new TaskPath(newElements);
    }
  }
  
  public static TaskPath root() {
    return new TaskPath(Collections.<Integer>emptyList());
  }
  
  @Override
  public int hashCode() {
    return this.elements.hashCode();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof TaskPath)) {
      return false;
    }
    TaskPath other = (TaskPath) obj;
    return this.elements.equals(other.elements);
  }
  
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    boolean first = true;
    for (Integer element : this.elements) {
      if (!first) {
        stringBuilder.append('/');
      }
      stringBuilder.append(element);
      first = false;
    }
    return stringBuilder.toString();
  }

  public static TaskPath fromString(String string) {
    String[] parts = string.split("/");
    if (parts.length == 0) {
      return TaskPath.root();
    } else if (parts.length == 1) {
      String part = parts[0];
      if ("".equals(part)) {
        return TaskPath.root();
      }
      return new TaskPath(Collections.singletonList(safeParse(string, part)));
    } else {
      List<Integer> elements = new ArrayList<>(parts.length);
      for (String part : parts) {
        elements.add(safeParse(string, part));
      }
      return new TaskPath(elements);
    }
  }
  
  private static int safeParse(String originalInput, String element) {
    try {
      return Integer.parseInt(element);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(originalInput + " isn't valid because it contains " + element + " which can't parsed as an int ");
    }
  }
  
}
