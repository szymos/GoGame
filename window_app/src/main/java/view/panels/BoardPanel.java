package view.panels;

import view.PlayerPanel;
import view.panels.panels_parts.BoardButton;
import view.panels.panels_parts.PanelState;
import view.panels.panels_parts.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements PanelState {
   private PlayerPanel panel;
   private BoardButton[][] board;

   public BoardPanel (PlayerPanel panel, int boardSize) {
      this.panel = panel;
      this.setLayout(new BorderLayout());
      this.add(getPassAndExit(), BorderLayout.SOUTH);
      board = new BoardButton[boardSize][boardSize];

      for (int i = 0; i < boardSize; i++) {
         for (int j = 0; j < boardSize; j++) {
            board[i][j] = new BoardButton(this.panel, i, j);
         }
      }

      Box box = new Box(BoxLayout.Y_AXIS);
      box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
      box.setPreferredSize(new Dimension(100, 100));
      box.add(Box.createVerticalGlue());

      JPanel boardPanel = new JPanel(new SpringLayout());
      for (int i = 0; i < boardSize; i++) {
         for (int j = 0; j < boardSize; j++) {
            boardPanel.add(board[i][j]);
         }
      }
      SpringUtilities.makeCompactGrid(boardPanel, boardSize, boardSize,3, 3, 10, 10);

      box.add(boardPanel);
      box.add(Box.createVerticalGlue());
      add(box, BorderLayout.CENTER);
   }

   private JPanel getPassAndExit() {
      final JPanel jPanel = new JPanel();
      jPanel.setLayout(new FlowLayout());

      JButton skip = new JButton("Skip");
      skip.setPreferredSize(new Dimension(100, 20));
      skip.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            panel.skipRound();
         }
      });

      JButton exit = new JButton("Exit");
      exit.setPreferredSize(new Dimension(100, 20));
      exit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            panel.endGame();
         }
      });

      jPanel.add(skip);
      jPanel.add(exit);
      return jPanel;
   }



   @Override
   public PanelState next(int value) {
      return new WaitingPanel(panel);
   }

   @Override
   public void refresh(int x, int y, int playerId) {
      board[y][x].changeMember(playerId);
   }

   @Override
   public JPanel getPanel() {
      return this;
   }
}
