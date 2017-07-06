package org.java9recipes.chapter10.recipe10_10;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * User: Freddy
 * Updated by Juneau
 * Recipe 10-10
 */
public class Recipe10_10 {
    private volatile boolean shouldRun = true;

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Recipe10_10 recipe = new Recipe10_10();
        recipe.start();
    }

    private void start() throws InvocationTargetException, InterruptedException {
        boolean[][] lifeBoard = new boolean[50][50];
        final LifeTableModel model = new LifeTableModel(lifeBoard);
        final JTable lifeTable = new JTable();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            lifeTable.setModel(model);
            lifeTable.setDefaultRenderer(Boolean.class, new LifeTableCellRenderer());
            frame.setContentPane(new JScrollPane(lifeTable));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowListener() {
                public void windowOpened(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
                
                public void windowClosing(WindowEvent e) {
                    shouldRun = false;
                }
                
                public void windowClosed(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
                
                public void windowIconified(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
                
                public void windowDeiconified(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
                
                public void windowActivated(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
                
                public void windowDeactivated(WindowEvent e) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });
            frame.setSize(1000,1000);
            frame.setVisible(true);
        });

        // first things first.
        populateRandomBoard(lifeBoard);
        model.fireTableDataChanged();

        ForkJoinPool pool = new ForkJoinPool();
        long i = 0;

        while (shouldRun) {
            i++;
            final boolean[][] newBoard = new boolean[lifeBoard.length][lifeBoard[0].length];
            long startTime = System.nanoTime();
            GameOfLifeAdvancer advancer = new GameOfLifeAdvancer(lifeBoard, 0,0, lifeBoard.length-1, lifeBoard[0].length-1,newBoard);
            pool.invoke(advancer);
            long endTime = System.nanoTime();
            if (i % 100 == 0 ) {
                System.out.println("Taking "+(endTime-startTime)/1000 + "ms");
            }
            SwingUtilities.invokeAndWait(() -> {
                model.setBoard(newBoard);
                lifeTable.repaint();
            });
            lifeBoard = newBoard;
        }
    }



    Random random = new Random();

    private void populateRandomBoard(boolean[][] lifeBoard) {
        for (boolean[] lifeBoard1 : lifeBoard) {
            for (int col = 0; col < lifeBoard[0].length; col++) {
                lifeBoard1[col] = random.nextBoolean();
            }
        }
    }

    class LifeTableCellRenderer extends JLabel implements TableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 7891245885353645394L;

		public LifeTableCellRenderer() {
            this.setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if ((Boolean) value) {
                this.setBackground(Color.black);
            } else {
                this.setBackground(Color.white);
            }
            return this;
        }
    }

    class LifeTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 6150566114138473563L;
		private boolean[][] lifeBoard;

        public LifeTableModel(boolean[][] lifeBoard) {
            this.lifeBoard = lifeBoard;
        }

        @Override
        public String getColumnName(int column) {
            return null;
        }

        @Override
        public int getRowCount() {
            return lifeBoard.length;
        }

        @Override
        public int getColumnCount() {
            return lifeBoard[0].length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return lifeBoard[rowIndex][columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Boolean.class;
        }

        public void setBoard(boolean[][] newBoard) {
            this.lifeBoard = newBoard;
        }
    }

    class GameOfLifeAdvancer extends RecursiveAction{

        /**
		 * 
		 */
		private static final long serialVersionUID = -915335121939081801L;
		private boolean[][] originalBoard;
        private boolean[][] destinationBoard;
        private int startRow;
        private int endRow;
        private int endCol;
        private int startCol;

        GameOfLifeAdvancer(boolean[][] originalBoard, int startRow, int startCol, int endRow, int endCol, boolean [][] destinationBoard) {
            this.originalBoard = originalBoard;
            this.destinationBoard = destinationBoard;
            this.startRow = startRow;
            this.endRow = endRow;
            this.endCol = endCol;
            this.startCol = startCol;
        }

        private void computeDirectly() {
            for (int row = startRow; row <= endRow;row++) {
                for (int col = startCol; col <= endCol; col++) {
                    int numberOfNeighbors = getNumberOfNeighbors (row, col);
                    if (originalBoard[row][col]) {
                        destinationBoard[row][col] = true;
                        if (numberOfNeighbors < 2) destinationBoard[row][col] = false;
                        if (numberOfNeighbors > 3) destinationBoard[row][col] = false;
                    } else {
                        destinationBoard[row][col] = false;
                        if (numberOfNeighbors == 3) destinationBoard[row][col] = true;
                    }
                }
            }
        }

        private int getNumberOfNeighbors(int row, int col) {
            int neighborCount = 0;
            for (int leftIndex = -1; leftIndex < 2; leftIndex++) {
                for (int topIndex = -1; topIndex < 2; topIndex++) {
                    if ((leftIndex == 0) && (topIndex == 0)) continue; // skip own
                    int neighbourRowIndex = row + leftIndex;
                    int neighbourColIndex = col + topIndex;
                    if (neighbourRowIndex<0) neighbourRowIndex = originalBoard.length + neighbourRowIndex;
                    if (neighbourColIndex<0) neighbourColIndex = originalBoard[0].length + neighbourColIndex ;
                    boolean neighbour = originalBoard[neighbourRowIndex % originalBoard.length][neighbourColIndex % originalBoard[0].length];
                    if (neighbour) neighborCount++;
                }
            }
            return neighborCount;
        }

        @Override
        protected void compute() {
            if (getArea() < 20) {
                computeDirectly();
                return;
            }
            int halfRows = (endRow - startRow) / 2;
            int halfCols = (endCol - startCol) / 2;
            if (halfRows > halfCols) {
                // split the rows
                invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, startRow+halfRows, endCol,destinationBoard),
                          new GameOfLifeAdvancer(originalBoard, startRow+halfRows+1, startCol, endRow, endCol,destinationBoard));
            } else {
                // split the columns
                invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, endRow, startCol+ halfCols,destinationBoard),
                          new GameOfLifeAdvancer(originalBoard, startRow, startCol+halfCols+1, endRow, endCol,destinationBoard));
            }

        }


        private int getArea() {
            return (endRow - startRow) * (endCol - startCol);
        }

    }
}
