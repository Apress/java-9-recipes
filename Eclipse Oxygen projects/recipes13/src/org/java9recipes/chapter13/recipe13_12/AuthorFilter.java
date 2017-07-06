
package org.java9recipes.chapter13.recipe13_12;

import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

/**
 * Recipe 13-12:  Predicate filter for FilteredRowSet Example.
 * 
 * @author juneau
 */
public class AuthorFilter implements Predicate {

  private String[] authors;
  private String colName = null;
  private int colNumber = -1;

  public AuthorFilter(String[] authors, String colName) {
    this.authors = authors;
    this.colNumber = -1;
    this.colName = colName;
  }

  public AuthorFilter(String[] authors, int colNumber) {
    this.authors = authors;
    this.colNumber = colNumber;
    this.colName = null;
  }

  @Override
  public boolean evaluate(Object value, String colName) {

    if (colName.equalsIgnoreCase(this.colName)) {
        for (String author : this.authors) {
            if (author.equalsIgnoreCase((String)value)) {
                return true;
            }
        }
    }
    return false;
  }

  @Override
  public boolean evaluate(Object value, int colNumber) {

    if (colNumber == this.colNumber) {
        for (String author : this.authors) {
            if (author.equalsIgnoreCase((String)value)) {
                return true;
            }
        }
    }
    return false;
  }


  @Override
  public boolean evaluate(RowSet rs) {

    if (rs == null)
      return false;

    try {
      for (int i = 0; i < this.authors.length; i++) {

        String authorLast = null;

        if (this.colNumber > 0) {
          authorLast = (String)rs.getObject(this.colNumber);
        } else if (this.colName != null) {
          authorLast = (String)rs.getObject(this.colName);
        } else {
          return false;
        }

        if (authorLast.equalsIgnoreCase(authors[i])) {
          return true;
        }
      }
    } catch (SQLException e) {
      return false;
    }
    return false;
  }

}