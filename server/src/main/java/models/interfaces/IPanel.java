package models.interfaces;

import models.exceptions.PushException;

import java.util.ArrayList;

public interface IPanel {

  void checkIfValid(int x, int y, int playerIndex) throws PushException;
  void push(int x, int y, int playerIndex) throws PushException;
  void setSize();
  int getSize();
  int[][] getPositions();

  ArrayList<String> getLogs();

  IPanel copy();
  IPanel getNewInstance();
}
